package org.openelisglobal.security;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.openelisglobal.common.log.LogEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Rewrites the database secrets written with the previous encryption key with
 * the install's own key. Running it again changes nothing.
 */
@Service
public class EncryptedValueRekeyService {

    private record EncryptedColumn(String table, String column, String condition) {
    }

    /** Every column the general text encryptor writes. */
    private static final List<EncryptedColumn> ENCRYPTED = List.of(
            new EncryptedColumn("clinlims.delivery_channel", "secret", ""),
            new EncryptedColumn("clinlims.eqa_panel_sample", "target_value", ""),
            new EncryptedColumn("clinlims.basic_authentication_data", "password", ""),
            new EncryptedColumn("clinlims.client_results_view", "password", ""),
            new EncryptedColumn("clinlims.site_information", "value", " AND encrypted"),
            new EncryptedColumn("clinlims.analyzer_bridge_pairing", "client_private_key", ""));

    @PersistenceContext
    private EntityManager entityManager;

    /** Returns how many values were rewritten. */
    @Transactional
    public int rekey(GeneralTextEncryptor encryptor) {
        int rewritten = 0;
        for (EncryptedColumn encrypted : ENCRYPTED) {
            String where = " WHERE " + encrypted.column() + " IS NOT NULL" + encrypted.condition();
            @SuppressWarnings("unchecked")
            List<String> values = entityManager
                    .createNativeQuery("SELECT DISTINCT " + encrypted.column() + " FROM " + encrypted.table() + where)
                    .getResultList();
            for (String value : values) {
                Optional<String> rekeyed = encryptor.rekey(value);
                if (rekeyed.isPresent()) {
                    rewritten += entityManager
                            .createNativeQuery("UPDATE " + encrypted.table() + " SET " + encrypted.column()
                                    + " = :rekeyed" + where + " AND " + encrypted.column() + " = :value")
                            .setParameter("rekeyed", rekeyed.get()).setParameter("value", value).executeUpdate();
                }
            }
        }
        if (rewritten > 0) {
            LogEvent.logInfo(getClass().getSimpleName(), "rekey",
                    "Rewrote " + rewritten + " database secrets with this install's encryption key");
        }
        return rewritten;
    }
}
