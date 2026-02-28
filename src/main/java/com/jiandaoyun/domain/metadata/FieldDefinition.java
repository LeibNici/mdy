package com.jiandaoyun.domain.metadata;

import com.jiandaoyun.common.constant.FieldType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表单字段元数据定义。
 *
 * @author Codex
 * @since 0.1.0
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