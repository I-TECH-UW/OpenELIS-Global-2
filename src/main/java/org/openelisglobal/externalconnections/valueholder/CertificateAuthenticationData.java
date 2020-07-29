package org.openelisglobal.externalconnections.valueholder;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.openelisglobal.externalconnections.valueholder.ExternalConnection.AuthType;

@Entity
@Table(name = "certificate_authentication_data")
public class CertificateAuthenticationData extends ExternalConnectionAuthenticationData {

    private static final long serialVersionUID = 5731070741617843373L;

    @Override
    public AuthType getAuthenticationType() {
        return AuthType.CERTIFICATE;
    }

    @Override
    public boolean hasAuthenticationString() {
        return false;
    }

    @Override
    public String getAuthenticationString() {
        return null;
    }

}
