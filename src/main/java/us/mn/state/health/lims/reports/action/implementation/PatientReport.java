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
package us.mn.state.health.lims.reports.action.implementation;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.validator.GenericValidator;

import spring.mine.common.form.BaseForm;
import spring.mine.internationalization.MessageUtil;
import spring.service.address.AddressPartService;
import spring.service.address.PersonAddressService;
import spring.service.analysis.AnalysisService;
import spring.service.analysis.AnalysisServiceImpl;
import spring.service.dictionary.DictionaryService;
import spring.service.note.NoteService;
import spring.service.note.NoteServiceImpl;
import spring.service.note.NoteServiceImpl.NoteType;
import spring.service.observationhistory.ObservationHistoryService;
import spring.service.observationhistory.ObservationHistoryServiceImpl;
import spring.service.observationhistory.ObservationHistoryServiceImpl.ObservationType;
import spring.service.patient.PatientService;
import spring.service.patient.PatientServiceImpl;
import spring.service.patientidentity.PatientIdentityService;
import spring.service.person.PersonService;
import spring.service.person.PersonServiceImpl;
import spring.service.provider.ProviderService;
import spring.service.referral.ReferralReasonService;
import spring.service.referral.ReferralResultService;
import spring.service.referral.ReferralService;
import spring.service.result.ResultServiceImpl;
import spring.service.sample.SampleService;
import spring.service.sample.SampleServiceImpl;
import spring.service.samplehuman.SampleHumanService;
import spring.service.test.TestService;
import spring.service.test.TestServiceImpl;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.TestIdentityService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ClinicalPatientData;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;

public abstract class PatientReport extends Report {

	private static final DecimalFormat twoDecimalFormat = new DecimalFormat("#.##");
	private static String ADDRESS_DEPT_ID;
	private static String ADDRESS_COMMUNE_ID;
	protected String currentContactInfo = "";
	protected String currentSiteInfo = "";

	protected SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
	protected DictionaryService dictionaryService = SpringContext.getBean(DictionaryService.class);
	protected SampleService sampleService = SpringContext.getBean(SampleService.class);
	protected PatientService patientService = SpringContext.getBean(PatientService.class);
	protected PersonService personService = SpringContext.getBean(PersonService.class);
	protected ProviderService providerService = SpringContext.getBean(ProviderService.class);
	protected TestService testService = SpringContext.getBean(TestService.class);
	protected ReferralReasonService referralReasonService = SpringContext.getBean(ReferralReasonService.class);
	protected ReferralService referralService = SpringContext.getBean(ReferralService.class);
	protected ReferralResultService referralResultService = SpringContext.getBean(ReferralResultService.class);
	protected ObservationHistoryService observationService = SpringContext.getBean(ObservationHistoryService.class);
	protected AnalysisService analysisService = SpringContext.getBean(AnalysisService.class);
	protected NoteService noteService = SpringContext.getBean(NoteService.class);
	protected PersonAddressService addressService = SpringContext.getBean(PersonAddressService.class);
	protected AddressPartService addressPartService = SpringContext.getBean(AddressPartService.class);

	private List<String> handledOrders;
	private List<Analysis> updatedAnalysis = new ArrayList<>();

	private String lowerNumber;
	private String upperNumber;
	protected String STNumber = null;
	protected String subjectNumber = null;
	protected String healthRegion = null;
	protected String healthDistrict = null;
	protected String patientName = null;
	protected String patientDOB = null;
	protected String currentConclusion = null;

	protected String patientDept = null;
	protected String patientCommune = null;

	protected Provider currentProvider;
	protected AnalysisServiceImpl currentAnalysisService;
	protected String reportReferralResultValue;
	protected List<ClinicalPatientData> reportItems;
	protected String completionDate;
	protected SampleServiceImpl currentSampleService;

	protected static final NoteType[] FILTER = { NoteType.EXTERNAL, NoteType.REJECTION_REASON,
			NoteType.NON_CONFORMITY };
	protected Map<String, Boolean> sampleCompleteMap;
	protected Map<String, Boolean> sampleCorrectedMap;

