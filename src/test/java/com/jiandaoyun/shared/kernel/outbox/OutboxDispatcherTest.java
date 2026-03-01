package com.jiandaoyun.shared.kernel.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiandaoyun.shared.kernel.event.DomainEvent;
import com.jiandaoyun.shared.kernel.outbox.infrastructure.InMemoryOutboxRepository;
import java.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * 出箱调度器测试.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
class OutboxDispatcherTest {

    @Test
    void shouldMarkProcessedWhenDeliverySuccess() {
        InMemoryOutboxRepository repository = new InMemoryOutboxRepository();
        OutboxService outboxService = new OutboxService(repository, new ObjectMapper());
        outboxService.append(new TestEvent("ok.event"));

        OutboxDeliveryGateway gateway = message -> { };
        OutboxDispatcher dispatcher = new OutboxDispatcher(outboxService, gateway, 100, 3, true);
        dispatcher.dispatchPending();

        if (outboxService.findPending(10).size() != 0) {
            throw new IllegalStateException("pending message count should be 0");
        }
        if (outboxService.countByStatus(OutboxStatus.PROCESSED) != 1) {
            throw new IllegalStateException("processed message count should be 1");
        }
    }

    @Test
    void shouldMarkFailedWhenRetryExhausted() {
        InMemoryOutboxRepository repository = new InMemoryOutboxRepository();
        OutboxService outboxService = new OutboxService(repository, new ObjectMapper());
        outboxService.append(new TestEvent("fail.event"));

        OutboxDeliveryGateway gateway = message -> {
            throw new IllegalStateException("deliver failed");
        };
        OutboxDispatcher dispatcher = new OutboxDispatcher(outboxService, gateway, 100, 1, true);
        dispatcher.dispatchPending();

        if (outboxService.findPending(10).size() != 0) {
            throw new IllegalStateException("pending message count should be 0");
        }
        if (outboxService.countByStatus(OutboxStatus.FAILED) != 1) {
            throw new IllegalStateException("failed message count should be 1");
        }
    }

    /**
     * 测试事件.
     *
     * @param type 事件类型.
     *
     * @author chenming
     *
     * @since 2026/03/01
     */
    private record TestEvent(String type) implements DomainEvent {

        /**
         * 获取事件发生时间.
         *
         * @return 事件发生时间.
         */
        @Override
        public Instant occurredOn() {
            return Instant.now();
        }

        /**
         * 获取事件类型.
         *
         * @return 事件类型.
         */
        @Override
        public String eventType() {
            return type;
        }
    }
}
