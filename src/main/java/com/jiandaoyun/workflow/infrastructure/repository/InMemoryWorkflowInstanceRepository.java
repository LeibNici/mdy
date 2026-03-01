package com.jiandaoyun.workflow.infrastructure.repository;

import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.workflow.domain.repository.WorkflowInstanceRepository;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * 基于内存的工作流实例仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
public class InMemoryWorkflowInstanceRepository implements WorkflowInstanceRepository {

    private final ConcurrentHashMap<String, WorkflowInstance> store = new ConcurrentHashMap<>();

    /**
     * 保存工作流实例.
     *
     * @param workflowInstance 工作流实例.
     */
    @Override
    public void save(WorkflowInstance workflowInstance) {
        store.put(workflowInstance.getId(), workflowInstance);
    }

    /**
     * 按标识查询工作流实例.
     *
     * @param instanceId 实例标识.
     * @return 工作流实例可选结果.
     */
    @Override
    public Optional<WorkflowInstance> findById(String instanceId) {
        return Optional.ofNullable(store.get(instanceId));
    }
}
