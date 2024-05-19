package cn.iocoder.ssy.module.erp.dal.mysql.purchase;

import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.ssy.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.ssy.module.erp.controller.admin.purchase.vo.supplier.ErpSupplierPageReqVO;
import cn.iocoder.ssy.module.erp.dal.dataobject.purchase.ErpSupplierDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ERP 供应商 Mapper
 *
 * @author  Ssy
 */
@Mapper
public interface ErpSupplierMapper extends BaseMapperX<ErpSupplierDO> {

    default PageResult<ErpSupplierDO> selectPage(ErpSupplierPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ErpSupplierDO>()
                .likeIfPresent(ErpSupplierDO::getName, reqVO.getName())
                .likeIfPresent(ErpSupplierDO::getMobile, reqVO.getMobile())
                .likeIfPresent(ErpSupplierDO::getTelephone, reqVO.getTelephone())
                .orderByDesc(ErpSupplierDO::getId));
    }

    default List<ErpSupplierDO> selectListByStatus(Integer status) {
        return selectList(ErpSupplierDO::getStatus, status);
    }

}