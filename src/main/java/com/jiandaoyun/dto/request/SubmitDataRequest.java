package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import lombok.Data;

/**
 * 閹绘劒姘︾悰銊ュ礋閺佺増宓佺拠閿嬬湴閵? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@Data

public class SubmitDataRequest {

    @NotBlank(message = "formId cannot be blank")

    private String formId;

    @NotNull(message = "data cannot be null")

    private Map<String, Object> data;
}