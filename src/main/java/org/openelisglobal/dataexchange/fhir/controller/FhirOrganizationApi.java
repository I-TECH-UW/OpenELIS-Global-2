package org.openelisglobal.dataexchange.fhir.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.r4.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.uhn.fhir.context.FhirContext;

@RestController
@RequestMapping("/fhir")
public class FhirOrganizationApi {

    @Autowired
    private FhirContext fhirContext;

    public static final String RESOURCE_NAME = "Organization";

    @PostMapping(value = "/" + RESOURCE_NAME)
    public ResponseEntity<String> testPost(HttpServletRequest request) {
        String json;
        try {
            json = IOUtils.toString(request.getInputStream(), "UTF-8");
            System.out.println("received in OE:  " + json);
            Organization organization = (Organization) fhirContext.newJsonParser().parseResource(json);
            System.out.println("Got organization named: " + organization.getName());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("could not parse recived object into a usable object");
        }
        return ResponseEntity.ok("request processed");
    }

}
