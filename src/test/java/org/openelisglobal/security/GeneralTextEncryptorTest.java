package org.openelisglobal.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.Test;

public class GeneralTextEncryptorTest {

    private static String encryptedWith(String key, String text) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(key);
        return encryptor.encrypt(text);
    }

    @Test
    public void encryptsWithTheInstallKey() {
        GeneralTextEncryptor encryptor = GeneralTextEncryptor.of("install-key", "kspass");

        String stored = encryptor.encrypt("secret");

        assertEquals("secret", GeneralTextEncryptor.of("install-key", "other").decrypt(stored));
        assertThrows(EncryptionOperationNotPossibleException.class,
                () -> GeneralTextEncryptor.of("kspass", "kspass").decrypt(stored));
    }

    @Test
    public void stillReadsAValueWrittenWithThePreviousKey() {
        GeneralTextEncryptor encryptor = GeneralTextEncryptor.of("install-key", "kspass");

        assertEquals("secret", encryptor.decrypt(encryptedWith("kspass", "secret")));
    }

    @Test
    public void rekeysOnlyAValueWrittenWithThePreviousKey() {
        GeneralTextEncryptor encryptor = GeneralTextEncryptor.of("install-key", "kspass");

        Optional<String> rekeyed = encryptor.rekey(encryptedWith("kspass", "secret"));

        assertTrue(rekeyed.isPresent());
        assertEquals("secret", GeneralTextEncryptor.of("install-key", "install-key").decrypt(rekeyed.get()));
        assertFalse(encryptor.rekey(encryptor.encrypt("secret")).isPresent());
        assertFalse("a value neither key reads is left alone", encryptor.rekey(encryptedWith("lost", "x")).isPresent());
    }

    @Test
    public void withoutAnInstallKeyThePreviousKeyIsTheOnlyKey() {
        GeneralTextEncryptor encryptor = GeneralTextEncryptor.of("", "kspass");

        assertFalse(encryptor.rekeys());
        assertEquals("secret", GeneralTextEncryptor.of("kspass", "kspass").decrypt(encryptor.encrypt("secret")));
    }
}
