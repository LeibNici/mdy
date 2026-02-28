package com.jiandaoyun.service.data;

import com.jiandaoyun.dto.request.SubmitDataRequest;
import java.util.List;
import java.util.Map;

/**
 * Dynamic data service for form records.
 */
public interface DataService {

    /**
     * Submits one record to target form.
     *
     * @param request submit request
     * @return created record
     */
    Map<String, Object> submit(SubmitDataRequest request);

    /**
     * Lists all records under one form.
     *
     * @param formId form ID
     * @return records list
     */
    List<Map<String, Object>> listByFormId(String formId);
}