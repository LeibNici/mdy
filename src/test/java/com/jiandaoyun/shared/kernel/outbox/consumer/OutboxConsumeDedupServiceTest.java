package com.jiandaoyun.shared.kernel.outbox.consumer;

import com.jiandaoyun.shared.kernel.outbox.consumer.infrastructure.InMemoryOutboxConsumeLogRepository;
import org.junit.jupiter.api.Test;

/**
 * 出箱消费幂等服务测试.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
class OutboxConsumeDedupServiceTest {

    @Test
    void shouldSkipAfterMarkedConsumed() {
        InMemoryOutboxConsumeLogRepository repository = new InMemoryOutboxConsumeLogRepository();
        OutboxConsumeDedupService service = new OutboxConsumeDedupService(repository, true);

        String outboxId = "outbox-1";
        if (service.shouldSkip(outboxId)) {
            throw new IllegalStateException("message should not be skipped before mark");
        }

        service.markConsumed(outboxId);
        if (!service.shouldSkip(outboxId)) {
            throw new IllegalStateException("message should be skipped after mark");
        }
    }
}
