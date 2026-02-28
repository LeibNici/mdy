package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;

public interface ReportService {
    ReportSummaryResponse getFormSummary(String formId);
}