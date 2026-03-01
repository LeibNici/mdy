package com.jiandaoyun.service.auth;

import java.util.Map;

/**
 * 认证服务接口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface AuthService {

    /**
     * 获取认证模块健康状态.
     *
     * @return 健康状态信息.
     */
    Map<String, String> health();
}
