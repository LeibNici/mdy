package com.jiandaoyun.shared.kernel.outbox.consumer;

/**
 * 出箱事件处理器接口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface OutboxEventHandler {

    /**
     * 判断是否支持处理指定事件类型.
     *
     * @param eventType 事件类型.
     * @return 是否支持处理.
     */
    boolean supports(String eventType);

    /**
     * 处理事件消息.
     *
     * @param eventType 事件类型.
     * @param payload 事件载荷.
     */
    void handle(String eventType, String payload);
}
