package spring.service.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.gender.dao.GenderDAO;
import us.mn.state.health.lims.gender.valueholder.Gender;

@Service
public class GenderServiceImpl extends BaseObjectServiceImpl<Gender> implements GenderService {
  @Autowired
  protected GenderDAO baseObjectDAO;

  GenderServiceImpl() {
    super(Gender.class);
  }

  @Override
  protected GenderDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
