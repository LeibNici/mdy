package com.jiandaoyun.service.workflow;

import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;

/**
 * 工作流编排服务接口。
 *
 * @author Codex
 * @since 0.1.0
 */
public interface WorkflowService {

    /**
     * 启动流程实例。
     *
     * @param request 启动请求
     * @return 流程实例
     */
    WorkflowInstance start(StartWorkflowRequest request);

    /**
     * 审批或驳回流程任务。
     *
     * @param request 审批请求
     * @return 更新后的流程实例
     */
    WorkflowInstance approve(ApproveTaskRequest request);

    /**
     * 按 ID 查询流程实例。
     *
     * @param instanceId 流程实例 ID
     * @return 流程实例
     */
    WorkflowInstance getById(String instanceId);
}