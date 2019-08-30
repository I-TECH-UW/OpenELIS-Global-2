package org.openelisglobal.testconfiguration.service;

import org.openelisglobal.typeofsample.valueholder.TypeOfSample;

public interface SampleTypeTestAssignService {

    void update(TypeOfSample typeOfSample, String testId, String typeOfSamplesTestID, String sampleTypeId,
            boolean deleteExistingTypeOfSampleTest, boolean updateTypeOfSample, TypeOfSample deActivateTypeOfSample,
            String systemUserId);

}
