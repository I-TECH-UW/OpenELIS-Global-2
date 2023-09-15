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
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Task.ParameterComponent;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.DateTimeType;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.services.StatusService.OrderStatus;
import org.openelisglobal.common.services.StatusService.SampleStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.dictionary.ObservationHistoryList;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.util.OrganizationTypeList;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.saving.ISampleEntry;
import org.openelisglobal.patient.saving.ISampleEntryAfterPatientEntry;
import org.openelisglobal.patient.saving.ISampleSecondEntry;
import org.openelisglobal.patient.valueholder.ObservationData;
import org.openelisglobal.provider.service.ProviderService;
import org.openelisglobal.sample.form.ProjectData;
import org.openelisglobal.sample.form.SampleEntryByProjectForm;
import org.openelisglobal.sample.form.SampleTbEntryForm;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.statusofsample.valueholder.StatusOfSample;
import org.openelisglobal.systemuser.service.UserService;
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
public class SampleTbEntryController extends BaseSampleEntryController {

	@Value("${org.openelisglobal.requester.identifier:}")
	private String requestFhirUuid;

	@Autowired
	private ElectronicOrderService electronicOrderService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private FhirPersistanceService fhirPersistanceService;

	@Autowired
	private FhirConfig fhirConfig;
	@Autowired
	private FhirUtil fhirUtil;
	@Autowired
	private UserService userService;

	private Task task = null;
	private Practitioner requesterPerson = null;
	private Practitioner collector = null;
	private org.hl7.fhir.r4.model.Organization referringOrganization = null;
	private Location location = null;
	private ServiceRequest serviceRequest = null;
	private Specimen specimen = null;
	private Patient fhirPatient = null;

	private static final String[] ALLOWED_FIELDS = new String[] { "currentDate", "domain", "project",
			"patientLastUpdated", "personLastUpdated", "patientUpdateStatus", "patientPK", "samplePK",
			"observations.projectFormName", "ProjectData.ARVcenterName", "ProjectData.ARVcenterCode",
			"observations.nameOfDoctor", "receivedDateForDisplay", "receivedTimeForDisplay", "interviewDate",
			"interviewTime", "subjectNumber", "siteSubjectNumber", "labNo", "gender", "birthDateForDisplay",
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
			"observations.priorVLDate", "electronicOrder.externalId" };

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(ALLOWED_FIELDS);
	}

	@RequestMapping(value = "/MicrobiologyTb", method = RequestMethod.GET)
	public ModelAndView showSampleEntryByProject(HttpServletRequest request) {
		SampleTbEntryForm form = new SampleTbEntryForm();
		request.setAttribute(IActionConstants.PAGE_SUBTITLE_KEY,
				MessageUtil.getMessage("add.tb.sample.title"));

		Date today = Calendar.getInstance().getTime();
		String dateAsText = DateUtil.formatDateAsText(today);
		form.setReceivedDate(dateAsText);

		setDisplayLists(form);
		addFlashMsgsToRequest(request);

		return findForward(FWD_SUCCESS, form);
	}

	//
