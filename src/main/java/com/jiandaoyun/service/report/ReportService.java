package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;

/**
 * 报表服务接口.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
public interface ReportService {

    /**
     * 获取表单汇总信息.
     *
     * @param formId 表单标识.
     * @return 表单汇总结果.
     */
    ReportSummaryResponse getFormSummary(String formId);
}
