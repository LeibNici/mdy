package com.jiandaoyun.service.core;

import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;

/**
 * Validates submitted form data against form metadata.
 */
public interface ValidatorService {

    /**
     * Validates submission payload.
     *
     * @param formDefinition target form definition
     * @param data submitted key-value data
     */
    void validateSubmission(FormDefinition formDefinition, Map<String, Object> data);
}