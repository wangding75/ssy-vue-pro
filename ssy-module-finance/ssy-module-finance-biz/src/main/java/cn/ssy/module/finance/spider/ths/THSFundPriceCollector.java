package cn.ssy.module.finance.spider.ths;

import cn.hutool.core.collection.CollectionUtil;
import cn.ssy.module.biz.enums.AssetType;
import cn.ssy.module.biz.enums.DataSource;
import cn.ssy.module.biz.enums.MarketType;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import cn.ssy.module.finance.dal.dataobject.FinanceFundPrice;
import cn.ssy.module.finance.spider.AbstractCollector;
import cn.ssy.module.finance.spider.timeWaitingStrategy.TimeWaitingStrategy;
import cn.ssy.module.finance.utils.FreeMarkerUtils;
import cn.ssy.module.finance.utils.RequestParaBuilder;
import cn.ssy.module.finance.utils.SsyNumberUtils;
import cn.ssy.module.finance.utils.URLMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.net.URL;
import java.util.List;
import java.util.Set;

public class THSFundPriceCollector extends AbstractCollector<List<FinanceFundPrice>> {


    private final MarketType marketType;

    private final String symbol;

    public THSFundPriceCollector(String cookies, MarketType marketType, String symbol) {
        this(null, cookies, marketType, symbol);
    }

    public THSFundPriceCollector(TimeWaitingStrategy strategy, String cookies, MarketType marketType, String symbol) {
        super(strategy, cookies);
        this.marketType = marketType;
        this.symbol = symbol;
    }


    @Override
    public List<FinanceFundPrice> collectLogic() throws Exception {
        String target = URLMapper.THS_FUND_PRICE_LIST.getURL();
        String targetUrl = FreeMarkerUtils.load(target, ImmutableMap.of("symbol", symbol));
        RequestParaBuilder builder = new RequestParaBuilder(targetUrl);
        URL url = new URL(builder.build());
        String resp = requestWithoutGzip(url);
        return processNode(resp);
    }

    private List<FinanceFundPrice> processNode(String resp) {

        List<FinanceFundPrice> results = Lists.newArrayList();
        JSONObject jsonResp = JSONObject.parseObject(resp);
        JSONArray jsonData = jsonResp.getJSONArray("data");
        if (CollectionUtil.isEmpty(jsonData)) return Lists.newArrayList();

        for (int i = 0; i < jsonData.size(); i++) {
            JSONObject priceDetail = jsonData.getJSONObject(i);
            FinanceFundPrice fundPrice = FinanceFundPrice.builder()
                    .symbol(symbol)
                    .dateTime(priceDetail.getString("date"))
                    .perPrice(SsyNumberUtils.doubleToInt(priceDetail.getDouble("net"), 10000))
                    .fqPrice(SsyNumberUtils.doubleToInt(priceDetail.getDouble("fqnet"), 10000))
                    .price(SsyNumberUtils.doubleToInt(priceDetail.getDouble("totalnet"), 10000))
                    .percent(SsyNumberUtils.doubleToInt(priceDetail.getDouble("rate"), 100))
                    .chg(SsyNumberUtils.doubleToInt(priceDetail.getDouble("inc"), 10000))
                    .build();
            results.add(fundPrice);
        }
        return results;
    }
}
