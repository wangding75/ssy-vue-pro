package cn.iocoder.ssy.module.pay.test;

import cn.iocoder.ssy.framework.datasource.config.ssyDataSourceAutoConfiguration;
import cn.iocoder.ssy.framework.mybatis.config.ssyMybatisAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BaseDbIntegrationTest.Application.class)
@ActiveProfiles("integration-test") // 设置使用 application-integration-test 配置文件
public class BaseDbIntegrationTest {

    @Import({
            // DB 配置类
            DynamicDataSourceAutoConfiguration.class, // Dynamic Datasource 配置类
            SsyDataSourceAutoConfiguration.class, // 自己的 DB 配置类
            DataSourceAutoConfiguration.class, // Spring DB 自动配置类
            DataSourceTransactionManagerAutoConfiguration.class, // Spring 事务自动配置类
            // MyBatis 配置类
            SsyMybatisAutoConfiguration.class, // 自己的 MyBatis 配置类
            MybatisPlusAutoConfiguration.class, // MyBatis 的自动配置类
    })
    public static class Application {
    }

}