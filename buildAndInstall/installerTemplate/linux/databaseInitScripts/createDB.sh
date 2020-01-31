#!/bin/bash
set -e

#must match value in docker-compose.yml
DB_DIR="/database" 

#create accounts and database
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -f ${DB_DIR}/pgsqlPermissions.sql

#populate db with base database
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname clinlims -f ${DB_DIR}/baseDatabase/OpenELIS-Global.sql

#modify site info based on installation values
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -f ${DB_DIR}/siteInfo.sql
