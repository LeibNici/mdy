package com.jiandaoyun.metadata.domain.event;

import com.jiandaoyun.shared.kernel.event.DomainEvent;
import java.time.Instant;

/**
 * 表单创建领域事件.
 *
 * @param formId 表单标识.
 * @param formName 表单名称.
 * @param occurredOn 事件发生时间.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public record FormCreatedEvent(
    String formId,
    String formName,
    Instant occurredOn
) implements DomainEvent {

    /**
     * 获取事件类型.
     *
     * @return 事件类型.
     */
    @Override
    public String eventType() {
        return "metadata.form.created";
    }
}
