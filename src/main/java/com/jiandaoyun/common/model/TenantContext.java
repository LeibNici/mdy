package com.jiandaoyun.common.model;

/**
 * 租户上下文类型.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
public final class TenantContext {

    private static final ThreadLocal<String> TENANT = new ThreadLocal<>();

    private TenantContext() {
    }

    /**
     * 执行set租户ID操作.
     *
     * @param tenantId 租户标识.
     */
    public static void setTenantId(String tenantId) {
        TENANT.set(tenantId);
    }

    /**
     * 获取租户ID.
     *
     * @return 字符串结果.
     */
    public static String getTenantId() {
        return TENANT.get();
    }

    /**
     * 执行clear操作.
     */
    public static void clear() {
        TENANT.remove();
    }
}
