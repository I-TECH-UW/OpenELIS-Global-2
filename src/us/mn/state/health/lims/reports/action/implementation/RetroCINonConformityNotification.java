/**
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
 *
 */
package us.mn.state.health.lims.reports.action.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.qaevent.action.retroCI.NonConformityAction;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.NonConformityReportData;
import us.mn.state.health.lims.reports.action.util.ReportUtil;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class RetroCINonConformityNotification extends RetroCIReport implements IReportCreator, IReportParameterSetter {

	private static SampleQaEventDAO sampleQADAO = new SampleQaEventDAOImpl();
	private static SampleOrganizationDAO sampleOrgDAO = new SampleOrganizationDAOImpl();
	private static SampleDAO sampleDAO = new SampleDAOImpl();
	private List<NonConformityReportData> reportItems;
	private String requestedAccessionNumber;
	private List<String> sampleQaEventIds;
	private Set<String> checkIdsForPriorPrintRecord;
	
	public RetroCINonConformityNotification() {
		super();
	}

	@Override
	public void setRequestParameters(BaseActionForm dynaForm) {
		try {
			PropertyUtils.setProperty(dynaForm, "reportName", StringUtil.getMessageForKey("reports.nonConformity.notification.report"));
            PropertyUtils.setProperty(dynaForm, "selectList", new ReportSpecificationList( getSiteList(), StringUtil.getMessageForKey( "report.select.site" )));
			PropertyUtils.setProperty(dynaForm, "useAccessionDirect", Boolean.TRUE);
			PropertyUtils.setProperty(dynaForm, "instructions",
					StringUtil.getMessageForKey("reports.nonConformity.notification.report.instructions"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<IdValuePair> getSiteList() {
		List<IdValuePair> sites = new ArrayList<IdValuePair>();

		Set<String> orgIds = new HashSet<String>();
		List<Organization> services = new ArrayList<Organization>();
		List<SampleQaEvent> events = sampleQADAO.getAllUncompleatedEvents();

		events = filterReportedEvents(events);

		for (SampleQaEvent event : events) {
			SampleOrganization sampleOrg = sampleOrgDAO.getDataBySample(event.getSample());
			if (sampleOrg != null) {
				if (!orgIds.contains(sampleOrg.getOrganization().getId())) {
					orgIds.add(sampleOrg.getOrganization().getId());
					services.add(sampleOrg.getOrganization());
				}
			}
		}

		Collections.sort(services, new Comparator<Organization>() {
			@Override
			public int compare(Organization o1, Organization o2) {
				return o1.getOrganizationName().compareTo(o2.getOrganizationName());
			}
		});

		for (Organization org : services) {
			sites.add(new IdValuePair(org.getId(), org.getOrganizationName()));
		}

		return sites;
	}

	private List<SampleQaEvent> filterReportedEvents(List<SampleQaEvent> events) {
		List<SampleQaEvent> filteredList = new ArrayList<SampleQaEvent>();

		for (SampleQaEvent event : events) {
			if (!ReportUtil.documentHasBeenPrinted(ReportUtil.DocumentTypes.NON_CONFORMITY_NOTIFCATION, event.getId())) {
				filteredList.add(event);
			}
		}

		return filteredList;
	}

	@Override
	public void initializeReport(BaseActionForm dynaForm) {
		super.initializeReport();
		sampleQaEventIds = new ArrayList<String>();
		checkIdsForPriorPrintRecord = new HashSet<String>();
		errorFound = false;
		requestedAccessionNumber = dynaForm.getString("accessionDirect");
        ReportSpecificationList specificationList = (ReportSpecificationList)dynaForm.get("selectList");

		createReportParameters();

		errorFound = !validateSubmitParameters(specificationList.getSelection());
		if (errorFound) {
			return;
		}
		createReportItems(specificationList.getSelection());
		if (this.reportItems.size() == 0) {
			add1LineErrorMessage("report.error.message.noPrintableItems");
		}
	}

	private boolean validateSubmitParameters(String serviceId) {
		if (GenericValidator.isBlankOrNull(requestedAccessionNumber) && (GenericValidator.isBlankOrNull(serviceId) || "0".equals(serviceId))) {
			add1LineErrorMessage("report.error.message.noParameters");
			return false;
		}

		if (!GenericValidator.isBlankOrNull(requestedAccessionNumber)) {
			Sample sample = sampleDAO.getSampleByAccessionNumber(requestedAccessionNumber);
			if (sample == null) {
				add1LineErrorMessage("report.error.message.accession.not.valid");
				return false;
			}
		}

		return true;
	}

	private void createReportItems(String serviceId) {
		reportItems = new ArrayList<NonConformityReportData>();
		List<Sample> samples = getNonConformingSamples(serviceId);

		samples = sortAndFilterSamples(samples);

		for (Sample sample : samples) {
			reportItems.addAll(createNonconformityItem(sample));
		}

	}

	private List<Sample> getNonConformingSamples(String serviceId) {
		List<Sample> samples = new ArrayList<Sample>();

		if (!GenericValidator.isBlankOrNull(requestedAccessionNumber)) {
			// we've already checked to make sure there is a sample for the accessionNumber
			samples.add(sampleDAO.getSampleByAccessionNumber(requestedAccessionNumber));
		} 
		
		if( !GenericValidator.isBlankOrNull(serviceId)){
			List<Sample> sampleList = sampleDAO.getSamplesWithPendingQaEventsByService(serviceId);
			samples.addAll(sampleList);
		}

		return samples;
	}

	private List<Sample> sortAndFilterSamples(List<Sample> samples) {
		Collections.sort(samples, new Comparator<Sample>() {
			@Override
			public int compare(Sample o1, Sample o2) {
				return o1.getAccessionNumber().compareTo(o2.getAccessionNumber());
			}
		});

		List<Sample> filteredSamples = new ArrayList<Sample>();

		String previousAccessionNumber = "";

		for (Sample sample : samples) {
			if (!previousAccessionNumber.equals(sample.getAccessionNumber())) {
				filteredSamples.add(sample);
				previousAccessionNumber = sample.getAccessionNumber();
			}
		}

		return filteredSamples;
	}

	private List<NonConformityReportData> createNonconformityItem(Sample sample) {

		List<NonConformityReportData> items = new ArrayList<NonConformityReportData>();

		Patient patient = ReportUtil.findPatient(sample);
		Project project = ReportUtil.findProject(sample);

		String sampleAccessionNumber = sample.getAccessionNumber();
		String receivedDate = sample.getReceivedDateForDisplay();
		String receivedHour = sample.getReceivedTimeForDisplay( );
		String doctor = ReportUtil.findDoctorForSample(sample);

		String orgName = "";
		SampleOrganization sampleOrg = sampleOrgDAO.getDataBySample(sample);
		if( sampleOrg != null && sampleOrg.getOrganization() != null){
			orgName = sampleOrg.getOrganization().getOrganizationName();
		}
		
		List<SampleQaEvent> sampleQaEvents = sampleQADAO.getSampleQaEventsBySample(sample);

		for (SampleQaEvent event : sampleQaEvents) {
			if (eventPrintable(sampleAccessionNumber, event)) {
				NonConformityReportData item = new NonConformityReportData();
				QAService qa = new QAService(event);
				item.setAccessionNumber(sampleAccessionNumber);
				item.setReceivedDate(receivedDate);
				item.setReceivedHour(receivedHour);
				item.setService(orgName);

				item.setBiologist(qa.getObservationForDisplay( QAObservationType.AUTHORIZER ));
				item.setNonConformityDate(DateUtil.convertTimestampToStringDate(qa.getLastupdated()));
				item.setSection(qa.getObservationForDisplay( QAObservationType.SECTION ));
				item.setSubjectNumber(patient.getNationalId());
				item.setSiteSubjectNumber(patient.getExternalId());
				item.setStudy((project != null) ? project.getLocalizedName() : "");

				item.setSampleType(ReportUtil.getSampleType(event));
				item.setQaNote(NonConformityAction.getNoteForSampleQaEvent(event));
				item.setSampleNote(NonConformityAction.getNoteForSample(sample));
				item.setNonConformityReason(qa.getQAEvent().getLocalizedName());
				item.setDoctor(doctor);
				items.add(item);

				sampleQaEventIds.add(qa.getEventId());
				if( sampleAccessionNumber.equals(requestedAccessionNumber)){
					checkIdsForPriorPrintRecord.add(qa.getEventId());
				}
			}
		}

		return items;
	}

	private boolean eventPrintable(String sampleAccessionNumber, SampleQaEvent event) {
		if( sampleAccessionNumber.equals(requestedAccessionNumber)){
			return true;
		}
		
		return !ReportUtil.documentHasBeenPrinted(ReportUtil.DocumentTypes.NON_CONFORMITY_NOTIFCATION, event.getId());
	}

	@Override
	public JRDataSource getReportDataSource() throws IllegalStateException {
		if (errorFound) {
			return new JRBeanCollectionDataSource(errorMsgs);
		} else {
			ReportUtil.markDocumentsAsPrinted(ReportUtil.DocumentTypes.NON_CONFORMITY_NOTIFCATION, sampleQaEventIds, "1", checkIdsForPriorPrintRecord);
			return new JRBeanCollectionDataSource(reportItems);
		}
	}

	@Override
	protected String reportFileName() {
		return "NonConformityNotification";
	}

}
