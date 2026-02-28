package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;

/**
 * 閹躲儴銆冮懕姘値閺堝秴濮熼幒銉ュ經閵? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */

public interface ReportService {

    /**
     * 閼惧嘲褰囩悰銊ュ礋濮瑰洦鈧崵绮虹拋掳鈧?     *
 *
 * @param formId 鐞涖劌宕?ID
 *
 * @return 濮瑰洦鈧鎼锋惔? */
    ReportSummaryResponse getFormSummary(String formId);
}