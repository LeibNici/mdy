package com.jiandaoyun.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * 鐞涖劌宕熷Ч鍥ㄢ偓缁樺Г鐞涖劌鎼锋惔鏂烩偓? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@Data
@Builder

public class ReportSummaryResponse {

    private String formId;

    private long totalRecords;
}