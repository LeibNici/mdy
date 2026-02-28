package com.jiandaoyun.service.metadata;

import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import java.util.List;

/**
 * 表单服务接口.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */

public interface FormService {

    /**
     * .
     *
     * @param request 请求参数.
     * @return 处理结果.
     */
    FormDefinition create(CreateFormRequest request);

    /**
     * 获取ByID.
     *
     * @param formId 表单标识.
     * @return 处理结果.
     */
    FormDefinition getById(String formId);

    /**
     * 查询.
     *
     * @return 结果列表.
     */
    List<FormDefinition> listAll();
}
