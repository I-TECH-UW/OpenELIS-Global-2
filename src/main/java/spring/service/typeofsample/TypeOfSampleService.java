package spring.service.typeofsample;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface TypeOfSampleService extends BaseObjectService<TypeOfSample> {
	void getData(TypeOfSample typeOfSample);

	void deleteData(List typeOfSamples);

	void updateData(TypeOfSample typeOfSample);

	boolean insertData(TypeOfSample typeOfSample);

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
