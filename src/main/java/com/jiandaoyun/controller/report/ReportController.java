package com.jiandaoyun.controller.report;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.report.application.service.ReportApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表控制器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportApplicationService reportApplicationService;

    /**
     * 构造报表控制器实例.
     *
     * @param reportApplicationService 报表应用服务实例.
     */
    public ReportController(ReportApplicationService reportApplicationService) {
        this.reportApplicationService = reportApplicationService;
    }

    /**
     * 获取指定表单的汇总报表.
     *
     * @param formId 表单标识.
     * @return 表单汇总结果.
     */
    @GetMapping("/forms/{formId}/summary")
    public ApiResponse<ReportSummaryResponse> getFormSummary(@PathVariable String formId) {
        return ApiResponse.ok(reportApplicationService.getFormSummary(formId));
    }
}
