package spring.service.dataexchange.orderresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.orderresult.dao.HL7MessageOutDAO;
import us.mn.state.health.lims.dataexchange.orderresult.valueholder.HL7MessageOut;

@Service
public class HL7MessageOutServiceImpl extends BaseObjectServiceImpl<HL7MessageOut> implements HL7MessageOutService {
  @Autowired
  protected HL7MessageOutDAO baseObjectDAO;

  HL7MessageOutServiceImpl() {
    super(HL7MessageOut.class);
  }

  @Override
  protected HL7MessageOutDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
