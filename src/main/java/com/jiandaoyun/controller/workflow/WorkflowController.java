package com.jiandaoyun.controller.workflow;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;
import com.jiandaoyun.service.workflow.WorkflowService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工作流控制器.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    /**
     * 构造工作流控制器实例.
     *
     * @param workflowService 工作流服务实例.
     */
    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * 启动工作流实例.
     *
     * @param request 启动流程请求.
     * @return 新建的工作流实例.
     */
    @PostMapping("/start")
    public ApiResponse<WorkflowInstance> start(@Valid @RequestBody StartWorkflowRequest request) {
        return ApiResponse.ok(workflowService.start(request));
    }

    /**
     * 审批流程任务.
     *
     * @param request 审批任务请求.
     * @return 审批后的工作流实例.
     */
    @PostMapping("/approve")
    public ApiResponse<WorkflowInstance> approve(@Valid @RequestBody ApproveTaskRequest request) {
        return ApiResponse.ok(workflowService.approve(request));
    }

    /**
     * 根据标识查询工作流实例.
     *
     * @param instanceId 工作流实例标识.
     * @return 工作流实例详情.
     */
    @GetMapping("/{instanceId}")
    public ApiResponse<WorkflowInstance> getById(@PathVariable String instanceId) {
        return ApiResponse.ok(workflowService.getById(instanceId));
    }
}
