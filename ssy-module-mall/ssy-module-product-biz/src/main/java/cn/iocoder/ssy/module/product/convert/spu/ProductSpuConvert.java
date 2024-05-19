package cn.iocoder.ssy.module.product.convert.spu;

import cn.iocoder.ssy.framework.common.util.collection.CollectionUtils;
import cn.iocoder.ssy.framework.common.util.object.BeanUtils;
import cn.iocoder.ssy.module.product.controller.admin.spu.vo.ProductSkuRespVO;
import cn.iocoder.ssy.module.product.controller.admin.spu.vo.ProductSpuPageReqVO;
import cn.iocoder.ssy.module.product.controller.admin.spu.vo.ProductSpuRespVO;
import cn.iocoder.ssy.module.product.controller.app.spu.vo.AppProductSpuPageReqVO;
import cn.iocoder.ssy.module.product.dal.dataobject.sku.ProductSkuDO;
import cn.iocoder.ssy.module.product.dal.dataobject.spu.ProductSpuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static cn.iocoder.ssy.framework.common.util.collection.CollectionUtils.convertMultiMap;

/**
 * 商品 SPU Convert
 *
 * @author  Ssy
 */
@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    ProductSpuPageReqVO convert(AppProductSpuPageReqVO bean);

    default ProductSpuRespVO convert(ProductSpuDO spu, List<ProductSkuDO> skus) {
        ProductSpuRespVO spuVO = BeanUtils.toBean(spu, ProductSpuRespVO.class);
        spuVO.setSkus(BeanUtils.toBean(skus, ProductSkuRespVO.class));
        return spuVO;
    }

    default List<ProductSpuRespVO> convertForSpuDetailRespListVO(List<ProductSpuDO> spus, List<ProductSkuDO> skus) {
        Map<Long, List<ProductSkuDO>> skuMultiMap = convertMultiMap(skus, ProductSkuDO::getSpuId);
        return CollectionUtils.convertList(spus, spu -> convert(spu, skuMultiMap.get(spu.getId())));
    }

}
