package cn.iocoder.ssy.module.promotion.convert.seckill.seckillconfig;

import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.module.promotion.controller.admin.seckill.vo.config.SeckillConfigCreateReqVO;
import cn.iocoder.ssy.module.promotion.controller.admin.seckill.vo.config.SeckillConfigRespVO;
import cn.iocoder.ssy.module.promotion.controller.admin.seckill.vo.config.SeckillConfigSimpleRespVO;
import cn.iocoder.ssy.module.promotion.controller.admin.seckill.vo.config.SeckillConfigUpdateReqVO;
import cn.iocoder.ssy.module.promotion.controller.app.seckill.vo.config.AppSeckillConfigRespVO;
import cn.iocoder.ssy.module.promotion.dal.dataobject.seckill.SeckillConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 秒杀时段 Convert
 *
 * @author  Ssy
 */
@Mapper
public interface SeckillConfigConvert {

    SeckillConfigConvert INSTANCE = Mappers.getMapper(SeckillConfigConvert.class);

    SeckillConfigDO convert(SeckillConfigCreateReqVO bean);

    SeckillConfigDO convert(SeckillConfigUpdateReqVO bean);

    SeckillConfigRespVO convert(SeckillConfigDO bean);

    List<SeckillConfigRespVO> convertList(List<SeckillConfigDO> list);

    List<SeckillConfigSimpleRespVO> convertList1(List<SeckillConfigDO> list);

    PageResult<SeckillConfigRespVO> convertPage(PageResult<SeckillConfigDO> page);

    List<AppSeckillConfigRespVO> convertList2(List<SeckillConfigDO> list);

    AppSeckillConfigRespVO convert1(SeckillConfigDO filteredConfig);
}
