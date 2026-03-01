package com.jiandaoyun.shared.kernel.outbox.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据事件处理器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class DataOutboxEventHandler implements OutboxEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataOutboxEventHandler.class);

    private final EventProcessLogService eventProcessLogService;

    /**
     * 构造数据事件处理器实例.
     *
     * @param eventProcessLogService 事件处理日志服务.
     */
    public DataOutboxEventHandler(EventProcessLogService eventProcessLogService) {
        this.eventProcessLogService = eventProcessLogService;
    }

    /**
     * 判断是否支持处理指定事件类型.
     *
     * @param eventType 事件类型.
     * @return 是否支持处理.
     */
    @Override
    public boolean supports(String eventType) {
        return "data.record.submitted".equals(eventType);
    }

    /**
     * 处理事件消息.
     *
     * @param eventType 事件类型.
     * @param payload 事件载荷.
     */
    @Override
    public void handle(String eventType, String payload) {
        LOGGER.info("handle data event, type={}, payload={}", eventType, payload);
        eventProcessLogService.record("DataOutboxEventHandler", eventType, payload);
    }
}
