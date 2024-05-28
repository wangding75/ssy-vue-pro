package cn.ssy.module.finance.config;

import cn.ssy.module.biz.entity.Entry;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class XQCacheConfig {

    private final Map<String, Object> xqConfig;

    private final List<Entry<String, Integer>> addresses;

    public Object get(String key) {
        return xqConfig.get(key);
    }

    public String getString(String key) {
        return (String) xqConfig.get(key);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) xqConfig.get(key);
    }

    public void set(String key, String value) {
        xqConfig.put(key, value);
    }
}
