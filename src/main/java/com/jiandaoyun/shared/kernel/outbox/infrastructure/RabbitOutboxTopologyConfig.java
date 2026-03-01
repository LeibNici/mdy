package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 出箱拓扑配置.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Configuration
@ConditionalOnClass(ConnectionFactory.class)
@ConditionalOnProperty(prefix = "app.outbox", name = "delivery-mode", havingValue = "rabbit")
public class RabbitOutboxTopologyConfig {

    /**
     * 声明出箱交换机.
     *
     * @param exchangeName 交换机名称.
     * @return 直连交换机.
     */
    @Bean
    @ConditionalOnProperty(prefix = "app.outbox.rabbit", name = "auto-declare", havingValue = "true", matchIfMissing = true)
    public DirectExchange outboxExchange(
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.exchange:jiandaoyun.outbox.exchange}")
        String exchangeName
    ) {
        return new DirectExchange(exchangeName, true, false);
    }

    /**
     * 声明出箱队列.
     *
     * @param queueName 队列名称.
     * @return 队列对象.
     */
    @Bean
    @ConditionalOnProperty(prefix = "app.outbox.rabbit", name = "auto-declare", havingValue = "true", matchIfMissing = true)
    public Queue outboxQueue(
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.queue:jiandaoyun.outbox.queue}")
        String queueName
    ) {
        return new Queue(queueName, true);
    }

    /**
     * 声明出箱绑定关系.
     *
     * @param outboxQueue 出箱队列.
     * @param outboxExchange 出箱交换机.
     * @param routingKey 路由键.
     * @return 绑定关系.
     */
    @Bean
    @ConditionalOnProperty(prefix = "app.outbox.rabbit", name = "auto-declare", havingValue = "true", matchIfMissing = true)
    public Binding outboxBinding(
        Queue outboxQueue,
        DirectExchange outboxExchange,
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.routing-key:jiandaoyun.outbox}") String routingKey
    ) {
        return BindingBuilder.bind(outboxQueue).to(outboxExchange).with(routingKey);
    }
}
