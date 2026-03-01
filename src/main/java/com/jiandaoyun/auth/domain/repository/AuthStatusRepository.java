package com.jiandaoyun.auth.domain.repository;

import java.util.Map;

/**
 * 认证状态仓储端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface AuthStatusRepository {

    /**
     * 获取认证模块状态.
     *
     * @return 状态信息.
     */
    Map<String, String> getStatus();
}
