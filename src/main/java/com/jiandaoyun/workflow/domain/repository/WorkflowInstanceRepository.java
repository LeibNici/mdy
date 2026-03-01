package com.jiandaoyun.workflow.domain.repository;

import com.jiandaoyun.domain.workflow.WorkflowInstance;
import java.util.Optional;

/**
 * 工作流实例仓储端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface WorkflowInstanceRepository {

    /**
     * 保存工作流实例.
     *
     * @param workflowInstance 工作流实例.
     */
    void save(WorkflowInstance workflowInstance);

    /**
     * 按标识查询工作流实例.
     *
     * @param instanceId 实例标识.
     * @return 工作流实例可选结果.
     */
    Optional<WorkflowInstance> findById(String instanceId);
}
