package com.jiandaoyun.common.model;

import java.util.List;
import lombok.Data;

/**
 * 分页结果对象.
 *
 * @param <T> 分页记录类型.
 *
 * @author chenming
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
