package com.jiandaoyun.service.report;

import com.jiandaoyun.dto.response.ReportSummaryResponse;
import com.jiandaoyun.service.data.DataService;
import org.springframework.stereotype.Service;

/**
 * 报表服务实现.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final DataService dataService;

    /**
     * 构造报表服务实例.
     *
     * @param dataService 数据服务实例.
     */
    public ReportServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * 获取表单汇总信息.
     *
     * @param formId 表单标识.
     * @return 表单汇总结果.
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
