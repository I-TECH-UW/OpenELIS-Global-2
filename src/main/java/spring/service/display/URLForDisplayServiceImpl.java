package spring.service.display;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.display.URLForDisplay;
import us.mn.state.health.lims.displaydao.URLForDisplayDAO;

@Service
public class URLForDisplayServiceImpl extends BaseObjectServiceImpl<URLForDisplay> implements URLForDisplayService {
  @Autowired
  protected URLForDisplayDAO baseObjectDAO;

  URLForDisplayServiceImpl() {
    super(URLForDisplay.class);
  }

  @Override
  protected URLForDisplayDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
