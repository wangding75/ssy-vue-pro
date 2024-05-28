package cn.ssy.module.finance.spider.xq;

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


/**
 * 股票清单
 */
public class StockListCollector extends AbstractCollector<List<FinanceAsset>> {


    private final MarketType marketType;

    private final int page;

    private final int pageSize;

    public StockListCollector(String cookies, MarketType marketType) {
        this(null, cookies, marketType, 1, Integer.MAX_VALUE);
    }

    public StockListCollector(String cookies, MarketType marketType, int page, int pageSize) {
        this(null, cookies, marketType, page, pageSize);
    }


    /**
     * @param strategy   超时等待策略（null则设置为默认等待策略）
     * @param marketType marketType
     */
    public StockListCollector(TimeWaitingStrategy strategy, String cookies, MarketType marketType, int page, int pageSize) {
        super(strategy, cookies);
        this.marketType = marketType;
        this.page = page;
        this.pageSize = pageSize;
    }

    @Override
    public List<FinanceAsset> collectLogic() throws Exception {
        String target = URLMapper.STOCK_LIST.getURL();
        RequestParaBuilder builder = new RequestParaBuilder(target)
                .addParameter("page", page)
                .addParameter("size", pageSize)
                .addParameter("order", "desc")
                .addParameter("orderby", "percent")
                .addParameter("order_by", "percent")
                .addParameter("market", marketType.getMarket())
                .addParameter("type", marketType.getType());
        URL url = new URL(builder.build());
        String json = request(url);
        return processNode(json);
    }

    private List<FinanceAsset> processNode(String json) {
        List<FinanceAsset> results = Lists.newArrayList();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject jsonData = jsonObject.getJSONObject("data");
        JSONArray respAssetList = jsonData.getJSONArray("list");
        if (respAssetList == null || respAssetList.isEmpty()) return Lists.newArrayList();
        for (int i = 0; i < respAssetList.size(); i++) {
            JSONObject respAsset = respAssetList.getJSONObject(i);
            FinanceAsset financeAsset = FinanceAsset.builder()
                    .symbol(respAsset.getString("symbol"))
                    .name(respAsset.getString("name"))
                    .financeType(AssetType.STOCK.name())
                    .marketType(marketType.getMarket())
                    .source(DataSource.XQ.name())
                    .build();
            results.add(financeAsset);
        }
        return results;
    }
}
