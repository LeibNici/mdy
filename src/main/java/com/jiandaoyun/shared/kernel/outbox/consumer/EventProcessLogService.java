package com.jiandaoyun.shared.kernel.outbox.consumer;

import java.time.Instant;
import org.springframework.stereotype.Service;

/**
 * 事件处理日志服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class EventProcessLogService {

    private static final String STATUS_SUCCESS = "SUCCESS";

    private static final String STATUS_FAILED = "FAILED";

    private static final String STATUS_DEAD_LETTER = "DEAD_LETTER";

    private final EventProcessLogRepository eventProcessLogRepository;

    /**
     * 构造事件处理日志服务实例.
     *
     * @param eventProcessLogRepository 事件处理日志仓储.
     */
    public EventProcessLogService(EventProcessLogRepository eventProcessLogRepository) {
        this.eventProcessLogRepository = eventProcessLogRepository;
    }

    /**
     * 记录处理成功日志.
     *
     * @param handlerName 处理器名称.
     * @param eventType 事件类型.
     * @param payload 事件负载.
     * @param outboxId 出箱消息标识.
     */
    public void recordSuccess(String handlerName, String eventType, String payload, String outboxId) {
        eventProcessLogRepository.save(
            handlerName,
            eventType,
            payload,
            outboxId,
            STATUS_SUCCESS,
            null,
            Instant.now()
        );
    }

    /**
     * 记录处理失败日志.
     *
     * @param handlerName 处理器名称.
     * @param eventType 事件类型.
     * @param payload 事件负载.
     * @param outboxId 出箱消息标识.
     * @param errorMessage 错误信息.
     */
    public void recordFailure(
        String handlerName,
        String eventType,
        String payload,
        String outboxId,
        String errorMessage
    ) {
        eventProcessLogRepository.save(
            handlerName,
            eventType,
            payload,
            outboxId,
            STATUS_FAILED,
            errorMessage,
            Instant.now()
        );
    }

    /**
     * 记录死信处理日志.
     *
     * @param handlerName 处理器名称.
     * @param eventType 事件类型.
     * @param payload 事件负载.
     * @param outboxId 出箱消息标识.
     * @param errorMessage 错误信息.
     */
    public void recordDeadLetter(
        String handlerName,
        String eventType,
        String payload,
        String outboxId,
        String errorMessage
    ) {
        eventProcessLogRepository.save(
            handlerName,
            eventType,
            payload,
            outboxId,
            STATUS_DEAD_LETTER,
            errorMessage,
            Instant.now()
        );
    }

    /**
     * 查询指定处理器日志数量.
     *
     * @param handlerName 处理器名称.
     * @return 日志数量.
     */
    public long countByHandler(String handlerName) {
        return eventProcessLogRepository.countByHandler(handlerName);
    }
}
