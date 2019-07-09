package us.mn.state.health.lims.dataexchange.orderresult.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.dataexchange.orderresult.valueholder.HL7MessageOut;

@Component
@Transactional 
public class HL7MessageOutDAOImpl extends BaseDAOImpl<HL7MessageOut, String> implements HL7MessageOutDAO {
  public HL7MessageOutDAOImpl() {
    super(HL7MessageOut.class);
  }
}
