package cn.ssy.module.finance.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import cn.iocoder.ssy.framework.common.util.collection.CollectionUtils;
import cn.ssy.module.biz.enums.AssetType;
import cn.ssy.module.biz.enums.MarketType;
import cn.ssy.module.finance.config.FinanceConstants;
import cn.ssy.module.finance.config.XQCacheConfig;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import cn.ssy.module.finance.dal.dataobject.FinanceFundPrice;
import cn.ssy.module.finance.dal.dataobject.FinanceStockKLineDay;
import cn.ssy.module.finance.dal.mysql.mapper.FinanceAssetMapper;
import cn.ssy.module.finance.dal.mysql.mapper.FinanceFundPriceMapper;
import cn.ssy.module.finance.dal.mysql.mapper.FinanceStockKLineDayMapper;
import cn.ssy.module.finance.spider.ths.THSFundListCollector;
import cn.ssy.module.finance.spider.ths.THSFundPriceCollector;
import cn.ssy.module.finance.spider.tt.TTFundListCollector;
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
import java.util.concurrent.atomic.AtomicLong;
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

    @Autowired
    private FinanceFundPriceMapper financeFundPriceMapper;

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

//    ths基金只能爬取9999个
//    @Scheduled(cron = "*/10 * * * * ?")
//    @Transactional(rollbackFor = Exception.class)
//    public void fetchTHSFundInfoList() throws Exception {
//        log.info("同花顺基金信息: 爬取中...");
//        for (int i = 0; i < 9999; i++) {
//            int page = i + 1;
//            log.info("同花顺基金信息: 爬取中, 页码[{}]", page);
//            THSFundListCollector fundListCollector = new THSFundListCollector("", MarketType.SH_SZ, page, 200);
//            List<FinanceAsset> financeAssets = fundListCollector.collectLogic();
//            log.info("同花顺基金信息: 爬取中, 页码[{}], 抓图条数[{}]", page, financeAssets.size());
//            if (CollectionUtils.isAnyEmpty(financeAssets)) break;
//            financeAssetMapper.insertBatch(financeAssets);
//        }
//    }

    //    @Scheduled(cron = "*/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
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


    //    @Scheduled(cron = "*/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void fetchTHSFundInfoList() throws Exception {
        log.info("东方财富基金信息: 爬取中...");
        for (int i = 0; i < 9999; i++) {
            int page = i + 1;
            log.info("东方财富基金信息: 爬取中, 页码[{}]", page);
            TTFundListCollector fundListCollector = new TTFundListCollector(null, page, 200);
            List<FinanceAsset> pairs = fundListCollector.collectLogic();
            log.info("东方财富基金信息: 爬取中, 页码[{}], 抓图条数[{}]", page, pairs.size());
            financeAssetMapper.insertBatch(pairs);
            if (pairs.size() != 200) break;
        }
    }

    @Scheduled(cron = "*/10 * * * * ?")
//    @Transactional(rollbackFor = Exception.class)
    public void fetchTHSFundPriceList() throws Exception {
        log.info("同花顺基金信息: 爬取中...");
        AtomicLong count = new AtomicLong();
        List<FinanceAsset> financeAssets = financeAssetMapper.selectList();
        financeAssets.parallelStream().forEach(asset -> {
            THSFundPriceCollector thsFundPriceCollector = new THSFundPriceCollector(null, null, asset.getSymbol());
            try {
                log.info("同花顺基金信息 [{}]: 爬取中...", asset.getSymbol());
                List<FinanceFundPrice> financeFundPrices = thsFundPriceCollector.collectLogic();
                if (CollectionUtil.isNotEmpty(financeFundPrices)) {
                    financeFundPriceMapper.insertBatch(financeFundPrices);
                    count.addAndGet(financeFundPrices.size());
                }
                log.info("同花顺基金信息 [{}]: 爬取结束, 条数: {}", asset.getSymbol(), financeFundPrices.size());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        long l = count.get();
        log.info("共爬取: {} 条记录", l);
        System.exit(1);
    }
}
