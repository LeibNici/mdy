package com.jiandaoyun.shared.kernel.outbox.consumer;

import com.jiandaoyun.shared.kernel.outbox.consumer.infrastructure.InMemoryEventProcessLogRepository;
import org.junit.jupiter.api.Test;

/**
 * 事件处理日志服务测试.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
class EventProcessLogServiceTest {

    @Test
    void shouldRecordHandlerLog() {
        EventProcessLogService service = new EventProcessLogService(new InMemoryEventProcessLogRepository());
        service.record("MetadataOutboxEventHandler", "metadata.form.created", "{\"formId\":\"1\"}");

        if (service.countByHandler("MetadataOutboxEventHandler") != 1) {
            throw new IllegalStateException("handler log count should be 1");
        }
    }
}