	@PostConstruct
	private void initialize() {
		List<AddressPart> partList = addressPartService.getAll();
		for (AddressPart part : partList) {
			if ("department".equals(part.getPartName())) {
				ADDRESS_DEPT_ID = part.getId();
			} else if ("commune".equals(part.getPartName())) {
				ADDRESS_COMMUNE_ID = part.getId();
			}
		}
	}

	abstract protected String getReportNameForParameterPage();

	abstract protected void postSampleBuild();

	abstract protected void createReportItems();

	abstract protected void setReferredResult(ClinicalPatientData data, Result result);

	protected boolean appendUOMToRange() {
		return true;
	}

	protected boolean augmentResultWithFlag() {
		return true;
	}

	protected boolean useReportingDescription() {
		return true;
	}

	public void setRequestParameters(BaseForm form) {
		try {
			PropertyUtils.setProperty(form, "reportName", getReportNameForParameterPage());

			PropertyUtils.setProperty(form, "useAccessionDirect", Boolean.TRUE);
			PropertyUtils.setProperty(form, "useHighAccessionDirect", Boolean.TRUE);
			PropertyUtils.setProperty(form, "usePatientNumberDirect", Boolean.TRUE);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initializeReport(BaseForm form) {
		super.initializeReport();
		errorFound = false;
		lowerNumber = form.getString("accessionDirect");
		upperNumber = form.getString("highAccessionDirect");
		String patientNumber = form.getString("patientNumberDirect");

		handledOrders = new ArrayList<>();

		createReportParameters();

		boolean valid;
		List<Sample> reportSampleList = new ArrayList<>();

		if (GenericValidator.isBlankOrNull(lowerNumber) && GenericValidator.isBlankOrNull(upperNumber)) {
			List<Patient> patientList = new ArrayList<>();
			valid = findPatientByPatientNumber(patientNumber, patientList);

			if (valid) {
				reportSampleList = findReportSamplesForReportPatient(patientList);
			}
		} else {
			valid = validateAccessionNumbers();

			if (valid) {
				reportSampleList = findReportSamples(lowerNumber, upperNumber);
			}
		}

		sampleCompleteMap = new HashMap<>();
		sampleCorrectedMap = new HashMap<>();
		initializeReportItems();

		if (reportSampleList.isEmpty()) {
			add1LineErrorMessage("report.error.message.noPrintableItems");
		} else {

			for (Sample sample : reportSampleList) {
				currentSampleService = new SampleServiceImpl(sample);
				handledOrders.add(sample.getId());
				sampleCompleteMap.put(sample.getAccessionNumber(), Boolean.TRUE);
				findCompletionDate();
				findPatientFromSample();
				findContactInfo();
				findPatientInfo();
				createReportItems();
			}

			postSampleBuild();
		}

		if (!updatedAnalysis.isEmpty()) {
			try {
				analysisService.updateAll(updatedAnalysis, true);
//				for (Analysis analysis : updatedAnalysis) {
//					analysisService.update(analysis, true);
//				}

			} catch (LIMSRuntimeException lre) {
				LogEvent.logErrorStack(this.getClass().getSimpleName(), "initializeReport()", lre);
			}
		}
	}

	private void findCompletionDate() {
		Date date = currentSampleService.getCompletedDate();
		completionDate = date == null ? null : DateUtil.convertSqlDateToStringDate(date);
	}

	private void findPatientInfo() {
		if (patientService.getPerson() == null) {
			return;
		}

		patientDept = "";
		patientCommune = "";
		if (ADDRESS_DEPT_ID != null) {
			PersonAddress deptAddress = addressService.getByPersonIdAndPartId(patientService.getPerson().getId(),
					ADDRESS_DEPT_ID);

			if (deptAddress != null && !GenericValidator.isBlankOrNull(deptAddress.getValue())) {
				patientDept = dictionaryService.getDictionaryById(deptAddress.getValue()).getDictEntry();
			}
		}

		if (ADDRESS_COMMUNE_ID != null) {
			PersonAddress deptAddress = addressService.getByPersonIdAndPartId(patientService.getPerson().getId(),
					ADDRESS_COMMUNE_ID);

			if (deptAddress != null) {
				patientCommune = deptAddress.getValue();
			}
		}

	}

	private void findContactInfo() {
		currentContactInfo = "";
		currentSiteInfo = "";
		currentProvider = null;

		Organization org = currentSampleService.getOrganizationRequester();
		if (org != null) {
			currentSiteInfo = org.getOrganizationName();
		}

		Person person = currentSampleService.getPersonRequester();
		if (person != null) {
			currentContactInfo = new PersonServiceImpl(person).getLastFirstName();
			currentProvider = providerService.getProviderByPerson(person);
		}
	}

	private boolean findPatientByPatientNumber(String patientNumber, List<Patient> patientList) {
		patientService = null;
		PatientIdentityService patientIdentityService = SpringContext.getBean(PatientIdentityService.class);
		patientList.addAll(patientService.getPatientsByNationalId(patientNumber));

		if (patientList.isEmpty()) {
			List<PatientIdentity> identities = patientIdentityService.getPatientIdentitiesByValueAndType(patientNumber,
					PatientServiceImpl.PATIENT_ST_IDENTITY);

			if (identities.isEmpty()) {
				identities = patientIdentityService.getPatientIdentitiesByValueAndType(patientNumber,
						PatientServiceImpl.PATIENT_SUBJECT_IDENTITY);
			}

			if (!identities.isEmpty()) {

				for (PatientIdentity patientIdentity : identities) {
					String reportPatientId = patientIdentity.getPatientId();
					Patient patient = new Patient();
					patient.setId(reportPatientId);
					patientService.getData(patient);
					patientList.add(patient);
				}
			}
		}

		return !patientList.isEmpty();
	}

	private List<Sample> findReportSamplesForReportPatient(List<Patient> patientList) {
		List<Sample> sampleList = new ArrayList<>();
		for (Patient searchPatient : patientList) {
			sampleList.addAll(sampleHumanService.getSamplesForPatient(searchPatient.getId()));
		}

		return sampleList;
	}

	private boolean validateAccessionNumbers() {

		if (GenericValidator.isBlankOrNull(lowerNumber) && GenericValidator.isBlankOrNull(upperNumber)) {
			add1LineErrorMessage("report.error.message.noParameters");
			return false;
		}

		if (GenericValidator.isBlankOrNull(lowerNumber)) {
			lowerNumber = upperNumber;
		} else if (GenericValidator.isBlankOrNull(upperNumber)) {
			upperNumber = lowerNumber;
		}

		if (lowerNumber.length() != upperNumber.length()
				|| AccessionNumberUtil.correctFormat(lowerNumber,
						false) != IAccessionNumberValidator.ValidationResults.SUCCESS
				|| AccessionNumberUtil.correctFormat(lowerNumber,
						false) != IAccessionNumberValidator.ValidationResults.SUCCESS) {
			add1LineErrorMessage("report.error.message.accession.not.valid");
			return false;
		}

		if (lowerNumber.compareToIgnoreCase(upperNumber) > 0) {
			String temp = upperNumber;
			upperNumber = lowerNumber;
			lowerNumber = temp;
		}

		return true;
	}

	private List<Sample> findReportSamples(String lowerNumber, String upperNumber) {
		List<Sample> sampleList = sampleService.getSamplesByAccessionRange(lowerNumber, upperNumber);
		return sampleList == null ? new ArrayList<>() : sampleList;
	}

	protected void findPatientFromSample() {
		Patient patient = sampleHumanService.getPatientForSample(currentSampleService.getSample());

		if (patientService == null || !patient.getId().equals(patientService.getPatientId())) {
			STNumber = null;
			patientDOB = null;
			patientService = new PatientServiceImpl(patient);
		}
	}

	@Override
	protected void createReportParameters() {
		super.createReportParameters();
		reportParameters.put("siteId", ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));
		reportParameters.put("headerName", getHeaderName());
		if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti LNSP")) {
			reportParameters.put("useSTNumber", Boolean.FALSE);
		} else {
			reportParameters.put("useSTNumber", Boolean.TRUE);
		}
	}

