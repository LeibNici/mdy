package com.jiandaoyun.service.metadata;

import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import java.util.List;

/**
 * 表单元数据服务接口。
 *
 * @author Codex
 * @since 0.1.0
 */
public interface FormService {

    /**
     * 创建并发布表单定义。
     *
     * @param request 创建请求
     * @return 创建后的表单定义
     */
    FormDefinition create(CreateFormRequest request);

    /**
     * 按 ID 查询表单定义。
     *
     * @param formId 表单 ID
     * @return 表单定义
     */
    FormDefinition getById(String formId);

    /**
     * 查询全部表单定义。
     *
     * @return 表单定义列表
     */
    List<FormDefinition> listAll();
}