/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package org.openelisglobal.common.services;

import java.util.List;

import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.service.OrganizationTypeService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.organization.valueholder.OrganizationType;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.requester.service.RequesterTypeService;
import org.openelisglobal.requester.service.SampleRequesterService;
import org.openelisglobal.requester.valueholder.RequesterType;
import org.openelisglobal.requester.valueholder.SampleRequester;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 */
@Service
@Scope("prototype")
@DependsOn({ "springContext" })
public class RequesterService {
    public static final String REFERRAL_ORG_TYPE = "referring clinic";
    public static final String REFERRAL_ORG_TYPE_ID;

    private static SampleRequesterService sampleRequesterService = SpringContext.getBean(SampleRequesterService.class);
    private static PersonService personService = SpringContext.getBean(PersonService.class);
    private static OrganizationTypeService organizationTypeService = SpringContext
            .getBean(OrganizationTypeService.class);
    private static OrganizationService organizationService = SpringContext.getBean(OrganizationService.class);
    private static RequesterTypeService requesterTypeService = SpringContext.getBean(RequesterTypeService.class);

    private String sampleId;
    private Person person;
    private List<SampleRequester> requesters;
    private Organization organization;

    public enum Requester {
        PERSON, ORGANIZATION;

        long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

    static {
        RequesterType requesterType = requesterTypeService.getRequesterTypeByName("organization");
        Requester.ORGANIZATION.setId(requesterType != null ? Long.parseLong(requesterType.getId()) : -1L);

        requesterType = requesterTypeService.getRequesterTypeByName("provider");
        Requester.PERSON.setId(requesterType != null ? Long.parseLong(requesterType.getId()) : -1L);

        OrganizationType orgType = organizationTypeService.getOrganizationTypeByName(REFERRAL_ORG_TYPE);

        REFERRAL_ORG_TYPE_ID = orgType == null ? null : orgType.getId();
    }

    public RequesterService(String sampleId) {
        setSampleId(sampleId);
    }

    public RequesterService() {
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getRequesterFirstName() {
        return getPerson() == null ? null : personService.getFirstName(getPerson());
    }

    public String getRequesterLastName() {
        return getPerson() == null ? null : personService.getLastName(getPerson());
    }

    public String getRequesterLastFirstName() {
        return getPerson() == null ? null : personService.getLastFirstName(getPerson());
    }

    public String getWorkPhone() {
        return getPerson() == null ? null : personService.getWorkPhone(getPerson());
    }

    public String getCellPhone() {
        return getPerson() == null ? null : personService.getCellPhone(getPerson());
    }

    public String getFax() {
        return getPerson() == null ? null : personService.getFax(getPerson());
    }

    public String getEmail() {
        return getPerson() == null ? null : personService.getEmail(getPerson());
    }

    public String getReferringSiteId() {
        return getOrganization() == null ? null : getOrganization().getId();
    }

    public String getReferringSiteName() {
        return getOrganization() == null ? null : getOrganization().getOrganizationName();
    }

    public String getReferringSiteCode() {
        return getOrganization() == null ? null : getOrganization().getCode();
    }

    public Person getPerson() {
        if (person == null) {
            buildRequesters();
        }

        return person;
    }

    public Organization getOrganization() {
        if (organization == null) {
            buildRequesters();
        }

        return organization;
    }

    public SampleRequester getSampleRequesterByType(Requester type, boolean createIfNotFound) {
        if (requesters == null) {
            buildRequesters();
        }

        for (SampleRequester requester : requesters) {
            if (requester.getRequesterTypeId() == type.getId()) {
                return requester;
            }
        }

        // reachable only if existing requester not found
        if (createIfNotFound) {
            SampleRequester newRequester = new SampleRequester();
            newRequester.setRequesterTypeId(type.getId());
            newRequester.setSampleId(Long.parseLong(sampleId));

            return newRequester;
        }

        return null;
    }

    private void buildRequesters() {
        requesters = sampleRequesterService.getRequestersForSampleId(sampleId);
        for (SampleRequester requester : requesters) {
            if (requester.getRequesterTypeId() == Requester.PERSON.getId()) {
                person = personService.getPersonById(String.valueOf(requester.getRequesterId()));
            } else if (requester.getRequesterTypeId() == Requester.ORGANIZATION.getId()) {
                organization = organizationService.getOrganizationById(String.valueOf(requester.getRequesterId()));
            }
        }
    }
}
