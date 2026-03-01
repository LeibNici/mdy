package com.jiandaoyun.shared.kernel.outbox.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 兜底事件日志处理器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class LoggingOutboxEventHandler implements OutboxEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingOutboxEventHandler.class);

    /**
     * 兜底处理器接收所有事件.
     *
     * @param eventType 事件类型.
     * @return 始终返回 true.
     */
    @Override
    public boolean supports(String eventType) {
        return true;
    }

    /**
     * 记录未被专用处理器消费的事件.
     *
     * @param eventType 事件类型.
     * @param payload 事件负载.
     */
    @Override
    public void handle(String eventType, String payload) {
        LOGGER.info("consume outbox event by fallback handler, type={}, payload={}", eventType, payload);
    }
}
