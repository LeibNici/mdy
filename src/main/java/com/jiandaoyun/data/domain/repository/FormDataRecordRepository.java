package com.jiandaoyun.data.domain.repository;

import java.util.List;
import java.util.Map;

/**
 * 表单数据记录仓储端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface FormDataRecordRepository {

    /**
     * 保存表单记录.
     *
     * @param formId 表单标识.
     * @param record 记录数据.
     */
    void save(String formId, Map<String, Object> record);

    /**
     * 按表单标识查询记录.
     *
     * @param formId 表单标识.
     * @return 记录列表.
     */
    List<Map<String, Object>> findByFormId(String formId);
}
