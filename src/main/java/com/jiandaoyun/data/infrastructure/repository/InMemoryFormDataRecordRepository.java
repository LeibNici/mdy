package com.jiandaoyun.data.infrastructure.repository;

import com.jiandaoyun.data.domain.repository.FormDataRecordRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * 基于内存的表单数据记录仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
public class InMemoryFormDataRecordRepository implements FormDataRecordRepository {

    private final ConcurrentHashMap<String, List<Map<String, Object>>> store = new ConcurrentHashMap<>();

    /**
     * 保存表单记录.
     *
     * @param formId 表单标识.
     * @param record 记录数据.
     */
    @Override
    public void save(String formId, Map<String, Object> record) {
        store.computeIfAbsent(formId, key -> new ArrayList<>()).add(record);
    }

    /**
     * 按表单标识查询记录.
     *
     * @param formId 表单标识.
     * @return 记录列表.
     */
    @Override
    public List<Map<String, Object>> findByFormId(String formId) {
        return store.getOrDefault(formId, List.of());
    }
}
