package com.jiandaoyun.shared.kernel.event;

/**
 * 领域事件发布器接口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface DomainEventPublisher {

    /**
     * 发布领域事件.
     *
     * @param event 领域事件.
     */
    void publish(DomainEvent event);
}
