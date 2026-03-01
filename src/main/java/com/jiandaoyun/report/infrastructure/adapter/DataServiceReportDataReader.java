package com.jiandaoyun.report.infrastructure.adapter;

import com.jiandaoyun.report.domain.repository.ReportDataReader;
import com.jiandaoyun.service.data.DataService;
import org.springframework.stereotype.Component;

/**
 * 基于数据服务的报表数据读取实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
public class DataServiceReportDataReader implements ReportDataReader {

    private final DataService dataService;

    /**
     * 构造报表数据读取实现实例.
     *
     * @param dataService 数据服务.
     */
    public DataServiceReportDataReader(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * 统计表单记录总数.
     *
     * @param formId 表单标识.
     * @return 记录总数.
     */
    @Override
    public long countByFormId(String formId) {
        return dataService.listByFormId(formId).size();
    }
}
