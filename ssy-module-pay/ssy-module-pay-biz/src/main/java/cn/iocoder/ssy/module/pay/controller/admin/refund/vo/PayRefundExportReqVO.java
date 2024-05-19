package cn.iocoder.ssy.module.pay.controller.admin.refund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.ssy.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 退款订单 Excel 导出 Request VO，参数和 PayRefundPageReqVO 是一致的")
@Data
public class PayRefundExportReqVO {

    @Schema(description = "应用编号", example = "1024")
    private Long appId;

    @Schema(description = "渠道编码", example = "wx_app")
    private String channelCode;

    @Schema(description = "商户支付单号", example = "10")
    private String merchantOrderId;

    @Schema(description = "商户退款单号", example = "20")
    private String merchantRefundId;

    @Schema(description = "渠道支付单号", example = "30")
    private String channelOrderNo;

    @Schema(description = "渠道退款单号", example = "40")
    private String channelRefundNo;

    @Schema(description = "退款状态", example = "0")
    private Integer status;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}
