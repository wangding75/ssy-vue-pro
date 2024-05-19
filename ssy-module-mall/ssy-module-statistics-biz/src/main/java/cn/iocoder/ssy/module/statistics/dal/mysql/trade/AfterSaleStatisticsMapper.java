package cn.iocoder.ssy.module.statistics.dal.mysql.trade;

import cn.iocoder.ssy.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.ssy.module.statistics.dal.dataobject.trade.TradeStatisticsDO;
import cn.iocoder.ssy.module.statistics.service.trade.bo.AfterSaleSummaryRespBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 售后订单的统计 Mapper
 *
 * @author owen
 */
@Mapper
public interface AfterSaleStatisticsMapper extends BaseMapperX<TradeStatisticsDO> {

    AfterSaleSummaryRespBO selectSummaryByRefundTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                            @Param("endTime") LocalDateTime endTime);

    Long selectCountByStatus(@Param("status") Integer status);

}
