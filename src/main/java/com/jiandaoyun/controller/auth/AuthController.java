package com.jiandaoyun.controller.auth;

import com.jiandaoyun.auth.application.service.AuthApplicationService;
import com.jiandaoyun.common.model.ApiResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthApplicationService authApplicationService;

    /**
     * 构造认证控制器实例.
     *
     * @param authApplicationService 认证应用服务实例.
     */
    public AuthController(AuthApplicationService authApplicationService) {
        this.authApplicationService = authApplicationService;
    }

    /**
     * 获取认证模块健康状态.
     *
     * @return 健康状态信息.
     */
    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health() {
        return ApiResponse.ok(authApplicationService.health());
    }
}
