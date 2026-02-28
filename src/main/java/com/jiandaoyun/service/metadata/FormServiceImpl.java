package com.jiandaoyun.service.metadata;

import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.common.utils.IdGenerator;
import com.jiandaoyun.core.engine.FormEngine;
import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * 表单服务实现.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Service
public class FormServiceImpl implements FormService {

    private final FormEngine formEngine;

    private final ConcurrentHashMap<String, FormDefinition> formStore = new ConcurrentHashMap<>();

    /**
     * 构造表单服务实例.
     *
     * @param formEngine 表单引擎实例.
     */
    public FormServiceImpl(FormEngine formEngine) {
        this.formEngine = formEngine;
    }

    /**
     * 创建表单定义.
     *
     * @param request 创建表单请求.
     * @return 新建的表单定义.
     */
    @Override
    public FormDefinition create(CreateFormRequest request) {
        String formId = IdGenerator.nextId();
        FormDefinition form = formEngine.buildForm(formId, request, Instant.now());
        formStore.put(formId, form);
        return form;
    }

    /**
     * 根据标识获取表单定义.
     *
     * @param formId 表单标识.
     * @return 表单定义.
     */
    @Override
    public FormDefinition getById(String formId) {
        FormDefinition form = formStore.get(formId);
        if (form == null) {
            throw new BusinessException("form not found: " + formId);
        }
        return form;
    }

    /**
     * 查询全部表单定义.
     *
     * @return 表单定义列表.
     */
    @Override
    public List<FormDefinition> listAll() {
        return formStore.values().stream().toList();
    }
}
