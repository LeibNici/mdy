package com.jiandaoyun.report.domain.service;

import com.jiandaoyun.dto.response.ReportSummaryResponse;
import org.springframework.stereotype.Component;

/**
 * 报表领域服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
public class ReportDomainService {

    /**
     * 构建表单汇总结果.
     *
     * @param formId 表单标识.
     * @param totalRecords 记录总数.
     * @return 表单汇总结果.
     */
    public ReportSummaryResponse buildSummary(String formId, long totalRecords) {
        return ReportSummaryResponse.builder()
            .formId(formId)
            .totalRecords(totalRecords)
            .build();
    }
}
