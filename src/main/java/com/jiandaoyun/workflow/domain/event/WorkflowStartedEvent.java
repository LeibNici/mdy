package com.jiandaoyun.workflow.domain.event;

import com.jiandaoyun.shared.kernel.event.DomainEvent;
import java.time.Instant;

/**
 * 工作流启动领域事件.
 *
 * @param instanceId 工作流实例标识.
 * @param formId 表单标识.
 * @param occurredOn 事件发生时间.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public record WorkflowStartedEvent(
    String instanceId,
    String formId,
    Instant occurredOn
) implements DomainEvent {

    /**
     * 获取事件类型.
     *
     * @return 事件类型.
     */
    @Override
    public String eventType() {
        return "workflow.instance.started";
    }
}
