package spring.service.renametestsection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.renametestsection.dao.RenameTestSectionDAO;
import us.mn.state.health.lims.renametestsection.valueholder.RenameTestSection;

@Service
public class RenameTestSectionServiceImpl extends BaseObjectServiceImpl<RenameTestSection> implements RenameTestSectionService {
  @Autowired
  protected RenameTestSectionDAO baseObjectDAO;

  RenameTestSectionServiceImpl() {
    super(RenameTestSection.class);
  }

  @Override
  protected RenameTestSectionDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
