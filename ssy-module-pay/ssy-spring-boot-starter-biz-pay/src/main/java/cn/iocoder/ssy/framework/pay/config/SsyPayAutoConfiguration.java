package cn.iocoder.ssy.framework.pay.config;

import cn.iocoder.ssy.framework.pay.core.client.PayClientFactory;
import cn.iocoder.ssy.framework.pay.core.client.impl.PayClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 支付配置类
 *
 * @author  Ssy
 */
@AutoConfiguration
public class SsyPayAutoConfiguration {

    @Bean
    public PayClientFactory payClientFactory() {
        return new PayClientFactoryImpl();
    }

}
