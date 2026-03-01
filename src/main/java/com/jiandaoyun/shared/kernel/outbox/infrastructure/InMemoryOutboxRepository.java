package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.OutboxMessage;
import com.jiandaoyun.shared.kernel.outbox.OutboxRepository;
import com.jiandaoyun.shared.kernel.outbox.OutboxStatus;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

/**
 * 基于内存的出箱仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
public class InMemoryOutboxRepository implements OutboxRepository {

    private final CopyOnWriteArrayList<OutboxMessage> store = new CopyOnWriteArrayList<>();

    /**
     * 保存出箱消息.
     *
     * @param message 出箱消息.
     */
    @Override
    public void save(OutboxMessage message) {
        store.add(message);
    }

    /**
     * 查询全部待投递消息.
     *
     * @return 待投递消息列表.
     */
    @Override
    public List<OutboxMessage> findPending() {
        return store.stream().filter(message -> message.getStatus() == OutboxStatus.PENDING).toList();
    }

    /**
     * 查询消息总数.
     *
     * @return 消息总数.
     */
    @Override
    public long count() {
        return store.size();
    }
}
