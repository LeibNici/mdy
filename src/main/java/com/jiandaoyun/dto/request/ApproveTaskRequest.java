package com.jiandaoyun.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApproveTaskRequest {

    @NotBlank(message = "instanceId不能为空")
    private String instanceId;

    @NotBlank(message = "taskId不能为空")
    private String taskId;

    @NotBlank(message = "审批人不能为空")
    private String approver;

    @NotNull(message = "approved不能为空")
    private Boolean approved;

    private String comment;
}