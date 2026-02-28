package com.jiandaoyun.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 错误响应对象.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    private int code;

    private String message;

    private long timestamp;
}
