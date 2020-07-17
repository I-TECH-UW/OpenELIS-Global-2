package org.openelisglobal.externalconnections.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openelisglobal.externalconnections.valueholder.CertificateAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.AuthType;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionAuthenticationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalConnectionAuthenticationDataServiceImpl implements ExternalConnectionAuthenticationDataService {

    @Autowired
    private CertificateAuthenticationDataService certificateAuthenticationDataService;

    @Override
    public Map<AuthType, ExternalConnectionAuthenticationData> getForExternalConnection(Integer externalConnectionId) {
        Map<AuthType, ExternalConnectionAuthenticationData> externalConnectionAuthenticationData = new HashMap<>();
        Optional<CertificateAuthenticationData> certAuthData = certificateAuthenticationDataService
                .getByExternalConnection(externalConnectionId);
        if (certAuthData.isPresent()) {
            externalConnectionAuthenticationData.put(AuthType.CERTIFICATE, certAuthData.get());
        }
        return externalConnectionAuthenticationData;
    }

    @Override
    public Integer insert(ExternalConnectionAuthenticationData authData) {
        switch (authData.getAuthenticationType()) {
        case CERTIFICATE:
            return certificateAuthenticationDataService.insert((CertificateAuthenticationData) authData);
        case BASIC:
        case NONE:
        default:
            throw new RuntimeException();
        }

    }

    @Override
    public ExternalConnectionAuthenticationData update(ExternalConnectionAuthenticationData authData) {
        switch (authData.getAuthenticationType()) {
        case CERTIFICATE:
            return certificateAuthenticationDataService.update((CertificateAuthenticationData) authData);
        case BASIC:
        case NONE:
        default:
            throw new RuntimeException();
        }
    }

    @Override
    public ExternalConnectionAuthenticationData save(ExternalConnectionAuthenticationData authData) {
        switch (authData.getAuthenticationType()) {
        case CERTIFICATE:
            return certificateAuthenticationDataService.save((CertificateAuthenticationData) authData);
        case BASIC:
        case NONE:
        default:
            throw new RuntimeException();
        }
    }
}
