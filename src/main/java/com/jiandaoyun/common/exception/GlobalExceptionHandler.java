package com.jiandaoyun.common.exception;

import com.jiandaoyun.common.model.ApiResponse;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 閸忋劌鐪鍌氱埗婢跺嫮鎮婇崳顭掔礉鐏忓棗绱撶敮鍝ョ埠娑撯偓鏉烆剚宕叉稉?API 閸濆秴绨查妴? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@RestControllerAdvice

public class GlobalExceptionHandler {

    /**
     * 婢跺嫮鎮婃稉姘瀵倸鐖堕妴?     *
 *
 * @param ex 娑撴艾濮熷鍌氱埗
 *
 * @return 400 閸濆秴绨?
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.fail(400, ex.getMessage()));
    }

    /**
     * 婢跺嫮鎮婇崣鍌涙殶閺嶏繝鐛欏鍌氱埗閵?     *
 *
 * @param ex 閸欏倹鏆熼弽锟犵崣瀵倸鐖?
 *
 * @return 400 閸濆秴绨?
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValid(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.fail(400, message));
    }

    /**
     * 婢跺嫮鎮婇張顏呭礋閼惧嘲绱撶敮鎼炩偓?     *
 *
 * @param ex 閺堫亞鐓″鍌氱埗
 *
 * @return 500 閸濆秴绨?
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.fail(500, ex.getMessage()));
    }
}