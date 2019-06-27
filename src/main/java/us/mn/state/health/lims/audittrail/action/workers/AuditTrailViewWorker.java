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
package us.mn.state.health.lims.audittrail.action.workers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import spring.mine.internationalization.MessageUtil;
import spring.service.analysis.AnalysisService;
import spring.service.patient.PatientService;
import spring.service.result.ResultService;
import spring.service.sample.SampleService;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.historyservices.AbstractHistoryService;
import us.mn.state.health.lims.common.services.historyservices.AnalysisHistoryService;
import us.mn.state.health.lims.common.services.historyservices.NoteHistoryService;
import us.mn.state.health.lims.common.services.historyservices.OrderHistoryService;
import us.mn.state.health.lims.common.services.historyservices.PatientHistoryHistoryService;
import us.mn.state.health.lims.common.services.historyservices.PatientHistoryService;
import us.mn.state.health.lims.common.services.historyservices.QaHistoryService;
import us.mn.state.health.lims.common.services.historyservices.ReportHistoryService;
import us.mn.state.health.lims.common.services.historyservices.ResultHistoryService;
import us.mn.state.health.lims.common.services.historyservices.SampleHistoryService;
import us.mn.state.health.lims.patient.action.bean.PatientManagementBridge;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class AuditTrailViewWorker {

	protected AnalysisService analysisService = SpringContext.getBean(AnalysisService.class);
	protected ResultService resultService = SpringContext.getBean(ResultService.class);
	protected SampleService sampleService = SpringContext.getBean(SampleService.class);

	private String accessionNumber = null;
	private Sample sample;

	public AuditTrailViewWorker(String accessionNumber) {
		this.accessionNumber = accessionNumber;
		sample = null;
	}

	public List<AuditTrailItem> getAuditTrail() throws IllegalStateException {
		if (GenericValidator.isBlankOrNull(accessionNumber)) {
			throw new IllegalStateException("AuditTrialViewWorker is not initialized");
		}

		getSample();

		List<AuditTrailItem> items = new ArrayList<>();

		if (sample != null) {
			items.addAll(addOrders());
			items.addAll(addSamples());
			items.addAll(addTestsAndResults());
			items.addAll(addReports());
			items.addAll(addPatientHistory());
			items.addAll(addNotes());
			items.addAll(addQAEvents());
		}

		sortItemsByTime(items);
		return items;
	}

	public SampleOrderItem getSampleOrderSnapshot() {
		if (GenericValidator.isBlankOrNull(accessionNumber)) {
			throw new IllegalStateException("AuditTrialViewWorker is not initialized");
		}

		SampleOrderService orderService = new SampleOrderService(accessionNumber, true);
		return orderService.getSampleOrderItem();
	}

	public PatientManagementInfo getPatientSnapshot() {
		if (GenericValidator.isBlankOrNull(accessionNumber)) {
			throw new IllegalStateException("AuditTrialViewWorker is not initialized");
		}

		getSample();

		if (sample != null) {
			PatientService patientSampleService = SpringContext.getBean(PatientService.class);
			patientSampleService.setPatientBySample(sample);
			return new PatientManagementBridge().getPatientManagementInfoFor(patientSampleService.getPatient(), true);
		} else {
			return new PatientManagementInfo();
		}
	}

	public List<AuditTrailItem> getPatientHistoryAuditTrail() throws IllegalStateException {
		if (GenericValidator.isBlankOrNull(accessionNumber)) {
			throw new IllegalStateException("AuditTrialViewWorker is not initialized");
		}

		getSample();

		List<AuditTrailItem> items = new ArrayList<>();

		if (sample != null) {
			items.addAll(addPatientHistory());
		}
		return items;

	}

	private void getSample() {
		if (sample == null) {
			sample = sampleService.getSampleByAccessionNumber(accessionNumber);
		}
	}

	private Collection<AuditTrailItem> addReports() {
		List<AuditTrailItem> items = new ArrayList<>();

		if (sample != null) {
			AbstractHistoryService historyService = new ReportHistoryService(sample);
			items.addAll(historyService.getAuditTrailItems());

			// sortItemsByTime(items);
		}

		for (AuditTrailItem auditTrailItem : items) {
			auditTrailItem.setClassName("reportAudit");
			setAttributeNewIfInsert(auditTrailItem);
		}
		return items;
	}

	private Collection<AuditTrailItem> addSamples() {
		List<AuditTrailItem> sampleItems = new ArrayList<>();
		if (sample != null) {
			AbstractHistoryService historyService = new SampleHistoryService(sample);
			sampleItems.addAll(historyService.getAuditTrailItems());

			// sortItems(sampleItems);

			for (AuditTrailItem auditTrailItem : sampleItems) {
				auditTrailItem.setClassName("sampleAudit");
				setAttributeNewIfInsert(auditTrailItem);
			}
		}

		return sampleItems;
	}

	private Collection<AuditTrailItem> addOrders() {
		List<AuditTrailItem> orderItems = new ArrayList<>();
		if (sample != null) {
			AbstractHistoryService historyService = new OrderHistoryService(sample);
			orderItems.addAll(historyService.getAuditTrailItems());

			// sortItems(orderItems);

			for (AuditTrailItem auditTrailItem : orderItems) {
				auditTrailItem.setClassName("orderAudit");
				setAttributeNewIfInsert(auditTrailItem);
			}
		}

		return orderItems;
	}

	private void setAttributeNewIfInsert(AuditTrailItem auditTrailItem) {
		if (auditTrailItem.getAction().equals("I")) {
			auditTrailItem.setAttribute(MessageUtil.getMessage("auditTrail.action.new"));
		}
	}

	private List<AuditTrailItem> addTestsAndResults() {
		List<AuditTrailItem> items = new ArrayList<>();

		List<Analysis> analysisList = analysisService.getAnalysesBySampleId(sample.getId());

		for (Analysis analysis : analysisList) {
			List<Result> resultList = resultService.getResultsByAnalysis(analysis);
			AbstractHistoryService historyService = new AnalysisHistoryService(analysis);
			List<AuditTrailItem> resultItems = historyService.getAuditTrailItems();
			items.addAll(resultItems);

			for (Result result : resultList) {
				historyService = new ResultHistoryService(result, analysis);
				resultItems = historyService.getAuditTrailItems();

				items.addAll(resultItems);
			}
		}

		// sortItems(items);
		for (AuditTrailItem auditTrailItem : items) {
			auditTrailItem.setClassName("testResultAudit");
			setAttributeNewIfInsert(auditTrailItem);
		}
		return items;
	}

	private Collection<AuditTrailItem> addPatientHistory() {
		List<AuditTrailItem> items = new ArrayList<>();
		AbstractHistoryService historyService;
		Patient patient = PatientUtil.getPatientForSample(sample);
		if (patient != null) {
			historyService = new PatientHistoryService(patient);
			items.addAll(historyService.getAuditTrailItems());
		}

//		historyService = new HistoryService(sample, HistoryType.PERSON);
//		items.addAll(historyService.getAuditTrailItems());

		historyService = new PatientHistoryHistoryService(sample);
		items.addAll(historyService.getAuditTrailItems());

		// sortItems(items);

		for (AuditTrailItem auditTrailItem : items) {
			auditTrailItem.setClassName("patientHistoryAudit");
			setAttributeNewIfInsert(auditTrailItem);
		}

		return items;
	}

	private Collection<AuditTrailItem> addNotes() {
		List<AuditTrailItem> notes = new ArrayList<>();
		if (sample != null) {
			AbstractHistoryService historyService = new NoteHistoryService(sample);
			notes.addAll(historyService.getAuditTrailItems());

			// sortItems(notes);

			for (AuditTrailItem auditTrailItem : notes) {
				auditTrailItem.setClassName("noteAudit");
				setAttributeNewIfInsert(auditTrailItem);
			}
		}

		return notes;
	}

	private Collection<AuditTrailItem> addQAEvents() {
		List<AuditTrailItem> qaEvents = new ArrayList<>();
		if (sample != null) {
			QaHistoryService qaService = new QaHistoryService(sample);
			qaEvents = qaService.getAuditTrailItems();

			for (AuditTrailItem auditTrailItem : qaEvents) {
				auditTrailItem.setClassName("qaEvent");
				setAttributeNewIfInsert(auditTrailItem);
			}
		}

		return qaEvents;
	}

	private void sortItems(List<AuditTrailItem> items) {
		Collections.sort(items, new Comparator<AuditTrailItem>() {
			@Override
			public int compare(AuditTrailItem o1, AuditTrailItem o2) {
				int sort = o1.getIdentifier().compareTo(o2.getIdentifier());
				if (sort != 0) {
					return sort;
				}

				sort = o1.getTimeStamp().compareTo(o2.getTimeStamp());
				if (sort != 0) {
					return sort;
				}

				return o1.getAction().compareTo(o2.getAction());
			}
		});
	}

	private void sortItemsByTime(List<AuditTrailItem> items) {
		Collections.sort(items, new Comparator<AuditTrailItem>() {
			@Override
			public int compare(AuditTrailItem o1, AuditTrailItem o2) {
				return o1.getTimeStamp().compareTo(o2.getTimeStamp());
			}
		});
	}

}
