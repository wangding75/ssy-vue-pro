package cn.iocoder.ssy.module.report.framework.security.config;

import cn.iocoder.ssy.framework.security.config.AuthorizeRequestsCustomizer;
import cn.iocoder.ssy.module.system.api.oauth2.OAuth2TokenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import javax.annotation.Resource;

/**
 * Report 模块的 Security 配置
 */
@Configuration("reportSecurityConfiguration")
public class SecurityConfiguration {

    @Resource
    private OAuth2TokenApi oauth2TokenApi;

    @Bean("reportAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                registry.antMatchers("/jmreport/**").permitAll(); // 积木报表
            }

        };
    }

}
