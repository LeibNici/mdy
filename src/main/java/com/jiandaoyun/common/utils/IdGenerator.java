package com.jiandaoyun.common.utils;

import java.util.UUID;

/**
 * Generates globally unique IDs for in-memory entities.
 */
public final class IdGenerator {

    private IdGenerator() {
    }

    /**
     * Generates an ID without dash characters.
     *
     * @return generated unique ID
     */
    public static String nextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}