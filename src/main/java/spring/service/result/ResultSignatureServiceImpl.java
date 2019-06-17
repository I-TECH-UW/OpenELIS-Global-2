package spring.service.result;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public void getData(ResultSignature resultSignature) {
		getBaseObjectDAO().getData(resultSignature);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ResultSignature> getResultSignaturesByResult(Result result) {
		return getBaseObjectDAO().getResultSignaturesByResult(result);
	}

	@Override
	@Transactional(readOnly = true)
	public ResultSignature getResultSignatureById(ResultSignature resultSignature) {
		return getBaseObjectDAO().getResultSignatureById(resultSignature);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResultSignature> getResultSignaturesByResults(List<Result> resultList) {
		return getBaseObjectDAO().getResultSignaturesByResults(resultList);
	}
}
