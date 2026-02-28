package com.jiandaoyun.dto.request;

import com.jiandaoyun.common.constant.FieldType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

/**
 * 閸掓稑缂撶悰銊ュ礋鐎规矮绠熺拠閿嬬湴閵? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@Data

public class CreateFormRequest {

    @NotBlank(message = "form name cannot be blank")

    private String name;

    private String description;

    @Valid
    @NotEmpty(message = "fields cannot be empty")

    private List<FieldRequest> fields;

    /**
     * 閸掓稑缂撶€涙顔岀€规矮绠熺拠閿嬬湴閵?     *
 *
 * @author Codex
 *
 * @since 2026/02/28
     */
    @Data

    public static class FieldRequest {
        @NotBlank(message = "field key cannot be blank")

        private String key;

        @NotBlank(message = "field label cannot be blank")

        private String label;

        @NotNull(message = "field type cannot be null")

        private FieldType type;

        private boolean required;
    }
}