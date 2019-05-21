package spring.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.ResultSignatureDAO;
import us.mn.state.health.lims.result.valueholder.ResultSignature;

@Service
public class ResultSignatureServiceImpl extends BaseObjectServiceImpl<ResultSignature> implements ResultSignatureService {
  @Autowired
  protected ResultSignatureDAO baseObjectDAO;

  ResultSignatureServiceImpl() {
    super(ResultSignature.class);
  }

  @Override
  protected ResultSignatureDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
