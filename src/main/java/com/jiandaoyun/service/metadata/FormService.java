package com.jiandaoyun.service.metadata;

import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import java.util.List;

public interface FormService {
    FormDefinition create(CreateFormRequest request);

    FormDefinition getById(String formId);

    List<FormDefinition> listAll();
}