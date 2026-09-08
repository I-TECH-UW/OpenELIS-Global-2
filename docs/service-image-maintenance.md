# Database and FHIR Image Maintenance

The database image uses PostgreSQL 14.24 on Debian Bookworm. The FHIR image runs
the unchanged HAPI 6.6.0 WAR on Tomcat 9 with Java 17 on Ubuntu Noble. The old
HAPI image supplies only the WAR during the build, not the final operating
system, Tomcat installation, or entrypoint. Configuration stays at
`/opt/openelis/config/hapi_application.yaml`; TLS settings and runtime user 8443
are preserved. The Tomcat installation is now `/usr/local/tomcat`.

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

The move from Debian 11 to Debian 12 takes glibc from 2.31 to 2.36. PostgreSQL 14
records the glibc version only for named collations in `pg_collation`; it does
not track the database's default collation (`en_US.utf8` in every OpenELIS
install), so indexes on text columns keep working silently on the new image
and can still hide ordering inconsistencies. Run the remediation once, in the
maintenance window, right after the first start on the new image and before
the site is opened to users:

```sql
-- as the postgres superuser, connected to clinlims
SELECT collname, collversion, pg_collation_actual_version(oid)
  FROM pg_collation WHERE collname IN ('en_US.utf8', 'en_US');
REINDEX DATABASE clinlims;
ALTER COLLATION "en_US.utf8" REFRESH VERSION;
ALTER COLLATION "en_US" REFRESH VERSION;
```

Measured on a development database with 914 indexes (11 MB), `REINDEX
DATABASE` took about one second; the time grows with index size. Take a backup
before the upgrade regardless, and rehearse the sequence on a copy of the
production volume first.
