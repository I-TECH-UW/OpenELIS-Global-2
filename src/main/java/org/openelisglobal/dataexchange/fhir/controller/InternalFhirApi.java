package org.openelisglobal.dataexchange.fhir.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.hl7.fhir.r4.model.ResourceType;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.dataexchange.fhir.service.FhirApiWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fhir")
public class InternalFhirApi {


    @Value("${org.openelisglobal.server.uri}")
    private String localServerPath;

    @Value("${org.openelisglobal.datasubscriber.uri}")
    private String dataSubscriberPath;

    @Value("${org.openelisglobal.fhirstore.uri}")
    private String fhirStorePath;

    @Autowired
    FhirApiWorkflowService fhirApiWorkflowService;

    @PostConstruct
    // TODO make this run once for all time, not once per startup
    public void registerExternalApi() throws IOException, URISyntaxException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPost = new HttpPut(dataSubscriberPath);
            BasicNameValuePair param1 = new BasicNameValuePair("resourceGroupName", "openElisOpenMrsBridge");
            BasicNameValuePair param2 = new BasicNameValuePair("sourceServerUri", fhirStorePath);
            BasicNameValuePair param3 = new BasicNameValuePair("notificationUri", localServerPath);
            BasicNameValuePair param4 = new BasicNameValuePair("directSubscription", Boolean.TRUE.toString());
            // TODO change to bearer authorization
            BasicNameValuePair param5 = new BasicNameValuePair("headers",
                    "Authorization: Basic YWRtaW46YWRtaW5BRE1JTiE=");
            HttpEntity entity;
            entity = new UrlEncodedFormEntity(Arrays.asList(param1, param2, param3, param4, param5));
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int responseCode = response.getStatusLine().getStatusCode();
                switch (responseCode) {
                case 200:
                case 201:
                case 202:
                    break;
                default:
                    LogEvent.logError(this.getClass().getName(), "registerExternalApi",
                            "could not successfully subscribe at " + localServerPath);
                    LogEvent.logError(this.getClass().getName(), "registerExternalApi", "responseCode " + responseCode);
                }

            }
        }
    }

    @PutMapping(value = "/{resourceType}/**")
    public ResponseEntity<String> receiveFhirRequest(@PathVariable("resourceType") ResourceType resourceType) {
        fhirApiWorkflowService.processWorkflow(resourceType);

        return ResponseEntity.ok("");
    }



}