	protected abstract String getHeaderName();

	protected String getPatientDOB() {
		if (patientDOB == null) {
			patientDOB = patientService.getBirthdayForDisplay();
		}

		return patientDOB;
	}

	protected String getLazyPatientIdentity(String identity, String id) {
		if (identity == null) {
			identity = " ";
			List<PatientIdentity> identities = patientService.getIdentityList();
			for (PatientIdentity patientIdentity : identities) {
				if (patientIdentity.getIdentityTypeId().equals(id)) {
					identity = patientIdentity.getIdentityData();
					break;
				}
			}
		}

		return identity;
	}

	protected void setPatientName(ClinicalPatientData data) {
		data.setPatientName(patientService.getLastFirstName());
		data.setFirstName(patientService.getFirstName());
		data.setLastName(patientService.getLastName());
	}

	protected void reportResultAndConclusion(ClinicalPatientData data) {
		List<Result> resultList = currentAnalysisService.getResults();

		Test test = currentAnalysisService.getTest();
		String note = new NoteServiceImpl(currentAnalysisService.getAnalysis()).getNotesAsString(true, true, "<br/>",
				FILTER, true);
		if (note != null) {
			data.setNote(note);
		}
		data.setTestSection(currentAnalysisService.getTestSection().getLocalizedName());
		data.setTestSortOrder(GenericValidator.isBlankOrNull(test.getSortOrder()) ? Integer.MAX_VALUE
				: Integer.parseInt(test.getSortOrder()));
		data.setSectionSortOrder(test.getTestSection().getSortOrderInt());

		if (StatusService.getInstance().matches(currentAnalysisService.getStatusId(), AnalysisStatus.Canceled)) {
			data.setResult(MessageUtil.getMessage("report.test.status.canceled"));
		} else if (currentAnalysisService.getAnalysis().isReferredOut()) {
			setReferredOutResult(data);
			return;
			/*
			 * Not sure which rules this would support -- above statement was conditional on
			 * no patient alerts if( noResults( resultList ) ){ data.setResult(
			 * MessageUtil.getMessage( "report.test.status.referredOut" ) ); }else{
			 * setAppropriateResults( resultList, data ); setReferredResult( data,
			 * resultList.get( 0 ) ); setNormalRange( data, test, resultList.get( 0 ) ); }
			 */
		} else if (!StatusService.getInstance().matches(currentAnalysisService.getStatusId(),
				AnalysisStatus.Finalized)) {
			sampleCompleteMap.put(currentSampleService.getAccessionNumber(), Boolean.FALSE);
			setEmptyResult(data);
		} else {
			if (resultList.isEmpty()) {
				setEmptyResult(data);
			} else {
				setAppropriateResults(resultList, data);
				Result result = resultList.get(0);
				setCorrectedStatus(result, data);
				setNormalRange(data, test, result);
				data.setResult(getAugmentedResult(data, result));
				data.setFinishDate(currentAnalysisService.getCompletedDateForDisplay());
				data.setAlerts(getResultFlag(result, null, data));
			}
		}

		data.setParentResult(currentAnalysisService.getAnalysis().getParentResult());
		data.setConclusion(currentConclusion);
	}

