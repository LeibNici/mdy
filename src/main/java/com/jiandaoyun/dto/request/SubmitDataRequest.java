package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import lombok.Data;

@Data
public class SubmitDataRequest {

    @NotBlank(message = "formId不能为空")
    private String formId;

    @NotNull(message = "data不能为空")
    private Map<String, Object> data;
}