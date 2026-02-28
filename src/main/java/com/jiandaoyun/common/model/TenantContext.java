package com.jiandaoyun.common.model;

/**
 * 绉熸埛涓婁笅鏂囧鍣?
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
public final class TenantContext {

    private static final ThreadLocal<String> TENANT = new ThreadLocal<>();

    private TenantContext() {
    }

    /**
     * 璁剧疆褰撳墠绉熸埛.
     *
 * @param tenantId 绉熸埛 ID
     */
    public static void setTenantId(String tenantId) {
        TENANT.set(tenantId);
    }

    /**
     * 鑾峰彇褰撳墠绉熸埛.
     *
 * @return 绉熸埛 ID
     */
    public static String getTenantId() {
        return TENANT.get();
    }

    /**
     * 娓呯悊绉熸埛涓婁笅鏂?
     */
    public static void clear() {
        TENANT.remove();
    }
}