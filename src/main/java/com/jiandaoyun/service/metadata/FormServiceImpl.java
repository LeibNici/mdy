package com.jiandaoyun.service.metadata;

import com.jiandaoyun.common.enums.FormStatus;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.common.utils.IdGenerator;
import com.jiandaoyun.domain.metadata.FieldDefinition;
import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl implements FormService {

    private final ConcurrentHashMap<String, FormDefinition> formStore = new ConcurrentHashMap<>();

    @Override
    public FormDefinition create(CreateFormRequest request) {
        String formId = IdGenerator.nextId();
        List<FieldDefinition> fields = request.getFields().stream()
            .map(field -> FieldDefinition.builder()
                .key(field.getKey())
                .label(field.getLabel())
                .type(field.getType())
                .required(field.isRequired())
                .build())
            .toList();

        Instant now = Instant.now();
        FormDefinition form = FormDefinition.builder()
            .id(formId)
            .name(request.getName())
            .description(request.getDescription())
            .status(FormStatus.PUBLISHED)
            .version(1)
            .fields(fields)
            .createdAt(now)
            .updatedAt(now)
            .build();

        formStore.put(formId, form);
        return form;
    }

    @Override
    public FormDefinition getById(String formId) {
        FormDefinition form = formStore.get(formId);
        if (form == null) {
            throw new BusinessException("表单不存在: " + formId);
        }
        return form;
    }

    @Override
    public List<FormDefinition> listAll() {
        return formStore.values().stream().toList();
    }
}