package cn.iocoder.ssy.module.product.convert.brand;

import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.module.product.controller.admin.brand.vo.ProductBrandCreateReqVO;
import cn.iocoder.ssy.module.product.controller.admin.brand.vo.ProductBrandRespVO;
import cn.iocoder.ssy.module.product.controller.admin.brand.vo.ProductBrandSimpleRespVO;
import cn.iocoder.ssy.module.product.controller.admin.brand.vo.ProductBrandUpdateReqVO;
import cn.iocoder.ssy.module.product.dal.dataobject.brand.ProductBrandDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 品牌 Convert
 *
 * @author  Ssy
 */
@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    ProductBrandDO convert(ProductBrandCreateReqVO bean);

    ProductBrandDO convert(ProductBrandUpdateReqVO bean);

    ProductBrandRespVO convert(ProductBrandDO bean);

    List<ProductBrandSimpleRespVO> convertList1(List<ProductBrandDO> list);

    List<ProductBrandRespVO> convertList(List<ProductBrandDO> list);

    PageResult<ProductBrandRespVO> convertPage(PageResult<ProductBrandDO> page);

}
