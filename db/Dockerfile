# Keep the PostgreSQL major version on a supported operating-system base.
FROM postgres:14.24-bookworm

# Debian 11 (bullseye) left LTS on 2026-08-31: the security suite's Release file has expired and its
# pool is being pruned, so packages come from archive.debian.org (main + updates only).
RUN printf 'deb http://archive.debian.org/debian bullseye main\ndeb http://archive.debian.org/debian bullseye-updates main\n' > /etc/apt/sources.list \
    && apt-get -o Acquire::Check-Valid-Until=false update && apt-get install -y gettext-base && rm -rf /var/lib/apt/lists/*
# Copy initialization scripts and data files into the container
# PostgreSQL will automatically run any *.sql or *.sh files in this folder
COPY ./db/dbInit /docker-entrypoint-initdb.d
COPY ./db/dbInit/OpenELIS-Global.sql /docker-entrypoint-initdb.d/OpenELIS-Global.sql
COPY ./db/dbInit/siteInfo.sql /docker-entrypoint-initdb.d/siteInfo.sql

RUN chmod -R 755 /docker-entrypoint-initdb.d \
    && chown -R postgres:postgres /docker-entrypoint-initdb.d

# Optional: Set working directory (not strictly needed)
WORKDIR /docker-entrypoint-initdb.d

# Expose the PostgreSQL port
EXPOSE 5432
