package org.openelisglobal.security;

import java.util.Optional;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.TextEncryptor;

/**
 * Encrypts database secrets with the install's own key and still reads values
 * written with the key it replaced, the keystore password every install used to
 * share. Without an install key the previous key is the only key.
 */
public final class GeneralTextEncryptor implements TextEncryptor {

    private final TextEncryptor current;
    private final TextEncryptor previous;

    private GeneralTextEncryptor(TextEncryptor current, TextEncryptor previous) {
        this.current = current;
        this.previous = previous;
    }

    /**
     * @param installKey  {@code OE_ENCRYPTION_PASSWORD}; blank keeps the previous
     *                    key
     * @param previousKey {@code encryption.general.password}
     */
    public static GeneralTextEncryptor of(String installKey, String previousKey) {
        if (installKey == null || installKey.isBlank() || installKey.equals(previousKey)) {
            return new GeneralTextEncryptor(aes(previousKey), null);
        }
        return new GeneralTextEncryptor(aes(installKey), aes(previousKey));
    }

    private static TextEncryptor aes(String key) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(key);
        return encryptor;
    }

    /**
     * Whether values written with a previous key are rewritten with the install
     * key.
     */
    public boolean rekeys() {
        return previous != null;
    }

    @Override
    public String encrypt(String message) {
        return current.encrypt(message);
    }

    @Override
    public String decrypt(String encryptedMessage) {
        try {
            return current.decrypt(encryptedMessage);
        } catch (EncryptionOperationNotPossibleException notThisKey) {
            if (previous == null) {
                throw notThisKey;
            }
            return previous.decrypt(encryptedMessage);
        }
    }

    /**
     * The value encrypted with the install key, when it was written with the
     * previous key; empty when the install key already reads it or neither key
     * does.
     */
    public Optional<String> rekey(String encryptedMessage) {
        if (previous == null || readable(current, encryptedMessage)) {
            return Optional.empty();
        }
        try {
            return Optional.of(current.encrypt(previous.decrypt(encryptedMessage)));
        } catch (EncryptionOperationNotPossibleException neitherKey) {
            return Optional.empty();
        }
    }

    private static boolean readable(TextEncryptor encryptor, String encryptedMessage) {
        try {
            encryptor.decrypt(encryptedMessage);
            return true;
        } catch (EncryptionOperationNotPossibleException e) {
            return false;
        }
    }
}
