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
 * 瀹搞儰缍斿ù浣瑰付閸掕泛娅掗妴? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/workflow")

public class WorkflowController {

    private final WorkflowService workflowService;

    /**
     * 閺嬪嫰鈧姴鍤遍弫鑸偓?     *
 *
 * @param workflowService 瀹搞儰缍斿ù浣规箛閸? */
    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * 閸氼垰濮╁ù浣衡柤鐎圭偘绶ラ妴?     *
 *
 * @param request 閸氼垰濮╃拠閿嬬湴
 *
 * @return 閸掓稑缂撻崥搴ｆ畱濞翠胶鈻肩€圭偘绶?
     */
    @PostMapping("/start")
    public ApiResponse<WorkflowInstance> start(@Valid @RequestBody StartWorkflowRequest request) {
        return ApiResponse.ok(workflowService.start(request));
    }

    /**
     * 鐎光剝澹掕ぐ鎾冲濞翠胶鈻兼禒璇插閵?     *
 *
 * @param request 鐎光剝澹掔拠閿嬬湴
 *
 * @return 閺囧瓨鏌婇崥搴ｆ畱濞翠胶鈻肩€圭偘绶?
     */
    @PostMapping("/approve")
    public ApiResponse<WorkflowInstance> approve(@Valid @RequestBody ApproveTaskRequest request) {
        return ApiResponse.ok(workflowService.approve(request));
    }

    /**
     * 閹稿鐤勬笟?ID 閺屻儴顕楀ù浣衡柤閵?     *
 *
 * @param instanceId 濞翠胶鈻肩€圭偘绶?ID
 *
 * @return 濞翠胶鈻肩€圭偘绶?
     */
    @GetMapping("/{instanceId}")
    public ApiResponse<WorkflowInstance> getById(@PathVariable String instanceId) {
        return ApiResponse.ok(workflowService.getById(instanceId));
    }
}