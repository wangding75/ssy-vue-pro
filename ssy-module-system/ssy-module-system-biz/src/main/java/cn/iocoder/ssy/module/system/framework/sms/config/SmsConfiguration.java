package cn.iocoder.ssy.module.system.framework.sms.config;

import cn.iocoder.ssy.module.system.framework.sms.core.client.SmsClientFactory;
import cn.iocoder.ssy.module.system.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信配置类，包括短信客户端、短信验证码两部分
 *
 * @author  Ssy
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SmsCodeProperties.class)
public class SmsConfiguration {

    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }

}