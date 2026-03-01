package com.jiandaoyun.shared.kernel.outbox.consumer;

import java.time.Instant;

/**
 * 事件处理日志仓储端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface EventProcessLogRepository {

    /**
     * 保存事件处理日志.
     *
     * @param handlerName 处理器名称.
     * @param eventType 事件类型.
     * @param payload 事件载荷.
     * @param processedAt 处理时间.
     */
    void save(String handlerName, String eventType, String payload, Instant processedAt);

    /**
     * 查询指定处理器的日志数量.
     *
     * @param handlerName 处理器名称.
     * @return 日志数量.
     */
    long countByHandler(String handlerName);
}
