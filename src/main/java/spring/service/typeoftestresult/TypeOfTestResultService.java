package spring.service.typeoftestresult;

import spring.service.common.BaseObjectService;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl.ResultType;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;

public interface TypeOfTestResultService extends BaseObjectService<TypeOfTestResult, String> {

	TypeOfTestResult getTypeOfTestResultByType(TypeOfTestResult typeOfTestResult);

	TypeOfTestResult getTypeOfTestResultByType(String type);

	ResultType getResultTypeById(String resultTypeId);
}
