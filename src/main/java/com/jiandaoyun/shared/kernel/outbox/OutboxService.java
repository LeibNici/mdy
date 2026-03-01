package com.jiandaoyun.shared.kernel.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.common.utils.IdGenerator;
import com.jiandaoyun.shared.kernel.event.DomainEvent;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 事件出箱服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class OutboxService {

    private final OutboxRepository outboxRepository;

    private final ObjectMapper objectMapper;

    /**
     * 构造事件出箱服务实例.
     *
     * @param outboxRepository 出箱仓储.
     * @param objectMapper JSON 序列化器.
     */
    public OutboxService(OutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * 将领域事件写入出箱.
     *
     * @param event 领域事件.
     * @throws BusinessException 事件序列化失败时抛出异常.
     */
    public void append(DomainEvent event) {
        String payload;
        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException ex) {
            throw new BusinessException("serialize domain event failed: " + event.eventType());
        }

        OutboxMessage message = OutboxMessage.builder()
            .id(IdGenerator.nextId())
            .eventType(event.eventType())
            .payload(payload)
            .occurredOn(event.occurredOn())
            .createdAt(Instant.now())
            .retryCount(0)
            .lastError(null)
            .status(OutboxStatus.PENDING)
            .build();
        outboxRepository.save(message);
    }

    /**
     * 查询待投递消息.
     *
     * @param limit 返回上限.
     * @return 待投递消息列表.
     */
    public List<OutboxMessage> findPending(int limit) {
        return outboxRepository.findPending(limit);
    }

    /**
     * 将消息标记为已投递.
     *
     * @param messageIds 消息标识列表.
     */
    public void markProcessed(List<String> messageIds) {
        outboxRepository.markProcessed(messageIds);
    }

    /**
     * 记录投递失败并更新重试次数.
     *
     * @param messageId 消息标识.
     * @param errorMessage 错误信息.
     * @param maxRetry 最大重试次数.
     */
    public void recordFailure(String messageId, String errorMessage, int maxRetry) {
        outboxRepository.recordFailure(messageId, errorMessage, maxRetry);
    }

    /**
     * 按状态统计消息数量.
     *
     * @param status 出箱状态.
     * @return 消息数量.
     */
    public long countByStatus(OutboxStatus status) {
        return outboxRepository.countByStatus(status);
    }

    /**
     * 查询消息总数.
     *
     * @return 消息总数.
     */
    public long count() {
        return outboxRepository.count();
    }
}
