package cn.iocoder.ssy.module.member.framework.web.config;

import cn.iocoder.ssy.framework.swagger.config.SsySwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * member 模块的 web 组件的 Configuration
 *
 * @author  Ssy
 */
@Configuration(proxyBeanMethods = false)
public class MemberWebConfiguration {

    /**
     * member 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi memberGroupedOpenApi() {
        return SsySwaggerAutoConfiguration.buildGroupedOpenApi("member");
    }

}
