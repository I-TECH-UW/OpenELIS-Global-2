package org.openelisglobal.externalconnections.valueholder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import org.openelisglobal.common.valueholder.BaseObject;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.AuthType;

@MappedSuperclass
public abstract class ExternalConnectionAuthenticationData extends BaseObject<Integer> {

    private static final long serialVersionUID = 3763992300405868160L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "external_connection_authentication_data_generator")
    @SequenceGenerator(name = "external_connection_authentication_data_generator", sequenceName = "external_connection_authentication_data_seq", allocationSize = 1)
    private Integer id;

    @Valid
    @OneToOne
    @JoinColumn(name = "external_connection_id", referencedColumnName = "id")
    private ExternalConnection externalConnection;

    public abstract AuthType getAuthenticationType();

    public abstract boolean hasAuthenticationString();

    public abstract String getAuthenticationString();

    public ExternalConnection getExternalConnection() {
        return externalConnection;
    }

    public void setExternalConnection(ExternalConnection externalConnection) {
        this.externalConnection = externalConnection;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
