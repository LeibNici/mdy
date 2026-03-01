package com.jiandaoyun.shared.kernel.outbox;

/**
 * 事件出箱状态枚举.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public enum OutboxStatus {
    /**
     * 待投递状态.
     */
    PENDING,

    /**
     * 已投递状态.
     */
    PROCESSED,

    /**
     * 投递失败状态.
     */
    FAILED
}
