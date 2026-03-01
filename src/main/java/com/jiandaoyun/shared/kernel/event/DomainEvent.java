package com.jiandaoyun.shared.kernel.event;

import java.time.Instant;

/**
 * 领域事件接口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface DomainEvent {

    /**
     * 获取事件发生时间.
     *
     * @return 事件发生时间.
     */
    Instant occurredOn();

    /**
     * 获取事件类型.
     *
     * @return 事件类型.
     */
    String eventType();
}
