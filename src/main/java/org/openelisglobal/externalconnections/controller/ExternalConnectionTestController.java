package org.openelisglobal.externalconnections.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.openelisglobal.externalconnections.form.ExternalConnectionForm;
import org.openelisglobal.externalconnections.service.ExternalConnectionAuthenticationDataService;
import org.openelisglobal.externalconnections.service.ExternalConnectionService;
import org.openelisglobal.externalconnections.service.ExternalConnectionTestService;
import org.openelisglobal.externalconnections.valueholder.BasicAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.CertificateAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.AuthType;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.ProgrammedConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalConnectionTestController {

	@Autowired
	private ExternalConnectionTestService externalConnectionTestService;
	@Autowired
	private ExternalConnectionService externalConnectionService;
	@Autowired
	private ExternalConnectionAuthenticationDataService externalConnectionAuthDataService;

    private static final String[] ALLOWED_FIELDS = new String[] { "externalConnection", "basicAuthenticationData", "certificateAuthenticationData",
            "externalConnectionContacts", "authenticationTypes", "programmedConnections" };
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }
    
	@PostMapping("/TestExternalConnection")
	public ResponseEntity<Map<RequestMethod, Integer>> testConnection(ExternalConnectionForm form) {

		ExternalConnection connection = form.getExternalConnection();
		ExternalConnectionAuthenticationData authData = null;
		if (connection.getActiveAuthenticationType().equals(AuthType.BASIC)) {
			authData = form.getBasicAuthenticationData();
		} else if (connection.getActiveAuthenticationType().equals(AuthType.CERTIFICATE)) {
			authData = form.getCertificateAuthenticationData();
		}

		Map<RequestMethod, Integer> status;
		status = externalConnectionTestService.testConnection(connection, authData);
		return ResponseEntity.ok(status);

	}

    @GetMapping("/TestExternalConnection")
	public ResponseEntity<Map<RequestMethod, Integer>> testConnection(
            @RequestParam("id") Integer externalConnectionId) {

		ExternalConnection connection = externalConnectionService.get(externalConnectionId);
		Map<AuthType, ExternalConnectionAuthenticationData> connectionAuthDataMap = externalConnectionAuthDataService
				.getForExternalConnection(externalConnectionId);

		Map<RequestMethod, Integer> status;
		status = externalConnectionTestService.testConnection(connection,
				connectionAuthDataMap.get(connection.getActiveAuthenticationType()));
		return ResponseEntity.ok(status);

	}

}
