package com.jiandaoyun.service.metadata;

import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import com.jiandaoyun.metadata.application.service.FormApplicationService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 表单服务适配实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class FormServiceImpl implements FormService {

    private final FormApplicationService formApplicationService;

    /**
     * 构造表单服务适配实例.
     *
     * @param formApplicationService 表单应用服务.
     */
    public FormServiceImpl(FormApplicationService formApplicationService) {
        this.formApplicationService = formApplicationService;
    }

    /**
     * 创建表单定义.
     *
     * @param request 创建表单请求.
     * @return 新建的表单定义.
     */
    @Override
    public FormDefinition create(CreateFormRequest request) {
        return formApplicationService.create(request);
    }

    /**
     * 根据标识获取表单定义.
     *
     * @param formId 表单标识.
     * @return 表单定义.
     */
    @Override
    public FormDefinition getById(String formId) {
        return formApplicationService.getById(formId);
    }

    /**
     * 查询全部表单定义.
     *
     * @return 表单定义列表.
     */
    @Override
    public List<FormDefinition> listAll() {
        return formApplicationService.listAll();
    }
}
