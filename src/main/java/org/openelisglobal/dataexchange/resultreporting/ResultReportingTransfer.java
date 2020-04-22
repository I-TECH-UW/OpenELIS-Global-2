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
package org.openelisglobal.dataexchange.resultreporting;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import org.openelisglobal.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import org.openelisglobal.dataexchange.common.ITransmissionResponseHandler;
import org.openelisglobal.dataexchange.common.ReportTransmission;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.orderresult.OrderResponseWorker.Event;
import org.openelisglobal.dataexchange.orderresult.valueholder.HL7MessageOut;
import org.openelisglobal.dataexchange.resultreporting.beans.ResultReportXmit;
import org.openelisglobal.dataexchange.service.aggregatereporting.ReportExternalExportService;
import org.openelisglobal.dataexchange.service.aggregatereporting.ReportQueueTypeService;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.dataexchange.service.orderresult.HL7MessageOutService;
import org.openelisglobal.referencetables.service.ReferenceTablesService;
import org.openelisglobal.referencetables.valueholder.ReferenceTables;
import org.openelisglobal.reports.service.DocumentTrackService;
import org.openelisglobal.reports.service.DocumentTypeService;
import org.openelisglobal.reports.valueholder.DocumentTrack;
import org.openelisglobal.reports.valueholder.DocumentType;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.spring.util.SpringContext;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class ResultReportingTransfer {

    private FhirContext fhirContext = SpringContext.getBean(FhirContext.class);
    protected ElectronicOrderService electronicOrderService = SpringContext.getBean(ElectronicOrderService.class);
    
    private static DocumentType DOCUMENT_TYPE;
    private static String QUEUE_TYPE_ID;
    private static String RESULT_REFERRANCE_TABLE_ID;

    static {
        DOCUMENT_TYPE = SpringContext.getBean(DocumentTypeService.class).getDocumentTypeByName("resultExport");
        ReferenceTables referenceTable = SpringContext.getBean(ReferenceTablesService.class)
                .getReferenceTableByName("RESULT");
        if (referenceTable != null) {
            RESULT_REFERRANCE_TABLE_ID = referenceTable.getId();
        }
        ReportQueueType queueType = SpringContext.getBean(ReportQueueTypeService.class)
                .getReportQueueTypeByName("Results");
        if (queueType != null) {
            QUEUE_TYPE_ID = queueType.getId();
        }
    }

    public void sendResults(ResultReportXmit resultReport, List<Result> reportingResult, String url) {

        if (resultReport.getTestResults() == null || resultReport.getTestResults().isEmpty()) {
            return;
        }
        
        if (resultReport.getTestResults().get(0).getReferringOrderNumber().isEmpty()) {
          ITransmissionResponseHandler responseHandler = new ResultFailHandler(reportingResult);
          new ReportTransmission().sendHL7Report(resultReport, url, responseHandler);
        } else { // FHIR
            
          String orderNumber = resultReport.getTestResults().get(0).getReferringOrderNumber();
          List<ElectronicOrder> eOrders = electronicOrderService.getElectronicOrdersByExternalId(orderNumber);
          ElectronicOrder eOrder = eOrders.get(eOrders.size() - 1);
         
          String json_servicerequest = "{\"resourceType\":\"ServiceRequest\",\"id\":\"b72db761-0508-426f-aa8f-40cdc7871db0\",\"status\":\"active\",\"intent\":\"order\",\"code\":{\"coding\":[{\"system\":\"http://loinc.org\",\"code\":\"25836-8\"}]},\"subject\":{\"reference\":\"Patient/e14e9bda-d273-4c74-8509-5732a4ebaf19\",\"type\":\"Patient\"},\"encounter\":{\"reference\":\"Encounter/f1c2d68a-cac6-4c76-b5ab-851cb6882c6f\",\"type\":\"Encounter\"}}";
          String json_patient = "{\"resourceType\":\"Patient\",\"id\":\"5981a256-d60c-44b1-beae-9bdd2cf572f8\",\"identifier\":[{\"id\":\"5981a256-d60c-44b1-beae-9bdd2cf572f8\",\"use\":\"official\",\"system\":\"iSantePlus ID\",\"value\":\"10012R\"},{\"id\":\"75a67d54-6fff-44d1-9c3e-2116c967b475\",\"use\":\"usual\",\"system\":\"Code National\",\"value\":\"100000\"},{\"id\":\"29447d21-3cd6-42a9-9ab2-79ebfa710a01\",\"use\":\"usual\",\"system\":\"ECID\",\"value\":\"04d759e0-5d02-11e8-b899-0242ac12000b\"}],\"active\":true,\"name\":[{\"id\":\"511275de-e301-44a3-95d2-28d0d3b35387\",\"family\":\"Mankowski\",\"given\":[\"Piotr\"]}],\"gender\":\"male\",\"birthDate\":\"1987-01-01\",\"deceasedBoolean\":false,\"address\":[{\"id\":\"d4f7c809-3d01-4032-b64d-4c22e8eccbbc\",\"use\":\"home\",\"country\":\"Haiti\"}]}";        ca.uhn.fhir.parser.IParser srparser = fhirContext.newJsonParser();
          
          ServiceRequest serviceRequest = srparser.parseResource(ServiceRequest.class, json_servicerequest);
          ca.uhn.fhir.parser.IParser pparser = fhirContext.newJsonParser();
          Patient patient = pparser.parseResource(Patient.class, json_patient);
          
          Observation observation = new Observation();
          observation.setId("f001");
          observation.setIdentifier(serviceRequest.getIdentifier());
          observation.setBasedOn(serviceRequest.getBasedOn());
          observation.setStatus(Observation.ObservationStatus.FINAL);
          observation.setCode(serviceRequest.getCode());
          observation.setSubject(serviceRequest.getSubject());
          Quantity quantity = new Quantity();
          quantity.setValue(new java.math.BigDecimal(
                  resultReport.getTestResults().get(0).getResults().get(0).getResult().getText()));
          quantity.setUnit(resultReport.getTestResults().get(0).getUnits());
          observation.setValue(quantity);
          IGenericClient localFhirClient = fhirContext.newRestfulGenericClient("https://host.openelis.org:8444/hapi-fhir-jpaserver/fhir/");
          MethodOutcome oOutcome =localFhirClient.create().resource(observation).execute();
          
          DiagnosticReport diagnosticReport = new DiagnosticReport();
          diagnosticReport.setId(resultReport.getTestResults().get(0).getTest().getCode());
          diagnosticReport.setIdentifier(serviceRequest.getIdentifier());
          diagnosticReport.setBasedOn(serviceRequest.getBasedOn());
          diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
          diagnosticReport.setCode(serviceRequest.getCode());
          diagnosticReport.setSubject(serviceRequest.getSubject());
          Reference observationReference = new Reference();
          
          diagnosticReport.addResult(observationReference);
          
//          System.out.println("ResultReportingTransfer:sendResults:observation: " + 
//                  fhirContext.newJsonParser().encodeResourceToString(observation));
//          System.out.println("ResultReportingTransfer:sendResults:: " + 
//                  fhirContext.newJsonParser().encodeResourceToString(diagnosticReport));
         
          observationReference.setId(oOutcome.getId().toString());
          MethodOutcome drOutcome =localFhirClient.create().resource(diagnosticReport).execute();
          System.out.println(oOutcome.getId());
          System.out.println(drOutcome.getId());
        }      

    }

    class ResultFailHandler implements ITransmissionResponseHandler {

        private List<Result> reportingResults;

        public ResultFailHandler(List<Result> reportingResults) {
            this.reportingResults = reportingResults;
        }

        @Override
        public void handleResponse(int httpReturnStatus, List<String> errors, String msg) {
            if (httpReturnStatus == HttpServletResponse.SC_OK) {
                markFinalResultsAsSent();
                persistMessage(msg, true);
            } else {
                bufferResults(msg);
                persistMessage(msg, false);
            }
        }

        private void persistMessage(String msg, boolean success) {
            HL7MessageOutService messageOutService = SpringContext.getBean(HL7MessageOutService.class);
            HL7MessageOut messageOut = new HL7MessageOut();
            messageOut.setData(msg);
            if (success) {
                messageOut.setStatus(HL7MessageOut.SUCCESS);
            } else {
                messageOut.setStatus(HL7MessageOut.FAIL);
            }
            messageOutService.insert(messageOut);
        }

        private void bufferResults(String msg) {
            ReportExternalExport report = new ReportExternalExport();
            report.setData(msg);
            report.setSysUserId("1");
            report.setEventDate(DateUtil.getNowAsTimestamp());
            report.setCollectionDate(DateUtil.getNowAsTimestamp());
            report.setTypeId(QUEUE_TYPE_ID);
            report.setBookkeepingData(getResultIdListString() == null ? "" : getResultIdListString());
            report.setSend(true);

            try {
                SpringContext.getBean(ReportExternalExportService.class).insert(report);
            } catch (LIMSRuntimeException e) {
                LogEvent.logErrorStack(e);
            }
        }

        private String getResultIdListString() {
            String comma = "";

            StringBuilder builder = new StringBuilder();

            for (Result result : reportingResults) {
                builder.append(comma); // empty first time through
                builder.append(result.getId());

                comma = ",";
            }

            return builder.toString();
        }

        private void markFinalResultsAsSent() {
            Timestamp now = DateUtil.getNowAsTimestamp();

            List<DocumentTrack> documents = new ArrayList<>();

            for (Result result : reportingResults) {
                if (result.getResultEvent() == Event.FINAL_RESULT || result.getResultEvent() == Event.CORRECTION) {
                    DocumentTrack document = new DocumentTrack();
                    document.setDocumentTypeId(DOCUMENT_TYPE.getId());
                    document.setRecordId(result.getId());
                    document.setReportTime(now);
                    document.setTableId(RESULT_REFERRANCE_TABLE_ID);
                    document.setSysUserId("1");
                    documents.add(document);
                }
            }

            DocumentTrackService trackService = SpringContext.getBean(DocumentTrackService.class);

            try {
                trackService.insertAll(documents);
//				for (DocumentTrack document : documents) {
//					trackService.insert(document);
//				}
            } catch (LIMSRuntimeException e) {
                LogEvent.logErrorStack(e);
            }
        }
    }
}