//	private void setupFormData(HttpServletRequest request, SampleEntryByProjectForm form) {
//		try {
//			String externalOrderNumber = request.getParameter("ID");
//			if (StringUtils.isNotBlank(externalOrderNumber)) {
//				ElectronicOrder eOrder = null;
//				List<ElectronicOrder> eOrders = electronicOrderService
//						.getElectronicOrdersByExternalId(externalOrderNumber);
//				if (eOrders.size() > 0)
//					eOrder = eOrders.get(eOrders.size() - 1);
//				if (eOrder != null) {
//					form.setElectronicOrder(eOrder);
//					IGenericClient localFhirClient = fhirUtil.getLocalFhirClient();
//					for (String remotePath : fhirConfig.getRemoteStorePaths()) {
//						Bundle srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
//								.where(ServiceRequest.RES_ID.exactly().code(externalOrderNumber))
//								.include(ServiceRequest.INCLUDE_SPECIMEN).execute();
//						for (BundleEntryComponent bundleComponent : srBundle.getEntry()) {
//							if (bundleComponent.hasResource() && ResourceType.ServiceRequest
//									.equals(bundleComponent.getResource().getResourceType())) {
//								serviceRequest = (ServiceRequest) bundleComponent.getResource();
//							}
//							if (bundleComponent.hasResource()
//									&& ResourceType.Specimen.equals(bundleComponent.getResource().getResourceType())) {
//								specimen = (Specimen) bundleComponent.getResource();
//							}
//						}
//						srBundle = (Bundle) localFhirClient
//								.search().forResource(ServiceRequest.class).where(ServiceRequest.IDENTIFIER.exactly()
//										.systemAndIdentifier(remotePath, externalOrderNumber))
//								.include(ServiceRequest.INCLUDE_SPECIMEN).execute();
//						for (BundleEntryComponent bundleComponent : srBundle.getEntry()) {
//							if (bundleComponent.hasResource() && ResourceType.ServiceRequest
//									.equals(bundleComponent.getResource().getResourceType())) {
//								serviceRequest = (ServiceRequest) bundleComponent.getResource();
//							}
//							if (bundleComponent.hasResource()
//									&& ResourceType.Specimen.equals(bundleComponent.getResource().getResourceType())) {
//								specimen = (Specimen) bundleComponent.getResource();
//							}
//						}
//					}
//					if (serviceRequest != null) {
//						LogEvent.logDebug(this.getClass().getName(), "processRequest",
//								"found matching serviceRequest " + serviceRequest.getIdElement().getIdPart());
//					} else {
//						LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching serviceRequest");
//					}
//					fhirPatient = localFhirClient.read()//
//							.resource(Patient.class)//
//							.withId(serviceRequest.getSubject().getReferenceElement().getIdPart())//
//							.execute();
//					if (fhirPatient != null) {
//						LogEvent.logDebug(this.getClass().getName(), "processRequest",
//								"found matching patient " + fhirPatient.getIdElement().getIdPart());
//					} else {
//						LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching patient");
//					}
//					task = fhirPersistanceService.getTaskBasedOnServiceRequest(externalOrderNumber).orElseThrow();
//					if (task != null) {
//						LogEvent.logDebug(this.getClass().getName(), "processRequest",
//								"found matching task " + task.getIdElement().getIdPart());
//					} else {
//						LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching task");
//					}
//					if (!GenericValidator.isBlankOrNull(
//							task.getRestriction().getRecipientFirstRep().getReferenceElement().getIdPart())) {
//						referringOrganization = localFhirClient.read()//
//								.resource(org.hl7.fhir.r4.model.Organization.class)//
//								.withId(task.getRestriction().getRecipientFirstRep().getReferenceElement().getIdPart())//
//								.execute();
//						if (referringOrganization != null) {
//							LogEvent.logDebug(this.getClass().getName(), "processRequest",
//									"found matching organization " + referringOrganization.getIdElement().getIdPart());
//						} else {
//							LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching organization");
//						}
//					}
//					if (!GenericValidator.isBlankOrNull(
//							serviceRequest.getLocationReferenceFirstRep().getReferenceElement().getIdPart())) {
//						location = localFhirClient.read()//
//								.resource(Location.class)//
//								.withId(serviceRequest.getLocationReferenceFirstRep().getReferenceElement().getIdPart())//
//								.execute();
//						if (location != null) {
//							LogEvent.logDebug(this.getClass().getName(), "processRequest",
//									"found matching location " + location.getIdElement().getIdPart());
//						} else {
//							LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching location");
//						}
//					}
//					if (!GenericValidator.isBlankOrNull(serviceRequest.getRequester().getReferenceElement().getIdPart())
//							&& serviceRequest.getRequester().getReference()
//									.contains(ResourceType.Practitioner.toString())) {
//						requesterPerson = localFhirClient.read()//
//								.resource(Practitioner.class)//
//								.withId(serviceRequest.getRequester().getReferenceElement().getIdPart())//
//								.execute();
//
//						if (requesterPerson != null) {
//							LogEvent.logDebug(this.getClass().getName(), "processRequest",
//									"found matching requester " + requesterPerson.getIdElement().getIdPart());
//						} else {
//							LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching requester");
//						}
//					}
//					if (specimen != null && !GenericValidator
//							.isBlankOrNull(specimen.getCollection().getCollector().getReferenceElement().getIdPart())) {
//						collector = localFhirClient.read()//
//								.resource(Practitioner.class)//
//								.withId(specimen.getCollection().getCollector().getReferenceElement().getIdPart())//
//								.execute();
//
//						if (collector != null) {
//							LogEvent.logDebug(this.getClass().getName(), "processRequest",
//									"found matching collector " + collector.getIdElement().getIdPart());
//						} else {
//							LogEvent.logDebug(this.getClass().getName(), "processRequest", "no matching collector");
//						}
//					}
//					loadDataInForm(form);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void loadDataInForm(SampleEntryByProjectForm form) {
//		ProjectData projectData = new ProjectData();
//		ObservationData observationData = new ObservationData();
//		form.setBirthDateForDisplay(DateUtil.formatDateAsText(fhirPatient.getBirthDate()));
//		form.setGender(fhirPatient.getGender().getDisplay().substring(0, 1).toUpperCase());
//		for (Identifier identifier : fhirPatient.getIdentifier()) {
//			if ((fhirConfig.getOeFhirSystem() + "/pat_subjectNumber").equals(identifier.getSystem())) {
//				form.setSubjectNumber(identifier.getValue());
//			}
//			if ((fhirConfig.getOeFhirSystem() + "/pat_nationalId").equals(identifier.getSystem())) {
//				form.setSiteSubjectNumber(identifier.getValue());
//			}
//		}
//		for (ParameterComponent parameter : task.getInput()) {
//			if (parameter.getType().hasCoding("https://openconceptlab.org/orgs/CIEL/sources/CIEL", "159376")) {
//				if (parameter.getValue() instanceof DateTimeType) {
//					if (ObjectUtils.isNotEmpty(parameter.getValue())) {
//						DateTimeType dateValue = (DateTimeType) parameter.getValue();
//						observationData.setInitcd4Date(DateUtil.formatDateAsText(dateValue.getValue()));
//					}
//				}
//			}
//			if (parameter.getType().hasCoding("https://openconceptlab.org/orgs/CIEL/sources/CIEL", "160103")) {
//				if (ObjectUtils.isNotEmpty(parameter.getValue())) {
//					if (parameter.getValue() instanceof DateTimeType) {
//						DateTimeType dateValue = (DateTimeType) parameter.getValue();
//						observationData.setDemandcd4Date(DateUtil.formatDateAsText(dateValue.getValue()));
//					}
//				}
//			}
//		}
//		if (ObjectUtils.isNotEmpty(serviceRequest)) {
//			if (ObjectUtils.isNotEmpty(serviceRequest.getOccurrencePeriod())) {
//				Date startDate = serviceRequest.getOccurrencePeriod().getStart();
//				form.setInterviewDate(DateUtil.formatDateAsText(startDate));
//			}
//		}
//
//		requesterPerson.getName().forEach(humanName -> {
//			String lastName = humanName.getFamily();
//			String firstName = String.join("",
//					humanName.getGiven().stream().map(e -> e.asStringValue()).collect(Collectors.toList()));
//			observationData.setNameOfDoctor(lastName + " " + firstName);
//		});
//
//		projectData.setViralLoadTest(true);
//		form.setProjectData(projectData);
//		form.setObservations(observationData);
//	}
//		setupFormData(request, form);

