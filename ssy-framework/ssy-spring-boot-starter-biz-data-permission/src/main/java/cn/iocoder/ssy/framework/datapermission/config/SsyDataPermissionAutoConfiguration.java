package cn.iocoder.ssy.framework.datapermission.config;

import cn.iocoder.ssy.framework.datapermission.core.aop.DataPermissionAnnotationAdvisor;
import cn.iocoder.ssy.framework.datapermission.core.db.DataPermissionDatabaseInterceptor;
import cn.iocoder.ssy.framework.datapermission.core.rule.DataPermissionRule;
import cn.iocoder.ssy.framework.datapermission.core.rule.DataPermissionRuleFactory;
import cn.iocoder.ssy.framework.datapermission.core.rule.DataPermissionRuleFactoryImpl;
import cn.iocoder.ssy.framework.mybatis.core.util.MyBatisUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 数据权限的自动配置类
 *
 * @author  Ssy
 */
@AutoConfiguration
public class SsyDataPermissionAutoConfiguration {

    @Bean
    public DataPermissionRuleFactory dataPermissionRuleFactory(List<DataPermissionRule> rules) {
        return new DataPermissionRuleFactoryImpl(rules);
    }

    @Bean
    public DataPermissionDatabaseInterceptor dataPermissionDatabaseInterceptor(MybatisPlusInterceptor interceptor,
                                                                               DataPermissionRuleFactory ruleFactory) {
        // 创建 DataPermissionDatabaseInterceptor 拦截器
        DataPermissionDatabaseInterceptor inner = new DataPermissionDatabaseInterceptor(ruleFactory);
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return inner;
    }

    @Bean
    public DataPermissionAnnotationAdvisor dataPermissionAnnotationAdvisor() {
        return new DataPermissionAnnotationAdvisor();
    }

}
