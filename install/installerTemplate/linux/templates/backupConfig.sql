alter system set archive_mode=on;
alter system set wal_level=hot_standby;
alter system set archive_command='test ! -f [% backups_dir %]archive/%f && cp %p [% backups_dir %]archive/%f';
alter system set max_replication_slots = 10;
alter system set max_wal_senders = 10;
alter system set wal_keep_segments = 5;