package us.mn.state.health.lims.displaydao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.display.URLForDisplay;

@Component
public class URLForDisplayDAOImpl extends BaseDAOImpl<URLForDisplay> implements URLForDisplayDAO {
  URLForDisplayDAOImpl() {
    super(URLForDisplay.class);
  }
}
