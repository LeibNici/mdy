package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.consumer.EventProcessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 出箱死信消费者.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
@ConditionalOnClass(RabbitListener.class)
@ConditionalOnProperty(prefix = "app.outbox", name = "delivery-mode", havingValue = "rabbit")
public class RabbitOutboxDeadLetterConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitOutboxDeadLetterConsumer.class);

    private final EventProcessLogService eventProcessLogService;

    /**
     * 构造 RabbitMQ 出箱死信消费者实例.
     *
     * @param eventProcessLogService 事件处理日志服务.
     */
    public RabbitOutboxDeadLetterConsumer(EventProcessLogService eventProcessLogService) {
        this.eventProcessLogService = eventProcessLogService;
    }

    /**
     * 消费死信队列消息.
     *
     * @param payload 消息载荷.
     * @param eventType 事件类型.
     * @param outboxId 出箱消息标识.
     */
    @RabbitListener(queues = "${app.outbox.rabbit.dead-letter-queue:jiandaoyun.outbox.dlq}")
    public void consumeDeadLetter(
        String payload,
        @Header(name = "eventType", required = false) String eventType,
        @Header(name = "outboxId", required = false) String outboxId
    ) {
        String safeEventType = eventType == null ? "unknown.event" : eventType;
        LOGGER.error("consume dead-letter outbox message, id={}, type={}", outboxId, safeEventType);
        eventProcessLogService.record("DeadLetterOutboxConsumer", safeEventType, payload);
    }
}
