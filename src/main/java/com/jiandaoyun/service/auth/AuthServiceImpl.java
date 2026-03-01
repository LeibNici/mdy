package com.jiandaoyun.service.auth;

import com.jiandaoyun.auth.application.service.AuthApplicationService;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 认证服务适配实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthApplicationService authApplicationService;

    /**
     * 构造认证服务适配实例.
     *
     * @param authApplicationService 认证应用服务.
     */
    public AuthServiceImpl(AuthApplicationService authApplicationService) {
        this.authApplicationService = authApplicationService;
    }

    /**
     * 获取认证模块健康状态.
     *
     * @return 健康状态信息.
     */
    @Override
    public Map<String, String> health() {
        return authApplicationService.health();
    }
}
