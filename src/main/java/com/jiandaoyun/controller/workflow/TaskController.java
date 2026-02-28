package com.jiandaoyun.controller.workflow;

import com.jiandaoyun.common.model.ApiResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 浠诲姟妯″潡鎺у埗鍣?
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {

    /**
     * 鍋ュ悍妫€鏌ユ帴鍙?
     *
 * @return 鍋ュ悍鐘舵€? */
    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health() {
        return ApiResponse.ok(Map.of("status", "ok"));
    }
}