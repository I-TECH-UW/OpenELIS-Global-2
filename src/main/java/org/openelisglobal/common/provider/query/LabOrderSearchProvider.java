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
package org.openelisglobal.common.provider.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Task;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.util.XMLUtil;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.panel.service.PanelService;
import org.openelisglobal.panel.valueholder.Panel;
import org.openelisglobal.panelitem.service.PanelItemService;
import org.openelisglobal.panelitem.valueholder.PanelItem;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.typeofsample.service.TypeOfSampleTestService;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;
import org.openelisglobal.typeofsample.valueholder.TypeOfSampleTest;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;

public class LabOrderSearchProvider extends BaseQueryProvider {
//	private TestDAO testDAO = new TestDAOImpl();
//	private PanelDAO panelDAO = new PanelDAOImpl();
//	private PanelItemDAO panelItemDAO = new PanelItemDAOImpl();
//	private TypeOfSampleTestDAO typeOfSampleTest = new TypeOfSampleTestDAOImpl();

//    @Value("${org.openelisglobal.fhirstore.uri.from.eclipse_common.properties}")
//    private String localFhirStorePath;
//    @Value("${org.openelisglobal.task.useBasedOn}")
//    private Boolean useBasedOn;

    protected TestService testService = SpringContext.getBean(TestService.class);
    protected PanelService panelService = SpringContext.getBean(PanelService.class);
    protected PanelItemService panelItemService = SpringContext.getBean(PanelItemService.class);
    protected TypeOfSampleTestService typeOfSampleTestService = SpringContext.getBean(TypeOfSampleTestService.class);
    protected ElectronicOrderService electronicOrderService = SpringContext.getBean(ElectronicOrderService.class);
    private TypeOfSampleService typeOfSampleService = SpringContext.getBean(TypeOfSampleService.class);

    private Map<TypeOfSample, PanelTestLists> typeOfSampleMap;
    private Map<Panel, List<TypeOfSample>> panelSampleTypesMap;
    private Map<String, List<TestSampleType>> testNameTestSampleTypeMap;
    
    private Bundle bundle = null;
    private Task task = null;
    private ServiceRequest serviceRequest = null;
    private Patient patient = null;
    private String patientGuid = null;

    private static final String NOT_FOUND = "Not Found";
    private static final String CANCELED = "Canceled";
    private static final String REALIZED = "Realized";
    private static final String PROVIDER_FIRST_NAME = "firstName";
    private static final String PROVIDER_LAST_NAME = "lastName";
    private static final String PROVIDER_PHONE = "phone";

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FhirContext fhirContext = new FhirContext(FhirVersionEnum.R4);
//      IGenericClient localFhirClient = fhirContext.newRestfulGenericClient("https://host.openelis.org:8444/hapi-fhir-jpaserver/fhir/");
//      Map<String, List<String>> localSearchParams = new HashMap<>();
//      Task task = fhirContext.newJsonParser().parseResource(Task.class, orderMessage);
//      localSearchParams.put("_id", Arrays.asList(task.getId()));
//      Bundle realizedTask = localFhirClient.search()//
//              .forResource(Task.class)//
//              // TODO use include
//              .include(Task.INCLUDE_PATIENT)//
//              .include(Task.INCLUDE_BASED_ON)//
//              .whereMap(localSearchParams)//
//              .returnBundle(Bundle.class)//
//              .execute();
        
