package com.jiandaoyun.controller.data;

import com.jiandaoyun.common.model.ApiResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询控制器.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/query")
public class QueryController {

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
