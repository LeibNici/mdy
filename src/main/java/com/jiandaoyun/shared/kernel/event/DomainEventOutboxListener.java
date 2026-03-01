package com.jiandaoyun.shared.kernel.event;

import com.jiandaoyun.shared.kernel.outbox.OutboxService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 领域事件出箱监听器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
public class DomainEventOutboxListener {

    private final OutboxService outboxService;

    /**
     * 构造领域事件出箱监听器实例.
     *
     * @param outboxService 出箱服务.
     */
    public DomainEventOutboxListener(OutboxService outboxService) {
        this.outboxService = outboxService;
    }

    /**
     * 监听领域事件并写入出箱.
     *
     * @param event 领域事件.
     */
    @EventListener
    public void onDomainEvent(DomainEvent event) {
        outboxService.append(event);
    }
}