        String json = "{\"resourceType\":\"Bundle\",\"id\":\"19a17f7b-00d6-4297-92ee-894b8317f304\",\"meta\":{\"lastUpdated\":\"2020-04-14T03:08:11.796+00:00\"},\"type\":\"searchset\",\"total\":1,\"link\":[{\"relation\":\"self\",\"url\":\"https://fhir.openelis.org:8443/hapi-fhir-jpaserver/fhir/Task?_id=https%3A%2F%2Ffhir.openelis.org%3A8443%2Fhapi-fhir-jpaserver%2Ffhir%2FTask%2F1%2F_history%2F1&_include=Task%3Apatient&_include=Task%3Abased-on\"}],\"entry\":[{\"fullUrl\":\"https://fhir.openelis.org:8443/hapi-fhir-jpaserver/fhir/Task/1\",\"resource\":{\"resourceType\":\"Task\",\"id\":\"1\",\"meta\":{\"versionId\":\"1\",\"lastUpdated\":\"2020-04-13T22:01:10.209+00:00\",\"source\":\"#2qWEjOulNwoH9lSP\"},\"identifier\":[{\"system\":\"http://isanteplus.org/ext/task/identifier\",\"value\":\"acefc798-6303-42f9-804b-e870c959ad55\"},{\"system\":\"https://isanteplusdemo.com/openmrs/ws/fhir2/\",\"value\":\"acefc798-6303-42f9-804b-e870c959ad55\"}],\"basedOn\":[{\"reference\":\"ServiceRequest/2\",\"type\":\"ServiceRequest\"}],\"status\":\"accepted\",\"intent\":\"order\",\"code\":{\"coding\":[{\"system\":\"http://loinc.org\",\"code\":\"3024-7\"}]},\"for\":{\"reference\":\"Patient/3\",\"type\":\"Patient\"},\"encounter\":{\"reference\":\"Encounter/e64957f4-497e-4b45-84a3-a6d54c8370fe\",\"type\":\"Encounter\"},\"authoredOn\":\"2020-04-08T20:48:12+00:00\",\"owner\":{\"reference\":\"Practitioner/f9badd80-ab76-11e2-9e96-0800200c9a66\",\"type\":\"Practitioner\"}},\"search\":{\"mode\":\"match\"}},{\"fullUrl\":\"https://fhir.openelis.org:8443/hapi-fhir-jpaserver/fhir/ServiceRequest/2\",\"resource\":{\"resourceType\":\"ServiceRequest\",\"id\":\"2\",\"meta\":{\"versionId\":\"1\",\"lastUpdated\":\"2020-04-13T22:01:10.209+00:00\",\"source\":\"#2qWEjOulNwoH9lSP\"},\"identifier\":[{\"system\":\"https://isanteplusdemo.com/openmrs/ws/fhir2/\",\"value\":\"d43606ba-ee2c-46f6-aaef-6164c764622f\"}],\"status\":\"unknown\",\"intent\":\"order\",\"encounter\":{\"reference\":\"Encounter/e64957f4-497e-4b45-84a3-a6d54c8370fe\",\"type\":\"Encounter\"}},\"search\":{\"mode\":\"include\"}},{\"fullUrl\":\"https://fhir.openelis.org:8443/hapi-fhir-jpaserver/fhir/Patient/3\",\"resource\":{\"resourceType\":\"Patient\",\"id\":\"3\",\"meta\":{\"versionId\":\"1\",\"lastUpdated\":\"2020-04-13T22:01:10.209+00:00\",\"source\":\"#2qWEjOulNwoH9lSP\"},\"text\":{\"status\":\"generated\",\"div\":\"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><div class=\\\"hapiHeaderText\\\">Piotr <b>MANKOWSKI </b></div><table class=\\\"hapiPropertyTable\\\"><tbody><tr><td>Identifier</td><td>10012R</td></tr><tr><td>Address</td><td><span>Haiti </span></td></tr><tr><td>Date of birth</td><td><span>01 January 1987</span></td></tr></tbody></table></div>\"},\"identifier\":[{\"id\":\"5981a256-d60c-44b1-beae-9bdd2cf572f8\",\"use\":\"official\",\"system\":\"iSantePlus ID\",\"value\":\"10012R\"},{\"id\":\"75a67d54-6fff-44d1-9c3e-2116c967b475\",\"use\":\"usual\",\"system\":\"Code National\",\"value\":\"100000\"},{\"id\":\"29447d21-3cd6-42a9-9ab2-79ebfa710a01\",\"use\":\"usual\",\"system\":\"ECID\",\"value\":\"04d759e0-5d02-11e8-b899-0242ac12000b\"},{\"system\":\"https://isanteplusdemo.com/openmrs/ws/fhir2/\",\"value\":\"e14e9bda-d273-4c74-8509-5732a4ebaf19\"}],\"active\":true,\"name\":[{\"id\":\"511275de-e301-44a3-95d2-28d0d3b35387\",\"family\":\"Mankowski\",\"given\":[\"Piotr\"]}],\"gender\":\"male\",\"birthDate\":\"1987-01-01\",\"deceasedBoolean\":false,\"address\":[{\"id\":\"d4f7c809-3d01-4032-b64d-4c22e8eccbbc\",\"use\":\"home\",\"country\":\"Haiti\"}]},\"search\":{\"mode\":\"include\"}}]}";
        // example
        //String json = "{\"resourceType\":\"Bundle\",\"id\":\"bundle-transaction\",\"meta\":{\"lastUpdated\":\"2014-08-18T01:43:30Z\"},\"type\":\"transaction\",\"entry\":[{\"fullUrl\":\"urn:uuid:61ebe359-bfdc-4613-8bf2-c5e300945f0a\",\"resource\":{\"resourceType\":\"Task\",\"id\":\"example4\",\"basedOn\":[{\"reference\":\"ServiceRequest/ft6\"}],\"status\":\"requested\",\"intent\":\"order\",\"code\":{\"text\":\"RefillRequest\"},\"for\":{\"reference\":\"Patient/f002\"},\"authoredOn\":\"2016-03-10T22:39:32-04:00\",\"lastModified\":\"2016-03-10T22:39:32-04:00\"},\"request\":{\"method\":\"PUT\",\"url\":\"Task/example4\"}},{\"fullUrl\":\"urn:uuid:61ebe359-bfdc-4613-8bf2-c5e300945f0b\",\"resource\":{\"resourceType\":\"ServiceRequest\",\"id\":\"ft6\",\"status\":\"active\",\"intent\":\"reflex-order\",\"code\":{\"coding\":[{\"system\":\"http://loinc.org\",\"code\":\"3024-7\",\"display\":\"Thyroxine(T4)free[Mass/<200b>volume]inSerumorPlasma\"}],\"text\":\"FreeT4\"},\"subject\":{\"reference\":\"Patient/f002\"},\"occurrenceDateTime\":\"2015-08-27T09:33:27+07:00\"},\"request\":{\"method\":\"PUT\",\"url\":\"ServiceRequest/ft6\"}},{\"fullUrl\":\"urn:uuid:61ebe359-bfdc-4613-8bf2-c5e300945f0c\",\"resource\":{\"resourceType\":\"Patient\",\"id\":\"f002\",\"identifier\":[{\"use\":\"usual\",\"system\":\"urn:oid:2.16.840.1.113883.2.4.6.3\",\"value\":\"738472983\"},{\"use\":\"usual\",\"system\":\"urn:oid:2.16.840.1.113883.2.4.6.3\"}],\"active\":true,\"name\":[{\"use\":\"usual\",\"family\":\"Rossum\",\"given\":[\"Greg\"],\"suffix\":[\"MSc\"]}],\"telecom\":[{\"system\":\"phone\",\"value\":\"0648352638\",\"use\":\"mobile\"},{\"system\":\"email\",\"value\":\"p.heuvel@gmail.com\",\"use\":\"home\"}],\"gender\":\"male\",\"birthDate\":\"1944-11-17\",\"deceasedBoolean\":false,\"address\":[{\"use\":\"home\",\"line\":[\"VanEgmondkade23\"],\"city\":\"Amsterdam\",\"postalCode\":\"1024RJ\",\"country\":\"NLD\"}],\"maritalStatus\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v3-MaritalStatus\",\"code\":\"M\",\"display\":\"Married\"}],\"text\":\"Getrouwd\"},\"multipleBirthBoolean\":true,\"contact\":[{\"relationship\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0131\",\"code\":\"C\"}]}],\"name\":{\"use\":\"usual\",\"family\":\"Abels\",\"given\":[\"Sarah\"]},\"telecom\":[{\"system\":\"phone\",\"value\":\"0690383372\",\"use\":\"mobile\"}]}],\"communication\":[{\"language\":{\"coding\":[{\"system\":\"urn:ietf:bcp:47\",\"code\":\"nl\",\"display\":\"Dutch\"}],\"text\":\"Nederlands\"},\"preferred\":true}],\"managingOrganization\":{\"reference\":\"http://localhost:8080/hapi-fhir-jpaserver/fhir/Organization/f002\",\"display\":\"BurgersUniversityMedicalCentre\"}},\"request\":{\"method\":\"PUT\",\"url\":\"Patient/f002\"}}]}";
        
