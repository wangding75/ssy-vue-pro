package cn.iocoder.ssy.module.pay.test;

import cn.iocoder.ssy.framework.redis.config.ssyRedisAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BaseRedisIntegrationTest.Application.class)
@ActiveProfiles("integration-test") // 设置使用 application-integration-test 配置文件
public class BaseRedisIntegrationTest {

    @Import({
            // Redis 配置类
            RedisAutoConfiguration.class, // Spring Redis 自动配置类
            SsyRedisAutoConfiguration.class, // 自己的 Redis 配置类
            RedissonAutoConfiguration.class, // Redisson 自动配置类
    })
    public static class Application {
    }

}
