package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 启动流程请求。
 *
 * @author Codex
 * @since 0.1.0
 */
@Data
public class StartWorkflowRequest {

    @NotBlank(message = "formId cannot be blank")
    private String formId;

    @NotBlank(message = "recordId cannot be blank")
    private String recordId;

    @NotBlank(message = "applicant cannot be blank")
    private String applicant;
}