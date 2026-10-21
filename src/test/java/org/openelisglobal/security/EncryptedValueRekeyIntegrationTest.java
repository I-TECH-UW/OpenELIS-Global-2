package org.openelisglobal.security;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;
import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class EncryptedValueRekeyIntegrationTest extends BaseWebContextSensitiveTest {

    private static final String SETTING = "rekey test secret";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EncryptedValueRekeyService rekeyService;

    private JdbcTemplate jdbc;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        jdbc = new JdbcTemplate(dataSource);
        cleanup();
        AES256TextEncryptor previous = new AES256TextEncryptor();
        previous.setPassword("kspass");
        jdbc.update(
                "INSERT INTO clinlims.site_information (id, name, value, value_type, encrypted, lastupdated)"
                        + " VALUES (nextval('clinlims.site_information_seq'), ?, ?, 'text', true, now())",
                SETTING, previous.encrypt("s3cret"));
    }

    @After
    public void tearDown() {
        cleanup();
    }

    private void cleanup() {
        jdbc.update("DELETE FROM clinlims.site_information WHERE name = ?", SETTING);
    }

    @Test
    public void aSecretWrittenWithThePreviousKeyIsRewrittenWithTheInstallKeyOnce() {
        GeneralTextEncryptor encryptor = GeneralTextEncryptor.of("install-key", "kspass");

        assertEquals(1, rekeyService.rekey(encryptor));
        assertEquals("already rewritten", 0, rekeyService.rekey(encryptor));

        String stored = jdbc.queryForObject("SELECT value FROM clinlims.site_information WHERE name = ?", String.class,
                SETTING);
        assertEquals("s3cret", GeneralTextEncryptor.of("install-key", "install-key").decrypt(stored));
    }
}
