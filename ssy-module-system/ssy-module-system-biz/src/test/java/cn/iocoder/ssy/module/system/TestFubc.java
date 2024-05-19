package cn.iocoder.ssy.module.system;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestFubc {

    @Test
    public void test1() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String wangding01 = bCryptPasswordEncoder.encode("wangding01");
        System.out.println(wangding01);
    }
}
