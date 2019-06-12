package spring.service.result;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.ResultSignatureDAO;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultSignature;

@Service
public class ResultSignatureServiceImpl extends BaseObjectServiceImpl<ResultSignature, String>
		implements ResultSignatureService {
	@Autowired
	protected ResultSignatureDAO baseObjectDAO;

	ResultSignatureServiceImpl() {
		super(ResultSignature.class);
	}

	@Override
	protected ResultSignatureDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(ResultSignature resultSignature) {
		getBaseObjectDAO().getData(resultSignature);

	}

	@Override
	public List<ResultSignature> getResultSignaturesByResult(Result result) {
		return getBaseObjectDAO().getResultSignaturesByResult(result);
	}

	@Override
	public ResultSignature getResultSignatureById(ResultSignature resultSignature) {
		return getBaseObjectDAO().getResultSignatureById(resultSignature);
	}

	@Override
	public List<ResultSignature> getResultSignaturesByResults(List<Result> resultList) {
		return getBaseObjectDAO().getResultSignaturesByResults(resultList);
	}
}
