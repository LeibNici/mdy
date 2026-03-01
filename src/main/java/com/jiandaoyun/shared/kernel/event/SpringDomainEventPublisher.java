package com.jiandaoyun.shared.kernel.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 基于 Spring 的领域事件发布器实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
public class SpringDomainEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 构造领域事件发布器实例.
     *
     * @param applicationEventPublisher Spring 事件发布器.
     */
    public SpringDomainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 发布领域事件.
     *
     * @param event 领域事件.
     */
    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
