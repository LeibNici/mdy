package com.jiandaoyun.shared.kernel.outbox.infrastructure;

import com.jiandaoyun.shared.kernel.outbox.OutboxMessage;
import com.jiandaoyun.shared.kernel.outbox.OutboxRepository;
import com.jiandaoyun.shared.kernel.outbox.OutboxStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 基于 JDBC 的出箱仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(prefix = "app.outbox", name = "mode", havingValue = "jdbc")
public class JdbcOutboxRepository implements OutboxRepository {

    private static final RowMapper<OutboxMessage> OUTBOX_ROW_MAPPER = new OutboxRowMapper();

    private final JdbcTemplate jdbcTemplate;

    /**
     * 构造 JDBC 出箱仓储实例.
     *
     * @param jdbcTemplate JDBC 模板.
     */
    public JdbcOutboxRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 保存出箱消息.
     *
     * @param message 出箱消息.
     */
    @Override
    public void save(OutboxMessage message) {
        String sql = """
            INSERT INTO outbox_message
            (id, event_type, payload, occurred_on, created_at, status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;
        jdbcTemplate.update(
            sql,
            message.getId(),
            message.getEventType(),
            message.getPayload(),
            Timestamp.from(message.getOccurredOn()),
            Timestamp.from(message.getCreatedAt()),
            message.getStatus().name()
        );
    }

    /**
     * 查询待投递消息.
     *
     * @param limit 返回上限.
     * @return 待投递消息列表.
     */
    @Override
    public List<OutboxMessage> findPending(int limit) {
        String sql = """
            SELECT id, event_type, payload, occurred_on, created_at, status
            FROM outbox_message
            WHERE status = ?
            ORDER BY created_at ASC
            LIMIT ?
            """;
        return jdbcTemplate.query(sql, OUTBOX_ROW_MAPPER, OutboxStatus.PENDING.name(), limit);
    }

    /**
     * 按标识将消息标记为已投递.
     *
     * @param messageIds 消息标识列表.
     */
    @Override
    public void markProcessed(List<String> messageIds) {
        if (messageIds.isEmpty()) {
            return;
        }
        StringJoiner placeholders = new StringJoiner(", ");
        List<Object> args = new ArrayList<>();
        args.add(OutboxStatus.PROCESSED.name());
        for (String id : messageIds) {
            placeholders.add("?");
            args.add(id);
        }
        String sql = "UPDATE outbox_message SET status = ? WHERE id IN (" + placeholders + ")";
        jdbcTemplate.update(sql, args.toArray());
    }

    /**
     * 查询消息总数.
     *
     * @return 消息总数.
     */
    @Override
    public long count() {
        String sql = "SELECT COUNT(1) FROM outbox_message";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count == null ? 0L : count;
    }

    /**
     * 出箱消息行映射器.
     *
     * @author chenming
     *
     * @since 2026/03/01
     */
    private static final class OutboxRowMapper implements RowMapper<OutboxMessage> {

        /**
         * 将结果行映射为出箱消息对象.
         *
         * @param resultSet 查询结果集.
         * @param rowNum 行号.
         * @return 出箱消息对象.
         * @throws SQLException 读取结果集失败时抛出异常.
         */
        @Override
        public OutboxMessage mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return OutboxMessage.builder()
                .id(resultSet.getString("id"))
                .eventType(resultSet.getString("event_type"))
                .payload(resultSet.getString("payload"))
                .occurredOn(resultSet.getTimestamp("occurred_on").toInstant())
                .createdAt(resultSet.getTimestamp("created_at").toInstant())
                .status(OutboxStatus.valueOf(resultSet.getString("status")))
                .build();
        }
    }
}
