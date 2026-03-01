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
    void shouldRecordSuccessAndFailureLog() {
        EventProcessLogService service = new EventProcessLogService(new InMemoryEventProcessLogRepository());
        service.recordSuccess(
            "MetadataOutboxEventHandler",
            "metadata.form.created",
            "{\"formId\":\"1\"}",
            "outbox-1"
        );
        service.recordFailure(
            "MetadataOutboxEventHandler",
            "metadata.form.created",
            "{\"formId\":\"1\"}",
            "outbox-1",
            "test failure"
        );

        if (service.countByHandler("MetadataOutboxEventHandler") != 2) {
            throw new IllegalStateException("handler log count should be 2");
        }
    }
}
