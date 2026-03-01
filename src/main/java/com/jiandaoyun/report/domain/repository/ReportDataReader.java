package com.jiandaoyun.report.domain.repository;

/**
 * 报表数据读取端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface ReportDataReader {

    /**
     * 统计表单记录总数.
     *
     * @param formId 表单标识.
     * @return 记录总数.
     */
    long countByFormId(String formId);
}
