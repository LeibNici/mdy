package com.jiandaoyun.dto.request;

import com.jiandaoyun.common.constant.FieldType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class CreateFormRequest {

    @NotBlank(message = "表单名称不能为空")
    private String name;

    private String description;

    @Valid
    @NotEmpty(message = "字段列表不能为空")
    private List<FieldRequest> fields;

    @Data
    public static class FieldRequest {
        @NotBlank(message = "字段key不能为空")
        private String key;

        @NotBlank(message = "字段标题不能为空")
        private String label;

        @NotNull(message = "字段类型不能为空")
        private FieldType type;

        private boolean required;
    }
}