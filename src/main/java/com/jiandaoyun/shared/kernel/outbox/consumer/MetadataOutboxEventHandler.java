package com.jiandaoyun.shared.kernel.outbox.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 表单元数据事件处理器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MetadataOutboxEventHandler implements OutboxEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataOutboxEventHandler.class);

    /**
     * 判断是否支持处理指定事件类型.
     *
     * @param eventType 事件类型.
     * @return 是否支持处理.
     */
    @Override
    public boolean supports(String eventType) {
        return "metadata.form.created".equals(eventType);
    }

    /**
     * 处理表单创建事件.
     *
     * @param eventType 事件类型.
     * @param payload 事件负载.
     */
    @Override
    public void handle(String eventType, String payload) {
        LOGGER.info("handle metadata event, type={}, payload={}", eventType, payload);
    }
}
