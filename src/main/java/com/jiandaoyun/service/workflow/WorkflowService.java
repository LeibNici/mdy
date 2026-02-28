package com.jiandaoyun.service.workflow;

import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;

public interface WorkflowService {
    WorkflowInstance start(StartWorkflowRequest request);

    WorkflowInstance approve(ApproveTaskRequest request);

    WorkflowInstance getById(String instanceId);
}