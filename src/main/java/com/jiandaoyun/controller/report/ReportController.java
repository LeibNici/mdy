package com.jiandaoyun.controller.report;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.service.report.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for report queries.
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    /**
     * Creates controller instance.
     *
     * @param reportService report service
     */
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Gets summary report for one form.
     *
     * @param formId form ID
     * @return summary result
     */
    @GetMapping("/forms/{formId}/summary")
    public ApiResponse<ReportSummaryResponse> getFormSummary(@PathVariable String formId) {
        return ApiResponse.ok(reportService.getFormSummary(formId));
    }
}