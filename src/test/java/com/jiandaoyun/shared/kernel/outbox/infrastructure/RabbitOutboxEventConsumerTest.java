package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.consumer.OutboxConsumeDedupService;
import com.jiandaoyun.shared.kernel.outbox.consumer.OutboxEventHandler;
import com.jiandaoyun.shared.kernel.outbox.consumer.infrastructure.InMemoryOutboxConsumeLogRepository;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

/**
 * RabbitMQ 出箱事件消费者测试.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
class RabbitOutboxEventConsumerTest {

    @Test
    void shouldConsumeOnlyOnceForSameOutboxId() {
        AtomicInteger counter = new AtomicInteger(0);
        OutboxEventHandler handler = new OutboxEventHandler() {
            @Override
            public boolean supports(String eventType) {
                return "data.record.submitted".equals(eventType);
            }

            @Override
            public void handle(String eventType, String payload) {
                counter.incrementAndGet();
            }
        };

        OutboxConsumeDedupService dedupService =
            new OutboxConsumeDedupService(new InMemoryOutboxConsumeLogRepository(), true);
        RabbitOutboxEventConsumer consumer = new RabbitOutboxEventConsumer(List.of(handler), dedupService);

        consumer.consume("{\"id\":\"1\"}", "data.record.submitted", "outbox-1");
        consumer.consume("{\"id\":\"1\"}", "data.record.submitted", "outbox-1");

        if (counter.get() != 1) {
            throw new IllegalStateException("duplicated message should be consumed once");
        }
    }
}
