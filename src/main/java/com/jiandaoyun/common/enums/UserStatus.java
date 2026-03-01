package com.jiandaoyun.common.enums;

/**
 * 用户状态枚举定义.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
public enum UserStatus {
    /**
     * 用户状态正常，可登录并访问系统.
     */
    ACTIVE,

    /**
     * 用户被禁用，禁止登录系统.
     */
    DISABLED,

    /**
     * 用户被锁定，需解锁后才能继续使用.
     */
    LOCKED
}
