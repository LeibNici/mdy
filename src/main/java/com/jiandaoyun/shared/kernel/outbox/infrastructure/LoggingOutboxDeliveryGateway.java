package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.OutboxDeliveryGateway;
import com.jiandaoyun.shared.kernel.outbox.OutboxMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 基于日志输出的出箱投递网关实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
@ConditionalOnProperty(prefix = "app.outbox", name = "delivery-mode", havingValue = "log", matchIfMissing = true)
public class LoggingOutboxDeliveryGateway implements OutboxDeliveryGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingOutboxDeliveryGateway.class);

    /**
     * 投递出箱消息.
     *
     * @param message 出箱消息.
     */
    @Override
    public void deliver(OutboxMessage message) {
        LOGGER.info("deliver outbox event, id={}, type={}", message.getId(), message.getEventType());
    }
}
