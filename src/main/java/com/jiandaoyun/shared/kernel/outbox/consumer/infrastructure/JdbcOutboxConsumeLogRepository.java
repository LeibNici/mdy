package com.jiandaoyun.shared.kernel.outbox.consumer.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.consumer.OutboxConsumeLogRepository;
import java.sql.Timestamp;
import java.time.Instant;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 基于 JDBC 的出箱消费日志仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(prefix = "app.outbox.consumer", name = "store", havingValue = "jdbc")
public class JdbcOutboxConsumeLogRepository implements OutboxConsumeLogRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 构造 JDBC 出箱消费日志仓储实例.
     *
     * @param jdbcTemplate JDBC 模板.
     */
    public JdbcOutboxConsumeLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 判断消息是否已消费.
     *
     * @param outboxId 出箱消息标识.
     * @return 是否已消费.
     */
    @Override
    public boolean isConsumed(String outboxId) {
        String sql = "SELECT COUNT(1) FROM outbox_consume_log WHERE outbox_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, outboxId);
        return count != null && count > 0;
    }

    /**
     * 标记消息已消费.
     *
     * @param outboxId 出箱消息标识.
     * @param consumedAt 消费时间.
     */
    @Override
    public void markConsumed(String outboxId, Instant consumedAt) {
        String sql = """
            INSERT INTO outbox_consume_log (outbox_id, consumed_at)
            VALUES (?, ?)
            """;
        jdbcTemplate.update(sql, outboxId, Timestamp.from(consumedAt));
    }
}
