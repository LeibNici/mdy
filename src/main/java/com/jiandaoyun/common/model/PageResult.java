package com.jiandaoyun.common.model;

import java.util.List;
import lombok.Data;

/**
 * 鍒嗛〉鍝嶅簲瀵硅薄.
 *
 * @param <T> 璁板綍绫诲瀷
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@Data
public class PageResult<T> {

    private List<T> records;

    private long total;

    private int page;

    private int size;
}