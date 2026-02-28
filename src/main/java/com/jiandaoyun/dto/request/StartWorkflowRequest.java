package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StartWorkflowRequest {

    @NotBlank(message = "formId不能为空")
    private String formId;

    @NotBlank(message = "recordId不能为空")
    private String recordId;

    @NotBlank(message = "申请人不能为空")
    private String applicant;
}