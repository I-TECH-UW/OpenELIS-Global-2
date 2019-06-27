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

package us.mn.state.health.lims.common.services;

import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import spring.service.organization.OrganizationService;
import spring.service.organization.OrganizationTypeService;
import spring.service.person.PersonService;
import spring.service.requester.RequesterTypeService;
import spring.service.requester.SampleRequesterService;
import spring.util.SpringContext;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.requester.valueholder.RequesterType;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;

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
	private PersonService personPersonService;
	private Organization organization;

	public static enum Requester {
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
		return personService == null ? null : personService.getFirstName(person);
	}

	public String getRequesterLastName() {
		return personService == null ? null : personService.getLastName(person);
	}

	public String getRequesterLastFirstName() {
		return personService == null ? null : personService.getLastFirstName(person);
	}

	public String getWorkPhone() {
		return personService == null ? null : personService.getWorkPhone(person);
	}

	public String getCellPhone() {
		return personService == null ? null : personService.getCellPhone(person);
	}

	public String getFax() {
		return personService == null ? null : personService.getFax(person);
	}

	public String getEmail() {
		return personService == null ? null : personService.getEmail(person);
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
				Person person = personService.getPersonById(String.valueOf(requester.getRequesterId()));
				this.person = person;
			} else if (requester.getRequesterTypeId() == Requester.ORGANIZATION.getId()) {
				organization = organizationService.getOrganizationById(String.valueOf(requester.getRequesterId()));
			}
		}
	}
}
