-- 出箱事件表
CREATE TABLE IF NOT EXISTS outbox_message (
    id VARCHAR(64) PRIMARY KEY,
    event_type VARCHAR(128) NOT NULL,
    payload TEXT NOT NULL,
    occurred_on TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(32) NOT NULL
);

CREATE INDEX idx_outbox_message_status_created_at
    ON outbox_message (status, created_at);
