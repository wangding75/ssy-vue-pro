package cn.iocoder.ssy.module.trade.convert.brokerage;

import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.framework.dict.core.DictFrameworkUtils;
import cn.iocoder.ssy.module.member.api.user.dto.MemberUserRespDTO;
import cn.iocoder.ssy.module.trade.controller.admin.brokerage.vo.withdraw.BrokerageWithdrawPageReqVO;
import cn.iocoder.ssy.module.trade.controller.admin.brokerage.vo.withdraw.BrokerageWithdrawRespVO;
import cn.iocoder.ssy.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawCreateReqVO;
import cn.iocoder.ssy.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawPageReqVO;
import cn.iocoder.ssy.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawRespVO;
import cn.iocoder.ssy.module.trade.dal.dataobject.brokerage.BrokerageWithdrawDO;
import cn.iocoder.ssy.module.trade.enums.DictTypeConstants;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 佣金提现 Convert
 *
 * @author  Ssy
 */
@Mapper
public interface BrokerageWithdrawConvert {

    BrokerageWithdrawConvert INSTANCE = Mappers.getMapper(BrokerageWithdrawConvert.class);

    BrokerageWithdrawDO convert(AppBrokerageWithdrawCreateReqVO createReqVO, Long userId, Integer feePrice);

    BrokerageWithdrawRespVO convert(BrokerageWithdrawDO bean);

    List<BrokerageWithdrawRespVO> convertList(List<BrokerageWithdrawDO> list);

    PageResult<BrokerageWithdrawRespVO> convertPage(PageResult<BrokerageWithdrawDO> page);

    default PageResult<BrokerageWithdrawRespVO> convertPage(PageResult<BrokerageWithdrawDO> pageResult, Map<Long, MemberUserRespDTO> userMap) {
        PageResult<BrokerageWithdrawRespVO> result = convertPage(pageResult);
        for (BrokerageWithdrawRespVO vo : result.getList()) {
            vo.setUserNickname(Optional.ofNullable(userMap.get(vo.getUserId())).map(MemberUserRespDTO::getNickname).orElse(null));
        }
        return result;
    }

    PageResult<AppBrokerageWithdrawRespVO> convertPage02(PageResult<BrokerageWithdrawDO> pageResult);

    default PageResult<AppBrokerageWithdrawRespVO> convertPage03(PageResult<BrokerageWithdrawDO> pageResult) {
        PageResult<AppBrokerageWithdrawRespVO> result = convertPage02(pageResult);
        for (AppBrokerageWithdrawRespVO vo : result.getList()) {
            vo.setStatusName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.BROKERAGE_WITHDRAW_STATUS, vo.getStatus()));
        }
        return result;
    }

    BrokerageWithdrawPageReqVO convert(AppBrokerageWithdrawPageReqVO pageReqVO, Long userId);
}
