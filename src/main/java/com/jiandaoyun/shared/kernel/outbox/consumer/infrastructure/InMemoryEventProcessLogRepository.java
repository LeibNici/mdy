package com.jiandaoyun.shared.kernel.outbox.consumer.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.consumer.EventProcessLogRepository;
import java.time.Instant;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * 基于内存的事件处理日志仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
@ConditionalOnProperty(prefix = "app.outbox.consumer", name = "store", havingValue = "memory", matchIfMissing = true)
public class InMemoryEventProcessLogRepository implements EventProcessLogRepository {

    private final CopyOnWriteArrayList<EventProcessRecord> records = new CopyOnWriteArrayList<>();

    /**
     * 保存事件处理日志.
     *
     * @param handlerName 处理器名称.
     * @param eventType 事件类型.
     * @param payload 事件载荷.
     * @param processedAt 处理时间.
     */
    @Override
    public void save(String handlerName, String eventType, String payload, Instant processedAt) {
        records.add(new EventProcessRecord(handlerName, eventType, payload, processedAt));
    }

    /**
     * 查询指定处理器的日志数量.
     *
     * @param handlerName 处理器名称.
     * @return 日志数量.
     */
    @Override
    public long countByHandler(String handlerName) {
        return records.stream().filter(record -> record.handlerName().equals(handlerName)).count();
    }

    /**
     * 事件处理记录.
     *
     * @param handlerName 处理器名称.
     * @param eventType 事件类型.
     * @param payload 事件载荷.
     * @param processedAt 处理时间.
     *
     * @author chenming
     *
     * @since 2026/03/01
     */
    private record EventProcessRecord(
        String handlerName,
        String eventType,
        String payload,
        Instant processedAt
    ) {
    }
}
