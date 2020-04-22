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
import org.hl7.fhir.r4.model.Identifier;
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
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.TokenClientParam;

public class LabOrderSearchProvider extends BaseQueryProvider {
//	private TestDAO testDAO = new TestDAOImpl();
//	private PanelDAO panelDAO = new PanelDAOImpl();
//	private PanelItemDAO panelItemDAO = new PanelItemDAOImpl();
//	private TypeOfSampleTestDAO typeOfSampleTest = new TypeOfSampleTestDAOImpl();

//    @Value("${org.openelisglobal.fhirstore.uri.from.eclipse_common.properties}")
//    private String localFhirStorePath;
//    @Value("${org.openelisglobal.task.useBasedOn}")
//    private Boolean useBasedOn;

    private FhirContext fhirContext = SpringContext.getBean(FhirContext.class);

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

    List<ElectronicOrder> eOrders = null;
    ElectronicOrder eOrder = null;
    ExternalOrderStatus eOrderStatus = null;

    private static final String NOT_FOUND = "Not Found";
    private static final String CANCELED = "Canceled";
    private static final String REALIZED = "Realized";
    private static final String PROVIDER_FIRST_NAME = "firstName";
    private static final String PROVIDER_LAST_NAME = "lastName";
    private static final String PROVIDER_PHONE = "phone";

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderNumber = request.getParameter("orderNumber");
        eOrders = electronicOrderService.getElectronicOrdersByExternalId(orderNumber);
        if (eOrders.isEmpty()) {
            // return NOT_FOUND;
            return;
        }
        eOrder = eOrders.get(eOrders.size() - 1);
        eOrderStatus = SpringContext.getBean(IStatusService.class).getExternalOrderStatusForID(eOrder.getStatusId());

        IGenericClient localFhirClient = fhirContext
                .newRestfulGenericClient("https://host.openelis.org:8444/hapi-fhir-jpaserver/fhir/");
        System.out.println("Data: " + eOrder.getData());

        Task task = fhirContext.newJsonParser().parseResource(Task.class, eOrder.getData());
        System.out.println("task: " + fhirContext.newJsonParser().encodeResourceToString(task));

//        task = null;
//        for (BundleEntryComponent bundleComponent : bundle.getEntry()) {
//            if (bundleComponent.hasResource()
//                    && ResourceType.Task.equals(bundleComponent.getResource().getResourceType())) {
//                task = (Task) bundleComponent.getResource();
//            }
//        }

        Bundle srBundle = (Bundle) localFhirClient.search().forResource(ServiceRequest.class)
                .where(new TokenClientParam("identifier").exactly()
                        .code(task.getBasedOn().get(0).getReferenceElement().getIdPart()))
                .prettyPrint().execute();

        serviceRequest = null;
        for (BundleEntryComponent bundleComponent : srBundle.getEntry()) {
            if (bundleComponent.hasResource()
                    && ResourceType.ServiceRequest.equals(bundleComponent.getResource().getResourceType())) {
                serviceRequest = (ServiceRequest) bundleComponent.getResource();
            }
        }

        System.out.println("serviceRequest: " + fhirContext.newJsonParser().encodeResourceToString(serviceRequest));
        serviceRequest.getSubject().getReferenceElement().getIdPart();

        Bundle pBundle = (Bundle) localFhirClient.search().forResource(Patient.class)
                .where(new TokenClientParam("identifier").exactly()
                        .code(serviceRequest.getSubject().getReferenceElement().getIdPart()))
                .prettyPrint().execute();

        patient = null;
        for (BundleEntryComponent bundleComponent : pBundle.getEntry()) {
            if (bundleComponent.hasResource()
                    && ResourceType.Patient.equals(bundleComponent.getResource().getResourceType())) {
                patient = (Patient) bundleComponent.getResource();
            }
        }

        System.out.println("patient: " + fhirContext.newJsonParser().encodeResourceToString(patient));

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
        System.out.println("LabOrderSearchProvider:xml: " + result + " " + xml.toString());
        ajaxServlet.sendData(xml.toString(), result, request, response);
    }

    private String createSearchResultXML(String orderNumber, StringBuilder xml) {

        String success = VALID;

        if (eOrderStatus == ExternalOrderStatus.Cancelled) {
            return CANCELED;
        } else if (eOrderStatus == ExternalOrderStatus.Realized) {
            return REALIZED;
        }

        String patientGuid = null;
        for (Identifier identifier : patient.getIdentifier()) {
            if (identifier.getSystem().equalsIgnoreCase("https://isanteplusdemo.com/openmrs/ws/fhir2/")) {
                patientGuid = identifier.getValue();
            }
        }

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
        // OML_O21 hapiMsg = (OML_O21) p.parse(orderMessage);
        // ORC commonOrderSegment = hapiMsg.getORDER().getORC();

        requesterValuesMap.put(PROVIDER_PHONE, "1234567890");
        requesterValuesMap.put(PROVIDER_LAST_NAME, "providerLast");
        requesterValuesMap.put(PROVIDER_FIRST_NAME, "providerFirst");


        // pass loinc
        String loinc = "";
        String system = "";
        Integer i = 0;
        while (i < serviceRequest.getCode().getCoding().size()) {
            system = serviceRequest.getCode().getCoding().get(i).getSystemElement().toString();
            if (system.equalsIgnoreCase("UriType[http://loinc.org]")) {
                loinc = serviceRequest.getCode().getCoding().get(i).getCodeElement().toString();
                break;
            }
            i++;
        }
        
        addToTestOrPanel(tests, loinc);

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
