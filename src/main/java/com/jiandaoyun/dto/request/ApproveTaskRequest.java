package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 鐎光剝澹掓禒璇插鐠囬攱鐪伴妴? *
 *
 * @author Codex
 *
 * @since 2026/02/28
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