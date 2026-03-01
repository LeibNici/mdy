package com.jiandaoyun.metadata.application.service;

import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.common.utils.IdGenerator;
import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import com.jiandaoyun.metadata.domain.service.FormDomainService;
import com.jiandaoyun.metadata.domain.event.FormCreatedEvent;
import com.jiandaoyun.metadata.domain.repository.FormDefinitionRepository;
import com.jiandaoyun.shared.kernel.event.DomainEventPublisher;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 表单应用服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class FormApplicationService {

    private final FormDomainService formDomainService;

    private final FormDefinitionRepository formDefinitionRepository;

    private final DomainEventPublisher domainEventPublisher;

    /**
     * 构造表单应用服务实例.
     *
     * @param formDomainService 表单领域服务.
     * @param formDefinitionRepository 表单定义仓储.
     * @param domainEventPublisher 领域事件发布器.
     */
    public FormApplicationService(
        FormDomainService formDomainService,
        FormDefinitionRepository formDefinitionRepository,
        DomainEventPublisher domainEventPublisher
    ) {
        this.formDomainService = formDomainService;
        this.formDefinitionRepository = formDefinitionRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    /**
     * 创建表单定义.
     *
     * @param request 创建表单请求.
     * @return 新建的表单定义.
     */
    public FormDefinition create(CreateFormRequest request) {
        String formId = IdGenerator.nextId();
        FormDefinition formDefinition = formDomainService.buildForm(formId, request, Instant.now());
        formDefinitionRepository.save(formDefinition);
        domainEventPublisher.publish(new FormCreatedEvent(formDefinition.getId(), formDefinition.getName(), Instant.now()));
        return formDefinition;
    }

    /**
     * 根据标识获取表单定义.
     *
     * @param formId 表单标识.
     * @return 表单定义.
     * @throws BusinessException 表单不存在时抛出异常.
     */
    public FormDefinition getById(String formId) {
        return formDefinitionRepository.findById(formId)
            .orElseThrow(() -> new BusinessException("form not found: " + formId));
    }

    /**
     * 查询全部表单定义.
     *
     * @return 表单定义列表.
     */
    public List<FormDefinition> listAll() {
        return formDefinitionRepository.findAll();
    }
}
