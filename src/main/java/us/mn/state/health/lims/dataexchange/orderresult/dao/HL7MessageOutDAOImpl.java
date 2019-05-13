package us.mn.state.health.lims.dataexchange.orderresult.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.dataexchange.orderresult.valueholder.HL7MessageOut;

@Component
public class HL7MessageOutDAOImpl extends BaseDAOImpl<HL7MessageOut> implements HL7MessageOutDAO {
  public HL7MessageOutDAOImpl() {
    super(HL7MessageOut.class);
  }
}
