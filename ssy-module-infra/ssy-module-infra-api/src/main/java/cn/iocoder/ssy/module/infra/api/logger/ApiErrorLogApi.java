package cn.iocoder.ssy.module.infra.api.logger;

import cn.iocoder.ssy.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;

import javax.validation.Valid;

/**
 * API 错误日志的 API 接口
 *
 * @author  Ssy
 */
public interface ApiErrorLogApi {

    /**
     * 创建 API 错误日志
     *
     * @param createDTO 创建信息
     */
    void createApiErrorLog(@Valid ApiErrorLogCreateReqDTO createDTO);

}
