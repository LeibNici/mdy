package com.jiandaoyun.workflow.application.service;

import com.jiandaoyun.common.enums.WorkflowStatus;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.common.utils.IdGenerator;
import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;
import com.jiandaoyun.workflow.domain.repository.WorkflowInstanceRepository;
import java.time.Instant;
import org.springframework.stereotype.Service;

/**
 * 工作流应用服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class WorkflowApplicationService {

    private final WorkflowInstanceRepository workflowInstanceRepository;

    /**
     * 构造工作流应用服务实例.
     *
     * @param workflowInstanceRepository 工作流实例仓储.
     */
    public WorkflowApplicationService(WorkflowInstanceRepository workflowInstanceRepository) {
        this.workflowInstanceRepository = workflowInstanceRepository;
    }

    /**
     * 启动工作流实例.
     *
     * @param request 启动流程请求.
     * @return 新建的工作流实例.
     */
    public WorkflowInstance start(StartWorkflowRequest request) {
        Instant now = Instant.now();
        WorkflowInstance instance = WorkflowInstance.builder()
            .id(IdGenerator.nextId())
            .formId(request.getFormId())
            .recordId(request.getRecordId())
            .applicant(request.getApplicant())
            .currentTaskId(IdGenerator.nextId())
            .status(WorkflowStatus.PENDING)
            .createdAt(now)
            .updatedAt(now)
            .build();
        workflowInstanceRepository.save(instance);
        return instance;
    }

    /**
     * 审批工作流实例.
     *
     * @param request 审批任务请求.
     * @return 审批后的工作流实例.
     * @throws BusinessException 当前任务标识不匹配时抛出异常.
     */
    public WorkflowInstance approve(ApproveTaskRequest request) {
        WorkflowInstance instance = getById(request.getInstanceId());
        if (!instance.getCurrentTaskId().equals(request.getTaskId())) {
            throw new BusinessException("task ID mismatch");
        }
        instance.setStatus(Boolean.TRUE.equals(request.getApproved()) ? WorkflowStatus.APPROVED : WorkflowStatus.REJECTED);
        instance.setUpdatedAt(Instant.now());
        workflowInstanceRepository.save(instance);
        return instance;
    }

    /**
     * 根据标识获取工作流实例.
     *
     * @param instanceId 实例标识.
     * @return 工作流实例.
     * @throws BusinessException 工作流实例不存在时抛出异常.
     */
    public WorkflowInstance getById(String instanceId) {
        return workflowInstanceRepository.findById(instanceId)
            .orElseThrow(() -> new BusinessException("workflow instance not found: " + instanceId));
    }
}
