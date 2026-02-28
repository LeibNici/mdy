package com.jiandaoyun.domain.workflow;

import com.jiandaoyun.common.enums.WorkflowStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作流任务类型.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class WorkflowTask {

    private String id;

    private String instanceId;

    private String assignee;

    private WorkflowStatus status;

    private String comment;
}
