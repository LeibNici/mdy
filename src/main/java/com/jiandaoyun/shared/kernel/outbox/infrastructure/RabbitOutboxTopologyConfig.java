package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Bean("outboxExchange")
    @ConditionalOnProperty(prefix = "app.outbox.rabbit", name = "auto-declare", havingValue = "true", matchIfMissing = true)
    public DirectExchange outboxExchange(
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.exchange:jiandaoyun.outbox.exchange}")
        String exchangeName
    ) {
        return new DirectExchange(exchangeName, true, false);
    }

    /**
     * 声明出箱死信交换机.
     *
     * @param deadLetterExchangeName 死信交换机名称.
     * @return 直连交换机.
     */
    @Bean("outboxDeadLetterExchange")
    @ConditionalOnProperty(prefix = "app.outbox.rabbit", name = "auto-declare", havingValue = "true", matchIfMissing = true)
    public DirectExchange outboxDeadLetterExchange(
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.dead-letter-exchange:jiandaoyun.outbox.dlx}")
        String deadLetterExchangeName
    ) {
        return new DirectExchange(deadLetterExchangeName, true, false);
    }

    /**
     * 声明出箱队列.
     *
     * @param queueName 队列名称.
     * @param deadLetterExchangeName 死信交换机名称.
     * @param deadLetterRoutingKey 死信路由键.
     * @return 队列对象.
     */
    @Bean("outboxQueue")
    @ConditionalOnProperty(prefix = "app.outbox.rabbit", name = "auto-declare", havingValue = "true", matchIfMissing = true)
    public Queue outboxQueue(
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.queue:jiandaoyun.outbox.queue}")
        String queueName,
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.dead-letter-exchange:jiandaoyun.outbox.dlx}")
        String deadLetterExchangeName,
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.dead-letter-routing-key:jiandaoyun.outbox.dlq}")
        String deadLetterRoutingKey
    ) {
        return new Queue(queueName, true, false, false, Map.of(
            "x-dead-letter-exchange", deadLetterExchangeName,
            "x-dead-letter-routing-key", deadLetterRoutingKey
        ));
    }

    /**
     * 声明出箱死信队列.
     *
     * @param deadLetterQueueName 死信队列名称.
     * @return 死信队列对象.
     */
    @Bean("outboxDeadLetterQueue")
    @ConditionalOnProperty(prefix = "app.outbox.rabbit", name = "auto-declare", havingValue = "true", matchIfMissing = true)
    public Queue outboxDeadLetterQueue(
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.dead-letter-queue:jiandaoyun.outbox.dlq}")
        String deadLetterQueueName
    ) {
        return new Queue(deadLetterQueueName, true);
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
        @Qualifier("outboxQueue") Queue outboxQueue,
        @Qualifier("outboxExchange") DirectExchange outboxExchange,
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.routing-key:jiandaoyun.outbox}") String routingKey
    ) {
        return BindingBuilder.bind(outboxQueue).to(outboxExchange).with(routingKey);
    }

    /**
     * 声明出箱死信绑定关系.
     *
     * @param outboxDeadLetterQueue 出箱死信队列.
     * @param outboxDeadLetterExchange 出箱死信交换机.
     * @param deadLetterRoutingKey 死信路由键.
     * @return 绑定关系.
     */
    @Bean
    @ConditionalOnProperty(prefix = "app.outbox.rabbit", name = "auto-declare", havingValue = "true", matchIfMissing = true)
    public Binding outboxDeadLetterBinding(
        @Qualifier("outboxDeadLetterQueue") Queue outboxDeadLetterQueue,
        @Qualifier("outboxDeadLetterExchange") DirectExchange outboxDeadLetterExchange,
        @org.springframework.beans.factory.annotation.Value("${app.outbox.rabbit.dead-letter-routing-key:jiandaoyun.outbox.dlq}")
        String deadLetterRoutingKey
    ) {
        return BindingBuilder.bind(outboxDeadLetterQueue).to(outboxDeadLetterExchange).with(deadLetterRoutingKey);
    }
}
