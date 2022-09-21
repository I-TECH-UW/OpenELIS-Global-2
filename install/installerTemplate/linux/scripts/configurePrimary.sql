ALTER SYSTEM SET wal_level = 'hot_standby';
ALTER SYSTEM SET wal_log_hints = 'on';
ALTER SYSTEM SET max_wal_senders = '3';
ALTER SYSTEM SET wal_keep_segments = '8';
ALTER SYSTEM SET hot_standby = 'on';
