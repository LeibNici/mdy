package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import lombok.Data;

/**
 * Request payload for submitting one form record.
 */
@Data
public class SubmitDataRequest {

    @NotBlank(message = "formId cannot be blank")
    private String formId;

    @NotNull(message = "data cannot be null")
    private Map<String, Object> data;
}