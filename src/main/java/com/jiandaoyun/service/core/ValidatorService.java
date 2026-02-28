package com.jiandaoyun.service.core;

import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;

/**
 * 提交数据校验服务接口.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
public interface ValidatorService {

    /**
     * 校验表单提交数据.
     *
     * @param formDefinition 表单定义.
     * @param data 提交数据.
     */
    void validateSubmission(FormDefinition formDefinition, Map<String, Object> data);
}
