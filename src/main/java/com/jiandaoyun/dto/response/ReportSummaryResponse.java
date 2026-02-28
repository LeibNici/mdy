package com.jiandaoyun.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * 报表汇总响应对象.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Data
@Builder

public class ReportSummaryResponse {

    private String formId;

    private long totalRecords;
}
