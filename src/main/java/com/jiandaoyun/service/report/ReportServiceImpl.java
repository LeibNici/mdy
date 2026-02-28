package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.service.data.DataService;
import org.springframework.stereotype.Service;

/**
 * 閹躲儴銆冮張宥呭姒涙顓荤€圭偟骞囬妴? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@Service

public class ReportServiceImpl implements ReportService {

    private final DataService dataService;

    /**
     * 閺嬪嫰鈧姴鍤遍弫鑸偓?     *
 *
 * @param dataService 閺佺増宓侀張宥呭
     */
    public ReportServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportSummaryResponse getFormSummary(String formId) {
        long total = dataService.listByFormId(formId).size();
        return ReportSummaryResponse.builder()
            .formId(formId)
            .totalRecords(total)
            .build();
    }
}