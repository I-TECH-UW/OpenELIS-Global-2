package org.openelisglobal.admin.controller;

import java.io.IOException;
import org.openelisglobal.dataexchange.fhir.exception.FhirGeneralException;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.organization.service.OrganizationImportService;
import org.openelisglobal.provider.service.ProviderImportService;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/import")
public class ImportController {

  @GetMapping(value = "/all")
  public void importAll() throws FhirLocalPersistingException, FhirGeneralException, IOException {
    SpringContext.getBean(OrganizationImportService.class).importOrganizationList();
    SpringContext.getBean(ProviderImportService.class).importPractitionerList();
  }

  @GetMapping(value = "/organization")
  public void importOrganizations()
      throws FhirLocalPersistingException, FhirGeneralException, IOException {
    SpringContext.getBean(OrganizationImportService.class).importOrganizationList();
  }

  @GetMapping(value = "/provider")
  public void importProviders()
      throws FhirLocalPersistingException, FhirGeneralException, IOException {
    SpringContext.getBean(ProviderImportService.class).importPractitionerList();
  }
}
