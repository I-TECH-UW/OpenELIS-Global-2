package spring.service.result;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultSignature;

public interface ResultSignatureService extends BaseObjectService<ResultSignature> {
	void getData(ResultSignature resultSignature);

	void deleteData(List<ResultSignature> results);

	void updateData(ResultSignature resultSignature);

	boolean insertData(ResultSignature resultSignature);

	List<ResultSignature> getResultSignaturesByResult(Result result);

	ResultSignature getResultSignatureById(ResultSignature resultSignature);

	List<ResultSignature> getResultSignaturesByResults(List<Result> resultList);
}
