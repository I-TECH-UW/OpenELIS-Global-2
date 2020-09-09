package org.openelisglobal.externalconnections.service;

import java.util.Map;

import org.openelisglobal.externalconnections.valueholder.ExternalConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionAuthenticationData;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ExternalConnectionTestService {

	Map<RequestMethod, Integer> testConnection(ExternalConnection connection,
			ExternalConnectionAuthenticationData authData);

}
