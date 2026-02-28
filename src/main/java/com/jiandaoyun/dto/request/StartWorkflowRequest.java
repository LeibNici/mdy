package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Request payload for starting a workflow instance.
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