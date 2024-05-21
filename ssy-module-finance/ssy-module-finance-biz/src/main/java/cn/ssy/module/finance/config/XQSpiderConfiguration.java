package cn.ssy.module.finance.config;

import cn.ssy.module.biz.entity.Entry;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(XQSpiderProperties.class)
public class XQSpiderConfiguration {


    @Bean
    public XQRmiConfig rmiConfig(XQSpiderProperties properties) {
        List<Entry<String, Integer>> addresses = Lists.newArrayList();
        String masterReqIp = properties.getMasterReqIp();
        String slaveRcvIp = properties.getSlaveRcvIp();
        addAddress(addresses, masterReqIp);
        addAddress(addresses, slaveRcvIp);
        return new XQRmiConfig(addresses);
    }

    private void addAddress(List<Entry<String, Integer>> addresses, String ipAddr) {
        String[] kv = ipAddr.split(":");
        String address = kv[0].trim();
        Integer port = Integer.parseInt(kv[1].trim());
        addresses.add(new Entry<>(address, port));
    }
}
