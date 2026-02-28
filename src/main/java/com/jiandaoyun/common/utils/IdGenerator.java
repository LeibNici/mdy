package com.jiandaoyun.common.utils;

import java.util.UUID;

/**
 * 生成全局唯一标识符。
 *
 * @author Codex
 * @since 0.1.0
 */
public final class IdGenerator {

    private IdGenerator() {
    }

    /**
     * 生成不包含横杠的随机 ID。
     *
     * @return 唯一 ID
     */
    public static String nextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}