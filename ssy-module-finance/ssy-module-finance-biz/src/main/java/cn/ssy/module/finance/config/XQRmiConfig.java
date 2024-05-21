package cn.ssy.module.finance.config;

import cn.ssy.module.biz.entity.Entry;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class XQRmiConfig {

    private final List<Entry<String, Integer>> addresses;

}
