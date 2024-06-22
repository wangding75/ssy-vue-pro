package cn.ssy.module.finance.spider.ths;

import cn.hutool.core.collection.CollectionUtil;
import cn.ssy.module.biz.enums.AssetType;
import cn.ssy.module.biz.enums.DataSource;
import cn.ssy.module.biz.enums.MarketType;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import cn.ssy.module.finance.spider.AbstractCollector;
import cn.ssy.module.finance.spider.timeWaitingStrategy.TimeWaitingStrategy;
import cn.ssy.module.finance.utils.RequestParaBuilder;
import cn.ssy.module.finance.utils.URLMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.net.URL;
import java.util.List;
import java.util.Set;

public class THSFundListCollector extends AbstractCollector<List<FinanceAsset>> {


    private final MarketType marketType;
    private final int page;
    private final int pageSize;

    public THSFundListCollector(String cookies, MarketType marketType, int page, int pageSize) {
        this(null, cookies, marketType, page, pageSize);
    }

    public THSFundListCollector(TimeWaitingStrategy strategy, String cookies, MarketType marketType, int page, int pageSize) {
        super(strategy, cookies);
        this.marketType = marketType;
        this.page = page;
        this.pageSize = pageSize;
    }


    @Override
    public List<FinanceAsset> collectLogic() throws Exception {
        String target = URLMapper.THS_FUND_LIST.getURL();
        String targetUrl = target.replace("${page}", String.valueOf(page))
                .replace("${pageSize}", String.valueOf(pageSize));
        RequestParaBuilder builder = new RequestParaBuilder(targetUrl);
        URL url = new URL(builder.build());
        String resp = requestWithoutGzip(url);
        return processNode(resp);
    }

    private List<FinanceAsset> processNode(String resp) {

        String respJson = resp.replace("g({", "{").replace("}})", "}}");

        List<FinanceAsset> results = Lists.newArrayList();
        JSONObject jsonResp = JSONObject.parseObject(respJson);
        JSONObject jsonData = jsonResp.getJSONObject("data");
        JSONObject jsonFundData = jsonData.getJSONObject("data");
        Set<String> fundKeyList = jsonFundData.keySet();
        System.out.println(fundKeyList.size());
        if (CollectionUtil.isEmpty(fundKeyList)) return Lists.newArrayList();
        fundKeyList.forEach(fundKey -> {
            JSONObject fundResp = jsonFundData.getJSONObject(fundKey);
            FinanceAsset financeAsset = FinanceAsset.builder()
                    .symbol(fundResp.getString("code"))
                    .name(fundResp.getString("name"))
                    .financeType(AssetType.FUND.name())
                    .marketType(marketType.getMarket())
                    .source(DataSource.THS.name())
                    .build();
            results.add(financeAsset);
        });
        return results;
    }
}
