package com.jiandaoyun.data.application.service;

import com.jiandaoyun.common.utils.IdGenerator;
import com.jiandaoyun.core.engine.FormEngine;
import com.jiandaoyun.data.domain.event.DataSubmittedEvent;
import com.jiandaoyun.data.domain.repository.FormDataRecordRepository;
import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.SubmitDataRequest;
import com.jiandaoyun.metadata.application.service.FormApplicationService;
import com.jiandaoyun.shared.kernel.event.DomainEventPublisher;
import com.jiandaoyun.shared.kernel.validation.ValidatorService;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 数据应用服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class DataApplicationService {

    private final FormApplicationService formApplicationService;

    private final FormEngine formEngine;

    private final ValidatorService validatorService;

    private final FormDataRecordRepository formDataRecordRepository;

    private final DomainEventPublisher domainEventPublisher;

    /**
     * 构造数据应用服务实例.
     *
     * @param formApplicationService 表单应用服务.
     * @param formEngine 表单引擎.
     * @param validatorService 校验服务.
     * @param formDataRecordRepository 表单数据记录仓储.
     * @param domainEventPublisher 领域事件发布器.
     */
    public DataApplicationService(
        FormApplicationService formApplicationService,
        FormEngine formEngine,
        ValidatorService validatorService,
        FormDataRecordRepository formDataRecordRepository,
        DomainEventPublisher domainEventPublisher
    ) {
        this.formApplicationService = formApplicationService;
        this.formEngine = formEngine;
        this.validatorService = validatorService;
        this.formDataRecordRepository = formDataRecordRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    /**
     * 提交表单数据.
     *
     * @param request 数据提交请求.
     * @return 持久化后的记录.
     */
    public Map<String, Object> submit(SubmitDataRequest request) {
        FormDefinition form = formApplicationService.getById(request.getFormId());
        Map<String, Object> normalizedData = formEngine.normalizeSubmission(form, request.getData());
        validatorService.validateSubmission(form, normalizedData);

        Map<String, Object> record = new HashMap<>(normalizedData);
        String recordId = IdGenerator.nextId();
        record.put("id", recordId);
        record.put("formId", request.getFormId());
        record.put("createdAt", Instant.now().toString());

        formDataRecordRepository.save(request.getFormId(), record);
        domainEventPublisher.publish(new DataSubmittedEvent(request.getFormId(), recordId, Instant.now()));
        return record;
    }

    /**
     * 查询表单数据列表.
     *
     * @param formId 表单标识.
     * @return 记录列表.
     */
    public List<Map<String, Object>> listByFormId(String formId) {
        return formDataRecordRepository.findByFormId(formId);
    }
}
