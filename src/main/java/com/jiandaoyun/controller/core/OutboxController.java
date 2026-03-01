package com.jiandaoyun.controller.core;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.dto.response.OutboxStatsResponse;
import com.jiandaoyun.shared.kernel.outbox.OutboxOpsService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 出箱运维控制器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@RestController
@RequestMapping("/api/outbox")
public class OutboxController {

    private final OutboxOpsService outboxOpsService;

    /**
     * 构造出箱运维控制器实例.
     *
     * @param outboxOpsService 出箱运维服务实例.
     */
    public OutboxController(OutboxOpsService outboxOpsService) {
        this.outboxOpsService = outboxOpsService;
    }

    /**
     * 获取出箱统计信息.
     *
     * @return 出箱统计响应.
     */
    @GetMapping("/stats")
    public ApiResponse<OutboxStatsResponse> stats() {
        return ApiResponse.ok(outboxOpsService.stats());
    }

    /**
     * 手动触发一次出箱投递.
     *
     * @return 执行结果.
     */
    @PostMapping("/dispatch")
    public ApiResponse<Map<String, Object>> dispatch() {
        outboxOpsService.dispatchNow();
        return ApiResponse.ok(Map.of("dispatched", true));
    }
}
