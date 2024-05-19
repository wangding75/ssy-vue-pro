package cn.iocoder.ssy.module.promotion.controller.admin.bargain;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.ssy.framework.common.pojo.CommonResult;
import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.module.member.api.user.MemberUserApi;
import cn.iocoder.ssy.module.member.api.user.dto.MemberUserRespDTO;
import cn.iocoder.ssy.module.promotion.controller.admin.bargain.vo.help.BargainHelpPageReqVO;
import cn.iocoder.ssy.module.promotion.controller.admin.bargain.vo.help.BargainHelpRespVO;
import cn.iocoder.ssy.module.promotion.convert.bargain.BargainHelpConvert;
import cn.iocoder.ssy.module.promotion.dal.dataobject.bargain.BargainHelpDO;
import cn.iocoder.ssy.module.promotion.service.bargain.BargainHelpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

import static cn.iocoder.ssy.framework.common.pojo.CommonResult.success;
import static cn.iocoder.ssy.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 砍价助力")
@RestController
@RequestMapping("/promotion/bargain-help")
@Validated
public class BargainHelpController {

    @Resource
    private BargainHelpService bargainHelpService;

    @Resource
    private MemberUserApi memberUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得砍价助力分页")
    @PreAuthorize("@ss.hasPermission('promotion:bargain-help:query')")
    public CommonResult<PageResult<BargainHelpRespVO>> getBargainHelpPage(@Valid BargainHelpPageReqVO pageVO) {
        PageResult<BargainHelpDO> pageResult = bargainHelpService.getBargainHelpPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接数据
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(
                convertSet(pageResult.getList(), BargainHelpDO::getUserId));
        return success(BargainHelpConvert.INSTANCE.convertPage(pageResult, userMap));
    }

}
