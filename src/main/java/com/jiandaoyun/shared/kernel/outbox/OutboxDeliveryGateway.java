package com.jiandaoyun.shared.kernel.outbox;

/**
 * 出箱消息投递网关接口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface OutboxDeliveryGateway {

    /**
     * 投递出箱消息.
     *
     * @param message 出箱消息.
     */
    void deliver(OutboxMessage message);
}
