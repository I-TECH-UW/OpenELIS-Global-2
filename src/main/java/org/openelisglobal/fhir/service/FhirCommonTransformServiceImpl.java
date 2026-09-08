package org.openelisglobal.fhir.service;

import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.common.valueholder.BaseObject;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.service.FhirFacilityOrganizationService;
import org.openelisglobal.dataexchange.fhir.service.TempIdGenerator;
import org.openelisglobal.note.valueholder.Note;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FhirCommonTransformServiceImpl implements FhirCommonTransformService {

    @Autowired
    private FhirConfig fhirConfig;
    @Autowired
    private FhirFacilityOrganizationService facilityOrganizationService;

    @Override
    public List<ContactPoint> transformToTelecom(Person person) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "transformToTelecom", "transformToTelecom called");

        List<ContactPoint> contactPoints = new ArrayList<>();
        if (person.getPrimaryPhone() != null) {
            contactPoints.add(new ContactPoint().setSystem(ContactPointSystem.PHONE).setValue(person.getPrimaryPhone())
                    .setUse(ContactPointUse.MOBILE));
        }

        if (person.getEmail() != null) {
            contactPoints.add(new ContactPoint().setSystem(ContactPointSystem.EMAIL).setValue(person.getEmail()));
        }

        if (person.getFax() != null) {
            contactPoints.add(new ContactPoint().setSystem(ContactPointSystem.FAX).setValue(person.getFax()));
        }

        return contactPoints;
    }

    @Override
    public DateType transformToDateElement(String strDate) throws ParseException {
        LogEvent.logTrace(this.getClass().getSimpleName(), "transformToDateElement", "transformToDateElement called");

        LogEvent.logTrace(this.getClass().getSimpleName(), "transformToDateElement", "transforming date " + strDate);
        if (GenericValidator.isBlankOrNull(strDate)) {
            return null;
        }
        boolean dayAmbiguous = false;
        boolean monthAmbiguous = false;
        // TODO look at this logic for detecting ambiguity
        if (strDate.contains(DateUtil.AMBIGUOUS_DATE_SEGMENT)) {
            strDate = strDate.replaceFirst(DateUtil.AMBIGUOUS_DATE_SEGMENT, "01");
            dayAmbiguous = true;
        }
        if (strDate.contains(DateUtil.AMBIGUOUS_DATE_SEGMENT)) {
            strDate = strDate.replaceFirst(DateUtil.AMBIGUOUS_DATE_SEGMENT, "01");
            monthAmbiguous = true;
        }
        Date birthDate = new SimpleDateFormat(DateUtil.getDateFormat()).parse(strDate);

        DateType dateType = new DateType();
        if (monthAmbiguous) {
            dateType.setValue(birthDate, TemporalPrecisionEnum.YEAR);
        } else if (dayAmbiguous) {
            dateType.setValue(birthDate, TemporalPrecisionEnum.MONTH);
        } else {
            dateType.setValue(birthDate, TemporalPrecisionEnum.DAY);
        }
        return dateType;
    }

    @Override
    public <T extends BaseObject<?>> T getItemByFhirId(String fhirUuid, BaseObjectService<T, ?> service) {

        if (fhirUuid == null) {
            return null;
        }

        try {
            List<T> matches = service.getAllMatching("fhirUuid", UUID.fromString(fhirUuid));
            return matches.isEmpty() ? null : matches.get(0);
        } catch (IllegalArgumentException e) {
            LogEvent.logError(getClass().getSimpleName(), "getItemByFhirId", "Invalid UUID: " + fhirUuid);
            return null;
        }
    }

    @Override
    public Annotation transformNoteToAnnotation(Note note) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "transformNoteToAnnotation",
                "transformNoteToAnnotation called");

        Annotation annotation = new Annotation();
        annotation.setText(note.getText());
        return annotation;
    }

    @Override
    public boolean setTempIdIfMissing(Resource resource, TempIdGenerator tempIdGenerator) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "setTempIdIfMissing", "setTempIdIfMissing called");

        if (GenericValidator.isBlankOrNull(resource.getId())) {
            resource.setId(tempIdGenerator.getNextId());
            return true;
        }
        return false;
    }

    @Override
    public Reference createReferenceFor(Resource resource) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "createReferenceFor", "createReferenceFor called");

        if (resource == null) {
            return null;
        }
        Reference reference = new Reference(resource);
        reference.setReference(resource.getResourceType() + "/" + resource.getIdElement().getIdPart());
        return reference;
    }

    @Override
    public Reference createReferenceFor(ResourceType resourceType, String id) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "createReferenceFor", "createReferenceFor called");

        if (GenericValidator.isBlankOrNull(id)) {
            LogEvent.logWarn(this.getClass().getName(), "createReferenceFor",
                    "null or empty id used in resource:" + resourceType + "/" + id);
        }
        Reference reference = new Reference();
        reference.setReference(resourceType + "/" + id);
        return reference;
    }

    @Override
    public String getIdFromLocation(String location) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "getIdFromLocation", "getIdFromLocation called");

        String id = location.substring(location.indexOf("/") + 1);
        while (id.lastIndexOf("/") > 0) {
            id = id.substring(0, id.lastIndexOf("/"));
        }
        return id;
    }

    @Override
    public Identifier createIdentifier(String system, String value) {
        LogEvent.logTrace(this.getClass().getSimpleName(), "createIdentifier", "createIdentifier called");

        Identifier identifier = new Identifier();
        identifier.setValue(value);

        if (Objects.equals(system, fhirConfig.getOeFhirSystem() + "/pat_nationalId")) {
            identifier.setUse(Identifier.IdentifierUse.OFFICIAL);
        } else {
            identifier.setUse(Identifier.IdentifierUse.USUAL);
        }

        identifier.setSystem(system);
        return identifier;
    }

    /**
     * Creates a facility identifier that links a FHIR resource to this OpenELIS
     * facility. This identifier uses the facility ID and includes the facility
     * Organization as the assigner.
     *
     * @return the facility identifier, or null if facility is not initialized
     */

    @Override
    public Identifier createFacilityIdentifier() {
        String facilityId = facilityOrganizationService.getFacilityId();
        String identifierSystem = facilityOrganizationService.getFacilityIdentifierSystem();
        Reference assignerRef = facilityOrganizationService.getFacilityOrganizationReference();

        if (facilityId == null) {
            return null;
        }

        Identifier identifier = new Identifier();
        identifier.setUse(Identifier.IdentifierUse.OFFICIAL);
        identifier.setSystem(identifierSystem);
        identifier.setValue(facilityId);

        if (assignerRef != null) {
            identifier.setAssigner(assignerRef);
        }

        return identifier;
    }

    @Override
    public void addHumanNameToPerson(HumanName humanName, Person person) {
        person.setFirstName(
                humanName.getGivenAsSingleString() == null ? "" : humanName.getGivenAsSingleString().strip());
        person.setLastName(humanName.getFamily() == null ? "" : humanName.getFamily().strip());
    }

    @Override
    public void addTelecomToPerson(List<ContactPoint> telecoms, Person person) {
        for (ContactPoint contact : telecoms) {
            String contactValue = contact.getValue();
            if (ContactPointSystem.EMAIL.equals(contact.getSystem())) {
                person.setEmail(contactValue);
            } else if (ContactPointSystem.FAX.equals(contact.getSystem())) {
                person.setFax(contactValue);
            } else if (ContactPointSystem.PHONE.equals(contact.getSystem())
                    && ContactPointUse.MOBILE.equals(contact.getUse())) {
                person.setCellPhone(contactValue);
                person.setPrimaryPhone(contactValue);
            } else if (ContactPointSystem.PHONE.equals(contact.getSystem())
                    && ContactPointUse.HOME.equals(contact.getUse())) {
                person.setHomePhone(contactValue);
                if (GenericValidator.isBlankOrNull(person.getPrimaryPhone())) {
                    person.setPrimaryPhone(contactValue);
                }
            } else if (ContactPointSystem.PHONE.equals(contact.getSystem())
                    && ContactPointUse.WORK.equals(contact.getUse())) {
                person.setWorkPhone(contactValue);
                if (GenericValidator.isBlankOrNull(person.getPrimaryPhone())) {
                    person.setPrimaryPhone(contactValue);
                }
            }
        }
    }
}
