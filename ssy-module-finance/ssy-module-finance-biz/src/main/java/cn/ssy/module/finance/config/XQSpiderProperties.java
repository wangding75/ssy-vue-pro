package cn.ssy.module.finance.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "ssy.finance.xq")
@Validated
@Data
public class XQSpiderProperties {

    private String areaCode = "86";

    @NotEmpty(message = "用户ID不能为空")
    private String userId;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private Boolean rememberMe = true;

    private String masterReqIp;

    private String slaveRcvIp;

}
