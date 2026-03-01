package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.OutboxDeliveryGateway;
import com.jiandaoyun.shared.kernel.outbox.OutboxMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 基于 RabbitMQ 的出箱投递网关实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
@ConditionalOnClass(RabbitTemplate.class)
@ConditionalOnProperty(prefix = "app.outbox", name = "delivery-mode", havingValue = "rabbit")
public class RabbitMqOutboxDeliveryGateway implements OutboxDeliveryGateway {

    private final RabbitTemplate rabbitTemplate;

    private final String exchange;

    private final String routingKey;

    /**
     * 构造 RabbitMQ 出箱投递网关实例.
     *
     * @param rabbitTemplate RabbitMQ 模板.
     * @param exchange 交换机名称.
     * @param routingKey 路由键.
     */
    public RabbitMqOutboxDeliveryGateway(
        RabbitTemplate rabbitTemplate,
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.exchange:jiandaoyun.outbox.exchange}") String exchange,
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.routing-key:jiandaoyun.outbox}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    /**
     * 投递出箱消息.
     *
     * @param message 出箱消息.
     */
    @Override
    public void deliver(OutboxMessage message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message.getPayload(), amqpMessage -> {
            amqpMessage.getMessageProperties().setHeader("eventType", message.getEventType());
            amqpMessage.getMessageProperties().setHeader("outboxId", message.getId());
            amqpMessage.getMessageProperties().setHeader("occurredOn", message.getOccurredOn().toString());
            return amqpMessage;
        });
    }
}
