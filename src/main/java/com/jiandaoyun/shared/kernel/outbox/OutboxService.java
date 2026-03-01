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
            .status(OutboxStatus.PENDING)
            .build();
        outboxRepository.save(message);
    }

    /**
     * 查询全部待投递消息.
     *
     * @return 待投递消息列表.
     */
    public List<OutboxMessage> findPending() {
        return outboxRepository.findPending();
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
