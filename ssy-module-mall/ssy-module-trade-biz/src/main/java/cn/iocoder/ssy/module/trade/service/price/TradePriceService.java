package cn.iocoder.ssy.module.trade.service.price;

import cn.iocoder.ssy.module.trade.service.price.bo.TradePriceCalculateReqBO;
import cn.iocoder.ssy.module.trade.service.price.bo.TradePriceCalculateRespBO;

import javax.validation.Valid;

/**
 * 价格计算 Service 接口
 *
 * @author  Ssy
 */
public interface TradePriceService {

    /**
     * 价格计算
     *
     * @param calculateReqDTO 计算信息
     * @return 计算结果
     */
    TradePriceCalculateRespBO calculatePrice(@Valid TradePriceCalculateReqBO calculateReqDTO);

}
