package cn.iocoder.ssy.module.product.controller.admin.history;

import cn.iocoder.ssy.framework.common.pojo.CommonResult;
import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.framework.common.util.object.BeanUtils;
import cn.iocoder.ssy.module.product.controller.admin.history.vo.ProductBrowseHistoryPageReqVO;
import cn.iocoder.ssy.module.product.controller.admin.history.vo.ProductBrowseHistoryRespVO;
import cn.iocoder.ssy.module.product.dal.dataobject.history.ProductBrowseHistoryDO;
import cn.iocoder.ssy.module.product.service.history.ProductBrowseHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.ssy.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 商品浏览记录")
@RestController
@RequestMapping("/product/browse-history")
@Validated
public class ProductBrowseHistoryController {

    @Resource
    private ProductBrowseHistoryService browseHistoryService;

    @GetMapping("/page")
    @Operation(summary = "获得商品浏览记录分页")
    @PreAuthorize("@ss.hasPermission('product:browse-history:query')")
    public CommonResult<PageResult<ProductBrowseHistoryRespVO>> getBrowseHistoryPage(@Valid ProductBrowseHistoryPageReqVO pageReqVO) {
        PageResult<ProductBrowseHistoryDO> pageResult = browseHistoryService.getBrowseHistoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProductBrowseHistoryRespVO.class));
    }

}