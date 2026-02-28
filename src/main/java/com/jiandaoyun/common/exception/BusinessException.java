package com.jiandaoyun.common.exception;

/**
 * 涓氬姟寮傚父瀹氫箟.
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
public class BusinessException extends RuntimeException {

    /**
     * 鏋勯€犱笟鍔″紓甯?
     *
 * @param message 寮傚父淇℃伅
     */
    public BusinessException(String message) {
        super(message);
    }
}