        String json_task = "";
        //String json_servicerequest = "{\"resourceType\":\"ServiceRequest\",\"id\":\"b72db761-0508-426f-aa8f-40cdc7871db0\",\"status\":\"active\",\"intent\":\"order\",\"code\":{\"coding\":[{\"code\":\"856AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"system\":\"urn:oid:2.16.840.1.113883.3.7201\",\"code\":\"856\"},{\"system\":\"http://loinc.org\",\"code\":\"25836-8\"}]},\"subject\":{\"reference\":\"Patient/e14e9bda-d273-4c74-8509-5732a4ebaf19\",\"type\":\"Patient\"},\"encounter\":{\"reference\":\"Encounter/f1c2d68a-cac6-4c76-b5ab-851cb6882c6f\",\"type\":\"Encounter\"}}";
        String json_servicerequest = "{\"resourceType\":\"ServiceRequest\",\"id\":\"b72db761-0508-426f-aa8f-40cdc7871db0\",\"status\":\"active\",\"intent\":\"order\",\"code\":{\"coding\":[{\"system\":\"http://loinc.org\",\"code\":\"25836-8\"}]},\"subject\":{\"reference\":\"Patient/e14e9bda-d273-4c74-8509-5732a4ebaf19\",\"type\":\"Patient\"},\"encounter\":{\"reference\":\"Encounter/f1c2d68a-cac6-4c76-b5ab-851cb6882c6f\",\"type\":\"Encounter\"}}";
        String json_patient = "{\"resourceType\":\"Patient\",\"id\":\"5981a256-d60c-44b1-beae-9bdd2cf572f8\",\"identifier\":[{\"id\":\"5981a256-d60c-44b1-beae-9bdd2cf572f8\",\"use\":\"official\",\"system\":\"iSantePlus ID\",\"value\":\"10012R\"},{\"id\":\"75a67d54-6fff-44d1-9c3e-2116c967b475\",\"use\":\"usual\",\"system\":\"Code National\",\"value\":\"100000\"},{\"id\":\"29447d21-3cd6-42a9-9ab2-79ebfa710a01\",\"use\":\"usual\",\"system\":\"ECID\",\"value\":\"04d759e0-5d02-11e8-b899-0242ac12000b\"}],\"active\":true,\"name\":[{\"id\":\"511275de-e301-44a3-95d2-28d0d3b35387\",\"family\":\"Mankowski\",\"given\":[\"Piotr\"]}],\"gender\":\"male\",\"birthDate\":\"1987-01-01\",\"deceasedBoolean\":false,\"address\":[{\"id\":\"d4f7c809-3d01-4032-b64d-4c22e8eccbbc\",\"use\":\"home\",\"country\":\"Haiti\"}]}";
        
