package com.jiandaoyun.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * 表单汇总报表响应。
 *
 * @author Codex
 * @since 0.1.0
 */
@Data
@Builder
public class ReportSummaryResponse {
    private String formId;
    private long totalRecords;
}