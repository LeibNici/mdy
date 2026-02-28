package com.jiandaoyun.service.workflow;

import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;

/**
 * Workflow orchestration service.
 */
public interface WorkflowService {

    /**
     * Starts a workflow instance for one record.
     *
     * @param request start request
     * @return created workflow instance
     */
    WorkflowInstance start(StartWorkflowRequest request);

    /**
     * Approves or rejects current task.
     *
     * @param request approve request
     * @return updated workflow instance
     */
    WorkflowInstance approve(ApproveTaskRequest request);

    /**
     * Gets workflow instance by ID.
     *
     * @param instanceId workflow instance ID
     * @return workflow instance
     */
    WorkflowInstance getById(String instanceId);
}