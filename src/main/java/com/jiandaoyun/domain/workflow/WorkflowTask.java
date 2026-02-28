package com.jiandaoyun.domain.workflow;

import com.jiandaoyun.common.enums.WorkflowStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流程实例中的任务节点。
 *
 * @author Codex
 * @since 0.1.0
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