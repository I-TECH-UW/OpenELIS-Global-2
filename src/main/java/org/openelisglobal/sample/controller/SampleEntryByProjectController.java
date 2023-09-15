package org.openelisglobal.sample.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.ParameterComponent;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DecimalType;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.services.StatusService.SampleStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.dictionary.ObservationHistoryList;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.util.OrganizationTypeList;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.saving.ISampleEntry;
import org.openelisglobal.patient.saving.ISampleEntryAfterPatientEntry;
import org.openelisglobal.patient.saving.ISampleSecondEntry;
import org.openelisglobal.patient.valueholder.ObservationData;
import org.openelisglobal.sample.form.ProjectData;
import org.openelisglobal.sample.form.SampleEntryByProjectForm;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.uhn.fhir.rest.client.api.IGenericClient;

@Controller
public class SampleEntryByProjectController extends BaseSampleEntryController {

	@Value("${org.openelisglobal.requester.identifier:}")
	private String requestFhirUuid;

	@Autowired
	private ElectronicOrderService electronicOrderService;
	@Autowired
	private FhirPersistanceService fhirPersistanceService;

	@Autowired
	private FhirConfig fhirConfig;
	@Autowired
	private FhirUtil fhirUtil;

	private Task task = null;
	private Practitioner requesterPerson = null;
	private Practitioner collector = null;
	private org.hl7.fhir.r4.model.Organization referringOrganization = null;
	private Location location = null;
	private ServiceRequest serviceRequest = null;
	private Specimen specimen = null;
	private Patient fhirPatient = null;

