package cn.iocoder.ssy.module.promotion.framework.web.config;

import cn.iocoder.ssy.framework.swagger.config.SsySwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * promotion 模块的 web 组件的 Configuration
 *
 * @author  Ssy
 */
@Configuration(proxyBeanMethods = false)
public class PromotionWebConfiguration {

    /**
     * promotion 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi promotionGroupedOpenApi() {
        return SsySwaggerAutoConfiguration.buildGroupedOpenApi("promotion");
    }

}
