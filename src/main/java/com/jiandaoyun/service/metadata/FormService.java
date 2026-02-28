package com.jiandaoyun.service.metadata;

import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import java.util.List;

/**
 * Form metadata service.
 */
public interface FormService {

    /**
     * Creates and publishes a new form definition.
     *
     * @param request create form request
     * @return created form definition
     */
    FormDefinition create(CreateFormRequest request);

    /**
     * Gets one form by ID.
     *
     * @param formId form ID
     * @return matched form definition
     */
    FormDefinition getById(String formId);

    /**
     * Lists all form definitions.
     *
     * @return all forms
     */
    List<FormDefinition> listAll();
}