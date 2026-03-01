package com.jiandaoyun.shared.kernel.outbox;

import java.util.List;

/**
 * 事件出箱仓储端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface OutboxRepository {

    /**
     * 保存出箱消息.
     *
     * @param message 出箱消息.
     */
    void save(OutboxMessage message);

    /**
     * 查询待投递消息.
     *
     * @param limit 返回上限.
     * @return 待投递消息列表.
     */
    List<OutboxMessage> findPending(int limit);

    /**
     * 按标识将消息标记为已投递.
     *
     * @param messageIds 消息标识列表.
     */
    void markProcessed(List<String> messageIds);

    /**
     * 查询消息总数.
     *
     * @return 消息总数.
     */
    long count();
}
