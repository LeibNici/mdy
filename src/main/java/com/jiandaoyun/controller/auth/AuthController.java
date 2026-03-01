package com.jiandaoyun.controller.auth;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.service.auth.AuthService;
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

    private final AuthService authService;

    /**
     * 构造认证控制器实例.
     *
     * @param authService 认证服务实例.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 获取认证模块健康状态.
     *
     * @return 健康状态信息.
     */
    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health() {
        return ApiResponse.ok(authService.health());
    }
}
