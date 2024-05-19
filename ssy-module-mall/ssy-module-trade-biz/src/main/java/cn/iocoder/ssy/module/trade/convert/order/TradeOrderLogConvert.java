package cn.iocoder.ssy.module.trade.convert.order;

import cn.iocoder.ssy.module.trade.dal.dataobject.order.TradeOrderLogDO;
import cn.iocoder.ssy.module.trade.service.order.bo.TradeOrderLogCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeOrderLogConvert {

    TradeOrderLogConvert INSTANCE = Mappers.getMapper(TradeOrderLogConvert.class);

    TradeOrderLogDO convert(TradeOrderLogCreateReqBO bean);

}
