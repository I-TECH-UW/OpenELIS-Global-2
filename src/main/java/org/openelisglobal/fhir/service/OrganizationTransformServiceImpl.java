package org.openelisglobal.fhir.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.ResourceType;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.organization.valueholder.OrganizationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationTransformServiceImpl implements OrganizationTransformService {

    @Autowired
    private FhirConfig fhirConfig;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private FhirCommonTransformService common;

    @Transactional(readOnly = true)

    @Override
    public org.hl7.fhir.r4.model.Organization transformToFhirOrganization(Organization organization) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "transformToFhirOrganization",
                "transformToFhirOrganization called");

        org.hl7.fhir.r4.model.Organization fhirOrganization = new org.hl7.fhir.r4.model.Organization();
        fhirOrganization
                .setId(organization.getFhirUuid() == null ? organization.getId() : organization.getFhirUuidAsString());
        fhirOrganization.getMeta().setLastUpdated(organization.getLastupdated());
        fhirOrganization.setName(organization.getOrganizationName());
        fhirOrganization.setActive(!IActionConstants.NO.equals(organization.getIsActive()));
        this.setFhirOrganizationIdentifiers(fhirOrganization, organization);
        this.setFhirAddressInfo(fhirOrganization, organization);
        this.setFhirOrganizationTypes(fhirOrganization, organization);
        if (organization.getOrganization() != null && organization.getOrganization().getFhirUuid() != null) {
            fhirOrganization.setPartOf(common.createReferenceFor(ResourceType.Organization,
                    organization.getOrganization().getFhirUuidAsString()));
        }
        return fhirOrganization;
    }

    @Transactional(readOnly = true)

    @Override
    public Organization transformToOrganization(org.hl7.fhir.r4.model.Organization fhirOrganization) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "transformToOrganization", "transformToOrganization called");

        Organization organization = new Organization();
        organization.setOrganizationName(fhirOrganization.getName());
        organization.setIsActive(Boolean.FALSE == fhirOrganization.getActiveElement().getValue() ? IActionConstants.NO
                : IActionConstants.YES);

        setOeOrganizationIdentifiers(organization, fhirOrganization);
        setOeOrganizationAddressInfo(organization, fhirOrganization);
        setOeOrganizationTypes(organization, fhirOrganization);

        organization.setMlsLabFlag(IActionConstants.NO);
        organization.setMlsSentinelLabFlag(IActionConstants.NO);

        return organization;
    }

    private void setOeOrganizationIdentifiers(Organization organization,
            org.hl7.fhir.r4.model.Organization fhirOrganization) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "setOeOrganizationIdentifiers",
                "setOeOrganizationIdentifiers called");

        organization.setFhirUuid(UUID.fromString(fhirOrganization.getIdElement().getIdPart()));
        for (Identifier identifier : fhirOrganization.getIdentifier()) {
            if (identifier.getSystem().equals(fhirConfig.getOeFhirSystem() + "/org_cliaNum")) {
                organization.setCliaNum(identifier.getValue());
            } else if (identifier.getSystem().equals(fhirConfig.getOeFhirSystem() + "/org_shortName")) {
                organization.setShortName(identifier.getValue());
            } else if (identifier.getSystem().equals(fhirConfig.getOeFhirSystem() + "/org_code")) {
                organization.setCode(identifier.getValue());
            } else if (identifier.getSystem().equals(fhirConfig.getOeFhirSystem() + "/org_uuid")) {
                organization.setFhirUuid(UUID.fromString(identifier.getValue()));
            }
        }
    }

    private void setFhirOrganizationIdentifiers(org.hl7.fhir.r4.model.Organization fhirOrganization,
            Organization organization) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "setFhirOrganizationIdentifiers",
                "setFhirOrganizationIdentifiers called");

        if (!GenericValidator.isBlankOrNull(organization.getCliaNum())) {
            fhirOrganization.addIdentifier(new Identifier().setSystem(fhirConfig.getOeFhirSystem() + "/org_cliaNum")
                    .setValue(organization.getCliaNum()));
        }
        if (!GenericValidator.isBlankOrNull(organization.getShortName())) {
            fhirOrganization.addIdentifier(new Identifier().setSystem(fhirConfig.getOeFhirSystem() + "/org_shortName")
                    .setValue(organization.getShortName()));
        }
        if (!GenericValidator.isBlankOrNull(organization.getCode())) {
            fhirOrganization.addIdentifier(new Identifier().setSystem(fhirConfig.getOeFhirSystem() + "/org_code")
                    .setValue(organization.getCode()));
        }
        if (!GenericValidator.isBlankOrNull(organization.getCode())) {
            fhirOrganization.addIdentifier(new Identifier().setSystem(fhirConfig.getOeFhirSystem() + "/org_uuid")
                    .setValue(organization.getFhirUuidAsString()));
        }
        Identifier facilityId = common.createFacilityIdentifier();
        if (facilityId != null) {
            fhirOrganization.addIdentifier(facilityId);
        }
    }

    private void setOeOrganizationTypes(Organization organization,
            org.hl7.fhir.r4.model.Organization fhirOrganization) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "setOeOrganizationTypes", "setOeOrganizationTypes called");

        Set<OrganizationType> orgTypes = new HashSet<>();
        OrganizationType orgType = null;
        for (CodeableConcept type : fhirOrganization.getType()) {
            for (Coding coding : type.getCoding()) {
                if (coding.getSystem() != null
                        && coding.getSystem().equals(fhirConfig.getOeFhirSystem() + "/orgType")) {
                    orgType = new OrganizationType();
                    orgType.setName(coding.getCode());
                    orgType.setDescription(type.getText());
                    orgType.setNameKey("org_type." + coding.getCode() + ".name");
                    orgType.setOrganizations(new HashSet<>());
                    orgType.getOrganizations().add(organization);
                    orgTypes.add(orgType);
                }
            }
        }
        organization.setOrganizationTypes(orgTypes);
    }

    private void setFhirOrganizationTypes(org.hl7.fhir.r4.model.Organization fhirOrganization,
            Organization organization) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "setFhirOrganizationTypes",
                "setFhirOrganizationTypes called");

        Set<OrganizationType> orgTypes = organizationService.get(organization.getId()).getOrganizationTypes();
        for (OrganizationType orgType : orgTypes) {
            fhirOrganization.addType(new CodeableConcept() //
                    .setText(orgType.getDescription()) //
                    .addCoding(new Coding() //
                            .setSystem(fhirConfig.getOeFhirSystem() + "/orgType") //
                            .setCode(orgType.getName())));
        }
    }

    private void setOeOrganizationAddressInfo(Organization organization,
            org.hl7.fhir.r4.model.Organization fhirOrganization) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "setOeOrganizationAddressInfo",
                "setOeOrganizationAddressInfo called");

        organization.setStreetAddress(fhirOrganization.getAddressFirstRep().getLine().stream()
                .map(e -> e.asStringValue()).collect(Collectors.joining("\\n")));
        organization.setCity(fhirOrganization.getAddressFirstRep().getCity());
        organization.setState(fhirOrganization.getAddressFirstRep().getState());
        organization.setZipCode(fhirOrganization.getAddressFirstRep().getPostalCode());
    }

    private void setFhirAddressInfo(org.hl7.fhir.r4.model.Organization fhirOrganization, Organization organization) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "setFhirAddressInfo", "setFhirAddressInfo called");

        if (!GenericValidator.isBlankOrNull(organization.getStreetAddress())) {
            fhirOrganization.getAddressFirstRep().addLine(organization.getStreetAddress());
        }
        if (!GenericValidator.isBlankOrNull(organization.getCity())) {
            fhirOrganization.getAddressFirstRep().setCity(organization.getCity());
        }
        if (!GenericValidator.isBlankOrNull(organization.getState())) {
            fhirOrganization.getAddressFirstRep().setState(organization.getState());
        }
        if (!GenericValidator.isBlankOrNull(organization.getZipCode())) {
            fhirOrganization.getAddressFirstRep().setPostalCode(organization.getZipCode());
        }
    }
}
