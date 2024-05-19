package cn.iocoder.ssy.framework.apilog.core.service;

import cn.iocoder.ssy.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;

/**
 * API 访问日志 Framework Service 接口
 *
 * @author  Ssy
 */
public interface ApiAccessLogFrameworkService {

    /**
     * 创建 API 访问日志
     *
     * @param reqDTO API 访问日志
     */
    void createApiAccessLog(ApiAccessLogCreateReqDTO reqDTO);

}
