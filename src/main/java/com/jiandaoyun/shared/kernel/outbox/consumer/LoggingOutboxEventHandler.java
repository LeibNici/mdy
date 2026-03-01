package com.jiandaoyun.shared.kernel.outbox.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 出箱事件日志处理器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
public class LoggingOutboxEventHandler implements OutboxEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingOutboxEventHandler.class);

    /**
     * 判断是否支持处理指定事件类型.
     *
     * @param eventType 事件类型.
     * @return 是否支持处理.
     */
    @Override
    public boolean supports(String eventType) {
        return true;
    }

    /**
     * 处理事件消息.
     *
     * @param eventType 事件类型.
     * @param payload 事件载荷.
     */
    @Override
    public void handle(String eventType, String payload) {
        LOGGER.info("consume outbox event, type={}, payload={}", eventType, payload);
    }
}
