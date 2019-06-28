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
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.reports.action.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.jfree.util.Log;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import spring.mine.common.form.BaseForm;
import spring.mine.internationalization.MessageUtil;
import spring.service.dictionary.DictionaryService;
import spring.service.note.NoteService;
import spring.service.observationhistory.ObservationHistoryService;
import spring.service.referencetables.ReferenceTablesService;
import spring.service.sample.SampleService;
import spring.service.samplehuman.SampleHumanService;
import spring.service.sampleorganization.SampleOrganizationService;
import spring.service.sampleqaevent.SampleQaEventService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.QAService.QAObservationType;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.qaevent.worker.NonConformityUpdateWorker;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.FollowupRequiredData;
import us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public class RetroCIFollowupRequiredByLocation extends RetroCIReport implements IReportParameterSetter, IReportCreator {

	private static String SAMPLE_TABLE_ID = null;
	private static String SAMPLE_QA_EVENT_TABLE_ID = null;
	private String lowDateStr;
	private String highDateStr;
	private DateRange dateRange;
	private List<FollowupRequiredData> reportItems;
	private SampleOrganizationService sampleOrganizationService = SpringContext
			.getBean(SampleOrganizationService.class);
	private ObservationHistoryService observationHistoryService = SpringContext
			.getBean(ObservationHistoryService.class);
	private NoteService noteService = SpringContext.getBean(NoteService.class);
	private SampleQaEventService sampleQaEventService = SpringContext.getBean(SampleQaEventService.class);
	private SampleService sampleService = SpringContext.getBean(SampleService.class);
	private DictionaryService dictionaryService = SpringContext.getBean(DictionaryService.class);
	private SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);

	static {
		SAMPLE_TABLE_ID = SpringContext.getBean(ReferenceTablesService.class).getReferenceTableByName("sample").getId();
		SAMPLE_QA_EVENT_TABLE_ID = SpringContext.getBean(ReferenceTablesService.class)
				.getReferenceTableByName("SAMPLE_QAEVENT").getId();
	}

	@Override
	protected String reportFileName() {
		return "RetroCI_FollowupRequired_ByLocation";
	}

	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.IReportParameterSetter#setRequestParameters(us.mn.state.health.lims.common.action.BaseActionForm)
	 */
	@Override
	public void setRequestParameters(BaseForm form) {
		try {
			PropertyUtils.setProperty(form, "reportName", getReportNameForParameterPage());
			PropertyUtils.setProperty(form, "useLowerDateRange", Boolean.TRUE);
			PropertyUtils.setProperty(form, "useUpperDateRange", Boolean.TRUE);
		} catch (Exception e) {
			Log.error("Error in FollowupRequired_ByLocation.setRequestParemeters: ", e);
			// throw e;
		}
	}

	/**
	 * @return
	 */
	private Object getReportNameForParameterPage() {
		return MessageUtil.getMessage("reports.label.followupRequired.title");
	}

	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.RetroCIReport#initializeReport(us.mn.state.health.lims.common.action.BaseActionForm)
	 */
	@Override
	public void initializeReport(BaseForm form) {
		super.initializeReport();
		errorFound = false;

		lowDateStr = form.getString("lowerDateRange");
		highDateStr = form.getString("upperDateRange");
		dateRange = new DateRange(lowDateStr, highDateStr);

		createReportParameters();

		errorFound = !validateSubmitParameters();
		if (errorFound) {
			return;
		}

		createReportItems();
		if (reportItems.size() == 0) {
			add1LineErrorMessage("report.error.message.noPrintableItems");
		}
	}

	@Override
	protected void createReportParameters() {
		super.createReportParameters();
		reportParameters.put("reportTitle", getReportNameForParameterPage() + "  -  " + dateRange.toString());
	}

	/**
	 * check everything
	 */
	private boolean validateSubmitParameters() {
		return (dateRange.validateHighLowDate("report.error.message.date.received.missing"));
	}

	/**
	 *
	 */
	private void createReportItems() {
		reportItems = new ArrayList<>();
		List<Sample> sampleList = sampleService.getSamplesReceivedInDateRange(
				DateUtil.convertSqlDateToStringDate(dateRange.getLowDate()),
				DateUtil.convertSqlDateToStringDate(dateRange.getHighDate()));

		for (Sample sample : sampleList) {
			if (QAService.isOrderNonConforming(sample) || isUnderInvestigation(sample)) {
				FollowupRequiredData item = new FollowupRequiredData();

				item.setCollectiondate(
						sample.getCollectionDateForDisplay() + " " + sample.getCollectionTimeForDisplay());
				item.setReceivedDate(sample.getReceivedDateForDisplay() + " " + sample.getReceivedTimeForDisplay());
				item.setLabNo(sample.getAccessionNumber());
				item.setDoctor(getOptionalObservationHistory(sample, OBSERVATION_DOCTOR_ID));
				item.setNonConformityNotes(getNonConformingNotes(sample));
				item.setUnderInvestigationNotes(getUnderInvestigationNotes(sample));
				item.setOrgname(getServiceName(sample));
				Patient patient = getPatient(sample);
				item.setSubjectNumber(patient.getNationalId());
				item.setSiteSubjectNumber(patient.getExternalId());
				reportItems.add(item);
			}
		}

		Collections.sort(reportItems, new FollowupRequiredData.OrderByOrgName());
	}

	protected boolean isUnderInvestigation(Sample sample) {
		String entryUnderInvestigationQuestion = getOptionalObservationHistory(sample,
				OBSERVATION_UNDER_INVESTIGATION_ID);
		return BaseProjectFormMapper.YES_ANSWERS.contains(entryUnderInvestigationQuestion);
	}

	private String getNonConformingNotes(Sample sample) {
		StringBuilder allNotes = new StringBuilder();

		NoteService noteService = SpringContext.getBean(NoteService.class);
		String notes = noteService.getNotesAsString(sample,
				MessageUtil.getMessage("report.followup.general.comment") + ": ", "<br/>");
		if (notes != null) {
			allNotes.append(notes);
			allNotes.append("<br/>");
		}

		List<SampleQaEvent> qaEventList = sampleQaEventService.getSampleQaEventsBySample(sample);

		for (SampleQaEvent event : qaEventList) {
			QAService qa = new QAService(event);
			if (qa.getSampleItem() == null) {
				allNotes.append(MessageUtil.getMessage("report.followup.no.sampleType"));
			} else {
				allNotes.append(qa.getSampleItem().getTypeOfSample().getLocalizedName());
			}
			allNotes.append(" : ");

			if ("0".equals(qa.getObservationValue(QAObservationType.SECTION))) {
				allNotes.append(MessageUtil.getMessage("report.followup.no.section"));
			} else {
				allNotes.append(qa.getObservationForDisplay(QAObservationType.SECTION));
			}
			allNotes.append(" : ");

			if (GenericValidator.isBlankOrNull(qa.getObservationValue(QAObservationType.AUTHORIZER))) {
				allNotes.append(MessageUtil.getMessage("report.followup.no.authorizer"));
			} else {
				allNotes.append(qa.getObservationForDisplay(QAObservationType.AUTHORIZER));
			}
			allNotes.append(" : ");

			List<Note> qaNotes = noteService.getNoteByRefIAndRefTableAndSubject(qa.getEventId(),
					SAMPLE_QA_EVENT_TABLE_ID, NonConformityUpdateWorker.NOTE_SUBJECT);

			if (qaNotes.isEmpty()) {
				allNotes.append(MessageUtil.getMessage("report.followup.no.note"));
			} else {
				allNotes.append(qaNotes.get(0).getText());
			}

			allNotes.append("<br/>");
		}

		return allNotes.length() == 0 ? null : allNotes.toString();
	}

	/**
	 * Either the sample is fully defined and has an organization, or there might be
	 * some random string defining a service stored in observation history. This
	 * second scenerio comes from enter something in the system directly at on the
	 * Non Conformant page.
	 *
	 * @param sample
	 * @return a displayable organization name
	 */
	private String getServiceName(Sample sample) {

		SampleOrganization so = sampleOrganizationService.getDataBySample(sample);
		String service;
		if (so == null) {
			return null;
		}
		service = so.getOrganization().getOrganizationName();
		if (service != null) {
			return service;
		}

		String serviceOH = getDictionaryValueForObservation(sample, OBSERVATION_SERVICE_ID);
		if (GenericValidator.isBlankOrNull(serviceOH)) {
			return null;
		}
		return service;
	}

	/**
	 * @param sample
	 * @return
	 */
	private Patient getPatient(Sample sample) {
		return sampleHumanService.getPatientForSample(sample);
	}

	private String getUnderInvestigationNotes(Sample sample) {
		String entryUnderInvestigationQuestion = getOptionalObservationHistory(sample,
				OBSERVATION_UNDER_INVESTIGATION_ID);

		if (BaseProjectFormMapper.YES_ANSWERS.contains(entryUnderInvestigationQuestion)) {
			List<Note> noteList = noteService.getNoteByRefIAndRefTableAndSubject(sample.getId(), SAMPLE_TABLE_ID,
					"UnderInvestigation");
			return noteList == null || noteList.isEmpty() ? null : noteList.get(0).getText();
		}

		return null;
	}

	private String getOptionalObservationHistory(Sample sample, String ohTypeId) {
		List<ObservationHistory> oh = observationHistoryService.getAll(null, sample, ohTypeId);
		if (oh == null || oh.size() == 0) {
			return null;
		}
		return oh.get(0).getValue();
	}

	private String getDictionaryValueForObservation(Sample sample, String ohTypeId) {
		String dictionaryId = getOptionalObservationHistory(sample, ohTypeId);
		if (dictionaryId == null) {
			return null;
		}
		Dictionary dictionary = null;
		try {
			dictionary = dictionaryService.getDictionaryById(dictionaryId);
		} catch (Exception e) {
			return dictionaryId; // I guess it wasn't really a dictionary ID
									// after all, so let's just return it.
		}
		if (dictionary == null) {
			return dictionaryId; // it was a number, but it wasn't in the
									// dictionary.
		}
		return dictionary.getLocalAbbreviation();
	}

	/**
	 * @see us.mn.state.health.lims.reports.action.implementation.Report#getReportDataSource()
	 */
	@Override
	public JRDataSource getReportDataSource() throws IllegalStateException {
		return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(reportItems);
	}
}
