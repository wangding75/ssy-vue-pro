package cn.ssy.module.finance.job;

import cn.iocoder.ssy.framework.common.util.collection.CollectionUtils;
import cn.ssy.module.biz.enums.MarketType;
import cn.ssy.module.finance.config.FinanceConstants;
import cn.ssy.module.finance.config.XQCacheConfig;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import cn.ssy.module.finance.dal.dataobject.FinanceStockKLineDay;
import cn.ssy.module.finance.dal.mysql.mapper.FinanceAssetMapper;
import cn.ssy.module.finance.dal.mysql.mapper.FinanceStockKLineDayMapper;
import cn.ssy.module.finance.spider.xq.StockKLineCollector;
import cn.ssy.module.finance.spider.xq.StockListCollector;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ScheduleSpider {

    @Autowired
    private XQCacheConfig cache;

    @Autowired
    private FinanceAssetMapper financeAssetMapper;

    @Autowired
    private FinanceStockKLineDayMapper financeStockKLineDayMapper;

//    @Scheduled(cron = "*/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void fetchXQStockInfoList() throws Exception {
        MarketType[] values = MarketType.values();
        for (MarketType value : values) {
            log.info("雪球[{}]股票信息: 爬取中...", value);
            for (int i = 0; i < 9999; i++) {
                log.info("雪球[{}]股票信息: 爬取中, 页码[{}]", value, i);
                StockListCollector collector = new StockListCollector(cache.getString(FinanceConstants.XQ_COOKIE_KEY), value, i, 500);
                List<FinanceAsset> financeAssets = collector.collectLogic();
                log.info("雪球[{}]股票信息: 爬取中, 页码[{}], 抓图条数[{}]", value, i, financeAssets.size());
                if (CollectionUtils.isAnyEmpty(financeAssets)) break;
                financeAssetMapper.insertBatch(financeAssets);
            }
            log.info("雪球[{}]股票信息: 爬取结束...", value);
        }
    }

    @Scheduled(cron = "*/10 * * * * ?")
//    @Transactional(rollbackFor = Exception.class)
    public void fetchXQStockKLineList() {
        MarketType[] values = MarketType.values();
        List<FinanceAsset> stockList = financeAssetMapper.selectList().stream().distinct().collect(Collectors.toList());
        log.info("雪球[{}]只股票待爬取", stockList.size());
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_YEAR, 1);
        long begin = instance.getTime().getTime();
        log.info("雪球股票信息: 爬取中...");
        stockList.parallelStream()
                .forEach(stockInfo -> {
                    String marketType = stockInfo.getMarketType();
                    MarketType value = MarketType.getMarketType(marketType);
                    StockKLineCollector collector = new StockKLineCollector(cache.getString(FinanceConstants.XQ_COOKIE_KEY), value, stockInfo.getSymbol(), begin, false);
                    try {
                        log.info("雪球[{}]股票信息: {}: 爬取中", value, stockInfo.getName());
                        List<FinanceStockKLineDay> financeStockKLineDays = collector.collectLogic();
                        log.info("雪球[{}]股票信息: {}: 爬取结束，条数:{}", value, stockInfo.getName(), financeStockKLineDays.size());
                        financeStockKLineDayMapper.insertBatch(financeStockKLineDays);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        log.info("雪球股票信息: 爬取结束");

        try {
            Thread.sleep(10000000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
