package spring.service.datasubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.datasubmission.dao.DataResourceDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataResource;

@Service
public class DataResourceServiceImpl extends BaseObjectServiceImpl<DataResource> implements DataResourceService {
  @Autowired
  protected DataResourceDAO baseObjectDAO;

  DataResourceServiceImpl() {
    super(DataResource.class);
  }

  @Override
  protected DataResourceDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
