package cn.iocoder.ssy.module.trade.api.order;

import cn.iocoder.ssy.module.trade.api.order.dto.TradeOrderRespDTO;
import cn.iocoder.ssy.module.trade.convert.order.TradeOrderConvert;
import cn.iocoder.ssy.module.trade.service.order.TradeOrderQueryService;
import cn.iocoder.ssy.module.trade.service.order.TradeOrderUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 订单 API 接口实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class TradeOrderApiImpl implements TradeOrderApi {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;
    @Resource
    private TradeOrderQueryService tradeOrderQueryService;

    @Override
    public List<TradeOrderRespDTO> getOrderList(Collection<Long> ids) {
        return TradeOrderConvert.INSTANCE.convertList04(tradeOrderQueryService.getOrderList(ids));
    }

    @Override
    public TradeOrderRespDTO getOrder(Long id) {
        return TradeOrderConvert.INSTANCE.convert(tradeOrderQueryService.getOrder(id));
    }

    @Override
    public void cancelPaidOrder(Long userId, Long orderId) {
        tradeOrderUpdateService.cancelPaidOrder(userId, orderId);
    }

}
