package com.jiandaoyun.report.application.service;

import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.report.domain.repository.ReportDataReader;
import com.jiandaoyun.report.domain.service.ReportDomainService;
import org.springframework.stereotype.Service;

/**
 * 报表应用服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class ReportApplicationService {

    private final ReportDataReader reportDataReader;

    private final ReportDomainService reportDomainService;

    /**
     * 构造报表应用服务实例.
     *
     * @param reportDataReader 报表数据读取端口.
     * @param reportDomainService 报表领域服务.
     */
    public ReportApplicationService(
        ReportDataReader reportDataReader,
        ReportDomainService reportDomainService
    ) {
        this.reportDataReader = reportDataReader;
        this.reportDomainService = reportDomainService;
    }

    /**
     * 获取表单汇总信息.
     *
     * @param formId 表单标识.
     * @return 表单汇总结果.
     */
    public ReportSummaryResponse getFormSummary(String formId) {
        long total = reportDataReader.countByFormId(formId);
        return reportDomainService.buildSummary(formId, total);
    }
}
