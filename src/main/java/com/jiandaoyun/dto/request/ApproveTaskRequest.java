package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Request payload for approving or rejecting a workflow task.
 */
@Data
public class ApproveTaskRequest {

    @NotBlank(message = "instanceId cannot be blank")
    private String instanceId;

    @NotBlank(message = "taskId cannot be blank")
    private String taskId;

    @NotBlank(message = "approver cannot be blank")
    private String approver;

    @NotNull(message = "approved cannot be null")
    private Boolean approved;

    private String comment;
}