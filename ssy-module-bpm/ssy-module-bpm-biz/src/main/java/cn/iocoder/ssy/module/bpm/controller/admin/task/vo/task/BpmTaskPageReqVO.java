package cn.iocoder.ssy.module.bpm.controller.admin.task.vo.task;

import cn.iocoder.ssy.framework.common.pojo.PageParam;
import cn.iocoder.ssy.framework.common.util.date.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 流程任务的的分页 Request VO") // 待办、已办，都使用该分页
@Data
public class BpmTaskPageReqVO extends PageParam {

    @Schema(description = "流程任务名", example = "芋道")
    private String name;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
