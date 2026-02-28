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
 * REST controller for workflow actions.
 */
@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    /**
     * Creates controller instance.
     *
     * @param workflowService workflow service
     */
    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Starts one workflow instance.
     *
     * @param request start request
     * @return created workflow instance
     */
    @PostMapping("/start")
    public ApiResponse<WorkflowInstance> start(@Valid @RequestBody StartWorkflowRequest request) {
        return ApiResponse.ok(workflowService.start(request));
    }

    /**
     * Approves or rejects workflow task.
     *
     * @param request approve request
     * @return updated workflow instance
     */
    @PostMapping("/approve")
    public ApiResponse<WorkflowInstance> approve(@Valid @RequestBody ApproveTaskRequest request) {
        return ApiResponse.ok(workflowService.approve(request));
    }

    /**
     * Gets workflow instance by ID.
     *
     * @param instanceId instance ID
     * @return workflow instance
     */
    @GetMapping("/{instanceId}")
    public ApiResponse<WorkflowInstance> getById(@PathVariable String instanceId) {
        return ApiResponse.ok(workflowService.getById(instanceId));
    }
}