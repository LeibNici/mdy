package com.jiandaoyun.data.domain.event;

import com.jiandaoyun.shared.kernel.event.DomainEvent;
import java.time.Instant;

/**
 * 数据提交领域事件.
 *
 * @param formId 表单标识.
 * @param recordId 记录标识.
 * @param occurredOn 事件发生时间.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public record DataSubmittedEvent(
    String formId,
    String recordId,
    Instant occurredOn
) implements DomainEvent {

    /**
     * 获取事件类型.
     *
     * @return 事件类型.
     */
    @Override
    public String eventType() {
        return "data.record.submitted";
    }
}
