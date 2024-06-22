package cn.ssy.module.finance.spider.tt;

import cn.hutool.core.lang.Pair;
import cn.ssy.module.biz.enums.AssetType;
import cn.ssy.module.biz.enums.DataSource;
import cn.ssy.module.biz.enums.MarketType;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import cn.ssy.module.finance.dal.dataobject.FinanceFundPrice;
import cn.ssy.module.finance.spider.AbstractCollector;
import cn.ssy.module.finance.spider.timeWaitingStrategy.TimeWaitingStrategy;
import cn.ssy.module.finance.utils.RequestParaBuilder;
import cn.ssy.module.finance.utils.SsyNumberUtils;
import cn.ssy.module.finance.utils.URLMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.List;


/**
 * 股票清单
 */
@Slf4j
public class TTFundListCollector extends AbstractCollector<List<FinanceAsset>> {


    private final int page;

    private final int pageSize;

    public TTFundListCollector(String cookies, MarketType marketType) {
        this(null, 1, Integer.MAX_VALUE);
    }

    public TTFundListCollector(int page, int pageSize) {
        this(null, page, pageSize);
    }

    public TTFundListCollector(TimeWaitingStrategy strategy, int page, int pageSize) {
        super(strategy, "");
        this.page = page;
        this.pageSize = pageSize;
    }

//    t=1&lx=1&letter=&gsid=&text=&sort=zdf,desc&page=1,200&dt=1716993876227&atfc=&onlySale=0
    @Override
    public List<FinanceAsset> collectLogic() throws Exception {
        String target = URLMapper.TT_FUND_LIST.getURL();
        RequestParaBuilder builder = new RequestParaBuilder(target)
                .addParameter("t", "1")
                .addParameter("lx", "1")
                .addParameter("letter", "")
                .addParameter("gsid", "")
                .addParameter("text", "")
                .addParameter("sort", "zdf,desc")
                .addParameter("page", page+ "," + pageSize)
                .addParameter("dt", "1716993876227")
                .addParameter("atfc", "")
                .addParameter("onlySale", "0");
        String targetUrl = builder.build();
        URL url = new URL(targetUrl);
        log.info("fetch URL: {}", targetUrl);
        String json = request(url);
        return processNode(json);
    }

    private List<FinanceAsset> processNode(String resp) {
        if (StringUtils.isBlank(resp)) return Lists.newArrayList();
        String respJson = resp.replace("var db=", "");
        JSONObject respJsonObject = JSONObject.parseObject(respJson);
        JSONArray datas = respJsonObject.getJSONArray("datas");
        String showday = respJsonObject.getJSONArray("showday").getString(0);
        List<FinanceAsset> results = Lists.newArrayList();
        for (int i = 0; i < datas.size(); i++) {
            JSONArray respInfo = datas.getJSONArray(i);
            String symbol = respInfo.getString(0);
            String name = respInfo.getString(1);
            FinanceAsset asset = FinanceAsset.builder()
                    .symbol(symbol)
                    .name(name)
                    .financeType(AssetType.FUND.name())
                    .marketType(MarketType.SH_SZ.getMarket())
                    .source(DataSource.TT.name())
                    .build();
//            String charge = respInfo.getString(17);
//
//            FinanceFundPrice fundPrice = FinanceFundPrice.builder()
//                    .symbol(symbol)
//                    .dateTime(showday)
//                    .lastPerPrice(SsyNumberUtils.doubleToInt(respInfo.getDouble(3), 10000))
//                    .lastPrice(SsyNumberUtils.doubleToInt(respInfo.getDouble(4), 10000))
//                    .prePerPrice(SsyNumberUtils.doubleToInt(respInfo.getDouble(5), 10000))
//                    .prePrice(SsyNumberUtils.doubleToInt(respInfo.getDouble(6), 10000))
//                    .chg(SsyNumberUtils.doubleToInt(respInfo.getDouble(7), 10000))
//                    .percent(Double.valueOf(respInfo.getDouble(8) * 100).intValue())
//                    .charge(Double.valueOf(Double.parseDouble(charge.replace("%", "")) * 100).intValue())
//                    .canBuy("开放申购".equals(respInfo.getString(9)) ? 1 : 0)
//                    .canSell("开放赎回".equals(respInfo.getString(10)) ? 1 : 0)
//                    .build();
            results.add(asset);
        }
        return results;
    }
}
