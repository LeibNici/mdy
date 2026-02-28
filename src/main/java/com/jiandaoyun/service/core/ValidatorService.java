package com.jiandaoyun.service.core;

import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;

public interface ValidatorService {
    void validateSubmission(FormDefinition formDefinition, Map<String, Object> data);
}