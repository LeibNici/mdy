package com.jiandaoyun.controller.report;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.service.report.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 閹躲儴銆冮弻銉嚄閹貉冨煑閸ｃ劊鈧? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/reports")

public class ReportController {

    private final ReportService reportService;

    /**
     * 閺嬪嫰鈧姴鍤遍弫鑸偓?     *
 *
 * @param reportService 閹躲儴銆冮張宥呭
     */
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 閹稿銆冮崡鏇″箯閸欐牗鐪归幀缁樺Г鐞涖劊鈧?     *
 *
 * @param formId 鐞涖劌宕?ID
 *
 * @return 濮瑰洦鈧崵绮ㄩ弸? */
    @GetMapping("/forms/{formId}/summary")
    public ApiResponse<ReportSummaryResponse> getFormSummary(@PathVariable String formId) {
        return ApiResponse.ok(reportService.getFormSummary(formId));
    }
}