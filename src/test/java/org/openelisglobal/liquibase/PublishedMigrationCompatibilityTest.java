package org.openelisglobal.liquibase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.ChangeSet;
import liquibase.parser.core.xml.XMLChangeLogSAXParser;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;
import org.junit.Test;

/**
 * Compares real historical changesets, rather than rebuilding history from
 * candidate SQL.
 */
public class PublishedMigrationCompatibilityTest {

    @Test
    public void candidateAcceptsPreviouslyPublishedChangesetChecksums() throws Exception {
        String directory = System.getenv("LIQUIBASE_BASELINE_DIR");
        assumeTrue("Set LIQUIBASE_BASELINE_DIR to an earlier revision's src/main/resources", directory != null);
        File root = new File(directory);
        assertTrue("Historical changelog must exist", new File(root, "liquibase/base-changelog.xml").isFile());
        var parser = new XMLChangeLogSAXParser();
        var previousResources = new FileSystemResourceAccessor(root);
        try (var candidateResources = new ClassLoaderResourceAccessor()) {
            var previous = parser.parse("liquibase/base-changelog.xml", new ChangeLogParameters(), previousResources);
            var candidate = parser.parse("liquibase/base-changelog.xml", new ChangeLogParameters(), candidateResources);
            Map<String, ChangeSet> current = new HashMap<>();
            for (ChangeSet change : candidate.getChangeSets()) {
                current.put(identity(change), change);
            }
            for (ChangeSet old : previous.getChangeSets()) {
                ChangeSet change = current.get(identity(old));
                assertNotNull("Published changeset was removed: " + identity(old), change);
                if (change.shouldAlwaysRun() || change.shouldRunOnChange()) {
                    continue;
                }
                assertTrue(
                        "Published changeset changed without accepting its prior checksum: " + identity(old) + " ("
                                + old.generateCheckSum() + "). Use a new migration for the schema change.",
                        change.isCheckSumValid(old.generateCheckSum()));
            }
        }
    }

    private static String identity(ChangeSet change) {
        return change.getFilePath() + "::" + change.getId() + "::" + change.getAuthor();
    }
}
