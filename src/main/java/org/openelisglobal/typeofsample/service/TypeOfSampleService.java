package org.openelisglobal.typeofsample.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.typeofsample.dao.TypeOfSampleDAO;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;

public interface TypeOfSampleService extends BaseObjectService<TypeOfSample, String> {
    void getData(TypeOfSample typeOfSample);

    String getNameForTypeOfSampleId(String id);

    List getAllTypeOfSamples();

    List<TypeOfSample> getAllTypeOfSamplesSortOrdered();

    List getTypesForDomain(TypeOfSampleDAO.SampleDomain domain);

    List getPreviousTypeOfSampleRecord(String id);

    Integer getTotalTypeOfSampleCount();

    List getNextTypeOfSampleRecord(String id);

    TypeOfSample getTypeOfSampleById(String typeOfSampleId);

    TypeOfSample getSampleTypeFromTest(Test test);

    List<TypeOfSample> getTypesForDomainBySortOrder(TypeOfSampleDAO.SampleDomain human);

    List getPageOfTypeOfSamples(int startingRecNo);

    List getTypes(String filter, String domain);

    TypeOfSample getTypeOfSampleByLocalAbbrevAndDomain(String localAbbrev, String domain);

    TypeOfSample getTypeOfSampleByDescriptionAndDomain(TypeOfSample tos, boolean ignoreCase);

    List<Test> getAllTestsBySampleTypeId(String sampleTypeId);

    List<Test> getActiveTestsBySampleTypeId(String sampleType, boolean b);

    TypeOfSample getTransientTypeOfSampleById(String sampleTypeId);

    void clearCache();

    TypeOfSample getTypeOfSampleForTest(String testId);

    String getTypeOfSampleNameForId(String id);

    String getTypeOfSampleIdForLocalAbbreviation(String name);

    List<TypeOfSample> getTypeOfSampleForPanelId(String id);
}