        ca.uhn.fhir.parser.IParser srparser = fhirContext.newJsonParser();
        serviceRequest = srparser.parseResource(ServiceRequest.class, json_servicerequest);
        
        ca.uhn.fhir.parser.IParser pparser = fhirContext.newJsonParser();
        patient = pparser.parseResource(Patient.class, json_patient);
        
        bundle = fhirContext.newJsonParser().parseResource(Bundle.class, json);
        System.out.println(
                "LabOrderSearchProvider:localBundle " + fhirContext.newJsonParser().encodeResourceToString(bundle));

        task = null;
        for (BundleEntryComponent bundleComponent : bundle.getEntry()) {
            if (bundleComponent.hasResource()
                    && ResourceType.Task.equals(bundleComponent.getResource().getResourceType())) {
                task = (Task) bundleComponent.getResource();
            }
        }

        String orderNumber = request.getParameter("orderNumber");

        StringBuilder xml = new StringBuilder();

        String result = createSearchResultXML(orderNumber, xml);

        if (!result.equals(VALID)) {
            if (result.equals(NOT_FOUND)) {
                result = MessageUtil.getMessage("electronic.order.message.orderNotFound");
            } else if (result.equals(CANCELED)) {
                result = MessageUtil.getMessage("electronic.order.message.canceled");
            } else if (result.equals(REALIZED)) {
                result = MessageUtil.getMessage("electronic.order.message.realized");
            }
            result += "\n\n" + MessageUtil.getMessage("electronic.order.message.suggestion");
            xml.append("empty");
        }
        System.out.println("LabOrderSearchProvider:xml: " + result + " " +xml.toString());
        ajaxServlet.sendData(xml.toString(), result, request, response);
    }

    private String createSearchResultXML(String orderNumber, StringBuilder xml) {

        String success = VALID;

        List<ElectronicOrder> eOrders = electronicOrderService.getElectronicOrdersByExternalId(orderNumber);
        if (eOrders.isEmpty()) {
            return NOT_FOUND;
        }

        ElectronicOrder eOrder = eOrders.get(eOrders.size() - 1);
        ExternalOrderStatus eOrderStatus = SpringContext.getBean(IStatusService.class)
                .getExternalOrderStatusForID(eOrder.getStatusId());

        if (eOrderStatus == ExternalOrderStatus.Cancelled) {
            return CANCELED;
        } else if (eOrderStatus == ExternalOrderStatus.Realized) {
            return REALIZED;
        }

        String localPatientGuid = patient.getId();
        String [] guidParts = localPatientGuid.split("/", 2);
        
        patientGuid = guidParts[1];
        createOrderXML(eOrder.getData(), patientGuid, xml);

        return success;
    }

    private String getPatientGuid(ElectronicOrder eOrder) {
        PatientService patientPatientService = SpringContext.getBean(PatientService.class);
        PersonService personService = SpringContext.getBean(PersonService.class);
        personService.getData(eOrder.getPatient().getPerson());
        return patientPatientService.getGUID(eOrder.getPatient());
    }
    
  

    private void createOrderXML(String orderMessage, String patientGuid, StringBuilder xml) {
        List<Request> tests = new ArrayList<>();
        List<Request> panels = new ArrayList<>();
        Map<String, String> requesterValuesMap = new HashMap<>();

        getTestsAndPanels(tests, panels, orderMessage, requesterValuesMap);
        createMaps(tests, panels);
        xml.append("<order>");
        addRequester(xml, requesterValuesMap);
        addPatientGuid(xml, patientGuid);
        addSampleTypes(xml);
        addCrossPanels(xml);
        addCrosstests(xml);
        addAlerts(xml, patientGuid);
        xml.append("</order>");
    }

    private void addRequester(StringBuilder xml, Map<String, String> requesterValuesMap) {
        xml.append("<requester>");
        XMLUtil.appendKeyValue(PROVIDER_FIRST_NAME, requesterValuesMap.get(PROVIDER_FIRST_NAME), xml);
        XMLUtil.appendKeyValue(PROVIDER_LAST_NAME, requesterValuesMap.get(PROVIDER_LAST_NAME), xml);
        XMLUtil.appendKeyValue(PROVIDER_PHONE, requesterValuesMap.get(PROVIDER_PHONE), xml);
        xml.append("</requester>");
    }

    private void getTestsAndPanels(List<Request> tests, List<Request> panels, String orderMessage,
            Map<String, String> requesterValuesMap) {
        //OML_O21 hapiMsg = (OML_O21) p.parse(orderMessage);
        //ORC commonOrderSegment = hapiMsg.getORDER().getORC();
          
        requesterValuesMap.put(PROVIDER_PHONE, "1234567890");
        requesterValuesMap.put(PROVIDER_LAST_NAME, "provider_last");
        requesterValuesMap.put(PROVIDER_FIRST_NAME, "provider_first");
//        List<ServiceRequest> serviceRequestList = getBasedOnServiceRequestFromBundle(bundle, task);
//        Patient forPatient = getForPatientFromBundle(bundle, task);
       
        //OML_O21_OBSERVATION_REQUEST orderRequest = hapiMsg.getORDER().getOBSERVATION_REQUEST();
        
        // pass loinc
        addToTestOrPanel(tests, serviceRequest.getCode().getCodingFirstRep().getCode());

//        List<OML_O21_ORDER_PRIOR> priorOrders = orderRequest.getPRIOR_RESULT().getORDER_PRIORAll();
//        for (OML_O21_ORDER_PRIOR priorOrder : priorOrders) {
//            addToTestOrPanel(tests, panels, commonOrderSegment, priorOrder.getOBSERVATION_PRIOR().getOBX());
//        }

//        String loincCode = "";
//        for (ServiceRequest serviceRequest : serviceRequestList) {
//            for (int i = 0; i < serviceRequest.getCode().getCoding().size(); i++) {
//                if (serviceRequest.getCode().getCoding().get(0).getSystem().toString().equals("http://loinc.org")) {
//                    break;
//                }
//            }
//        }
    }

    private List<ServiceRequest> getBasedOnServiceRequestFromBundle(Bundle bundle, Task task) {
        List<ServiceRequest> basedOn = new ArrayList<>();
        for (Reference reference : task.getBasedOn()) {
            basedOn.add((ServiceRequest) findResourceInBundle(bundle, reference.getReference()));
        }
        return basedOn;
    }

    private Patient getForPatientFromBundle(Bundle bundle, Task task) {
        return (Patient) findResourceInBundle(bundle, task.getFor().getReference());
    }

    private IBaseResource findResourceInBundle(Bundle bundle, String reference) {
        for (BundleEntryComponent bundleComponent : bundle.getEntry()) {
            if (bundleComponent.hasResource() && bundleComponent.getFullUrl().endsWith(reference)) {
                return bundleComponent.getResource();
            }
        }
        return null;

    }

