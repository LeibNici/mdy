package com.jiandaoyun.common.exception;

/**
 * 业务异常类型.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
public class BusinessException extends RuntimeException {

    /**
     * 构造业务异常实例.
     *
     * @param message 异常消息.
     */
    public BusinessException(String message) {
        super(message);
    }
}
