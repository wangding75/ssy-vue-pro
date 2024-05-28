package cn.ssy.module.finance.support.impl;

import cn.ssy.module.finance.config.FinanceConstants;
import cn.ssy.module.finance.config.XQCacheConfig;
import cn.ssy.module.finance.support.CookieProcessor;
import cn.ssy.module.finance.utils.RequestParaBuilder;
import cn.ssy.module.finance.utils.URLMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;


@Service
@Slf4j
public class XQCookieProcessor implements CookieProcessor {

    @Autowired
    private XQCacheConfig xqCacheConfig;

    @Scheduled(cron = "0 0/10 * * * ?")
    @Override
    public void refreshCookie() throws Exception {
        log.info("开始刷新雪球Cookie...");
        String areacode = xqCacheConfig.getString(FinanceConstants.XQ_AREA_CODE_KEY);
        String userID = xqCacheConfig.getString(FinanceConstants.XQ_USER_ID_KEY);
        String passwd = xqCacheConfig.getString(FinanceConstants.XQ_USER_PASSWORD_KEY);
        String cookies = xqCacheConfig.getString(FinanceConstants.XQ_COOKIE_KEY);
        boolean rememberMe = xqCacheConfig.getBoolean(FinanceConstants.XQ_REMEMBER_KEY);

        HttpURLConnection connection = null;
        if (userID != null && passwd != null) {
            connection = login(areacode, userID, passwd, rememberMe);
        }
        try {
            connection = connection == null ?
                    (HttpURLConnection) new URL(URLMapper.MAIN_PAGE.getURL()).openConnection() : connection;
            connection.connect();

            String cookie = cookies != null ? cookies : connection.getHeaderFields().get("Set-Cookie")
                    .stream()
                    .map(x -> x.split(";")[0].concat(";"))
                    .filter(x -> x.contains("token=") || x.contains("s="))
                    .reduce("", String::concat);
            xqCacheConfig.set(FinanceConstants.XQ_COOKIE_KEY, cookie);
            log.info("new Cookie: {}", cookie);
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    public HttpURLConnection login(String areacode,
                                   String userID,
                                   String passwd,
                                   boolean rememberMe) throws Exception {

        areacode = areacode == null ? "86" : areacode;
        if (userID == null || passwd == null) {
            throw new IllegalArgumentException("null parameter: userID or password");
        }

        RequestParaBuilder builder = new RequestParaBuilder(URLMapper.LOGIN_PAGE.getURL())
                .addParameter("areacode", areacode)
                .addParameter("telephone", userID)
                .addParameter("password", passwd)
                .addParameter("remember_me", rememberMe ? "on" : "off");

        URL url = new URL(builder.build());
        return (HttpURLConnection) url.openConnection();
    }
}
