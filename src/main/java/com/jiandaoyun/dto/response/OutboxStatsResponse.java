package com.jiandaoyun.dto.response;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * 出箱监控统计响应对象.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Data
@Builder
public class OutboxStatsResponse {

    private long totalCount;

    private long pendingCount;

    private long processedCount;

    private long failedCount;

    private Map<String, Long> handlerCounts;
}
