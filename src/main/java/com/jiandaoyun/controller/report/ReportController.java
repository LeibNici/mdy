package com.jiandaoyun.controller.report;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.service.report.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表控制器.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    /**
     * 构造报表控制器实例.
     *
     * @param reportService 报表服务实例.
     */
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 获取指定表单的汇总报表.
     *
     * @param formId 表单标识.
     * @return 表单汇总结果.
     */
    @GetMapping("/forms/{formId}/summary")
    public ApiResponse<ReportSummaryResponse> getFormSummary(@PathVariable String formId) {
        return ApiResponse.ok(reportService.getFormSummary(formId));
    }
}
