package com.jiandaoyun.service.workflow;

import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;
import com.jiandaoyun.workflow.application.service.WorkflowApplicationService;
import org.springframework.stereotype.Service;

/**
 * 工作流服务适配实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    private final WorkflowApplicationService workflowApplicationService;

    /**
     * 构造工作流服务适配实例.
     *
     * @param workflowApplicationService 工作流应用服务.
     */
    public WorkflowServiceImpl(WorkflowApplicationService workflowApplicationService) {
        this.workflowApplicationService = workflowApplicationService;
    }

    /**
     * 启动工作流实例.
     *
     * @param request 启动流程请求.
     * @return 新建的工作流实例.
     */
    @Override
    public WorkflowInstance start(StartWorkflowRequest request) {
        return workflowApplicationService.start(request);
    }

    /**
     * 审批工作流任务.
     *
     * @param request 审批任务请求.
     * @return 审批后的工作流实例.
     */
    @Override
    public WorkflowInstance approve(ApproveTaskRequest request) {
        return workflowApplicationService.approve(request);
    }

    /**
     * 根据标识查询工作流实例.
     *
     * @param instanceId 工作流实例标识.
     * @return 工作流实例.
     */
    @Override
    public WorkflowInstance getById(String instanceId) {
        return workflowApplicationService.getById(instanceId);
    }
}
