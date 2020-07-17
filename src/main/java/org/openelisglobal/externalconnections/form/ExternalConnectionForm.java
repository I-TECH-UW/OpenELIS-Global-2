package org.openelisglobal.externalconnections.form;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.AuthType;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionContact;

public class ExternalConnectionForm extends BaseForm {

    private static final long serialVersionUID = -6449520042353223609L;

    @Valid
    private ExternalConnection externalConnection;

    @Valid
    private Map<AuthType, ExternalConnectionAuthenticationData> externalConnectionAuthData;

    @Valid
    private List<ExternalConnectionContact> externalConnectionContacts;

    private List<AuthType> authenticationTypes;

    public ExternalConnection getExternalConnection() {
        return externalConnection;
    }

    public void setExternalConnection(ExternalConnection externalConnection) {
        this.externalConnection = externalConnection;
    }

    public List<AuthType> getAuthenticationTypes() {
        return authenticationTypes;
    }

    public void setAuthenticationTypes(List<AuthType> authenticationTypes) {
        this.authenticationTypes = authenticationTypes;
    }

    public Map<AuthType, ExternalConnectionAuthenticationData> getExternalConnectionAuthData() {
        return externalConnectionAuthData;
    }

    public void setExternalConnectionAuthData(
            Map<AuthType, ExternalConnectionAuthenticationData> externalConnectionAuthData) {
        this.externalConnectionAuthData = externalConnectionAuthData;
    }

    public List<ExternalConnectionContact> getExternalConnectionContacts() {
        return externalConnectionContacts;
    }

    public void setExternalConnectionContacts(List<ExternalConnectionContact> externalConnectionContacts) {
        this.externalConnectionContacts = externalConnectionContacts;
    }

}
