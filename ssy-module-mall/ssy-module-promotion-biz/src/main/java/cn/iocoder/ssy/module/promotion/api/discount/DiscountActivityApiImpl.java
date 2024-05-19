package cn.iocoder.ssy.module.promotion.api.discount;

import cn.iocoder.ssy.module.promotion.api.discount.dto.DiscountProductRespDTO;
import cn.iocoder.ssy.module.promotion.convert.discount.DiscountActivityConvert;
import cn.iocoder.ssy.module.promotion.service.discount.DiscountActivityService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 限时折扣 API 实现类
 *
 * @author  Ssy
 */
@Service
@Validated
public class DiscountActivityApiImpl implements DiscountActivityApi {

    @Resource
    private DiscountActivityService discountActivityService;

    @Override
    public List<DiscountProductRespDTO> getMatchDiscountProductList(Collection<Long> skuIds) {
        return DiscountActivityConvert.INSTANCE.convertList02(discountActivityService.getMatchDiscountProductList(skuIds));
    }

}
