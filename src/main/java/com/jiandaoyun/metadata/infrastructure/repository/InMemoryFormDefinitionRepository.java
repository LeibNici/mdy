package com.jiandaoyun.metadata.infrastructure.repository;

import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.metadata.domain.repository.FormDefinitionRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * 基于内存的表单定义仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
public class InMemoryFormDefinitionRepository implements FormDefinitionRepository {

    private final ConcurrentHashMap<String, FormDefinition> store = new ConcurrentHashMap<>();

    /**
     * 保存表单定义.
     *
     * @param formDefinition 表单定义.
     */
    @Override
    public void save(FormDefinition formDefinition) {
        store.put(formDefinition.getId(), formDefinition);
    }

    /**
     * 按标识查询表单定义.
     *
     * @param formId 表单标识.
     * @return 表单定义可选结果.
     */
    @Override
    public Optional<FormDefinition> findById(String formId) {
        return Optional.ofNullable(store.get(formId));
    }

    /**
     * 查询全部表单定义.
     *
     * @return 表单定义列表.
     */
    @Override
    public List<FormDefinition> findAll() {
        return store.values().stream().toList();
    }
}
