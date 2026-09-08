#!/usr/bin/env bash
# Rebuilds indexes once when the operating system's collation library changed under an existing cluster.
#
# Moving the image to another Debian release changes glibc, and with it the sort order of locale
# collations such as en_US.utf8. PostgreSQL 14 records the glibc version for the collations in
# pg_collation but not for a database's default collation, so indexes on text columns keep being
# used silently after the change. On an existing data directory this wrapper starts the server
# privately (no TCP listener), looks for collations whose recorded version differs from the one the
# OS now provides, rebuilds every index of the affected databases, records the new version, stops
# the private server and hands over to the stock entrypoint. Empty volumes (first initialisation)
# and already-refreshed volumes pass straight through.
#
# The check never blocks start-up: any failure (private server not starting, local authentication
# refusing the postgres role, a rebuild error) is logged with the manual statements to run, and the
# stock entrypoint starts PostgreSQL normally. Set OE_DB_SKIP_COLLATION_REINDEX=true to skip the check.
set -uo pipefail

PGDATA="${PGDATA:-/var/lib/postgresql/data}"
export PGPASSWORD="${PGPASSWORD:-${POSTGRES_PASSWORD:-}}"

log() { printf '%s [collation-check] %s\n' "$(date -u '+%Y-%m-%d %H:%M:%S UTC')" "$*"; }

as_postgres() {
  if [ "$(id -u)" = '0' ]; then gosu postgres "$@"; else "$@"; fi
}

private_server_running=false
stop_private_server() {
  if [ "$private_server_running" = true ]; then
    as_postgres pg_ctl -D "$PGDATA" -m fast -w -t 120 stop >/dev/null 2>&1 || true
    private_server_running=false
  fi
}

manual_hint() {
  log "run the statements by hand as the postgres superuser once the server is up:"
  log "  REINDEX DATABASE <db>; ALTER COLLATION \"en_US.utf8\" REFRESH VERSION; ALTER COLLATION \"en_US\" REFRESH VERSION;"
}

collation_check() {
  local mismatch_sql db stale coll started
  if ! as_postgres pg_ctl -D "$PGDATA" -o "-c listen_addresses=''" -w -t 300 start >/dev/null 2>&1; then
    log "private server did not start within 300s; skipping the check"; manual_hint; return 0
  fi
  private_server_running=true

  mismatch_sql="SELECT quote_ident(collname) FROM pg_collation
                 WHERE collprovider = 'c' AND collversion IS NOT NULL
                   AND collversion IS DISTINCT FROM pg_collation_actual_version(oid)"
  local dbs
  if ! dbs=$(as_postgres psql -X -At -v ON_ERROR_STOP=1 -c "SELECT datname FROM pg_database WHERE datallowconn AND NOT datistemplate ORDER BY datname" 2>&1); then
    log "cannot query the cluster as the postgres role ($dbs); skipping the check"; manual_hint; return 0
  fi
  while IFS= read -r db; do
    [ -z "$db" ] && continue
    if ! stale=$(as_postgres psql -X -At -v ON_ERROR_STOP=1 -d "$db" -c "$mismatch_sql" 2>&1); then
      log "$db: cannot read pg_collation ($stale); skipping this database"; manual_hint; continue
    fi
    if [ -z "$stale" ]; then
      log "$db: collation versions match, nothing to do"; continue
    fi
    log "$db: collation library changed for $(echo "$stale" | tr '\n' ' '); rebuilding all indexes"
    started=$SECONDS
    if ! as_postgres psql -X -q -v ON_ERROR_STOP=1 -d "$db" -c "REINDEX DATABASE $(as_postgres psql -X -At -d "$db" -c 'SELECT quote_ident(current_database())')" 2>&1; then
      log "$db: REINDEX failed; versions left unchanged so the check runs again next start"; manual_hint; continue
    fi
    while IFS= read -r coll; do
      [ -z "$coll" ] && continue
      as_postgres psql -X -q -v ON_ERROR_STOP=1 -d "$db" -c "ALTER COLLATION $coll REFRESH VERSION" 2>&1 || log "$db: could not refresh $coll"
    done <<< "$stale"
    log "$db: indexes rebuilt and collation versions refreshed in $((SECONDS - started))s"
  done <<< "$dbs"
  return 0
}

if [ "${1:-}" = 'postgres' ] && [ -s "$PGDATA/PG_VERSION" ] && [ "${OE_DB_SKIP_COLLATION_REINDEX:-false}" != 'true' ]; then
  log "existing cluster found (PostgreSQL $(cat "$PGDATA/PG_VERSION") data directory), checking collation versions"
  trap stop_private_server EXIT
  collation_check || log "check ended with an error; starting PostgreSQL normally"
  stop_private_server
  trap - EXIT
  log "check complete, starting PostgreSQL normally"
fi

exec docker-entrypoint.sh "$@"
