package org.openelisglobal.fhir.transormation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.servlet.http.HttpServletResponse;
import org.hl7.fhir.r4.model.Bundle;
import org.itech.fhir.dataexport.api.service.DataExportService;
import org.itech.fhir.dataexport.core.model.DataExportTask;
import org.itech.fhir.dataexport.core.service.DataExportTaskService;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.dataexchange.fhir.exception.FhirLocalPersistingException;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FhirTransformationController extends BaseController {
  @Autowired private SampleService sampleService;
  @Autowired private SampleHumanService sampleHumanService;
  @Autowired private FhirTransformService fhirTransformService;

  @Autowired private DataExportService dataExportService;
  @Autowired private DataExportTaskService dataExportTaskService;

  private static boolean running = false;

  @GetMapping("/PatientToFhir")
  public void transformPersistFhirPatients(
      @RequestParam(defaultValue = "false") Boolean checkAll,
      @RequestParam(defaultValue = "100") int batchSize,
      @RequestParam(defaultValue = "1") int threads,
      HttpServletResponse response)
      throws FhirLocalPersistingException, IOException {

    List<Patient> patients;
    int batches = 0;
    int batchFailure = 0;
    if (checkAll) {
      patients = sampleHumanService.getAllPatientsWithSampleEntered();
    } else {
      patients = sampleHumanService.getAllPatientsWithSampleEnteredMissingFhirUuid();
    }
    LogEvent.logDebug(
        this.getClass().getSimpleName(),
        "transformPersistFhirPatients",
        "patients to convert: " + patients.size());
    List<String> patientIds = new ArrayList<>();
    List<Future<Bundle>> promises = new ArrayList<>();
    for (int i = 0; i < patients.size(); ++i) {
      patientIds.add(patients.get(i).getId());
      if (i % batchSize == batchSize - 1 || i + 1 == patients.size()) {
        LogEvent.logDebug(
            this.getClass().getSimpleName(),
            "",
            "persisting batch " + (i - batchSize + 1) + "-" + i + " of " + patients.size());
        try {
          promises.add(fhirTransformService.transformPersistPatients(patientIds));
          ++batches;
          patientIds = new ArrayList<>();
          if (promises.size() >= threads || i + 1 == patients.size()) {
            waitForResults(promises);
            promises = new ArrayList<>();
          }
        } catch (FhirPersistanceException e) {
          ++batchFailure;
          LogEvent.logError(e);
          LogEvent.logError(
              this.getClass().getSimpleName(),
              "transformPersistFhirPatients",
              "error persisting batch " + (i - batchSize + 1) + "-" + i);
        } catch (Exception e) {
          ++batchFailure;
          LogEvent.logError(e);
          LogEvent.logError(
              this.getClass().getSimpleName(),
              "transformPersistFhirPatients",
              "error with batch " + (i - batchSize + 1) + "-" + i);
        } finally {
          if (promises.size() >= threads || i + 1 == patients.size()) {
            promises = new ArrayList<>();
          }
        }
      }
    }
    LogEvent.logDebug(
        this.getClass().getSimpleName(), "transformPersistFhirPatients", "finished all batches");

    response.getWriter().println("patient batches total: " + batches);
    response.getWriter().println("patient batches failed: " + batchFailure);
  }

  @GetMapping("/OEToFhir")
  public void transformPersistMissingFhirObjects(
      @RequestParam(defaultValue = "false") Boolean checkAll,
      @RequestParam(defaultValue = "100") int batchSize,
      @RequestParam(defaultValue = "1") int threads,
      HttpServletResponse response)
      throws FhirLocalPersistingException, IOException {

    if (inProcess()) {
      LogEvent.logWarn(
          this.getClass().getSimpleName(),
          "transformPersistMissingFhirObjects",
          "processs already running");
      response.getWriter().println("processs already running");
      return;
    }
    try {
      List<Patient> patients;
      if (checkAll) {
        patients = sampleHumanService.getAllPatientsWithSampleEntered();
      } else {
        patients = sampleHumanService.getAllPatientsWithSampleEnteredMissingFhirUuid();
      }
      LogEvent.logDebug(
          this.getClass().getSimpleName(),
          "transformPersistMissingFhirObjects",
          "patients to convert: " + patients.size());
      List<String> patientIds = new ArrayList<>();
      List<Future<Bundle>> promises = new ArrayList<>();
      for (int i = 0; i < patients.size(); ++i) {
        patientIds.add(patients.get(i).getId());
        if (i % batchSize == batchSize - 1 || i + 1 == patients.size()) {
          LogEvent.logDebug(
              this.getClass().getSimpleName(),
              "",
              "persisting batch " + (i - batchSize + 1) + "-" + i + " of " + patients.size());
          try {
            promises.add(fhirTransformService.transformPersistPatients(patientIds));
            patientIds = new ArrayList<>();
            if (promises.size() >= threads || i + 1 == patients.size()) {
              waitForResults(promises);
            }
          } catch (FhirPersistanceException e) {
            LogEvent.logError(e);
            LogEvent.logError(
                this.getClass().getSimpleName(),
                "transformPersistMissingFhirObjects",
                "error persisting batch " + (i - batchSize + 1) + "-" + i);
          } catch (Exception e) {
            LogEvent.logError(e);
            LogEvent.logError(
                this.getClass().getSimpleName(),
                "transformPersistMissingFhirObjects",
                "error with batch " + (i - batchSize + 1) + "-" + i);
          }
        }
      }

      List<Sample> samples;
      if (checkAll) {
        samples = sampleService.getAll();
      } else {
        samples = sampleService.getAllMissingFhirUuid();
      }
      LogEvent.logDebug(
          this.getClass().getSimpleName(),
          "transformPersistMissingFhirObjects",
          "samples to convert: " + samples.size());

      int batches = 0;
      int batchFailure = 0;
      List<String> sampleIds = new ArrayList<>();
      promises = new ArrayList<>();
      for (int i = 0; i < samples.size(); ++i) {
        sampleIds.add(samples.get(i).getId());
        if (i % batchSize == batchSize - 1 || i + 1 == samples.size()) {
          LogEvent.logDebug(
              this.getClass().getSimpleName(),
              "",
              "persisting batch " + (i - batchSize + 1) + "-" + i + " of " + samples.size());
          try {
            promises.add(fhirTransformService.transformPersistObjectsUnderSamples(sampleIds));
            ++batches;
            sampleIds = new ArrayList<>();
            if (promises.size() >= threads) {
              waitForResults(promises);
            }
          } catch (FhirPersistanceException e) {
            ++batchFailure;
            LogEvent.logError(e);
            LogEvent.logError(
                this.getClass().getSimpleName(),
                "transformPersistMissingFhirObjects",
                "error persisting batch " + (i - batchSize + 1) + "-" + i);
          } catch (Exception e) {
            ++batchFailure;
            LogEvent.logError(e);
            LogEvent.logError(
                this.getClass().getSimpleName(),
                "transformPersistMissingFhirObjects",
                "error with batch " + (i - batchSize + 1) + "-" + i);
          }
        }
      }
      LogEvent.logDebug(
          this.getClass().getSimpleName(),
          "transformPersistMissingFhirObjects",
          "finished all batches");

      response.getWriter().println("sample batches total: " + batches);
      response.getWriter().println("sample batches failed: " + batchFailure);
    } catch (RuntimeException e) {
      throw e;
    } finally {
      endProcess();
    }
  }

  private void waitForResults(List<Future<Bundle>> promises) throws Exception {
    for (int i = promises.size() - 1; i >= 0; i--) {
      try {
        promises.remove(i).get();
      } catch (InterruptedException | ExecutionException e) {
        LogEvent.logError(e);
        LogEvent.logError(
            this.getClass().getSimpleName(), "waitForResults", "Error getting value from thread");
        throw new Exception();
      }
    }
    // done so if there is a lot of data being processed, we backup to the CS in
    // tandem
    runExportTasks();
  }

  private void runExportTasks() {
    for (DataExportTask dataExportTask : dataExportTaskService.getDAO().findAll()) {
      dataExportService.exportNewDataFromLocalToRemote(dataExportTask);
    }
  }

  @Override
  protected String findLocalForward(String forward) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String getPageTitleKey() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String getPageSubtitleKey() {
    // TODO Auto-generated method stub
    return null;
  }

  private synchronized boolean inProcess() {
    if (!running) {
      running = true;
      return false;
    }
    return true;
  }

  private synchronized void endProcess() {
    running = false;
  }
}
