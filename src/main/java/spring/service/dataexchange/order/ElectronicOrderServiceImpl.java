package spring.service.dataexchange.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

@Service
public class ElectronicOrderServiceImpl extends BaseObjectServiceImpl<ElectronicOrder> implements ElectronicOrderService {
  @Autowired
  protected ElectronicOrderDAO baseObjectDAO;

  ElectronicOrderServiceImpl() {
    super(ElectronicOrder.class);
  }

  @Override
  protected ElectronicOrderDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
