package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.report.application.service.ReportApplicationService;
import org.springframework.stereotype.Service;

/**
 * 报表服务适配实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportApplicationService reportApplicationService;

    /**
     * 构造报表服务适配实例.
     *
     * @param reportApplicationService 报表应用服务.
     */
    public ReportServiceImpl(ReportApplicationService reportApplicationService) {
        this.reportApplicationService = reportApplicationService;
    }

    /**
     * 获取表单汇总信息.
     *
     * @param formId 表单标识.
     * @return 表单汇总结果.
     */
    @Override
    public ReportSummaryResponse getFormSummary(String formId) {
        return reportApplicationService.getFormSummary(formId);
    }
}
