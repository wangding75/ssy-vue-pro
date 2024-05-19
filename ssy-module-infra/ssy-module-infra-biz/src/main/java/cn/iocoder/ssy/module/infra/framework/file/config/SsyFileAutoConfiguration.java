package cn.iocoder.ssy.module.infra.framework.file.config;

import cn.iocoder.ssy.module.infra.framework.file.core.client.FileClientFactory;
import cn.iocoder.ssy.module.infra.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件配置类
 *
 * @author  Ssy
 */
@Configuration(proxyBeanMethods = false)
public class SsyFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
