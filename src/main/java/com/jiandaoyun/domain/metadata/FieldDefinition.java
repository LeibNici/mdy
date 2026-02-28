package com.jiandaoyun.domain.metadata;

import com.jiandaoyun.common.constant.FieldType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Field metadata definition inside a form.
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