package cn.iocoder.ssy.module.trade.job.order;

import cn.iocoder.ssy.framework.quartz.core.handler.JobHandler;
import cn.iocoder.ssy.framework.tenant.core.job.TenantJob;
import cn.iocoder.ssy.module.trade.service.order.TradeOrderUpdateService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 交易订单的自动过期 Job
 *
 * @author  Ssy
 */
@Component
public class TradeOrderAutoCancelJob implements JobHandler {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;

    @Override
    @TenantJob
    public String execute(String param) {
        int count = tradeOrderUpdateService.cancelOrderBySystem();
        return String.format("过期订单 %s 个", count);
    }

}
