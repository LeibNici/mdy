package com.jiandaoyun.shared.kernel.outbox.consumer;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 出箱消费幂等服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class OutboxConsumeDedupService {

    private final OutboxConsumeLogRepository outboxConsumeLogRepository;

    private final boolean enabled;

    /**
     * 构造出箱消费幂等服务实例.
     *
     * @param outboxConsumeLogRepository 出箱消费日志仓储.
     * @param enabled 是否启用幂等.
     */
    public OutboxConsumeDedupService(
        OutboxConsumeLogRepository outboxConsumeLogRepository,
        @Value("${app.outbox.consumer.idempotent:true}") boolean enabled
    ) {
        this.outboxConsumeLogRepository = outboxConsumeLogRepository;
        this.enabled = enabled;
    }

    /**
     * 判断消息是否应被跳过.
     *
     * @param outboxId 出箱消息标识.
     * @return 是否应跳过.
     */
    public boolean shouldSkip(String outboxId) {
        if (!enabled || outboxId == null || outboxId.isBlank()) {
            return false;
        }
        return outboxConsumeLogRepository.isConsumed(outboxId);
    }

    /**
     * 标记消息已消费.
     *
     * @param outboxId 出箱消息标识.
     */
    public void markConsumed(String outboxId) {
        if (!enabled || outboxId == null || outboxId.isBlank()) {
            return;
        }
        outboxConsumeLogRepository.markConsumed(outboxId, Instant.now());
    }
}
