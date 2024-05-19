package cn.iocoder.ssy.module.promotion.convert.reward;

import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.module.promotion.controller.admin.reward.vo.RewardActivityCreateReqVO;
import cn.iocoder.ssy.module.promotion.controller.admin.reward.vo.RewardActivityRespVO;
import cn.iocoder.ssy.module.promotion.controller.admin.reward.vo.RewardActivityUpdateReqVO;
import cn.iocoder.ssy.module.promotion.dal.dataobject.reward.RewardActivityDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 满减送活动 Convert
 *
 * @author  Ssy
 */
@Mapper
public interface RewardActivityConvert {

    RewardActivityConvert INSTANCE = Mappers.getMapper(RewardActivityConvert.class);

    RewardActivityDO convert(RewardActivityCreateReqVO bean);

    RewardActivityDO convert(RewardActivityUpdateReqVO bean);

    RewardActivityRespVO convert(RewardActivityDO bean);

    PageResult<RewardActivityRespVO> convertPage(PageResult<RewardActivityDO> page);

}
