package cn.ssy.module.finance.job;

import cn.iocoder.ssy.framework.common.util.collection.CollectionUtils;
import cn.iocoder.ssy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.ssy.module.biz.enums.DataSource;
import cn.ssy.module.biz.enums.MarketType;
import cn.ssy.module.finance.config.FinanceConstants;
import cn.ssy.module.finance.config.XQCacheConfig;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import cn.ssy.module.finance.dal.mysql.mapper.FinanceAssetMapper;
import cn.ssy.module.finance.dal.mysql.service.FinanceAssetService;
import cn.ssy.module.finance.spider.xq.StockListCollector;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ScheduleSpider {

    @Autowired
    private XQCacheConfig cache;

    @Autowired
    private FinanceAssetService financeAssetService;

    @Autowired
    private FinanceAssetMapper financeAssetMapper;

    @Scheduled(cron = "*/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void fetchXQStockInfoList() throws Exception {
        MarketType[] values = MarketType.values();
        for (MarketType value : values) {
            log.info("雪球[{}]股票信息: 爬取中...", value);
            List<String> existSymbolList = financeAssetMapper.selectList(Wrappers.lambdaQuery(FinanceAsset.class)
                            .select(FinanceAsset::getSymbol)
                            .eq(FinanceAsset::getMarketType, value.getMarket())
                            .eq(FinanceAsset::getSource, DataSource.XQ.name())
                    ).stream()
                    .map(FinanceAsset::getSymbol)
                    .collect(Collectors.toList());
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
}
