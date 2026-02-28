package com.jiandaoyun.common.model;

import lombok.Data;

/**
 * Unified REST response envelope.
 *
 * @param <T> payload type
 */
@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp = System.currentTimeMillis();

    /**
     * Creates a successful response.
     *
     * @param data business payload
     * @param <T> payload type
     * @return success response with default code 200
     */
    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    /**
     * Creates a failed response.
     *
     * @param code business error code
     * @param message error message
     * @param <T> payload type
     * @return error response
     */
    public static <T> ApiResponse<T> fail(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}