package org.openelisglobal.externalconnections.controller;

import java.util.Map;

import org.openelisglobal.externalconnections.form.ExternalConnectionForm;
import org.openelisglobal.externalconnections.service.ExternalConnectionAuthenticationDataService;
import org.openelisglobal.externalconnections.service.ExternalConnectionService;
import org.openelisglobal.externalconnections.service.ExternalConnectionTestService;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.AuthType;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionAuthenticationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
