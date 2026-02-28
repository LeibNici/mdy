package com.jiandaoyun.common.utils;

import java.util.UUID;

/**
 * 閻㈢喐鍨氶崗銊ョ湰閸烆垯绔撮弽鍥槕缁楋负鈧? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */

public final class IdGenerator {

    private IdGenerator() {
    }

    /**
     * 閻㈢喐鍨氭稉宥呭瘶閸氼偅铆閺夌姷娈戦梾蹇旀簚 ID閵?     *
 *
 * @return 閸烆垯绔?ID
     */
    public static String nextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}