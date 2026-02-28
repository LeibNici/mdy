package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.service.data.DataService;
import org.springframework.stereotype.Service;

/**
 * 报表服务默认实现。
 *
 * @author Codex
 * @since 0.1.0
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final DataService dataService;

    /**
     * 构造函数。
     *
     * @param dataService 数据服务
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