package cn.ssy.module.finance.spider;

import cn.ssy.module.finance.spider.timeWaitingStrategy.DefaultTimeWaitingStrategy;
import cn.ssy.module.finance.spider.timeWaitingStrategy.TimeWaitingStrategy;
import cn.ssy.module.finance.utils.HttpRequestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

/**
 * 请求发送
 * @author: decaywood
 * @date: 2015/12/4 13:35
 */
public abstract class AbstractRequester {

    protected String webSite;

    protected TimeWaitingStrategy strategy;

    protected ObjectMapper mapper;

    protected String cookies;


    public AbstractRequester(TimeWaitingStrategy strategy, String webSite, String cookies) {
        super();
        this.cookies = cookies;
        this.webSite = webSite;
        this.strategy = strategy == null ? new DefaultTimeWaitingStrategy() : strategy;
        this.mapper = new ObjectMapper();

    }

    protected String request(URL url) throws IOException {
        return new HttpRequestHelper(webSite, cookies).request(url);
    }

    protected String requestWithoutGzip(URL url) throws IOException {
        return new HttpRequestHelper(webSite, false, cookies).request(url);
    }

}
