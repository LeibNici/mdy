package com.jiandaoyun.common.model;

import lombok.Data;

/**
 * 统一的接口响应包装对象。
 *
 * @param <T> 业务数据类型
 * @author Codex
 * @since 0.1.0
 */
@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp = System.currentTimeMillis();

    /**
     * 构建成功响应。
     *
     * @param data 业务数据
     * @param <T> 业务数据类型
     * @return 成功响应对象
     */
    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    /**
     * 构建失败响应。
     *
     * @param code 错误码
     * @param message 错误信息
     * @param <T> 业务数据类型
     * @return 失败响应对象
     */
    public static <T> ApiResponse<T> fail(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}