	protected void setReferredOutResult(ClinicalPatientData data) {
		data.setResult(MessageUtil.getMessage("report.test.status.inProgress"));
	}

	protected void setEmptyResult(ClinicalPatientData data) {
		data.setResult(MessageUtil.getMessage("report.test.status.inProgress"));
	}

	private void setCorrectedStatus(Result result, ClinicalPatientData data) {
		if (currentAnalysisService.getAnalysis().isCorrectedSincePatientReport()
				&& !GenericValidator.isBlankOrNull(result.getValue())) {
			data.setCorrectedResult(true);
			sampleCorrectedMap.put(currentSampleService.getAccessionNumber(), true);
			currentAnalysisService.getAnalysis().setCorrectedSincePatientReport(false);
			updatedAnalysis.add(currentAnalysisService.getAnalysis());
		}
	}

	private void setNormalRange(ClinicalPatientData data, Test test, Result result) {
		String uom = getUnitOfMeasure(test);
		data.setTestRefRange(addIfNotEmpty(getRange(result), appendUOMToRange() ? uom : null));
		data.setUom(uom);
	}

	private String getAugmentedResult(ClinicalPatientData data, Result result) {
		String resultValue = data.getResult();
		if (TestIdentityService.getInstance().isTestNumericViralLoad(currentAnalysisService.getTest())) {
			try {
				resultValue += " (" + twoDecimalFormat.format(Math.log10(Double.parseDouble(resultValue))) + ")log ";
			} catch (IllegalFormatException e) {
				// no-op
			}
		}

		return resultValue + (augmentResultWithFlag() ? getResultFlag(result, null) : "");
	}

