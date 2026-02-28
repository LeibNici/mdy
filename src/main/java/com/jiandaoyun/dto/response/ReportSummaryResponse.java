package com.jiandaoyun.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportSummaryResponse {
    private String formId;
    private long totalRecords;
}