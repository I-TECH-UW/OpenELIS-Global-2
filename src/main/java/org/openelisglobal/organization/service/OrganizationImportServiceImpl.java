package org.openelisglobal.organization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.fhir.exception.FhirGeneralException;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.organization.valueholder.OrganizationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

@Service
public class OrganizationImportServiceImpl implements OrganizationImportService {

    @Value("${org.openelisglobal.facilitylist.fhirstore:}")
    private String facilityFhirStore;

    @Autowired
    private FhirContext fhirContext;
    @Autowired
    private FhirUtil fhirUtil;
    @Autowired
    private FhirTransformService fhirTransformService;
    @Autowired
    private FhirPersistanceService fhirPersistanceService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationTypeService organizationTypeService;

    @Override
    @Transactional
    @Scheduled(initialDelay = 1000, fixedRate = 24 * 60 * 60 * 1000)
    public void importOrganizationList() throws FhirGeneralException {
        if (!GenericValidator.isBlankOrNull(facilityFhirStore)) {
            IGenericClient client = fhirUtil.getFhirClient(facilityFhirStore);
            List<Bundle> responseBundles = new ArrayList<>();
            Bundle responseBundle = client.search().forResource(org.hl7.fhir.r4.model.Organization.class)
                    .returnBundle(Bundle.class).execute();
            responseBundles.add(responseBundle);
            while (responseBundle.getLink(IBaseBundle.LINK_NEXT) != null) {
                responseBundle = client.loadPage().next(responseBundle).execute();
                responseBundles.add(responseBundle);
            }
            organizationService.deactivateAllOrganizations();
            importFromBundle(client, responseBundles);
        }
        DisplayListService.getInstance().refreshList(ListType.REFERRAL_ORGANIZATIONS);
        DisplayListService.getInstance().refreshList(ListType.SAMPLE_PATIENT_REFERRING_CLINIC);
        DisplayListService.getInstance().refreshList(ListType.PATIENT_HEALTH_REGIONS);
    }

    private void importFromBundle(IGenericClient client, List<Bundle> responseBundles) throws FhirGeneralException {
        Map<String, Resource> remoteFhirOrganizations = new HashMap<>();

        Map<String, OrganizationObjects> organizationObjectsByOrgUUID = new HashMap<>();
        Map<String, OrganizationType> orgTypesByName = new HashMap<>();

        Map<String, Organization> dbOrgsByUUID = new HashMap<>();
        Map<String, OrganizationType> dbOrgTypesByName = new HashMap<>();

        for (Bundle responseBundle : responseBundles) {
            for (BundleEntryComponent entry : responseBundle.getEntry()) {
                if (entry.hasResource() && entry.getResource().getResourceType().equals(ResourceType.Organization)) {
                    org.hl7.fhir.r4.model.Organization fhirOrganization = (org.hl7.fhir.r4.model.Organization) entry
                            .getResource();
                    remoteFhirOrganizations.put(fhirOrganization.getIdElement().getIdPart(), fhirOrganization);

                    OrganizationObjects organizationObjects = organizationObjectsByOrgUUID
                            .getOrDefault(fhirOrganization.getIdElement().getIdPart(), new OrganizationObjects());

                    // preserve the mappings between the objects
                    organizationObjects.organization = fhirTransformService.transformToOrganization(fhirOrganization);
                    organizationObjects.organizationTypeNames = organizationObjects.organization.getOrganizationTypes()
                            .stream().map(e -> e.getName()).collect(Collectors.toSet());
                    if (fhirOrganization.getPartOf() != null && !GenericValidator
                            .isBlankOrNull(fhirOrganization.getPartOf().getReferenceElement().getIdPart())) {
                        organizationObjects.parentUUID = fhirOrganization.getPartOf().getReferenceElement().getIdPart();
                        organizationObjects.organization.setOrganization(null);
                    }

                    for (OrganizationType orgType : organizationObjects.organization.getOrganizationTypes()) {
                        orgTypesByName.putIfAbsent(orgType.getName(), orgType);
                        orgType.setOrganizations(new HashSet<>());
                    }
                    organizationObjects.organization.setOrganizationTypes(new HashSet<>());
                    organizationObjectsByOrgUUID.put(fhirOrganization.getIdElement().getIdPart(), organizationObjects);
                }
            }
        }

        // ensure the org types are in the db
        for (Entry<String, OrganizationType> entry : orgTypesByName.entrySet()) {
            dbOrgTypesByName.put(entry.getKey(), this.insertOrUpdateOrganizationType(entry.getValue()));
        }

        for (OrganizationObjects organizationObjects : organizationObjectsByOrgUUID.values()) {
            try {
            Organization curOrganization = organizationObjects.organization;
            // ensure the parent org is in the db
            if (!GenericValidator.isBlankOrNull(organizationObjects.parentUUID)) {
                Organization dbParentOrg;
                if (dbOrgsByUUID.containsKey(organizationObjects.parentUUID)) {
                    dbParentOrg = dbOrgsByUUID.get(organizationObjects.parentUUID);
                } else {
                    dbParentOrg = insertOrUpdateOrganization(
                            organizationObjectsByOrgUUID.get(organizationObjects.parentUUID).organization);
                    dbOrgsByUUID.put(organizationObjects.parentUUID, dbParentOrg);
                }
                // set the parent org to the db parent org
                curOrganization.setOrganization(dbParentOrg);
            }

            // save this org with all it's db pointers set
            Organization dbOrg = insertOrUpdateOrganization(curOrganization);
            dbOrgsByUUID.put(dbOrg.getFhirUuidAsString(), dbOrg);
            for (String orgTypeName : organizationObjects.organizationTypeNames) {
                OrganizationType orgType = dbOrgTypesByName.get(orgTypeName);
                dbOrg.getOrganizationTypes().add(orgType);
                orgType.getOrganizations().add(dbOrg);
            }
            } catch (LIMSRuntimeException e) {
                LogEvent.logError(e);
                LogEvent.logError(this.getClass().getName(), "",
                        "error importing an organization with id: "
                                + organizationObjects.organization.getFhirUuidAsString());
            }

        }

        // import fhir organizations as is
        fhirPersistanceService.updateFhirResourcesInFhirStore(remoteFhirOrganizations);
    }

    private OrganizationType insertOrUpdateOrganizationType(OrganizationType orgType) {
        OrganizationType dbOrgType = organizationTypeService.getOrganizationTypeByName(orgType.getName());
        if (dbOrgType != null) {
            dbOrgType.setDescription(orgType.getDescription());
            dbOrgType.setOrganizations(orgType.getOrganizations());
        } else {
            dbOrgType = organizationTypeService.save(orgType);
        }
        return dbOrgType;
    }

    private Organization insertOrUpdateOrganization(Organization organization) {
        Organization dbOrg = organizationService.getOrganizationByFhirId(organization.getFhirUuidAsString());
        if (dbOrg != null) {
            dbOrg.setOrganizationName(organization.getOrganizationName());
            dbOrg.setFhirUuid(organization.getFhirUuid());
            dbOrg.setStreetAddress(organization.getStreetAddress());
            dbOrg.setCity(organization.getCity());
            dbOrg.setZipCode(organization.getZipCode());
            dbOrg.setState(organization.getState());
            dbOrg.setInternetAddress(organization.getInternetAddress());
            dbOrg.setIsActive(organization.getIsActive());
        } else {
            dbOrg = organizationService.save(organization);
        }
        return dbOrg;
    }

    public class OrganizationObjects {
        public Organization organization;
        public String parentUUID;
        public Set<String> organizationTypeNames;
    }

}
