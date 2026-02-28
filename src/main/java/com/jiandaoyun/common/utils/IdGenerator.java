package com.jiandaoyun.common.utils;

import java.util.UUID;

/**
 * IDGenerator类型.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */

public final class IdGenerator {

    private IdGenerator() {
    }

    /**
     * 执行nextID操作.
     *
     * @return 字符串结果.
     */
    public static String nextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