//	@RequestMapping(value = "/SampleEntryByProject", method = RequestMethod.POST)
//	public ModelAndView postSampleEntryByProject(HttpServletRequest request,
//			@ModelAttribute("form") @Valid SampleEntryByProjectForm form, BindingResult result,
//			RedirectAttributes redirectAttributes) {
//		if (result.hasErrors()) {
//			saveErrors(result);
//			setDisplayLists(form);
//			return findForward(FWD_FAIL_INSERT, form);
//		}
//
//		String forward;
//
//		ISampleSecondEntry sampleSecondEntry = SpringContext.getBean(ISampleSecondEntry.class);
//		sampleSecondEntry.setFieldsFromForm(form);
//		sampleSecondEntry.setSysUserId(getSysUserId(request));
//		sampleSecondEntry.setRequest(request);
//		if (sampleSecondEntry.canAccession()) {
//			forward = handleSave(request, sampleSecondEntry, form);
//			updateElectronicOrderStatus(form);
//			if (forward != null) {
//				if (FWD_SUCCESS_INSERT.equals(forward)) {
//					redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
//				} else {
//					setDisplayLists(form);
//				}
//				return findForward(forward, form);
//			}
//		}
//		ISampleEntry sampleEntry = SpringContext.getBean(ISampleEntry.class);
//		sampleEntry.setFieldsFromForm(form);
//		sampleEntry.setSysUserId(getSysUserId(request));
//		sampleEntry.setRequest(request);
//		if (sampleEntry.canAccession()) {
//			forward = handleSave(request, sampleEntry, form);
//			updateElectronicOrderStatus(form);
//			if (forward != null) {
//				if (FWD_SUCCESS_INSERT.equals(forward)) {
//					redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
//				} else {
//					setDisplayLists(form);
//				}
//				return findForward(forward, form);
//			}
//		}
//		ISampleEntryAfterPatientEntry sampleEntryAfterPatientEntry = SpringContext
//				.getBean(ISampleEntryAfterPatientEntry.class);
//		sampleEntryAfterPatientEntry.setFieldsFromForm(form);
//		sampleEntryAfterPatientEntry.setSysUserId(getSysUserId(request));
//		sampleEntryAfterPatientEntry.setRequest(request);
//		if (sampleEntryAfterPatientEntry.canAccession()) {
//			forward = handleSave(request, sampleEntryAfterPatientEntry, form);
//			updateElectronicOrderStatus(form);
//			if (forward != null) {
//				if (FWD_SUCCESS_INSERT.equals(forward)) {
//					redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
//				} else {
//					setDisplayLists(form);
//				}
//				return findForward(forward, form);
//			}
//		}
//		logAndAddMessage(request, "postSampleEntryByProject", "errors.UpdateException");
//
//		setDisplayLists(form);
//		return findForward(FWD_FAIL_INSERT, form);
//	}
//
//	private void updateElectronicOrderStatus(SampleEntryByProjectForm form) {
//		try {
//			if (ObjectUtils.isNotEmpty(form.getElectronicOrder())) {
//				String externalOrderId = form.getElectronicOrder().getExternalId();
//				List<ElectronicOrder> eOrders = electronicOrderService.getElectronicOrdersByExternalId(externalOrderId);
//				if (eOrders.size() > 0) {
//					ElectronicOrder eOrder = eOrders.get(eOrders.size() - 1);
//					eOrder.setStatusId(
//							SpringContext.getBean(IStatusService.class).getStatusID(ExternalOrderStatus.Realized));
//					electronicOrderService.update(eOrder);
//					form.setElectronicOrder(eOrder);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			LogEvent.logError(e);
//		}
//	}
//
//	public SampleItem getSampleItem(Sample sample, TypeOfSample typeofsample) {
//		SampleItem item = new SampleItem();
//		item.setSample(sample);
//		item.setTypeOfSample(typeofsample);
//		item.setSortOrder(Integer.toString(1));
//		item.setStatusId(SpringContext.getBean(IStatusService.class).getStatusID(SampleStatus.Entered));
//
//		return item;
//	}

	private void setDisplayLists(SampleTbEntryForm form) {
		List<Dictionary> listOfDictionary = new ArrayList<>();
		List<IdValuePair> genders = DisplayListService.getInstance().getList(ListType.GENDERS);

		for (IdValuePair i : genders) {
			Dictionary dictionary = new Dictionary();
			dictionary.setId(i.getId());
			dictionary.setDictEntry(i.getValue());
			listOfDictionary.add(dictionary);
		}

		form.setGenders(genders);
		form.setReferralOrganizations(DisplayListService.getInstance().getList(ListType.SAMPLE_PATIENT_REFERRING_CLINIC));
		form.setTbSpecimenNatures(userService.getUserSampleTypes(getSysUserId(request), Constants.ROLE_RECEPTION));
		form.setTestSectionList(DisplayListService.getInstance().getList(ListType.TEST_SECTION_ACTIVE));
		form.setCurrentDate(DateUtil.getCurrentDateAsText());
		form.setRejectReasonList(DisplayListService.getInstance().getList(ListType.REJECTION_REASONS));
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "sampleTbEntryDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
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
