package com.jiandaoyun.shared.kernel.outbox.consumer.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.consumer.EventProcessLogRepository;
import java.sql.Timestamp;
import java.time.Instant;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 基于 JDBC 的事件处理日志仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(prefix = "app.outbox.consumer", name = "store", havingValue = "jdbc")
public class JdbcEventProcessLogRepository implements EventProcessLogRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 构造 JDBC 事件处理日志仓储实例.
     *
     * @param jdbcTemplate JDBC 模板.
     */
    public JdbcEventProcessLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 保存事件处理日志.
     *
     * @param handlerName 处理器名称.
     * @param eventType 事件类型.
     * @param payload 事件载荷.
     * @param processedAt 处理时间.
     */
    @Override
    public void save(String handlerName, String eventType, String payload, Instant processedAt) {
        String sql = """
            INSERT INTO event_process_log (handler_name, event_type, payload, processed_at)
            VALUES (?, ?, ?, ?)
            """;
        jdbcTemplate.update(sql, handlerName, eventType, payload, Timestamp.from(processedAt));
    }

    /**
     * 查询指定处理器的日志数量.
     *
     * @param handlerName 处理器名称.
     * @return 日志数量.
     */
    @Override
    public long countByHandler(String handlerName) {
        String sql = "SELECT COUNT(1) FROM event_process_log WHERE handler_name = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, handlerName);
        return count == null ? 0L : count;
    }
}
