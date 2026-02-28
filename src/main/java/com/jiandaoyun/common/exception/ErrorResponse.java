package com.jiandaoyun.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 寮傚父鍝嶅簲浣?
 *
 * @author Codex
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