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
 * 工作流控制器。
 *
 * @author Codex
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    /**
     * 构造函数。
     *
     * @param workflowService 工作流服务
     */
    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * 启动流程实例。
     *
     * @param request 启动请求
     * @return 创建后的流程实例
     */
    @PostMapping("/start")
    public ApiResponse<WorkflowInstance> start(@Valid @RequestBody StartWorkflowRequest request) {
        return ApiResponse.ok(workflowService.start(request));
    }

    /**
     * 审批当前流程任务。
     *
     * @param request 审批请求
     * @return 更新后的流程实例
     */
    @PostMapping("/approve")
    public ApiResponse<WorkflowInstance> approve(@Valid @RequestBody ApproveTaskRequest request) {
        return ApiResponse.ok(workflowService.approve(request));
    }

    /**
     * 按实例 ID 查询流程。
     *
     * @param instanceId 流程实例 ID
     * @return 流程实例
     */
    @GetMapping("/{instanceId}")
    public ApiResponse<WorkflowInstance> getById(@PathVariable String instanceId) {
        return ApiResponse.ok(workflowService.getById(instanceId));
    }
}