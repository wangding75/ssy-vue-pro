package cn.iocoder.ssy.module.erp.service.statistics;

import cn.iocoder.ssy.module.erp.dal.mysql.statistics.ErpPurchaseStatisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ERP 采购统计 Service 实现类
 *
 * @author  Ssy
 */
@Service
public class ErpPurchaseStatisticsServiceImpl implements ErpPurchaseStatisticsService {

    @Resource
    private ErpPurchaseStatisticsMapper purchaseStatisticsMapper;

    @Override
    public BigDecimal getPurchasePrice(LocalDateTime beginTime, LocalDateTime endTime) {
        return purchaseStatisticsMapper.getPurchasePrice(beginTime, endTime);
    }

}
