package com.jiandaoyun.shared.kernel.outbox.consumer;

import java.time.Instant;

/**
 * 事件处理日志仓储接口.
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
     * @param payload 事件负载.
     * @param outboxId 出箱消息标识.
     * @param processStatus 处理结果状态.
     * @param errorMessage 失败原因.
     * @param processedAt 处理时间.
     */
    void save(
        String handlerName,
        String eventType,
        String payload,
        String outboxId,
        String processStatus,
        String errorMessage,
        Instant processedAt
    );

    /**
     * 查询指定处理器的日志数量.
     *
     * @param handlerName 处理器名称.
     * @return 日志数量.
     */
    long countByHandler(String handlerName);
}
