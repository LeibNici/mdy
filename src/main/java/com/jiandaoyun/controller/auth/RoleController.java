package com.jiandaoyun.controller.auth;

import com.jiandaoyun.common.model.ApiResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制器.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    /**
     * 执行健康检查.
     *
     * @return 处理结果.
     */
    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health() {
        return ApiResponse.ok(Map.of("status", "ok"));
    }
}
