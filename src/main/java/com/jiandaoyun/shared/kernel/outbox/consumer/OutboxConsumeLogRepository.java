package com.jiandaoyun.shared.kernel.outbox.consumer;

import java.time.Instant;

/**
 * 出箱消费日志仓储端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface OutboxConsumeLogRepository {

    /**
     * 判断消息是否已消费.
     *
     * @param outboxId 出箱消息标识.
     * @return 是否已消费.
     */
    boolean isConsumed(String outboxId);

    /**
     * 标记消息已消费.
     *
     * @param outboxId 出箱消息标识.
     * @param consumedAt 消费时间.
     */
    void markConsumed(String outboxId, Instant consumedAt);
}
