package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.consumer.OutboxEventHandler;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 出箱事件消费者.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
@ConditionalOnClass(RabbitListener.class)
@ConditionalOnProperty(prefix = "app.outbox", name = "delivery-mode", havingValue = "rabbit")
public class RabbitOutboxEventConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitOutboxEventConsumer.class);

    private final List<OutboxEventHandler> outboxEventHandlers;

    /**
     * 构造 RabbitMQ 出箱事件消费者实例.
     *
     * @param outboxEventHandlers 事件处理器列表.
     */
    public RabbitOutboxEventConsumer(List<OutboxEventHandler> outboxEventHandlers) {
        this.outboxEventHandlers = outboxEventHandlers;
    }

    /**
     * 消费出箱事件消息.
     *
     * @param payload 消息载荷.
     * @param eventType 事件类型.
     * @param outboxId 出箱消息标识.
     */
    @RabbitListener(queues = "${app.outbox.rabbit.queue:jiandaoyun.outbox.queue}")
    public void consume(
        String payload,
        @Header(name = "eventType", required = false) String eventType,
        @Header(name = "outboxId", required = false) String outboxId
    ) {
        String safeEventType = eventType == null ? "unknown.event" : eventType;
        for (OutboxEventHandler handler : outboxEventHandlers) {
            if (handler.supports(safeEventType)) {
                handler.handle(safeEventType, payload);
                return;
            }
        }
        LOGGER.warn("no outbox event handler found, id={}, type={}", outboxId, safeEventType);
    }
}