	protected String getResultFlag(Result result, String imbed) {
		return getResultFlag(result, imbed, null);
	}

	protected String getResultFlag(Result result, String imbed, ClinicalPatientData data) {
		String flag = "";
		try {
			if (TypeOfTestResultServiceImpl.ResultType.NUMERIC.matches(result.getResultType())
					&& !GenericValidator.isBlankOrNull(result.getValue())) {
				if (result.getMinNormal() != null & result.getMaxNormal() != null
						&& (result.getMinNormal() != 0.0 || result.getMaxNormal() != 0.0)) {
					if (Double.valueOf(result.getValue()) < result.getMinNormal()) {
						flag = "B";
					} else if (Double.valueOf(result.getValue()) > result.getMaxNormal()) {
						flag = "E";
					}
				}
			} else if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(result.getResultType())) {
				boolean isAbnormal;

				if (data == null) {
					isAbnormal = new ResultServiceImpl(result).isAbnormalDictionaryResult();
				} else {
					isAbnormal = data.getAbnormalResult();
				}
				if (isAbnormal) {
					flag = "*";
				}
			}

			if (!GenericValidator.isBlankOrNull(flag)) {
				if (data != null) {
					data.setAbnormalResult(Boolean.TRUE);
				}
				if (!GenericValidator.isBlankOrNull(imbed)) {
					return " <b>" + flag + "," + imbed + "</b>";
				} else {
					return " <b>" + flag + "</b>";
				}
			}
		} catch (NumberFormatException e) {
			// no-op
		}

		if (!GenericValidator.isBlankOrNull(imbed)) {
			return " (<b>" + imbed + "</b>)";
		}

