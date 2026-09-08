# Database and FHIR Image Maintenance

The database image uses PostgreSQL 14.24 on Debian Bookworm. The FHIR image runs
the unchanged HAPI 6.6.0 WAR on Tomcat 9 with Java 17 on Ubuntu Noble. The old
HAPI image supplies only the WAR during the build, not the final operating
system, Tomcat installation, or entrypoint. Configuration stays at
`/opt/openelis/config/hapi_application.yaml`; TLS settings and runtime user 8443
are preserved. The Tomcat installation is now `/usr/local/tomcat`. Deployments
that override the server configuration by mounting a file at the old Bitnami
path (`/opt/bitnami/tomcat/conf/server.xml`, as `dev.docker-compose.yml` and the
installer template do) keep working: the image entrypoint
(`fhir/docker-entrypoint.sh`) applies such a file to the Tomcat configuration
directory before start-up. Deployments that pass `-Dhapi.ssl.*` through
`CATALINA_OPTS` use the baked `server.xml` as before.

This addresses the expired Bullseye package metadata that prevented clean image
builds. It does not constitute a HAPI application upgrade or a PostgreSQL
major-version upgrade. Both still need their own planned upgrades; pinning
versions does not replace regular dependency maintenance.

## Validate Images

Run from the repository root with Docker and jq available:

```bash
docker build --platform linux/amd64 -f db/Dockerfile -t openelis-base-refresh-db:local .
docker build --platform linux/amd64 -f fhir/Dockerfile -t openelis-base-refresh-fhir:local .
bash scripts/smoke-test-service-images.sh
```

The smoke test creates only uniquely named containers, a network, and a
certificate volume. It exposes no host ports, removes its resources on exit, and
prints the directory containing its logs and response evidence. It checks fresh
OpenELIS database initialization, non-root FHIR startup, mutual TLS, FHIR R4
metadata, and saving/reading a synthetic patient across both services' restart.
It is an infrastructure test, not browser acceptance or a new CI pipeline. The
existing end-to-end workflow remains the application gate.

## Existing Deployments

Back up and rehearse the update on a copy before replacing an existing database
container. Keep the database volume; do not reset production data. An
operating-system change also changes locale libraries. Existing indexes that
depend on locale-sensitive text ordering may need rebuilding before normal
traffic resumes. Review both the database's default locale and any explicit
column/index collations. Refreshing a recorded collation version alone does not
rebuild affected indexes. Follow the PostgreSQL
[collation maintenance guidance](https://www.postgresql.org/docs/14/sql-altercollation.html).

The fresh-database smoke test does not establish that an existing site's indexes
are safe after that library change. Site-specific backup, index maintenance,
restore, and rollback validation are deployment responsibilities. Do not disable
package signature or expiration checks to retain an obsolete base image.

### What the library change looks like on an OpenELIS database

The move from Debian 11 to Debian 12 takes glibc from 2.31 to 2.36. PostgreSQL
14 records the glibc version only for named collations in `pg_collation`; it
does not track the database's default collation (`en_US.utf8` in every OpenELIS
install), so indexes on text columns keep working silently on the new image and
can still hide ordering inconsistencies.

The database image handles this itself. Its entrypoint
(`db/docker-entrypoint-collation.sh`) runs before PostgreSQL accepts
connections: on an existing data directory it starts the server privately,
compares the recorded collation versions with the ones the operating system
provides, and only when they differ rebuilds every index of the affected
databases (`REINDEX DATABASE`) and records the new versions
(`ALTER COLLATION ... REFRESH VERSION`). Empty volumes and already-refreshed
volumes pass straight through, so the check costs nothing on later starts. The
check never blocks start-up: if the private server does not come up or the
`postgres` role cannot connect over the local socket (the check uses
`POSTGRES_PASSWORD` when local authentication requires one), the wrapper logs
the statements to run and PostgreSQL starts normally. Set
`OE_DB_SKIP_COLLATION_REINDEX=true` on the database service to skip it and run
the same statements by hand in a maintenance window:

```sql
-- as the postgres superuser, connected to clinlims
SELECT collname, collversion, pg_collation_actual_version(oid)
  FROM pg_collation WHERE collname IN ('en_US.utf8', 'en_US');
REINDEX DATABASE clinlims;
ALTER COLLATION "en_US.utf8" REFRESH VERSION;
ALTER COLLATION "en_US" REFRESH VERSION;
```

Measured on a development database with 913 indexes (11 MB), the rebuild took
between one and seven seconds depending on host load; the time grows with index
size, and the first start on the new image is delayed by that much. Take a
backup before the upgrade regardless, and rehearse on a copy of the production
volume first. Rolling back to the previous image on the same volume works (same
PostgreSQL major); the old image then sees recorded version 2.36 against its own
2.31 and the check would rebuild again on the next start of the new image.
