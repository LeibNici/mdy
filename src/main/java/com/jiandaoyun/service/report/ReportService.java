package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;

/**
 * Report aggregation service.
 */
public interface ReportService {

    /**
     * Gets summary statistics by form ID.
     *
     * @param formId form ID
     * @return summary response
     */
    ReportSummaryResponse getFormSummary(String formId);
}