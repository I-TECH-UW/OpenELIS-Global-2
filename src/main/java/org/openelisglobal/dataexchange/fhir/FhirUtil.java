package org.openelisglobal.dataexchange.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import ca.uhn.fhir.rest.client.interceptor.BearerTokenAuthInterceptor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.hl7.fhir.r4.model.CapabilityStatement;
import org.itech.fhir.dataexport.core.service.FhirClientFetcher;
import org.openelisglobal.config.condition.ConditionalOnProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FhirUtil implements FhirClientFetcher {

    private static final Logger logger = LoggerFactory.getLogger(FhirUtil.class);

    private static final int MAX_RETRY_ATTEMPTS = 3;

    private static final int RETRY_DELAY_MS = 10000;

    @Autowired
    private FhirConfig fhirConfig;

    @Autowired
    private FhirContext fhirContext;

    @Autowired
    private CloseableHttpClient closeableHttpClient;

    @Override
    public IGenericClient getFhirClient(String fhirStorePath) {
        IGenericClient fhirClient = fhirContext.newRestfulGenericClient(fhirStorePath);
        if (!GenericValidator.isBlankOrNull(fhirConfig.getUsername())
                && !fhirConfig.getLocalFhirStorePath().equals(fhirStorePath)) {
            IClientInterceptor authInterceptor = new BasicAuthInterceptor(fhirConfig.getUsername(),
                    fhirConfig.getPassword());
            fhirClient.registerInterceptor(authInterceptor);
        }

        return fhirClient;
    }

    @Bean(name = "clientRegistryFhirClient")
    @ConditionalOnProperty(properties = { "org.openelisglobal.crserver.uri", "org.openelisglobal.crserver.username",
            "org.openelisglobal.crserver.password" }, nonEmpty = { false, false, false })
    public IGenericClient getCRFhirClient() throws Exception {
        if (fhirConfig.getClientRegistryServerUrl() == null || fhirConfig.getClientRegistryServerUrl().isEmpty()
                || fhirConfig.getClientRegistryUserName() == null || fhirConfig.getClientRegistryUserName().isEmpty()
                || fhirConfig.getClientRegistryPassword() == null || fhirConfig.getClientRegistryPassword().isEmpty()) {
            logger.warn("Required properties for clientRegistryFhirClient are missing. Skipping bean creation.");
            return NoOpFhirClientFactory.create();
        }
        IGenericClient fhirClient = createCRFhirClient();
        validateFhirClient(fhirClient);
        return fhirClient;
    }

    private IGenericClient createCRFhirClient() throws Exception {
        Exception lastException = null;

        Thread.sleep(20000);

        for (int attempts = 0; attempts < MAX_RETRY_ATTEMPTS; attempts++) {
            try {
                IGenericClient fhirClient = fhirContext
                        .newRestfulGenericClient(fhirConfig.getClientRegistryServerUrl());
                if (!fhirConfig.getClientRegistryUserName().isEmpty()
                        && !fhirConfig.getClientRegistryPassword().isEmpty()
                        && !fhirConfig.getClientRegistryServerUrl().isEmpty()) {
                    logger.info("CR Server - Url: {}", fhirConfig.getClientRegistryServerUrl());

                    BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor(
                            fhirConfig.getClientRegistryUserName(), fhirConfig.getClientRegistryPassword());
                    fhirClient.registerInterceptor(authInterceptor);
                }

                return fhirClient;
            } catch (Exception e) {
                lastException = e;
                logger.error("Failed to connect to FHIR server (Attempt {}): {}", attempts + 1, e.getMessage(), e);
                Thread.sleep(RETRY_DELAY_MS);
            }
        }

        logger.error("Last exception details:", lastException);
        throw new Exception("Failed to connect to FHIR server after " + MAX_RETRY_ATTEMPTS + " attempts.",
                lastException);
    }

    private void validateFhirClient(IGenericClient fhirClient) throws Exception {
        try {
            CapabilityStatement capabilityStatement = retrieveCapabilityStatement(fhirClient);
            logServerInfo(capabilityStatement, fhirConfig.getClientRegistryServerUrl());
        } catch (Exception e) {
            logger.error("Failed to retrieve CapabilityStatement: {}", e.getMessage(), e);
            throw e;
        }
    }

    private CapabilityStatement retrieveCapabilityStatement(IGenericClient fhirClient) {
        return fhirClient.capabilities().ofType(CapabilityStatement.class).execute();
    }

    private void logServerInfo(CapabilityStatement capabilityStatement, String serverUrl) {
        logger.info("FHIR client connected successfully to {}", serverUrl);
        if (capabilityStatement != null) {
            logger.info("Server name: {}", capabilityStatement.getSoftware().getName());
            logger.info("Server version: {}", capabilityStatement.getSoftware().getVersion());
        }
    }

    public IGenericClient getLocalFhirClient() {
        return fhirContext.newRestfulGenericClient(fhirConfig.getLocalFhirStorePath());
    }

    public IParser getFhirParser() {
        return fhirContext.newJsonParser();
    }

    public IGenericClient getFhirClient(String fhirStorePath, String token) {
        IGenericClient fhirClient = fhirContext.newRestfulGenericClient(fhirStorePath);
        BearerTokenAuthInterceptor authInterceptor = new BearerTokenAuthInterceptor(token);
        fhirClient.registerInterceptor(authInterceptor);
        return fhirClient;
    }

    public IGenericClient getFhirClient(String fhirStorePath, String username, String password) {
        IGenericClient fhirClient = fhirContext.newRestfulGenericClient(fhirStorePath);
        BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor(username, password);
        fhirClient.registerInterceptor(authInterceptor);
        return fhirClient;
    }

    public String getAccessToken(String authUrl, String authUserName, String authPassowrd) throws IOException {
        HttpPost httpPost = new HttpPost(authUrl);

        String json = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", authUserName, authPassowrd);
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = mapper.createObjectNode();
        try (CloseableHttpResponse res = closeableHttpClient.execute(httpPost)) {
            if (res.getStatusLine().getStatusCode() == 200) {
                response = mapper.readTree(EntityUtils.toString(res.getEntity(), StandardCharsets.UTF_8));
            }
        }
        return response.get("access_token").asText();
    }
}
