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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.dataexchange.resultreporting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import spring.service.note.NoteServiceImpl;
import spring.service.patient.PatientServiceImpl;
import spring.service.patientidentity.PatientIdentityService;
import spring.service.patientidentitytype.PatientIdentityTypeService;
import spring.service.result.ResultServiceImpl;
import spring.service.resultlimit.ResultLimitService;
import spring.service.samplehuman.SampleHumanService;
import spring.service.test.TestServiceImpl;
import spring.service.typeoftestresult.TypeOfTestResultService;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.IPatientService;
import us.mn.state.health.lims.common.services.LabIdentificationService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dataexchange.orderresult.OrderResponseWorker.Event;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.CodedValueXmit;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ResultReportXmit;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.ResultXmit;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.TestRangeXmit;
import us.mn.state.health.lims.dataexchange.resultreporting.beans.TestResultsXmit;
import us.mn.state.health.lims.dictionary.util.DictionaryUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.result.action.util.ResultUtil;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;

public class ResultReportingCollator {

	private PatientIdentityService patientIdentityService = SpringContext.getBean(PatientIdentityService.class);
	private PatientIdentityTypeService patientIdentityTypeService = SpringContext
			.getBean(PatientIdentityTypeService.class);
	private TypeOfTestResultService typeOfTestResultService = SpringContext.getBean(TypeOfTestResultService.class);
	private SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);

	private String VALIDATED_RESULT_STATUS_ID;

	private String GUID_IDENTITY_TYPE;
	private String ST_IDENTITY_TYPE;

	private Map<String, List<TestResultsXmit>> patientIDToResultsMap = new HashMap<>();
	private Map<String, List<ResultXmit>> analysisIdToResultBeanMap = new HashMap<>();
	private Collection<String> noGUIDPatients = new HashSet<>();
	private Map<String, String> resultTypeToHL7TypeMap;

	public ResultReportingCollator() {
		GUID_IDENTITY_TYPE = patientIdentityTypeService.getNamedIdentityType("GUID").getId();
		ST_IDENTITY_TYPE = patientIdentityTypeService.getNamedIdentityType("ST").getId();

		resultTypeToHL7TypeMap = new HashMap<>();
		List<TypeOfTestResult> typeOfResultList = typeOfTestResultService.getAll();

		for (TypeOfTestResult type : typeOfResultList) {
			resultTypeToHL7TypeMap.put(type.getTestResultType(), type.getHl7Value());
		}

		VALIDATED_RESULT_STATUS_ID = StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
	}

	public void clearResults() {
		patientIDToResultsMap.clear();
		analysisIdToResultBeanMap.clear();
		noGUIDPatients.clear();
	}

	public boolean addResult(Result result, Patient patient, boolean isUpdate, boolean forMalaria) {
		if (hasNoReportableResults(result, patient)) {
			return false;
		}

		TestResultsXmit testResult = getResultsWrapperForPatient(patient.getId(), forMalaria);

		if (testResult == null) {
			return false;
		}

		List<ResultXmit> results = analysisIdToResultBeanMap.get(result.getAnalysis().getId());

		if (results == null) {
			results = new ArrayList<>();
			analysisIdToResultBeanMap.put(result.getAnalysis().getId(), results);
		}

		ResultXmit resultBean = new ResultXmit();

		CodedValueXmit codedValue = new CodedValueXmit();
		if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(result.getResultType())) {
			codedValue.setCode(DictionaryUtil.getHL7ForDictioanryById(result.getValue()));
		}

		codedValue.setText(ResultUtil.getStringValueOfResult(result));
		codedValue.setCodeName("LOINC");
		codedValue.setCodeSystem("2.16.840.1.113883.6.1");
		resultBean.setResult(codedValue);

		String hl7type = resultTypeToHL7TypeMap.get(result.getResultType());
		if (hl7type == null) {
			hl7type = "TX";
		}
		resultBean.setTypeResult(hl7type);
		resultBean.setUpdateStatus(isUpdate ? "update" : "new");
		resultBean.setLoinc(new ResultServiceImpl(result).getLOINCCode());
		results.add(resultBean);

		SampleItem sampleItemForResult = result.getAnalysis().getSampleItem();
		String accessionNumber = sampleItemForResult.getSample().getAccessionNumber();
		String sequenceNumber = sampleItemForResult.getSortOrder();
		testResult.setAccessionNumber(accessionNumber + "-" + sequenceNumber);

		String referringOrderNumber = sampleItemForResult.getSample().getReferringId();
		testResult.setReferringOrderNumber(referringOrderNumber);

		CodedValueXmit codedSampleType = new CodedValueXmit();
		codedSampleType.setCode("41");
		codedSampleType.setText(sampleItemForResult.getTypeOfSample().getDescription());
		codedSampleType.setCodeName("LOINC");
		codedSampleType.setCodeSystem("2.16.840.1.113883.6.1");
		testResult.setSampleType(codedSampleType);

		CodedValueXmit codedTest = new CodedValueXmit();
		/* if (forMalaria) { */
		ResultServiceImpl resultService = new ResultServiceImpl(result);
		codedTest.setCode(resultService.getLOINCCode() == null ? "34" : resultService.getLOINCCode());
		/*
		 * } else { codedTest.setCode("34"); }
		 */
		codedTest.setText(TestServiceImpl.getUserLocalizedTestName(result.getAnalysis().getTest()));
		codedTest.setCodeName("LN");
		codedTest.setCodeSystem("2.16.840.1.113883.6.1");
		testResult.setTest(codedTest);
		if (forMalaria) {
			testResult.setTestDate(result.getAnalysis().getLastupdated());
		} else {
			// N.B. This really should be gotten from the analysis
			testResult.setTestDate(new Date());
		}
		testResult.setStatus("Valid");
		testResult.setResults(results);

		// For test section
		String convertedSection = result.getAnalysis().getTestSection().getTestSectionName();
		String actualSection;

		// Need to reverse the conversion done when the test catalog was imported
		if ("Hematology".equals(convertedSection)) {
			actualSection = "Hematologie";
		} else if ("Biochemistry".equals(convertedSection)) {
			actualSection = "Biochimie";
		} else if ("Bacteria".equals(convertedSection)) {
			actualSection = "Mycobacteriologie";
		} else if ("Parasitology".equals(convertedSection)) {
			actualSection = "Parasitologie";
		} else if ("Immunology".equals(convertedSection)) {
			actualSection = "Immuno-virologie";
		} else if ("VCT".equals(convertedSection)) {
			actualSection = "CDV";
		} else if ("Hematology".equals(convertedSection)) {
			actualSection = "Hematologie";
		} else {
			actualSection = convertedSection;
		}
		testResult.setTestSection(actualSection);

		if (result.getMinNormal().doubleValue() != result.getMaxNormal().doubleValue()) {
			TestRangeXmit normalRange = new TestRangeXmit();
			normalRange.setLow(
					StringUtil.doubleWithSignificantDigits(result.getMinNormal(), result.getSignificantDigits()));
			normalRange.setHigh(
					StringUtil.doubleWithSignificantDigits(result.getMaxNormal(), result.getSignificantDigits()));
			normalRange.setUnits(getUnitOfMeasure(result));

			testResult.setNormalRange(normalRange);
		}

		// For valid range min/max
		ResultLimit validLimit = SpringContext.getBean(ResultLimitService.class).getResultLimitForTestAndPatient(
				result.getAnalysis().getTest(),
				sampleHumanService.getPatientForSample(result.getAnalysis().getSampleItem().getSample()));
		if (validLimit != null && (validLimit.getLowValid() != validLimit.getHighValid())) {
			TestRangeXmit validRange = new TestRangeXmit();
			validRange.setLow(String.valueOf(validLimit.getLowValid()));
			validRange.setHigh(String.valueOf(validLimit.getHighValid()));
			validRange.setUnits(getUnitOfMeasure(result));

			testResult.setValidRange(validRange);
		}

		// For notes
		testResult.setTestNotes(getResultNote(result));

		// Malaria case report needs the following extra data elements
		if (forMalaria) {
			// Patient demographic data
			IPatientService patientService = new PatientServiceImpl(patient);
			testResult.setPatientFirstName(patientService.getFirstName());
			testResult.setPatientLastName(patientService.getLastName());
			testResult.setPatientGender(patientService.getGender());
			testResult.setPatientBirthdate(patientService.getEnteredDOB());
			testResult.setPatientTelephone(patientService.getPhone());

			Map<String, String> addressParts = patientService.getAddressComponents();
			testResult.setPatientStreetAddress(addressParts.get("Street"));
			testResult.setPatientCity(addressParts.get("City"));
			testResult.setPatientState(addressParts.get("State"));
			testResult.setPatientZipCode(addressParts.get("Zip"));
			testResult.setPatientCountry(addressParts.get("Country"));
		}

		testResult.setResultsEvent(result.getResultEvent());

		return true;
	}

	// is this a result update (ie Final_Result) as opposed to a non-result update
	// (Cancelled)
	private boolean isResultUpdate(Result result) {
		Event event = result.getResultEvent();
		if (event == null) {
			return true;
		} else if (result.getResultEvent() == Event.FINAL_RESULT) {
			return true;
		} else if (result.getResultEvent() == Event.RESULT) {
			return true;
		} else if (result.getResultEvent() == Event.PRELIMINARY_RESULT) {
			return true;
		}
		return false;
	}

	protected String getResultNote(Result result) {
		if (result != null) {
			Analysis analysis = new Analysis();
			analysis.setId(result.getAnalysis().getId());
			return new NoteServiceImpl(analysis).getNotesAsString(false, false, "<br/>", false);
		}
		return null;
	}

	protected String getUnitOfMeasure(Result result) {
		if (result.getAnalysis().getTest().getUnitOfMeasure() != null) {
			return result.getAnalysis().getTest().getUnitOfMeasure().getUnitOfMeasureName();
		} else {
			return "";
		}
	}

	private boolean hasNoReportableResults(Result result, Patient patient) {
		return noGUIDPatients.contains(patient.getId()) || GenericValidator.isBlankOrNull(result.getValue())
				|| (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(result.getResultType()) && "0".equals(
						result.getValue()) /*
											 * || !VALIDATED_RESULT_STATUS_ID.equals(result.getAnalysis().getStatusId())
											 */ );
	}

	public ResultReportXmit getResultReport() {
		List<TestResultsXmit> wrapperList = new ArrayList<>();

		for (String key : patientIDToResultsMap.keySet()) {
			wrapperList.addAll(patientIDToResultsMap.get(key));
		}

		ResultReportXmit resultReport = new ResultReportXmit();
		resultReport.setTestResults(wrapperList);
		resultReport.setTransmissionDate(new Date());
		resultReport.setSendingSiteId(ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));
		resultReport.setSendingSiteName(ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName));
		LabIdentificationService labService = new LabIdentificationService();
		resultReport.setSendingSiteLanguage(labService.getLanguageLocale().toString());

		return resultReport;
	}

	public ResultReportXmit getResultReport(String patientId) {
		List<TestResultsXmit> wrapperList = new ArrayList<>();

		wrapperList.addAll(patientIDToResultsMap.get(patientId));
		ResultReportXmit resultReport = new ResultReportXmit();
		resultReport.setTestResults(wrapperList);
		resultReport.setTransmissionDate(new Date());
		resultReport.setSendingSiteId(ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));
		resultReport.setSendingSiteName(ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName));
		LabIdentificationService labService = new LabIdentificationService();
		resultReport.setSendingSiteLanguage(labService.getLanguageLocale().toString());

		return resultReport;
	}

	private TestResultsXmit getResultsWrapperForPatient(String patientId, boolean preferSTNumber) {
		boolean usedSTNumber = false;
		List<TestResultsXmit> wrapperList = patientIDToResultsMap.get(patientId);

		String patId = null;
		if (wrapperList != null) {
			if (preferSTNumber) {
				patId = wrapperList.get(0).getPatientSTID();
				usedSTNumber = true;
			}

			if (patId == null) {
				patId = wrapperList.get(0).getPatientGUID();
			}
		} else {
			PatientIdentity patientIdentity = null;
			if (preferSTNumber) {
				patientIdentity = patientIdentityService.getPatitentIdentityForPatientAndType(patientId,
						ST_IDENTITY_TYPE);
				usedSTNumber = true;
			}

			if (patientIdentity == null) {
				patientIdentity = patientIdentityService.getPatitentIdentityForPatientAndType(patientId,
						GUID_IDENTITY_TYPE);
			}

			// Everything between these comments are for testing only and should be remove
			// beforE they go into production
//			if (SystemConfiguration.getInstance().useTestPatientGUID() &&  patientIdentity == null) {
//				patientIdentity = new PatientIdentity();
//				patientIdentity.setIdentityData(UUID.randomUUID().toString());
//				patientIdentity.setIdentityTypeId(GUID_IDENTITY_TYPE);
//				patientIdentity.setPatientId(patientId);
//			}
			// End of testing comment

			if (patientIdentity == null) {
				noGUIDPatients.add(patientId);
				return null;
			}

			wrapperList = new ArrayList<>();
			patientIDToResultsMap.put(patientId, wrapperList);
			patId = patientIdentity.getIdentityData();
		}

		TestResultsXmit wrapper = new TestResultsXmit();
		wrapperList.add(wrapper);

		if (usedSTNumber) {
			wrapper.setPatientSTID(patId);
		} else {
			wrapper.setPatientGUID(patId);
		}

		return wrapper;
	}
}
