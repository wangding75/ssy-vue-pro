package cn.iocoder.ssy.module.trade.job.order;

import cn.iocoder.ssy.framework.quartz.core.handler.JobHandler;
import cn.iocoder.ssy.framework.tenant.core.job.TenantJob;
import cn.iocoder.ssy.module.trade.service.order.TradeOrderUpdateService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 交易订单的自动收货 Job
 *
 * @author  Ssy
 */
@Component
public class TradeOrderAutoReceiveJob implements JobHandler {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;

    @Override
    @TenantJob
    public String execute(String param) {
        int count = tradeOrderUpdateService.receiveOrderBySystem();
        return String.format("自动收货 %s 个", count);
    }

}
