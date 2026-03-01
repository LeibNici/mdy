package com.jiandaoyun.shared.kernel.outbox;

import com.jiandaoyun.dto.response.OutboxStatsResponse;
import com.jiandaoyun.shared.kernel.outbox.consumer.EventProcessLogService;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 出箱运维服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class OutboxOpsService {

    private final OutboxService outboxService;

    private final OutboxDispatcher outboxDispatcher;

    private final EventProcessLogService eventProcessLogService;

    /**
     * 构造出箱运维服务实例.
     *
     * @param outboxService 出箱服务.
     * @param outboxDispatcher 出箱调度器.
     * @param eventProcessLogService 事件处理日志服务.
     */
    public OutboxOpsService(
        OutboxService outboxService,
        OutboxDispatcher outboxDispatcher,
        EventProcessLogService eventProcessLogService
    ) {
        this.outboxService = outboxService;
        this.outboxDispatcher = outboxDispatcher;
        this.eventProcessLogService = eventProcessLogService;
    }

    /**
     * 获取出箱统计信息.
     *
     * @return 出箱统计响应对象.
     */
    public OutboxStatsResponse stats() {
        Map<String, Long> handlerCounts = Map.of(
            "MetadataOutboxEventHandler", eventProcessLogService.countByHandler("MetadataOutboxEventHandler"),
            "DataOutboxEventHandler", eventProcessLogService.countByHandler("DataOutboxEventHandler"),
            "WorkflowOutboxEventHandler", eventProcessLogService.countByHandler("WorkflowOutboxEventHandler"),
            "LoggingOutboxEventHandler", eventProcessLogService.countByHandler("LoggingOutboxEventHandler"),
            "DeadLetterOutboxConsumer", eventProcessLogService.countByHandler("DeadLetterOutboxConsumer")
        );
        return OutboxStatsResponse.builder()
            .totalCount(outboxService.count())
            .pendingCount(outboxService.countByStatus(OutboxStatus.PENDING))
            .processedCount(outboxService.countByStatus(OutboxStatus.PROCESSED))
            .failedCount(outboxService.countByStatus(OutboxStatus.FAILED))
            .handlerCounts(handlerCounts)
            .build();
    }

    /**
     * 立即触发一次出箱投递.
     */
    public void dispatchNow() {
        outboxDispatcher.dispatchPending();
    }
}
