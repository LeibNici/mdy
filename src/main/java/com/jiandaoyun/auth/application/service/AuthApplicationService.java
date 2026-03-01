package com.jiandaoyun.auth.application.service;

import com.jiandaoyun.auth.domain.repository.AuthStatusRepository;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 认证应用服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class AuthApplicationService {

    private final AuthStatusRepository authStatusRepository;

    /**
     * 构造认证应用服务实例.
     *
     * @param authStatusRepository 认证状态仓储.
     */
    public AuthApplicationService(AuthStatusRepository authStatusRepository) {
        this.authStatusRepository = authStatusRepository;
    }

    /**
     * 获取认证模块健康状态.
     *
     * @return 健康状态信息.
     */
    public Map<String, String> health() {
        return authStatusRepository.getStatus();
    }
}
