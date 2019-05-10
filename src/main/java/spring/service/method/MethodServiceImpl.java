package spring.service.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.method.dao.MethodDAO;
import us.mn.state.health.lims.method.valueholder.Method;

@Service
public class MethodServiceImpl extends BaseObjectServiceImpl<Method> implements MethodService {
  @Autowired
  protected MethodDAO baseObjectDAO;

  MethodServiceImpl() {
    super(Method.class);
  }

  @Override
  protected MethodDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
