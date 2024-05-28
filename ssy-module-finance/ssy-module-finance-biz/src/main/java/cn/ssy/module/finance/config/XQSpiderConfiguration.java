package cn.ssy.module.finance.config;

import cn.ssy.module.biz.entity.Entry;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(XQSpiderProperties.class)
public class XQSpiderConfiguration {


    @Bean
    public XQCacheConfig rmiConfig(XQSpiderProperties properties) {
        List<Entry<String, Integer>> addresses = Lists.newArrayList();
        String masterReqIp = properties.getMasterReqIp();
        String slaveRcvIp = properties.getSlaveRcvIp();
        addAddress(addresses, masterReqIp);
        addAddress(addresses, slaveRcvIp);

        Map<String, Object> config = Maps.newConcurrentMap();
        config.put(FinanceConstants.XQ_AREA_CODE_KEY, properties.getAreaCode());
        config.put(FinanceConstants.XQ_USER_ID_KEY, properties.getUserId());
        config.put(FinanceConstants.XQ_USER_PASSWORD_KEY, properties.getPassword());
        config.put(FinanceConstants.XQ_COOKIE_KEY, properties.getCookies());
        config.put(FinanceConstants.XQ_REMEMBER_KEY, properties.getRememberMe());

        return new XQCacheConfig(config, addresses);
    }

    private void addAddress(List<Entry<String, Integer>> addresses, String ipAddr) {
        String[] kv = ipAddr.split(":");
        String address = kv[0].trim();
        Integer port = Integer.parseInt(kv[1].trim());
        addresses.add(new Entry<>(address, port));
    }
}
