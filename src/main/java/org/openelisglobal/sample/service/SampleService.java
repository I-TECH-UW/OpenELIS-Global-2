package org.openelisglobal.sample.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleqaevent.valueholder.SampleQaEvent;

public interface SampleService extends BaseObjectService<Sample, String> {
    void getData(Sample sample);

    List<Sample> getConfirmationSamplesReceivedInDateRange(Date receivedDateStart, Date receivedDateEnd);

    List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(List<Integer> inclusiveProjectIdList,
            List<Integer> inclusiveStatusIdList, String minAccession, String maxAccession);

    List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(String projectId, List<Integer> inclusiveStatusIdList,
            String minAccession, String maxAccession);

    String getLargestAccessionNumberWithPrefix(String prefix);

    String getLargestAccessionNumberMatchingPattern(String startingWith, int size);

    List<Sample> getSamplesWithPendingQaEventsByService(String serviceId);

    List getSamplesByStatusAndDomain(List statuses, String domain);

    List getPreviousSampleRecord(String id);

    List<Sample> getSamplesReceivedOn(String recievedDate);

    List<Sample> getSamplesCollectedOn(String collectionDate);

    String getLargestAccessionNumber();

    List<Sample> getSamplesWithPendingQaEvents(Sample sample, boolean filterByCategory, String qaEventCategoryId,
            boolean filterByDomain);

    List getNextSampleRecord(String id);

    Sample getSampleByReferringId(String referringId);

    List<Sample> getSamplesReceivedInDateRange(String receivedDateStart, String receivedDateEnd);

    List<Sample> getSamplesByAccessionRange(String minAccession, String maxAccession);

    boolean insertDataWithAccessionNumber(Sample sample);

    void getSampleByAccessionNumber(Sample sample);

    Sample getSampleByAccessionNumber(String accessionNumber);

    List getPageOfSamples(int startingRecNo);

    List<Sample> getSamplesForPatient(String patientID);

    String generateAccessionNumberAndInsert(Sample sample);

    Organization getOrganizationRequester(Sample sample);

    Person getPersonRequester(Sample sample);

    List<SampleQaEvent> getSampleQAEventList(Sample sample);

    List<Analysis> getAnalysis(Sample sample);

    Patient getPatient(Sample sample);

    String getId(Sample sample);

    boolean isConfirmationSample(Sample sample);

    String getReceived24HourTimeForDisplay(Sample sample);

    String getReceivedTimeForDisplay(Sample sample);

    String getReceivedDateWithTwoYearDisplay(Sample sample);

    String getReceivedDateForDisplay(Sample sample);

    String getAccessionNumber(Sample sample);

    Timestamp getOrderedDate(Sample sample);

    Date getCompletedDate(Sample sample);

    String getTwoYearReceivedDateForDisplay(Sample sample);
}
