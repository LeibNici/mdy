package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 启动工作流请求对象.
 *
 * @author chenming
 *
 * @since 2026/02/28
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
