package cn.ssy.module.finance.spider.xq;

import cn.ssy.module.biz.enums.AssetType;
import cn.ssy.module.biz.enums.DataSource;
import cn.ssy.module.biz.enums.MarketType;
import cn.ssy.module.finance.dal.dataobject.FinanceStockKLineDay;
import cn.ssy.module.finance.spider.AbstractCollector;
import cn.ssy.module.finance.spider.timeWaitingStrategy.TimeWaitingStrategy;
import cn.ssy.module.finance.utils.RequestParaBuilder;
import cn.ssy.module.finance.utils.URLMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * K线
 */
public class StockKLineCollector extends AbstractCollector<List<FinanceStockKLineDay>> {

    private final MarketType marketType;

    private final String symbol;

    private final Long begin;

    private final boolean isInit;

    public StockKLineCollector(String cookies, MarketType marketType, String symbol, Long begin, boolean isInit) {
        this(null, cookies, marketType, symbol, begin, isInit);
    }

    public StockKLineCollector(TimeWaitingStrategy strategy, String cookies, MarketType marketType, String symbol, Long begin, boolean isInit) {
        super(strategy, cookies);
        this.marketType = marketType;
        this.symbol = symbol;
        this.begin = begin;
        this.isInit = isInit;
    }

    @Override
    public List<FinanceStockKLineDay> collectLogic() throws Exception {
        String target = URLMapper.STOCK_KLINE.getURL();
        String count = isInit ? "-9999999": "-1";
        RequestParaBuilder builder = new RequestParaBuilder(target)
                .addParameter("symbol", symbol)
                .addParameter("begin", begin.toString())
                .addParameter("period", "day")
                .addParameter("type", "before")
                .addParameter("count", count);
        URL url = new URL(builder.build());
        String json = request(url);
        return processNode(json);
    }


    private List<FinanceStockKLineDay> processNode(String json) {

        List<FinanceStockKLineDay> results = Lists.newArrayList();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject jsonData = jsonObject.getJSONObject("data");
        JSONArray respKlineList = jsonData.getJSONArray("item");
        if (respKlineList == null || respKlineList.isEmpty()) return Lists.newArrayList();
        for (int i = 0; i < respKlineList.size(); i++) {
            JSONArray respKLine = respKlineList.getJSONArray(i);
            Long timestamp = respKLine.getLong(0);
            FinanceStockKLineDay financeAsset = FinanceStockKLineDay.builder()
                    .symbol(this.symbol)
                    .timestamp(timestamp)
                    .dateTime(new Date(timestamp))
                    .volume(respKLine.getLong(1))
                    .open(Double.valueOf(100.0 * respKLine.getDouble(2)).intValue())
                    .high(Double.valueOf(100.0 * respKLine.getLong(3)).intValue())
                    .low(Double.valueOf(100.0 * respKLine.getLong(4)).intValue())
                    .close(Double.valueOf(100.0 * respKLine.getLong(5)).intValue())
                    .chg(Double.valueOf(100.0 * respKLine.getLong(6)).intValue())
                    .percent(Double.valueOf(100.0 * respKLine.getLong(7)).intValue())
                    .turnoverrate(Double.valueOf(100.0 * respKLine.getLong(8)).intValue())
                    .amount(respKLine.getLong(9))
                    .financeType(AssetType.STOCK.name())
                    .marketType(marketType.getMarket())
                    .source(DataSource.XQ.name())
                    .build();
            results.add(financeAsset);
        }
        return results;
    }
}
