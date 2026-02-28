package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;

/**
 * 报表聚合服务接口。
 *
 * @author Codex
 * @since 0.1.0
 */
public interface ReportService {

    /**
     * 获取表单汇总统计。
     *
     * @param formId 表单 ID
     * @return 汇总响应
     */
    ReportSummaryResponse getFormSummary(String formId);
}