package org.openelisglobal.externalconnections.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.openelisglobal.externalconnections.valueholder.BasicAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.CertificateAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionAuthenticationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class ExternalConnectionTestServiceImpl implements ExternalConnectionTestService {

	@Autowired
	private CloseableHttpClient httpClient;

	@Override
	public Map<RequestMethod, Integer> testConnection(ExternalConnection connection,
			@Nullable ExternalConnectionAuthenticationData authData){
		HttpUriRequest optionsRequest = new HttpOptions(connection.getUri());
		HttpUriRequest getRequest = new HttpGet(connection.getUri());
		if (authData != null) {
			if (authData instanceof BasicAuthenticationData) {
				BasicAuthenticationData basicAuthData = (BasicAuthenticationData) authData;
				optionsRequest.setHeader(HttpHeaders.AUTHORIZATION, basicAuthData.getAuthenticationString());
				getRequest.setHeader(HttpHeaders.AUTHORIZATION, basicAuthData.getAuthenticationString());
			} else if (authData instanceof CertificateAuthenticationData) {
				// do nothing, no authentication is needed
			}
		}
		Map<RequestMethod, Integer> resultsMap = new HashMap<>();
		try (CloseableHttpResponse response = httpClient.execute(optionsRequest)) {
			resultsMap.put(RequestMethod.OPTIONS, response.getStatusLine().getStatusCode());
		} catch (IOException e) {
			resultsMap.put(RequestMethod.OPTIONS, -1);
		}
		try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
			resultsMap.put(RequestMethod.GET, response.getStatusLine().getStatusCode());
		} catch (IOException e) {
			resultsMap.put(RequestMethod.GET, -1);
		}
		return resultsMap;
	}


}
