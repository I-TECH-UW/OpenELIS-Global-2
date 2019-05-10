package spring.service.sampleitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

@Service
public class SampleItemServiceImpl extends BaseObjectServiceImpl<SampleItem> implements SampleItemService {
  @Autowired
  protected SampleItemDAO baseObjectDAO;

  SampleItemServiceImpl() {
    super(SampleItem.class);
  }

  @Override
  protected SampleItemDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
