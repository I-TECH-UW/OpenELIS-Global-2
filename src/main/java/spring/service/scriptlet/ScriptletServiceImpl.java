package spring.service.scriptlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.scriptlet.dao.ScriptletDAO;
import us.mn.state.health.lims.scriptlet.valueholder.Scriptlet;

@Service
public class ScriptletServiceImpl extends BaseObjectServiceImpl<Scriptlet> implements ScriptletService {
  @Autowired
  protected ScriptletDAO baseObjectDAO;

  ScriptletServiceImpl() {
    super(Scriptlet.class);
  }

  @Override
  protected ScriptletDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
