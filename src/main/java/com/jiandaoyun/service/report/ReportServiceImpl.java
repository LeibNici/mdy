package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.service.data.DataService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private final DataService dataService;

    public ReportServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public ReportSummaryResponse getFormSummary(String formId) {
        long total = dataService.listByFormId(formId).size();
        return ReportSummaryResponse.builder()
            .formId(formId)
            .totalRecords(total)
            .build();
    }
}