-- 事件处理日志表结构增量升级
ALTER TABLE event_process_log
    ADD COLUMN IF NOT EXISTS outbox_id VARCHAR(128);

ALTER TABLE event_process_log
    ADD COLUMN IF NOT EXISTS process_status VARCHAR(32) NOT NULL DEFAULT 'SUCCESS';

ALTER TABLE event_process_log
    ADD COLUMN IF NOT EXISTS error_message VARCHAR(512);
