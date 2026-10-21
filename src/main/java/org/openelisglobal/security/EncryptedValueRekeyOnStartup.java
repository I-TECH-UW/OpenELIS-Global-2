package org.openelisglobal.security;

import java.util.concurrent.atomic.AtomicBoolean;
import org.jasypt.util.text.TextEncryptor;
import org.openelisglobal.common.log.LogEvent;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Once the application is up, rewrites the secrets still encrypted with the
 * previous key. A failure is logged and leaves the values readable through the
 * previous key; it never stops startup.
 */
@Component
public class EncryptedValueRekeyOnStartup implements ApplicationListener<ContextRefreshedEvent> {

    private final ObjectProvider<TextEncryptor> encryptor;
    private final EncryptedValueRekeyService rekeyService;
    private final AtomicBoolean done = new AtomicBoolean();

    public EncryptedValueRekeyOnStartup(ObjectProvider<TextEncryptor> encryptor,
            EncryptedValueRekeyService rekeyService) {
        this.encryptor = encryptor;
        this.rekeyService = rekeyService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!(encryptor.getIfAvailable() instanceof GeneralTextEncryptor general) || !general.rekeys()
                || !done.compareAndSet(false, true)) {
            return;
        }
        try {
            rekeyService.rekey(general);
        } catch (RuntimeException e) {
            LogEvent.logError("Could not rewrite database secrets with this install's encryption key", e);
        }
    }
}
