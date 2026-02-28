package com.jiandaoyun.domain.metadata;

import com.jiandaoyun.common.constant.FieldType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段定义类型.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FieldDefinition {

    private String key;

    private String label;

    private FieldType type;

    private boolean required;
}
