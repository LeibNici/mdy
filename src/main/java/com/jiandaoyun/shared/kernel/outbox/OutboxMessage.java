package com.jiandaoyun.shared.kernel.outbox;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

/**
 * 事件出箱消息对象.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Data
@Builder
public class OutboxMessage {

    private String id;

    private String eventType;

    private String payload;

    private Instant occurredOn;

    private Instant createdAt;

    private OutboxStatus status;
}
