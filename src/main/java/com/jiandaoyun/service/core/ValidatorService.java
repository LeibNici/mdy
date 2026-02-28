package com.jiandaoyun.service.core;

import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;

/**
 * 数据校验服务接口。
 *
 * @author Codex
 * @since 0.1.0
 */
public interface ValidatorService {

    /**
     * 按表单定义校验提交数据。
     *
     * @param formDefinition 表单定义
     * @param data 提交数据
     */
    void validateSubmission(FormDefinition formDefinition, Map<String, Object> data);
}