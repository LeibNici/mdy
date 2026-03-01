package com.jiandaoyun.shared.kernel.outbox.consumer.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.consumer.OutboxConsumeLogRepository;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * 基于内存的出箱消费日志仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
@ConditionalOnProperty(prefix = "app.outbox.consumer", name = "store", havingValue = "memory", matchIfMissing = true)
public class InMemoryOutboxConsumeLogRepository implements OutboxConsumeLogRepository {

    private final ConcurrentHashMap<String, Instant> consumedIds = new ConcurrentHashMap<>();

    /**
     * 判断消息是否已消费.
     *
     * @param outboxId 出箱消息标识.
     * @return 是否已消费.
     */
    @Override
    public boolean isConsumed(String outboxId) {
        return consumedIds.containsKey(outboxId);
    }

    /**
     * 标记消息已消费.
     *
     * @param outboxId 出箱消息标识.
     * @param consumedAt 消费时间.
     */
    @Override
    public void markConsumed(String outboxId, Instant consumedAt) {
        consumedIds.putIfAbsent(outboxId, consumedAt);
    }
}
