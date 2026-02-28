package com.jiandaoyun.service.workflow;

import com.jiandaoyun.common.enums.WorkflowStatus;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.common.utils.IdGenerator;
import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * In-memory implementation of {@link WorkflowService}.
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    private final ConcurrentHashMap<String, WorkflowInstance> instanceStore = new ConcurrentHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
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
        instanceStore.put(instance.getId(), instance);
        return instance;
    }

    /**
     * {@inheritDoc}
     *
     * @throws BusinessException when task ID mismatch occurs
     */
    @Override
    public WorkflowInstance approve(ApproveTaskRequest request) {
        WorkflowInstance instance = getById(request.getInstanceId());
        if (!instance.getCurrentTaskId().equals(request.getTaskId())) {
            throw new BusinessException("task ID mismatch");
        }
        instance.setStatus(Boolean.TRUE.equals(request.getApproved()) ? WorkflowStatus.APPROVED : WorkflowStatus.REJECTED);
        instance.setUpdatedAt(Instant.now());
        return instance;
    }

    /**
     * {@inheritDoc}
     *
     * @throws BusinessException when instance does not exist
     */
    @Override
    public WorkflowInstance getById(String instanceId) {
        WorkflowInstance instance = instanceStore.get(instanceId);
        if (instance == null) {
            throw new BusinessException("workflow instance not found: " + instanceId);
        }
        return instance;
    }
}