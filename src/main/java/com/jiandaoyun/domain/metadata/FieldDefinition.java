package com.jiandaoyun.domain.metadata;

import com.jiandaoyun.common.constant.FieldType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 鐞涖劌宕熺€涙顔岄崗鍐╂殶閹诡喖鐣炬稊澶堚偓? *
 *
 * @author Codex
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