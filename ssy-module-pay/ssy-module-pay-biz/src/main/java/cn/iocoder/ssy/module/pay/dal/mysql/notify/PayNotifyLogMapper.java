package cn.iocoder.ssy.module.pay.dal.mysql.notify;

import cn.iocoder.ssy.module.pay.dal.dataobject.notify.PayNotifyLogDO;
import cn.iocoder.ssy.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayNotifyLogMapper extends BaseMapperX<PayNotifyLogDO> {

    default List<PayNotifyLogDO> selectListByTaskId(Long taskId) {
        return selectList(PayNotifyLogDO::getTaskId, taskId);
    }

}
