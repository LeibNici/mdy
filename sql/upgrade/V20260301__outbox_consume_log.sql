-- 出箱消费幂等日志表
CREATE TABLE IF NOT EXISTS outbox_consume_log (
    outbox_id VARCHAR(64) PRIMARY KEY,
    consumed_at TIMESTAMP NOT NULL
);
