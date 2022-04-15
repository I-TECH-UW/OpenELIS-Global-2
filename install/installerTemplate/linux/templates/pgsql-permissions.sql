
CREATE USER clinlims PASSWORD '[% itechappPassword %]';
CREATE USER admin SUPERUSER PASSWORD '[% adminPassword %]';
CREATE USER backup REPLICATION PASSWORD '[% backupPassword %]';
ALTER DATABASE clinlims OWNER TO clinlims;

