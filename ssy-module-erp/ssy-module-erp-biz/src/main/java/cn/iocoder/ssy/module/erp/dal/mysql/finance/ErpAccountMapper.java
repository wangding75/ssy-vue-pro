package cn.iocoder.ssy.module.erp.dal.mysql.finance;

import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.ssy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.ssy.module.erp.controller.admin.finance.vo.account.ErpAccountPageReqVO;
import cn.iocoder.ssy.module.erp.dal.dataobject.finance.ErpAccountDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ERP 结算账户 Mapper
 *
 * @author  Ssy
 */
@Mapper
public interface ErpAccountMapper extends BaseMapperX<ErpAccountDO> {

    default PageResult<ErpAccountDO> selectPage(ErpAccountPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ErpAccountDO>()
                .likeIfPresent(ErpAccountDO::getName, reqVO.getName())
                .likeIfPresent(ErpAccountDO::getNo, reqVO.getNo())
                .eqIfPresent(ErpAccountDO::getRemark, reqVO.getRemark())
                .orderByDesc(ErpAccountDO::getId));
    }

    default ErpAccountDO selectByDefaultStatus() {
        return selectOne(ErpAccountDO::getDefaultStatus, true);
    }

    default List<ErpAccountDO> selectListByStatus(Integer status) {
        return selectList(ErpAccountDO::getStatus, status);
    }

}