	private static final String[] ALLOWED_FIELDS = new String[] { "currentDate", "domain", "project",
			"patientLastUpdated", "personLastUpdated", "patientUpdateStatus", "patientPK", "patientFhirUuid",
			"samplePK", "observations.projectFormName", "ProjectData.ARVcenterName", "ProjectData.ARVcenterCode",
			"observations.nameOfDoctor", "receivedDateForDisplay", "receivedTimeForDisplay", "interviewDate",
			"interviewTime", "subjectNumber", "siteSubjectNumber", "upidCode", "labNo", "gender", "birthDateForDisplay",
			"ProjectData.dryTubeTaken", "ProjectData.edtaTubeTaken", "ProjectData.serologyHIVTest",
			"ProjectData.glycemiaTest", "ProjectData.creatinineTest", "ProjectData.transaminaseTest",
			"ProjectData.nfsTest", "ProjectData.cd4cd8Test", "ProjectData.viralLoadTest", "ProjectData.genotypingTest",
			"observations.underInvestigation", "ProjectData.underInvestigationNote", "observations.hivStatus",
			"ProjectData.EIDSiteName", "projectData.EIDsiteCode", "observations.whichPCR",
			"observations.reasonForSecondPCRTest", "observations.nameOfRequestor", "observations.nameOfSampler",
			"observations.eidInfantPTME", "observations.eidTypeOfClinic", "observations.eidHowChildFed",
			"observations.eidStoppedBreastfeeding", "observations.eidInfantSymptomatic", "observations.eidInfantsARV",
			"observations.eidInfantCotrimoxazole", "observations.eidMothersHIVStatus", "observations.eidMothersARV",
			"ProjectData.dbsTaken", "ProjectData.dbsvlTaken", "ProjectData.pscvlTaken", "ProjectData.dnaPCR",
			"ProjectData.INDsiteName", "ProjectData.address", "ProjectData.phoneNumber", "ProjectData.faxNumber",
			"ProjectData.email", "observations.indFirstTestDate", "observations.indFirstTestName",
			"observations.indFirstTestResult", "observations.indSecondTestDate", "observations.indSecondTestName",
			"observations.indSecondTestResult", "observations.indSiteFinalResult", "observations.reasonForRequest",
			"ProjectData.murexTest", "ProjectData.integralTest", "ProjectData.vironostikaTest",
			"ProjectData.innoliaTest", "ProjectData.transaminaseALTLTest", "ProjectData.transaminaseASTLTest",
			"ProjectData.gbTest", "ProjectData.lymphTest", "ProjectData.monoTest", "ProjectData.eoTest",
			"ProjectData.basoTest", "ProjectData.grTest", "ProjectData.hbTest", "ProjectData.hctTest",
			"ProjectData.vgmTest", "ProjectData.tcmhTest", "ProjectData.ccmhTest", "ProjectData.plqTest",
			"ProjectData.cd3CountTest", "ProjectData.cd4CountTest", "observations.vlPregnancy", "observations.vlSuckle",
			"observations.currentARVTreatment", "observations.arvTreatmentInitDate", "observations.arvTreatmentRegime",
			"observations.currentARVTreatmentINNsList*", "observations.vlReasonForRequest",
			"observations.vlOtherReasonForRequest", "observations.initcd4Count", "observations.initcd4Percent",
			"observations.initcd4Date", "observations.demandcd4Count", "observations.demandcd4Percent",
			"observations.demandcd4Date", "observations.vlBenefit", "observations.priorVLValue",
			"observations.priorVLDate", "electronicOrder.externalId", "ProjectData.asanteTest",
			"ProjectData.plasmaTaken", "ProjectData.serumTaken"};

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(ALLOWED_FIELDS);
	}

	@RequestMapping(value = "/SampleEntryByProject", method = RequestMethod.GET)
	public ModelAndView showSampleEntryByProject(HttpServletRequest request) {
		SampleEntryByProjectForm form = new SampleEntryByProjectForm();

		Date today = Calendar.getInstance().getTime();
		String dateAsText = DateUtil.formatDateAsText(today);
		form.setReceivedDateForDisplay(dateAsText);
		form.setInterviewDate(dateAsText);

		setupFormData(request, form);

		setDisplayLists(form);
		addFlashMsgsToRequest(request);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupFormData(HttpServletRequest request, SampleEntryByProjectForm form) {
		try {
			String externalOrderNumber = request.getParameter("ID");
			if (StringUtils.isNotBlank(externalOrderNumber)) {
				ElectronicOrder eOrder = null;
				List<ElectronicOrder> eOrders = electronicOrderService
						.getElectronicOrdersByExternalId(externalOrderNumber);
				if (eOrders.size() > 0)
					eOrder = eOrders.get(eOrders.size() - 1);
				if (eOrder != null) {
					form.setElectronicOrder(eOrder);
					IGenericClient localFhirClient = fhirUtil.getLocalFhirClient();
					for (String remotePath : fhirConfig.getRemoteStorePaths()) {
						Bundle srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
								.where(ServiceRequest.RES_ID.exactly().code(externalOrderNumber))
								.include(ServiceRequest.INCLUDE_SPECIMEN).execute();
						for (BundleEntryComponent bundleComponent : srBundle.getEntry()) {
							if (bundleComponent.hasResource() && ResourceType.ServiceRequest
									.equals(bundleComponent.getResource().getResourceType())) {
								serviceRequest = (ServiceRequest) bundleComponent.getResource();
							}
							if (bundleComponent.hasResource()
									&& ResourceType.Specimen.equals(bundleComponent.getResource().getResourceType())) {
								specimen = (Specimen) bundleComponent.getResource();
							}
						}
						srBundle = (Bundle) localFhirClient
								.search().forResource(ServiceRequest.class).where(ServiceRequest.IDENTIFIER.exactly()
										.systemAndIdentifier(remotePath, externalOrderNumber))
								.include(ServiceRequest.INCLUDE_SPECIMEN).execute();
						for (BundleEntryComponent bundleComponent : srBundle.getEntry()) {
							if (bundleComponent.hasResource() && ResourceType.ServiceRequest
									.equals(bundleComponent.getResource().getResourceType())) {
								serviceRequest = (ServiceRequest) bundleComponent.getResource();
							}
							if (bundleComponent.hasResource()
									&& ResourceType.Specimen.equals(bundleComponent.getResource().getResourceType())) {
								specimen = (Specimen) bundleComponent.getResource();
							}
						}
					}
					if (serviceRequest != null) {
						LogEvent.logDebug(this.getClass().getName(), "processRequest",
								"found matching serviceRequest " + serviceRequest.getIdElement().getIdPart());
					} else {
						LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching serviceRequest");
					}
					fhirPatient = localFhirClient.read()//
							.resource(Patient.class)//
							.withId(serviceRequest.getSubject().getReferenceElement().getIdPart())//
							.execute();
					if (fhirPatient != null) {
						LogEvent.logDebug(this.getClass().getName(), "processRequest",
								"found matching patient " + fhirPatient.getIdElement().getIdPart());
					} else {
						LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching patient");
					}
					task = fhirPersistanceService.getTaskBasedOnServiceRequest(externalOrderNumber).orElseThrow();
					if (task != null) {
						LogEvent.logDebug(this.getClass().getName(), "processRequest",
								"found matching task " + task.getIdElement().getIdPart());
					} else {
						LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching task");
					}
					if (!GenericValidator.isBlankOrNull(
							task.getRestriction().getRecipientFirstRep().getReferenceElement().getIdPart())) {
						referringOrganization = localFhirClient.read()//
								.resource(org.hl7.fhir.r4.model.Organization.class)//
								.withId(task.getRestriction().getRecipientFirstRep().getReferenceElement().getIdPart())//
								.execute();
						if (referringOrganization != null) {
							LogEvent.logDebug(this.getClass().getName(), "processRequest",
									"found matching organization " + referringOrganization.getIdElement().getIdPart());
						} else {
							LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching organization");
						}
					}
					if (!GenericValidator.isBlankOrNull(
							serviceRequest.getLocationReferenceFirstRep().getReferenceElement().getIdPart())) {
						location = localFhirClient.read()//
								.resource(Location.class)//
								.withId(serviceRequest.getLocationReferenceFirstRep().getReferenceElement().getIdPart())//
								.execute();
						if (location != null) {
							LogEvent.logDebug(this.getClass().getName(), "processRequest",
									"found matching location " + location.getIdElement().getIdPart());
						} else {
							LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching location");
						}
					}
					if (!GenericValidator.isBlankOrNull(serviceRequest.getRequester().getReferenceElement().getIdPart())
							&& serviceRequest.getRequester().getReference()
									.contains(ResourceType.Practitioner.toString())) {
						requesterPerson = localFhirClient.read()//
								.resource(Practitioner.class)//
								.withId(serviceRequest.getRequester().getReferenceElement().getIdPart())//
								.execute();

						if (requesterPerson != null) {
							LogEvent.logDebug(this.getClass().getName(), "processRequest",
									"found matching requester " + requesterPerson.getIdElement().getIdPart());
						} else {
							LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching requester");
						}
					}
					if (specimen != null && !GenericValidator
							.isBlankOrNull(specimen.getCollection().getCollector().getReferenceElement().getIdPart())) {
						collector = localFhirClient.read()//
								.resource(Practitioner.class)//
								.withId(specimen.getCollection().getCollector().getReferenceElement().getIdPart())//
								.execute();

						if (collector != null) {
							LogEvent.logDebug(this.getClass().getName(), "processRequest",
									"found matching collector " + collector.getIdElement().getIdPart());
						} else {
							LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching collector");
						}
					}
					loadDataInForm(form);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadDataInForm(SampleEntryByProjectForm form) {
		ProjectData projectData = new ProjectData();
		ObservationData observationData = new ObservationData();
		form.setBirthDateForDisplay(DateUtil.formatDateAsText(fhirPatient.getBirthDate()));
		form.setGender(fhirPatient.getGender().getDisplay().substring(0, 1).toUpperCase());
		if (ObjectUtils.isNotEmpty(fhirPatient.getIdElement())) {
			form.setPatientFhirUuid(fhirPatient.getIdElement().getIdPart());
		}
		for (Identifier identifier : fhirPatient.getIdentifier()) {
			if ((fhirConfig.getOeFhirSystem() + "/pat_subjectNumber").equals(identifier.getSystem())) {
				form.setSubjectNumber(identifier.getValue());
			}
			if ((fhirConfig.getOeFhirSystem() + "/pat_nationalId").equals(identifier.getSystem())) {
				form.setSiteSubjectNumber(identifier.getValue());
			}
		}
		for (ParameterComponent parameter : task.getInput()) {
			if (parameter.getType().getCodingFirstRep().getCode().equals("160533AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// currentARVTreatment
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType onARTTreatment = (StringType) parameter.getValue();
						if (onARTTreatment.getValue().equalsIgnoreCase("true")) {
							observationData.setCurrentARVTreatment("1253");
						} else if (onARTTreatment.getValue().equalsIgnoreCase("false")) {
							observationData.setCurrentARVTreatment("1251");
						}
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("CI0050001AAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vlOtherReasonForRequest
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType vlOtherReasonForRequestType = (StringType) parameter.getValue();
						observationData.setVlOtherReasonForRequest(vlOtherReasonForRequestType.getValue());
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("CI0050007AAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// sampleType
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType sampleTypeType = (StringType) parameter.getValue();
						if (sampleTypeType.getValue().equalsIgnoreCase("Plasma")) {
							projectData.setEdtaTubeTaken(true);
						} else if (sampleTypeType.getValue().equalsIgnoreCase("DBS")) {
							projectData.setdbsvlTaken(true);
						} else if (sampleTypeType.getValue().equalsIgnoreCase("PSC")) {
							projectData.setPscvlTaken(true);
						}
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("166073AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// arvTreatmentRegime
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType arvTreatmentRegimeType = (StringType) parameter.getValue();
						if (arvTreatmentRegimeType.getValue().equalsIgnoreCase("Première")) {
							observationData.setArvTreatmentRegime("1206");
						} else if (arvTreatmentRegimeType.getValue().equalsIgnoreCase("Deuxième")) {
							observationData.setArvTreatmentRegime("1213");
						} else if (arvTreatmentRegimeType.getValue().equalsIgnoreCase("Troisième")) {
							observationData.setArvTreatmentRegime("1219");
						}
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("164792AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.initcd4Percent
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DecimalType) {
						DecimalType initcd4PercentType = (DecimalType) parameter.getValue();
						observationData.setInitcd4Percent(initcd4PercentType.getValue().toString());
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("730AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.demandcd4Percent
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DecimalType) {
						DecimalType demandcd4PercentType = (DecimalType) parameter.getValue();
						observationData.setDemandcd4Percent(demandcd4PercentType.getValue().toString());
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("163545AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.priorVLValue
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DecimalType) {
						DecimalType priorVLValueType = (DecimalType) parameter.getValue();
						observationData.setPriorVLValue(priorVLValueType.getValue().toString());
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("CI0030001AAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.hivStatus
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType hivStatusType = (StringType) parameter.getValue();
						if (hivStatusType.getValue().equalsIgnoreCase("VIH-1")) {
							observationData.setHivStatus("1264");
						} else if (hivStatusType.getValue().equalsIgnoreCase("VIH-1+2")) {
							observationData.setHivStatus("1263");
						} else if (hivStatusType.getValue().equalsIgnoreCase("VIH-2")) {
							observationData.setHivStatus("1265");
						}
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("159599AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.arvTreatmentInitDate
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DateTimeType) {
						DateTimeType dateValue = (DateTimeType) parameter.getValue();
						observationData.setArvTreatmentInitDate(DateUtil.formatDateAsText(dateValue.getValue()));
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("160103AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.demandcd4Date
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DateTimeType) {
						DateTimeType dateValue = (DateTimeType) parameter.getValue();
						observationData.setDemandcd4Date(DateUtil.formatDateAsText(dateValue.getValue()));
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("163281AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.priorVLDate
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DateTimeType) {
						DateTimeType dateValue = (DateTimeType) parameter.getValue();
						observationData.setPriorVLDate(DateUtil.formatDateAsText(dateValue.getValue()));
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("164429AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.initcd4Count
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DecimalType) {
						DecimalType initcd4CountType = (DecimalType) parameter.getValue();
						observationData.setInitcd4Count(initcd4CountType.getValue().toString());
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("5497AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.demandcd4Count
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DecimalType) {
						DecimalType demandcd4CountType = (DecimalType) parameter.getValue();
						observationData.setDemandcd4Count(demandcd4CountType.getValue().toString());
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("159376AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.initcd4Date
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof DateTimeType) {
						DateTimeType dateValue = (DateTimeType) parameter.getValue();
						observationData.setInitcd4Date(DateUtil.formatDateAsText(dateValue.getValue()));
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("CI0050004AAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// vl.vlBenefit
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType vlBenefitType = (StringType) parameter.getValue();
						if (vlBenefitType.getValue().equalsIgnoreCase("Oui")
								|| vlBenefitType.getValue().equalsIgnoreCase("Yes")) {
							observationData.setVlBenefit("1253");
						} else if (vlBenefitType.getValue().equalsIgnoreCase("Non")
								|| vlBenefitType.getValue().equalsIgnoreCase("No")) {
							observationData.setVlBenefit("1251");
						}
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("162240AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// ARV
																													// Treatment
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType regimenType = (StringType) parameter.getValue();
						String regimen = regimenType.getValue();
						String[] regimenParts = regimen.split(" ");
						for (int i = 0; i < regimenParts.length && i < 3; i++) {
							observationData.setCurrentARVTreatmentINNs(i, regimenParts[i].trim());
						}
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("5272AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// Pregnancy
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType vlPregnancyType = (StringType) parameter.getValue();
						if (vlPregnancyType.getValue().equalsIgnoreCase("Oui")
								|| vlPregnancyType.getValue().equalsIgnoreCase("Yes")) {
							observationData.setVlPregnancy(("1253"));
						} else if (vlPregnancyType.getValue().equalsIgnoreCase("Non")
								|| vlPregnancyType.getValue().equalsIgnoreCase("No")) {
							observationData.setVlPregnancy("1251");
						}
					}
				}
			}
			if (parameter.getType().getCodingFirstRep().getCode().equals("5632AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")) {// Breastfeeding
				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
					if (parameter.getValue() instanceof StringType) {
						StringType vlBreastFeedingType = (StringType) parameter.getValue();
						if (vlBreastFeedingType.getValue().equalsIgnoreCase("Oui")
								|| vlBreastFeedingType.getValue().equalsIgnoreCase("Yes")) {
							observationData.setVlSuckle(("1253"));
						} else if (vlBreastFeedingType.getValue().equalsIgnoreCase("Non")
								|| vlBreastFeedingType.getValue().equalsIgnoreCase("No")) {
							observationData.setVlSuckle("1251");
						}
					}
				}
			}
		}
		if (ObjectUtils.isNotEmpty(serviceRequest)) {
			if (ObjectUtils.isNotEmpty(serviceRequest.getOccurrencePeriod())) {
				Date startDate = serviceRequest.getOccurrencePeriod().getStart();
				form.setInterviewDate(DateUtil.formatDateAsText(startDate));
			}
		}

		requesterPerson.getName().forEach(humanName -> {
			String lastName = humanName.getFamily();
			String firstName = String.join("",
					humanName.getGiven().stream().map(e -> e.asStringValue()).collect(Collectors.toList()));
			observationData.setNameOfDoctor(lastName + " " + firstName);
		});

		projectData.setViralLoadTest(true);
		form.setProjectData(projectData);
		form.setObservations(observationData);
	}

	@RequestMapping(value = "/SampleEntryByProject", method = RequestMethod.POST)
	public ModelAndView postSampleEntryByProject(HttpServletRequest request,
			@ModelAttribute("form") @Valid SampleEntryByProjectForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			saveErrors(result);
			setDisplayLists(form);
			return findForward(FWD_FAIL_INSERT, form);
		}
		String forward;

		ISampleSecondEntry sampleSecondEntry = SpringContext.getBean(ISampleSecondEntry.class);
		sampleSecondEntry.setFieldsFromForm(form);
		sampleSecondEntry.setSysUserId(getSysUserId(request));
		sampleSecondEntry.setRequest(request);
		if (sampleSecondEntry.canAccession()) {
			forward = handleSave(request, sampleSecondEntry, form);
			updateElectronicOrderStatus(form);
			if (forward != null) {
				if (FWD_SUCCESS_INSERT.equals(forward)) {
					redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
				} else {
					setDisplayLists(form);
				}
				return findForward(forward, form);
			}
		}
		ISampleEntry sampleEntry = SpringContext.getBean(ISampleEntry.class);
		sampleEntry.setFieldsFromForm(form);
		sampleEntry.setSysUserId(getSysUserId(request));
		sampleEntry.setRequest(request);
		if (sampleEntry.canAccession()) {
			forward = handleSave(request, sampleEntry, form);
			updateElectronicOrderStatus(form);
			if (forward != null) {
				if (FWD_SUCCESS_INSERT.equals(forward)) {
					redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
				} else {
					setDisplayLists(form);
				}
				return findForward(forward, form);
			}
		}
		ISampleEntryAfterPatientEntry sampleEntryAfterPatientEntry = SpringContext
				.getBean(ISampleEntryAfterPatientEntry.class);
		sampleEntryAfterPatientEntry.setFieldsFromForm(form);
		sampleEntryAfterPatientEntry.setSysUserId(getSysUserId(request));
		sampleEntryAfterPatientEntry.setRequest(request);
		if (sampleEntryAfterPatientEntry.canAccession()) {
			forward = handleSave(request, sampleEntryAfterPatientEntry, form);
			updateElectronicOrderStatus(form);
			if (forward != null) {
				if (FWD_SUCCESS_INSERT.equals(forward)) {
					redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
				} else {
					setDisplayLists(form);
				}
				return findForward(forward, form);
			}
		}
		logAndAddMessage(request, "postSampleEntryByProject", "errors.UpdateException");

		setDisplayLists(form);
		return findForward(FWD_FAIL_INSERT, form);
	}

	private void updateElectronicOrderStatus(SampleEntryByProjectForm form) {
		try {
			if (ObjectUtils.isNotEmpty(form.getElectronicOrder())) {
				String externalOrderId = form.getElectronicOrder().getExternalId();
				List<ElectronicOrder> eOrders = electronicOrderService.getElectronicOrdersByExternalId(externalOrderId);
				if (eOrders.size() > 0) {
					ElectronicOrder eOrder = eOrders.get(eOrders.size() - 1);
					eOrder.setStatusId(
							SpringContext.getBean(IStatusService.class).getStatusID(ExternalOrderStatus.Realized));
					electronicOrderService.update(eOrder);
					form.setElectronicOrder(eOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogEvent.logError(e);
		}
	}

	public SampleItem getSampleItem(Sample sample, TypeOfSample typeofsample) {
		SampleItem item = new SampleItem();
		item.setSample(sample);
		item.setTypeOfSample(typeofsample);
		item.setSortOrder(Integer.toString(1));
		item.setStatusId(SpringContext.getBean(IStatusService.class).getStatusID(SampleStatus.Entered));

		return item;
	}

	private void setDisplayLists(SampleEntryByProjectForm form) {
		Map<String, List<Dictionary>> formListsMapOfLists = new HashMap<>();
		List<Dictionary> listOfDictionary = new ArrayList<>();
		List<IdValuePair> genders = DisplayListService.getInstance().getList(ListType.GENDERS);

		for (IdValuePair i : genders) {
			Dictionary dictionary = new Dictionary();
			dictionary.setId(i.getId());
			dictionary.setDictEntry(i.getValue());
			listOfDictionary.add(dictionary);
		}

		formListsMapOfLists.put("GENDERS", listOfDictionary);
		form.setFormLists(formListsMapOfLists);

		// Get Lists
		Map<String, List<Dictionary>> observationHistoryMapOfLists = new HashMap<>();
		observationHistoryMapOfLists.put("EID_WHICH_PCR", ObservationHistoryList.EID_WHICH_PCR.getList());
		observationHistoryMapOfLists.put("EID_SECOND_PCR_REASON",
				ObservationHistoryList.EID_SECOND_PCR_REASON.getList());
		observationHistoryMapOfLists.put("EID_TYPE_OF_CLINIC", ObservationHistoryList.EID_TYPE_OF_CLINIC.getList());
		observationHistoryMapOfLists.put("EID_HOW_CHILD_FED", ObservationHistoryList.EID_HOW_CHILD_FED.getList());
		observationHistoryMapOfLists.put("EID_STOPPED_BREASTFEEDING",
				ObservationHistoryList.EID_STOPPED_BREASTFEEDING.getList());
		observationHistoryMapOfLists.put("YES_NO", ObservationHistoryList.YES_NO.getList());
		observationHistoryMapOfLists.put("EID_INFANT_PROPHYLAXIS_ARV",
				ObservationHistoryList.EID_INFANT_PROPHYLAXIS_ARV.getList());
		observationHistoryMapOfLists.put("YES_NO_UNKNOWN", ObservationHistoryList.YES_NO_UNKNOWN.getList());
		observationHistoryMapOfLists.put("EID_MOTHERS_HIV_STATUS",
				ObservationHistoryList.EID_MOTHERS_HIV_STATUS.getList());
		observationHistoryMapOfLists.put("EID_MOTHERS_ARV_TREATMENT",
				ObservationHistoryList.EID_MOTHERS_ARV_TREATMENT.getList());
		observationHistoryMapOfLists.put("HIV_STATUSES", ObservationHistoryList.HIV_STATUSES.getList());
		observationHistoryMapOfLists.put("HIV_TYPES", ObservationHistoryList.HIV_TYPES.getList());
		observationHistoryMapOfLists.put("SPECIAL_REQUEST_REASONS",
				ObservationHistoryList.SPECIAL_REQUEST_REASONS.getList());
		observationHistoryMapOfLists.put("ARV_REGIME", ObservationHistoryList.ARV_REGIME.getList());
		observationHistoryMapOfLists.put("ARV_REASON_FOR_VL_DEMAND",
				ObservationHistoryList.ARV_REASON_FOR_VL_DEMAND.getList());

		form.setDictionaryLists(observationHistoryMapOfLists);

		// Get EID Sites
		Map<String, List<Organization>> organizationTypeMapOfLists = new HashMap<>();
		organizationTypeMapOfLists.put("ARV_ORGS", OrganizationTypeList.ARV_ORGS.getList());
		organizationTypeMapOfLists.put("ARV_ORGS_BY_NAME", OrganizationTypeList.ARV_ORGS_BY_NAME.getList());
		organizationTypeMapOfLists.put("EID_ORGS_BY_NAME", OrganizationTypeList.EID_ORGS_BY_NAME.getList());
		organizationTypeMapOfLists.put("EID_ORGS", OrganizationTypeList.EID_ORGS.getList());
		form.setOrganizationTypeLists(organizationTypeMapOfLists);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "sampleEntryByProjectDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/SampleEntryByProject?type=" + Encode.forUriComponent(request.getParameter("type"));
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "sampleEntryByProjectDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}
}
