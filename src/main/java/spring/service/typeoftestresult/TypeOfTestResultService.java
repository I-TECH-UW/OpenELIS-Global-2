package spring.service.typeoftestresult;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;

public interface TypeOfTestResultService extends BaseObjectService<TypeOfTestResult> {
	void getData(TypeOfTestResult typeOfTestResult);

	void deleteData(List typeOfTestResults);

	void updateData(TypeOfTestResult typeOfTestResult);

	boolean insertData(TypeOfTestResult typeOfTestResult);

	Integer getTotalTypeOfTestResultCount();

	List getNextTypeOfTestResultRecord(String id);

	List getPageOfTypeOfTestResults(int startingRecNo);

	List getAllTypeOfTestResults();

	TypeOfTestResult getTypeOfTestResultByType(TypeOfTestResult typeOfTestResult);

	TypeOfTestResult getTypeOfTestResultByType(String type);

	List getPreviousTypeOfTestResultRecord(String id);
}
