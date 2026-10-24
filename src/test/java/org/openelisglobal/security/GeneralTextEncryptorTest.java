package org.openelisglobal.security;

import static org.junit.Assert.*;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.Test;

public class GeneralTextEncryptorTest {
    @Test
    public void freshWritesUseOnlyTheInstallationKey() {
        GeneralTextEncryptor encryptor = GeneralTextEncryptor.of("install-key", "previous-key");
        String stored = encryptor.encrypt("secret");
        assertTrue(stored.startsWith("OE1:"));
        assertEquals("secret", GeneralTextEncryptor.of("install-key", null).decrypt(stored));
        assertThrows(EncryptionOperationNotPossibleException.class,
                () -> GeneralTextEncryptor.of("different-key", "install-key").decrypt(stored));
    }

    @Test
    public void normalOperationDoesNotFallBackToThePreviousKey() {
        AES256TextEncryptor old = new AES256TextEncryptor();
        old.setPassword("previous-key");
        String ciphertext = old.encrypt("secret");
        assertThrows(EncryptionOperationNotPossibleException.class,
                () -> GeneralTextEncryptor.of("install-key", "previous-key").decrypt(ciphertext));
    }

    @Test
    public void nativeInstallationsCanKeepTheirExplicitlyConfiguredKey() {
        AES256TextEncryptor old = new AES256TextEncryptor();
        old.setPassword("native-key");
        assertEquals("secret", GeneralTextEncryptor.of(null, "native-key").decrypt(old.encrypt("secret")));
    }

    @Test
    public void installerKeysKeepJavaPropertiesEscapeSemantics() throws Exception {
        String rawKey = java.util.UUID.randomUUID() + "\\key";
        java.util.Properties properties = new java.util.Properties();
        properties.load(new java.io.StringReader("encryption.general.password=" + rawKey));
        String effectiveKey = properties.getProperty("encryption.general.password");
        assertNotEquals(rawKey, effectiveKey);
        AES256TextEncryptor old = new AES256TextEncryptor();
        old.setPassword(effectiveKey);
        String stored = old.encrypt("existing secret");
        GeneralTextEncryptor current = GeneralTextEncryptor.of(null, effectiveKey);
        assertEquals("existing secret", current.decrypt(stored));
        assertEquals("new secret", current.decrypt(current.encrypt("new secret")));
        assertThrows(EncryptionOperationNotPossibleException.class,
                () -> GeneralTextEncryptor.of(rawKey, effectiveKey).decrypt(stored));
    }

    @Test
    public void missingKeyIsRejectedAndNullValuesRemainNull() {
        assertThrows(IllegalArgumentException.class, () -> GeneralTextEncryptor.of(null, ""));
        assertNull(GeneralTextEncryptor.of("key", null).encrypt(null));
        assertNull(GeneralTextEncryptor.of("key", null).decrypt(null));
    }
}
