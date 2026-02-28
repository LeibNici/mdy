package com.jiandaoyun.controller.report;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.service.report.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表查询控制器。
 *
 * @author Codex
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    /**
     * 构造函数。
     *
     * @param reportService 报表服务
     */
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 按表单获取汇总报表。
     *
     * @param formId 表单 ID
     * @return 汇总结果
     */
    @GetMapping("/forms/{formId}/summary")
    public ApiResponse<ReportSummaryResponse> getFormSummary(@PathVariable String formId) {
        return ApiResponse.ok(reportService.getFormSummary(formId));
    }
}