//    private void addToTestOrPanel(List<Request> tests, List<Request> panels, ORC orc, OBX obx) {
//        String loinc = orc.getOrderType().getIdentifier().toString();
//        String testName = testService.getActiveTestsByLoinc(loinc).get(0).getName();
//        tests.add(new Request(testName, loinc,
//                typeOfSampleService.getTypeOfSampleNameForId(testService.getActiveTestsByLoinc(loinc).get(0).getId())));
//    }
    
    private void addToTestOrPanel(List<Request> tests, String loinc) {
        List<Test> testNames = testService.getActiveTestsByLoinc(loinc);
        String testName = testNames.get(0).getName();
        
        tests.add(new Request(testName, loinc,
                typeOfSampleService.getTypeOfSampleNameForId(testService.getActiveTestsByLoinc(loinc).get(0).getId())));
    }

    private void createMaps(List<Request> testRequests, List<Request> panelNames) {
        typeOfSampleMap = new HashMap<>();
        panelSampleTypesMap = new HashMap<>();
        testNameTestSampleTypeMap = new HashMap<>();

        createMapsForTests(testRequests);

        createMapsForPanels(panelNames);
    }

    private void addPatientGuid(StringBuilder xml, String patientGuid) {
        xml.append("<patient>");
        XMLUtil.appendKeyValue("guid", patientGuid, xml);
        xml.append("</patient>");
    }

    private void createMapsForTests(List<Request> testRequests) {
        for (Request testRequest : testRequests) {
            List<Test> tests = testService.getActiveTestsByLoinc(testRequest.getLoinc());

            Test singleTest = tests.get(0);
            TypeOfSample singleSampleType = typeOfSampleService.getTypeOfSampleForTest(singleTest.getId());
            boolean hasSingleSampleType = tests.size() == 1;

            if (tests.size() > 1) {
                if (!GenericValidator.isBlankOrNull(testRequest.getSampleType())) {
                    for (Test test : tests) {
                        TypeOfSample typeOfSample = typeOfSampleService.getTypeOfSampleForTest(test.getId());
                        if (typeOfSample.getDescription().equals(testRequest.getSampleType())) {
                            hasSingleSampleType = true;
                            singleSampleType = typeOfSample;
                            singleTest = test;
                            break;
                        }
                    }
                }

                if (!hasSingleSampleType) {
                    List<TestSampleType> testSampleTypeList = testNameTestSampleTypeMap.get(testRequest.getName());

                    if (testSampleTypeList == null) {
                        testSampleTypeList = new ArrayList<>();
                        testNameTestSampleTypeMap.put(testRequest.getName(), testSampleTypeList);
                    }

                    for (Test test : tests) {
                        testSampleTypeList.add(
                                new TestSampleType(test, typeOfSampleService.getTypeOfSampleForTest(test.getId())));
                    }
                }
            }

            if (hasSingleSampleType) {
                PanelTestLists panelTestLists = typeOfSampleMap.get(singleSampleType);
                if (panelTestLists == null) {
                    panelTestLists = new PanelTestLists();
                    typeOfSampleMap.put(singleSampleType, panelTestLists);
                }

                panelTestLists.addTest(singleTest);
            }
        }
    }

    private void createMapsForPanels(List<Request> panelRequests) {
        for (Request panelRequest : panelRequests) {
            Panel panel = panelService.getPanelByName(panelRequest.getName());

            if (panel != null) {
                List<TypeOfSample> typeOfSamples = typeOfSampleService.getTypeOfSampleForPanelId(panel.getId());
                boolean hasSingleSampleType = typeOfSamples.size() == 1;
                TypeOfSample singleTypeOfSample = typeOfSamples.get(0);

                if (!GenericValidator.isBlankOrNull(panelRequest.getSampleType())) {
                    if (typeOfSamples.size() > 1) {
                        for (TypeOfSample typeOfSample : typeOfSamples) {
                            if (typeOfSample.getDescription().equals(panelRequest.getSampleType())) {
                                hasSingleSampleType = true;
                                singleTypeOfSample = typeOfSample;
                                break;
                            }
                        }
                    }
                }

                if (hasSingleSampleType) {
                    PanelTestLists panelTestLists = typeOfSampleMap.get(singleTypeOfSample);
                    if (panelTestLists == null) {
                        panelTestLists = new PanelTestLists();
                        typeOfSampleMap.put(singleTypeOfSample, panelTestLists);
                    }

                    panelTestLists.addPanel(panel);
                } else {
                    panelSampleTypesMap.put(panel, typeOfSamples);
                }
            }
        }
    }

    private void addSampleTypes(StringBuilder xml) {
        xml.append("<sampleTypes>");
        for (TypeOfSample typeOfSample : typeOfSampleMap.keySet()) {
            if (typeOfSample != null) {
                addSampleType(xml, typeOfSample, typeOfSampleMap.get(typeOfSample));
            }
        }
        xml.append("</sampleTypes>");
    }

    private void addSampleType(StringBuilder xml, TypeOfSample typeOfSample, PanelTestLists panelTestLists) {
        xml.append("<sampleType>");
        XMLUtil.appendKeyValue("id", typeOfSample.getId(), xml);
        XMLUtil.appendKeyValue("name", typeOfSample.getLocalizedName(), xml);
        addPanels(xml, panelTestLists.getPanels(), typeOfSample.getId());
        addTests(xml, "tests", panelTestLists.getTests());
        xml.append("</sampleType>");
    }

    private void addPanels(StringBuilder xml, List<Panel> panels, String sampleTypeId) {
        xml.append("<panels>");
        for (Panel panel : panels) {
            xml.append("<panel>");
            XMLUtil.appendKeyValue("id", panel.getId(), xml);
            XMLUtil.appendKeyValue("name", panel.getLocalizedName(), xml);
            addPanelTests(xml, panel.getId(), sampleTypeId);
            xml.append("</panel>");
        }
        xml.append("</panels>");
    }

    private void addPanelTests(StringBuilder xml, String panelId, String sampleTypeId) {
        List<Test> panelTests = getTestsForPanelAndType(panelId, sampleTypeId);
        addTests(xml, "panelTests", panelTests);
    }

    private List<Test> getTestsForPanelAndType(String panelId, String sampleTypeId) {
        List<TypeOfSampleTest> sampleTestList = typeOfSampleTestService.getTypeOfSampleTestsForSampleType(sampleTypeId);
        List<Integer> testList = new ArrayList<>();
        for (TypeOfSampleTest typeTest : sampleTestList) {
            testList.add(Integer.parseInt(typeTest.getTestId()));
        }

        List<PanelItem> panelList = panelItemService.getPanelItemsForPanelAndItemList(panelId, testList);
        List<Test> tests = new ArrayList<>();
        for (PanelItem item : panelList) {
            tests.add(item.getTest());
        }

        return tests;
    }

    private void addTests(StringBuilder xml, String parent, List<Test> tests) {

        xml.append(XMLUtil.makeStartTag(parent));
        for (Test test : tests) {
            xml.append("<test>");
            XMLUtil.appendKeyValue("id", test.getId(), xml);
            XMLUtil.appendKeyValue("name", test.getLocalizedName(), xml);
            xml.append("</test>");
        }
        xml.append(XMLUtil.makeEndTag(parent));
    }

    private void addCrossPanels(StringBuilder xml) {
        xml.append("<crosspanels>");
        for (Panel panel : panelSampleTypesMap.keySet()) {
            addCrosspanel(xml, panel, panelSampleTypesMap.get(panel));
        }

        xml.append("</crosspanels>");
    }

    private void addCrosspanel(StringBuilder xml, Panel panel, List<TypeOfSample> typeOfSampleList) {
        xml.append("<crosspanel>");
        XMLUtil.appendKeyValue("name", panel.getLocalizedName(), xml);
        XMLUtil.appendKeyValue("id", panel.getId(), xml);
        addPanelCrosssampletypes(xml, typeOfSampleList);
        xml.append("</crosspanel>");
    }

    private void addPanelCrosssampletypes(StringBuilder xml, List<TypeOfSample> typeOfSampleList) {
        xml.append("<crosssampletypes>");
        for (TypeOfSample typeOfSample : typeOfSampleList) {
            addCrosspanelTypeOfSample(xml, typeOfSample);
        }
        xml.append("</crosssampletypes>");
    }

    private void addCrosspanelTypeOfSample(StringBuilder xml, TypeOfSample typeOfSample) {
        xml.append("<crosssampletype>");
        XMLUtil.appendKeyValue("id", typeOfSample.getId(), xml);
        XMLUtil.appendKeyValue("name", typeOfSample.getLocalizedName(), xml);
        xml.append("</crosssampletype>");
    }

    private void addCrosstests(StringBuilder xml) {
        xml.append("<crosstests>");
        for (String testName : testNameTestSampleTypeMap.keySet()) {
            addCrosstestForTestName(xml, testName, testNameTestSampleTypeMap.get(testName));
        }
        xml.append("</crosstests>");

    }

    private void addCrosstestForTestName(StringBuilder xml, String testName, List<TestSampleType> list) {
        xml.append("<crosstest>");
        XMLUtil.appendKeyValue("name", testName, xml);
        xml.append("<crosssampletypes>");
        for (TestSampleType testSampleType : list) {
            addTestCrosssampleType(xml, testSampleType);
        }
        xml.append("</crosssampletypes>");
        xml.append("</crosstest>");
    }

    private void addTestCrosssampleType(StringBuilder xml, TestSampleType testSampleType) {
        xml.append("<crosssampletype>");
        XMLUtil.appendKeyValue("id", testSampleType.getSampleType().getId(), xml);
        XMLUtil.appendKeyValue("name", testSampleType.getSampleType().getLocalizedName(), xml);
        XMLUtil.appendKeyValue("testid", testSampleType.getTest().getId(), xml);
        xml.append("</crosssampletype>");
    }

    private void addAlerts(StringBuilder xml, String patientGuid) {
        PatientService patientService = SpringContext.getBean(PatientService.class);
        org.openelisglobal.patient.valueholder.Patient patient = patientService.getPatientForGuid(patientGuid);
        if (GenericValidator.isBlankOrNull(patientService.getEnteredDOB(patient))
                || GenericValidator.isBlankOrNull(patientService.getGender(patient))) {
            XMLUtil.appendKeyValue("user_alert", MessageUtil.getMessage("electroinic.order.warning.missingPatientInfo"),
                    xml);
        }
    }

    public class PanelTestLists {
        private List<Test> tests = new ArrayList<>();
        private List<Panel> panels = new ArrayList<>();

        public List<Test> getTests() {
            return tests;
        }

        public List<Panel> getPanels() {
            return panels;
        }

        public void addPanel(Panel panel) {
            if (panel != null) {
                panels.add(panel);
            }
        }

        public void addTest(Test test) {
            if (test != null) {
                tests.add(test);
            }
        }

    }

    public class TestSampleType {
        private Test test;
        private TypeOfSample sampleType;

        public TestSampleType(Test test, TypeOfSample sampleType) {
            this.test = test;
            this.sampleType = sampleType;
        }

        public Test getTest() {
            return test;
        }

        public TypeOfSample getSampleType() {
            return sampleType;
        }
    }

    private class Request {
        private String name;
        private String loinc;
        private String sampleType;

        public Request(String name, String loinc, String sampleType) {
            this.name = name;
            this.loinc = loinc;
            this.sampleType = sampleType;
        }

        public String getSampleType() {
            return sampleType;
        }

        public String getLoinc() {
            return loinc;
        }

        public String getName() {
            return name;
        }

    }
}
