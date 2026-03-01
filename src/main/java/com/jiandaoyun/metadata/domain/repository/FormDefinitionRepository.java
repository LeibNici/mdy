package com.jiandaoyun.metadata.domain.repository;

import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.List;
import java.util.Optional;

/**
 * 表单定义仓储端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface FormDefinitionRepository {

    /**
     * 保存表单定义.
     *
     * @param formDefinition 表单定义.
     */
    void save(FormDefinition formDefinition);

    /**
     * 按标识查询表单定义.
     *
     * @param formId 表单标识.
     * @return 表单定义可选结果.
     */
    Optional<FormDefinition> findById(String formId);

    /**
     * 查询全部表单定义.
     *
     * @return 表单定义列表.
     */
    List<FormDefinition> findAll();
}
