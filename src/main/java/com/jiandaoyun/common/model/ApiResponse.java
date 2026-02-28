package com.jiandaoyun.common.model;

import lombok.Data;

/**
 * 接口响应对象.
 *
 * @param <T> 响应数据类型.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Data
public class ApiResponse<T> {

    private int code;

    private String message;

    private T data;

    private long timestamp = System.currentTimeMillis();

    /**
     * 构建成功响应.
     *
     * @param <T> 响应数据类型.
     * @param data 响应数据.
     * @return 统一响应结果.
     */
    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    /**
     * 构建失败响应.
     *
     * @param <T> 响应数据类型.
     * @param code 业务状态码.
     * @param message 响应消息.
     * @return 统一响应结果.
     */
    public static <T> ApiResponse<T> fail(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
