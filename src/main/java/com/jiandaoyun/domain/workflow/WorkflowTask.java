package com.jiandaoyun.domain.workflow;

import com.jiandaoyun.common.enums.WorkflowStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 濞翠胶鈻肩€圭偘绶ユ稉顓犳畱娴犺濮熼懞鍌滃仯閵? *
 *
 * @author Codex
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