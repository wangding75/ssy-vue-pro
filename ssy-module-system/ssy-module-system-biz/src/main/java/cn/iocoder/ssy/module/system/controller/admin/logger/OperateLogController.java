package cn.iocoder.ssy.module.system.controller.admin.logger;

import cn.iocoder.ssy.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.ssy.framework.common.pojo.CommonResult;
import cn.iocoder.ssy.framework.common.pojo.PageParam;
import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.framework.common.util.object.BeanUtils;
import cn.iocoder.ssy.framework.excel.core.util.ExcelUtils;
import cn.iocoder.ssy.framework.translate.core.TranslateUtils;
import cn.iocoder.ssy.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import cn.iocoder.ssy.module.system.controller.admin.logger.vo.operatelog.OperateLogRespVO;
import cn.iocoder.ssy.module.system.dal.dataobject.logger.OperateLogDO;
import cn.iocoder.ssy.module.system.service.logger.OperateLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.ssy.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.ssy.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 操作日志")
@RestController
@RequestMapping("/system/operate-log")
@Validated
public class OperateLogController {

    @Resource
    private OperateLogService operateLogService;

    @GetMapping("/page")
    @Operation(summary = "查看操作日志分页列表")
    @PreAuthorize("@ss.hasPermission('system:operate-log:query')")
    public CommonResult<PageResult<OperateLogRespVO>> pageOperateLog(@Valid OperateLogPageReqVO pageReqVO) {
        PageResult<OperateLogDO> pageResult = operateLogService.getOperateLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OperateLogRespVO.class));
    }

    @Operation(summary = "导出操作日志")
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('system:operate-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOperateLog(HttpServletResponse response, @Valid OperateLogPageReqVO exportReqVO) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OperateLogDO> list = operateLogService.getOperateLogPage(exportReqVO).getList();
        ExcelUtils.write(response, "操作日志.xls", "数据列表", OperateLogRespVO.class,
                TranslateUtils.translate(BeanUtils.toBean(list, OperateLogRespVO.class)));
    }

}
