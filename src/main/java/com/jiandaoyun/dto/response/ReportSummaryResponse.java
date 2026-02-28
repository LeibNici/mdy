package com.jiandaoyun.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Report summary response for a form.
 */
@Data
@Builder
public class ReportSummaryResponse {
    private String formId;
    private long totalRecords;
}