		return "";
	}

	protected String getRange(Result result) {
		return new ResultServiceImpl(result).getDisplayReferenceRange(true);
	}

	protected String getUnitOfMeasure(Test test) {
		return (test != null && test.getUnitOfMeasure() != null) ? test.getUnitOfMeasure().getName() : "";
	}

	private void setAppropriateResults(List<Result> resultList, ClinicalPatientData data) {
		String reportResult = "";
		if (!resultList.isEmpty()) {

			// If only one result just get it and get out
			if (resultList.size() == 1) {
				Result result = resultList.get(0);
				if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(result.getResultType())) {
					Dictionary dictionary = new Dictionary();
					dictionary.setId(result.getValue());
					dictionaryService.getData(dictionary);
					data.setAbnormalResult(new ResultServiceImpl(result).isAbnormalDictionaryResult());

					if (result.getAnalyte() != null && "Conclusion".equals(result.getAnalyte().getAnalyteName())) {
						currentConclusion = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
					} else {
						reportResult = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
					}
				} else {
					reportResult = new ResultServiceImpl(result).getResultValue(true);
					// TODO - how is this used. Selection types can also have
					// UOM and reference ranges
					data.setHasRangeAndUOM(
							TypeOfTestResultServiceImpl.ResultType.NUMERIC.matches(result.getResultType()));
				}
			} else {
				// If multiple results it can be a quantified result, multiple
				// results with quantified other results or it can be a
				// conclusion
				ResultServiceImpl resultService = new ResultServiceImpl(resultList.get(0));

				if (TypeOfTestResultServiceImpl.ResultType.DICTIONARY.matches(resultService.getTestType())) {
					data.setAbnormalResult(resultService.isAbnormalDictionaryResult());
					List<Result> dictionaryResults = new ArrayList<>();
					Result quantification = null;
					for (Result sibResult : resultList) {
						if (TypeOfTestResultServiceImpl.ResultType.DICTIONARY.matches(sibResult.getResultType())) {
							dictionaryResults.add(sibResult);
						} else if (TypeOfTestResultServiceImpl.ResultType.ALPHA.matches(sibResult.getResultType())
								&& sibResult.getParentResult() != null) {
							quantification = sibResult;
						}
					}

					Dictionary dictionary = new Dictionary();
					for (Result sibResult : dictionaryResults) {
						dictionary.setId(sibResult.getValue());
						dictionaryService.getData(dictionary);
						if (sibResult.getAnalyte() != null
								&& "Conclusion".equals(sibResult.getAnalyte().getAnalyteName())) {
							currentConclusion = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
						} else {
							reportResult = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
							if (quantification != null
									&& quantification.getParentResult().getId().equals(sibResult.getId())) {
								reportResult += ": " + quantification.getValue();
							}
						}
					}
				} else if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(resultService.getTestType())) {
					Dictionary dictionary = new Dictionary();
					StringBuilder multiResult = new StringBuilder();

					Collections.sort(resultList, new Comparator<Result>() {
						@Override
						public int compare(Result o1, Result o2) {
							if (o1.getGrouping() == o2.getGrouping()) {
								return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
							} else {
								return o1.getGrouping() - o2.getGrouping();
							}
						}
					});

					Result quantifiedResult = null;
					for (Result subResult : resultList) {
						if (TypeOfTestResultServiceImpl.ResultType.ALPHA.matches(subResult.getResultType())) {
							quantifiedResult = subResult;
							resultList.remove(subResult);
							break;
						}
					}
					int currentGrouping = resultList.get(0).getGrouping();
					for (Result subResult : resultList) {
						if (subResult.getGrouping() != currentGrouping) {
							currentGrouping = subResult.getGrouping();
							multiResult.append("-------\n");
						}
						dictionary.setId(subResult.getValue());
						dictionaryService.getData(dictionary);

						if (dictionary.getId() != null) {
							multiResult.append(dictionary.getLocalizedName());
							if (quantifiedResult != null
									&& quantifiedResult.getParentResult().getId().equals(subResult.getId())
									&& !GenericValidator.isBlankOrNull(quantifiedResult.getValue())) {
								multiResult.append(": ");
								multiResult.append(quantifiedResult.getValue());
							}
							multiResult.append("\n");
						}
					}

					if (multiResult.length() > 1) {
						// remove last "\n"
						multiResult.setLength(multiResult.length() - 1);
					}

					reportResult = multiResult.toString();
				}
			}
		}
		data.setResult(reportResult);

	}

	protected void setCollectionTime(Set<SampleItem> sampleSet, List<ClinicalPatientData> currentSampleReportItems,
			boolean addAccessionNumber) {
		StringBuilder buffer = new StringBuilder();
		boolean firstItem = true;
		for (SampleItem sampleItem : sampleSet) {
			if (firstItem) {
				firstItem = false;
			} else {
				buffer.append(", ");
			}

			buffer.append(sampleItem.getTypeOfSample().getLocalizedName());
			if (addAccessionNumber) {
				buffer.append(" ");
				buffer.append(sampleItem.getSample().getAccessionNumber() + "-" + sampleItem.getSortOrder());
			}
			if (sampleItem.getCollectionDate() == null) {
				buffer.append(" -- ");
				buffer.append(MessageUtil.getMessage("label.not.available"));
			} else {
				buffer.append(" ");
				buffer.append(
						DateUtil.convertTimestampToStringDateAndConfiguredHourTime(sampleItem.getCollectionDate()));
			}
		}

		String collectionTimes = buffer.toString();
		for (ClinicalPatientData clinicalPatientData : currentSampleReportItems) {
			clinicalPatientData.setCollectionDateTime(collectionTimes);
		}
	}

	/**
	 * @see PatientReport#initializeReportItems()
	 */
	protected void initializeReportItems() {
		reportItems = new ArrayList<>();
	}

	/**
	 * If you have a string that you wish to add a suffix like units of measure, use
	 * this.
	 *
	 * @param base something
	 * @param plus something to add, if the above is not null or blank.
	 * @return the two args put together, or the original if it was blank to begin
	 *         with.
	 */
	protected String addIfNotEmpty(String base, String plus) {
		return (!GenericValidator.isBlankOrNull(plus)) ? base + " " + plus : base;
	}

	/**
	 * Pushes all of the information about a patient, analysis, result and the
	 * conclusion into a new reporting object
	 *
	 * @return A single record
	 */
	protected ClinicalPatientData buildClinicalPatientData(boolean hasParent) {
		ClinicalPatientData data = new ClinicalPatientData();
		String testName = null;
		String sortOrder = "";
		String receivedDate = currentSampleService.getReceivedDateForDisplay();

		boolean doAnalysis = currentAnalysisService != null;

		if (doAnalysis) {
			testName = getTestName(hasParent);
			// Not sure if it is a bug in escapeHtml but the wrong markup is
			// generated
			testName = StringEscapeUtils.escapeHtml(testName).replace("&mu", "&micro");
		}

		if (FormFields.getInstance().useField(Field.SampleEntryUseReceptionHour)) {
			receivedDate += " " + currentSampleService.getReceivedTimeForDisplay();
		}

		data.setContactInfo(currentContactInfo);
		data.setSiteInfo(currentSiteInfo);
		data.setReceivedDate(receivedDate);
		data.setDob(getPatientDOB());
		data.setAge(createReadableAge(data.getDob()));
		data.setGender(patientService.getGender());
		data.setNationalId(patientService.getNationalId());
		setPatientName(data);
		data.setDept(patientDept);
		data.setCommune(patientCommune);
		data.setStNumber(getLazyPatientIdentity(STNumber, PatientServiceImpl.PATIENT_ST_IDENTITY));
		data.setSubjectNumber(getLazyPatientIdentity(subjectNumber, PatientServiceImpl.PATIENT_SUBJECT_IDENTITY));
		data.setHealthRegion(getLazyPatientIdentity(healthRegion, PatientServiceImpl.PATIENT_HEALTH_REGION_IDENTITY));
		data.setHealthDistrict(
				getLazyPatientIdentity(healthDistrict, PatientServiceImpl.PATIENT_HEALTH_DISTRICT_IDENTITY));
		data.setLabOrderType(ObservationHistoryServiceImpl.getInstance().getValueForSample(ObservationType.PROGRAM,
				currentSampleService.getId()));
		data.setTestName(testName);
		data.setPatientSiteNumber(ObservationHistoryServiceImpl.getInstance()
				.getValueForSample(ObservationType.REFERRERS_PATIENT_ID, currentSampleService.getId()));
		data.setBillingNumber(ObservationHistoryServiceImpl.getInstance()
				.getValueForSample(ObservationType.BILLING_REFERENCE_NUMBER, currentSampleService.getId()));

		if (doAnalysis) {
			data.setPanel(currentAnalysisService.getPanel());
			if (currentAnalysisService.getPanel() != null) {
				data.setPanelName(currentAnalysisService.getPanel().getLocalizedName());
			}
			data.setTestDate(currentAnalysisService.getCompletedDateForDisplay());
			data.setSampleSortOrder(currentAnalysisService.getAnalysis().getSampleItem().getSortOrder());
			data.setOrderFinishDate(completionDate);
			data.setOrderDate(
					DateUtil.convertTimestampToStringDateAndConfiguredHourTime(currentSampleService.getOrderedDate()));
			data.setSampleId(currentSampleService.getAccessionNumber() + "-" + data.getSampleSortOrder());
			data.setSampleType(currentAnalysisService.getTypeOfSample().getLocalizedName());
			data.setCollectionDateTime(DateUtil.convertTimestampToStringDateAndConfiguredHourTime(
					currentAnalysisService.getAnalysis().getSampleItem().getCollectionDate()));
		}

		data.setAccessionNumber(currentSampleService.getAccessionNumber() + "-" + sortOrder);

		if (doAnalysis) {
			reportResultAndConclusion(data);
		}

		return data;
	}

	private String getTestName(boolean indent) {
		String testName;

		if (useReportingDescription()) {
			testName = TestServiceImpl.getUserLocalizedReportingTestName(currentAnalysisService.getTest());
		} else {
			testName = TestServiceImpl.getUserLocalizedTestName(currentAnalysisService.getTest());
		}

		if (GenericValidator.isBlankOrNull(testName)) {
			testName = TestServiceImpl.getUserLocalizedTestName(currentAnalysisService.getTest());
		}
		return (indent ? "    " : "") + testName;
	}

	/**
	 * Given a list of referralResults for a particular analysis, generated the next
	 * displayable value made of from one or more of the values from the list
	 * starting at the given index. It uses multiresult form the list when the
	 * results are for the same test.
	 *
	 * @param referralResultsForReferral The referral
	 * @param i                          starting index.
	 * @return last index actually used. If you start with 2 and this routine uses
	 *         just item #2, then return result is 2, but if there are two results
	 *         for the same test (e.g. a multi-select result) and those are in item
	 *         item 2 and item 3 this routine returns #3.
	 */
	protected int reportReferralResultValue(List<ReferralResult> referralResultsForReferral, int i) {
		ReferralResult referralResult = referralResultsForReferral.get(i);
		reportReferralResultValue = "";
		String currTestId = referralResult.getTestId();
		do {
			reportReferralResultValue += findDisplayableReportResult(referralResult.getResult()) + ", ";
			i++;
			if (i >= referralResultsForReferral.size()) {
				break;
			}
			referralResult = referralResultsForReferral.get(i);
		} while (currTestId.equals(referralResult.getTestId()));
		reportReferralResultValue = reportReferralResultValue.substring(0, reportReferralResultValue.length() - 2);
		i--;
		return i;
	}

	/**
	 * Derive the appropriate displayable string results, either dictionary result
	 * or direct value.
	 *
	 * @param result The result
	 * @return a reportable result string.
	 */
	private String findDisplayableReportResult(Result result) {
		String reportResult = "";
		if (result == null) {
			return reportResult;
		}
		String type = result.getResultType();
		String value = result.getValue();
		if (value == null) {
			return reportResult;
		}
		if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(type)) {
			if (result.getValue() != null && !"null".equals(result.getValue())) {
				Dictionary dictionary = new Dictionary();
				dictionary.setId(result.getValue());
				dictionaryService.getData(dictionary);
				reportResult = dictionary.getId() != null ? dictionary.getLocalizedName() : "";
			}
		} else {
			reportResult = result.getValue();
		}
		return reportResult;
	}

	private String createReadableAge(String dob) {
		if (GenericValidator.isBlankOrNull(dob)) {
			return "";
		}

		dob = dob.replaceAll("xx", "01");
		Date dobDate = DateUtil.convertStringDateToSqlDate(dob);
		int months = DateUtil.getAgeInMonths(dobDate, DateUtil.getNowAsSqlDate());
		if (months > 35) {
			return (months / 12) + " " + MessageUtil.getMessage("abbreviation.year.single");
		} else if (months > 0) {
			return months + " " + MessageUtil.getMessage("abbreviation.month.single");
		} else {
			int days = DateUtil.getAgeInDays(dobDate, DateUtil.getNowAsSqlDate());
			return days + " " + MessageUtil.getMessage("abbreviation.day.single");
		}

	}

	@Override
	public List<String> getReportedOrders() {
		return handledOrders;
	}
}
