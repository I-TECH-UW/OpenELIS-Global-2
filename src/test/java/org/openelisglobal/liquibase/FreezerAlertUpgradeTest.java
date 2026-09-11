package org.openelisglobal.liquibase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.parser.core.xml.XMLChangeLogSAXParser;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(Parameterized.class)
public class FreezerAlertUpgradeTest {

    private static final String CURRENT = "liquibase/3.5.x.x/";
    private static final String HISTORICAL = "database-upgrades/freezer-history/";
    private static final String OFFLINE = "089-cold-storage-monitoring-hardening.xml";
    private static final String HUMIDITY = "092-freezer-humidity-alert-type.xml";
    private static final String REPAIR = "099-reconcile-freezer-microbiology-alert-types.xml";
    private final String baseline;

    public FreezerAlertUpgradeTest(String baseline) {
        this.baseline = baseline;
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<String> baselines() {
        return List.of("fresh", "published-freezer", "published-microbiology");
    }

    @Test
    public void upgradesBothPublishedHistoriesWithoutLosingAlerts() throws Exception {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.4").withDatabaseName("clinlims")
                .withUsername("clinlims").withPassword("clinlims")) {
            postgres.start();
            try (var connection = DriverManager.getConnection(postgres.getJdbcUrl(), "clinlims", "clinlims");
                    var statement = connection.createStatement()) {
                statement.execute("CREATE SCHEMA clinlims");
                statement.execute(
                        "CREATE TABLE clinlims.alert (id SERIAL PRIMARY KEY, alert_type VARCHAR(100) NOT NULL)");
                statement.execute("INSERT INTO clinlims.alert(alert_type) VALUES ('FREEZER_TEMPERATURE')");
            }
            if (!baseline.equals("fresh")) {
                String prefix = baseline.equals("published-freezer") ? HISTORICAL : CURRENT;
                try (Liquibase before = migrations(postgres, prefix, false)) {
                    before.update(new Contexts());
                }
            }
            try (Liquibase upgrade = migrations(postgres, CURRENT, true)) {
                upgrade.update(new Contexts());
                upgrade.update(new Contexts());
                assertTrue(upgrade.listUnrunChangeSets(new Contexts()).isEmpty());
                try (var connection = DriverManager.getConnection(postgres.getJdbcUrl(), "clinlims", "clinlims");
                        var statement = connection.createStatement()) {
                    try (var rows = statement.executeQuery(
                            "SELECT count(*) FROM clinlims.alert WHERE id=1 AND alert_type='FREEZER_TEMPERATURE'")) {
                        rows.next();
                        assertEquals("Existing alert is retained", 1, rows.getInt(1));
                    }
                    for (String type : List.of("FREEZER_HUMIDITY", "FREEZER_OFFLINE", "MICROBIOLOGY_CRITICAL",
                            "CRITICAL_RESULT", "REFERRAL_CRITICAL_RESULT", "EQA_DEADLINE")) {
                        statement.executeUpdate("INSERT INTO clinlims.alert(alert_type) VALUES ('" + type + "')");
                    }
                    try {
                        statement.executeUpdate("INSERT INTO clinlims.alert(alert_type) VALUES ('NOT_AN_ALERT_TYPE')");
                        fail("The constraint must still reject unsupported alert types");
                    } catch (SQLException expected) {
                        assertEquals("23514", expected.getSQLState());
                    }
                    if (baseline.equals("published-freezer")) {
                        try (var rows = statement.executeQuery(
                                "SELECT md5sum FROM clinlims.databasechangelog WHERE id='3.5.0-092-add-freezer-humidity-alert-type'")) {
                            rows.next();
                            assertEquals("Published checksum is retained", "8:9874f4ac18e40bc491bab25d06051072",
                                    rows.getString(1));
                        }
                    }
                }
                // The preceding release already permits this union. Rolling back the
                // reconciliation must not reject microbiology alerts recorded since upgrade.
                upgrade.rollback(1, new Contexts(), new LabelExpression());
                upgrade.update(new Contexts());
            }
        }
    }

    private static Liquibase migrations(PostgreSQLContainer<?> postgres, String prefix, boolean repair)
            throws Exception {
        var resources = new ClassLoaderResourceAccessor();
        var parser = new XMLChangeLogSAXParser();
        var changes = new DatabaseChangeLog();
        for (String file : repair ? List.of(OFFLINE, HUMIDITY, REPAIR) : List.of(OFFLINE, HUMIDITY)) {
            // Execute the actual alert changeset; unrelated freezer tables are not
            // part of this focused fixture. Historical snapshots retain logical paths.
            changes.addChangeSet(
                    parser.parse(prefix + file, new ChangeLogParameters(), resources).getChangeSets().get(0));
        }
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                new JdbcConnection(DriverManager.getConnection(postgres.getJdbcUrl(), "clinlims", "clinlims")));
        database.setDefaultSchemaName("clinlims");
        database.setLiquibaseSchemaName("clinlims");
        return new Liquibase(changes, resources, database);
    }
}
