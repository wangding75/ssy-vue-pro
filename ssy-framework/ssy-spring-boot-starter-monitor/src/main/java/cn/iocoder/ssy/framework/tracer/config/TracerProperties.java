package cn.iocoder.ssy.framework.tracer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * BizTracer配置类
 *
 * @author 麻薯
 */
@ConfigurationProperties("ssy.tracer")
@Data
public class TracerProperties {
}
