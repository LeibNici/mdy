package com.jiandaoyun.report.infrastructure.adapter;

import com.jiandaoyun.data.application.service.DataApplicationService;
import com.jiandaoyun.report.domain.repository.ReportDataReader;
import org.springframework.stereotype.Component;

/**
 * 基于数据应用服务的报表数据读取实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Component
public class DataServiceReportDataReader implements ReportDataReader {

    private final DataApplicationService dataApplicationService;

    /**
     * 构造报表数据读取实现实例.
     *
     * @param dataApplicationService 数据应用服务.
     */
    public DataServiceReportDataReader(DataApplicationService dataApplicationService) {
        this.dataApplicationService = dataApplicationService;
    }

    /**
     * 统计表单记录总数.
     *
     * @param formId 表单标识.
     * @return 记录总数.
     */
    @Override
    public long countByFormId(String formId) {
        return dataApplicationService.listByFormId(formId).size();
    }
}
