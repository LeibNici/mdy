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
     * 记录事件处理日志.
     *
     * @param handlerName 处理器名称.
     * @param eventType 事件类型.
     * @param payload 事件载荷.
     */
    public void record(String handlerName, String eventType, String payload) {
        eventProcessLogRepository.save(handlerName, eventType, payload, Instant.now());
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
