package com.jiandaoyun.shared.kernel.outbox;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 出箱消息调度投递器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
public class OutboxDispatcher {

    private final OutboxService outboxService;

    private final OutboxDeliveryGateway outboxDeliveryGateway;

    private final int batchSize;

    private final int maxRetry;

    private final boolean enabled;

    /**
     * 构造出箱消息调度投递器实例.
     *
     * @param outboxService 出箱服务.
     * @param outboxDeliveryGateway 出箱投递网关.
     * @param batchSize 批次大小.
     * @param maxRetry 最大重试次数.
     * @param enabled 是否启用调度.
     */
    public OutboxDispatcher(
        OutboxService outboxService,
        OutboxDeliveryGateway outboxDeliveryGateway,
        @Value("${app.outbox.dispatcher.batch-size:100}") int batchSize,
        @Value("${app.outbox.dispatcher.max-retry:3}") int maxRetry,
        @Value("${app.outbox.dispatcher.enabled:true}") boolean enabled
    ) {
        this.outboxService = outboxService;
        this.outboxDeliveryGateway = outboxDeliveryGateway;
        this.batchSize = batchSize;
        this.maxRetry = maxRetry;
        this.enabled = enabled;
    }

    /**
     * 调度投递待处理出箱消息.
     */
    @Scheduled(fixedDelayString = "${app.outbox.dispatcher.fixed-delay-ms:5000}")
    public void dispatchPending() {
        if (!enabled) {
            return;
        }
        List<OutboxMessage> pending = outboxService.findPending(batchSize);
        if (pending.isEmpty()) {
            return;
        }
        List<String> processedIds = new ArrayList<>();
        for (OutboxMessage message : pending) {
            try {
                outboxDeliveryGateway.deliver(message);
                processedIds.add(message.getId());
            } catch (RuntimeException ex) {
                outboxService.recordFailure(message.getId(), ex.getMessage(), maxRetry);
            }
        }
        outboxService.markProcessed(processedIds);
    }
}
