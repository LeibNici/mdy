package com.jiandaoyun.service.workflow;

import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;

/**
 * 工作流服务接口.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */

public interface WorkflowService {

    /**
     * .
     *
     * @param request 请求参数.
     * @return 处理结果.
     */
    WorkflowInstance start(StartWorkflowRequest request);

    /**
     * .
     *
     * @param request 请求参数.
     * @return 处理结果.
     */
    WorkflowInstance approve(ApproveTaskRequest request);

    /**
     * 获取ByID.
     *
     * @param instanceId 主键标识.
     * @return 处理结果.
     */
    WorkflowInstance getById(String instanceId);
}
