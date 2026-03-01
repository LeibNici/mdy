-- 事件处理日志表
CREATE TABLE IF NOT EXISTS event_process_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    handler_name VARCHAR(128) NOT NULL,
    event_type VARCHAR(128) NOT NULL,
    payload TEXT NOT NULL,
    processed_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_event_process_log_handler_name
    ON event_process_log (handler_name);
