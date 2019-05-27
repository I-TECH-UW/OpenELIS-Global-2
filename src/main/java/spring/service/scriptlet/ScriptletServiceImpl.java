package spring.service.scriptlet;

import java.util.List;

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
		return baseObjectDAO;
	}

	@Override
	public void getData(Scriptlet scriptlet) {
        getBaseObjectDAO().getData(scriptlet);

	}

	@Override
	public void deleteData(List scriptlets) {
        getBaseObjectDAO().deleteData(scriptlets);

	}

	@Override
	public void updateData(Scriptlet scriptlet) {
        getBaseObjectDAO().updateData(scriptlet);

	}

	@Override
	public boolean insertData(Scriptlet scriptlet) {
        return getBaseObjectDAO().insertData(scriptlet);
	}

	@Override
	public List getPreviousScriptletRecord(String id) {
        return getBaseObjectDAO().getPreviousScriptletRecord(id);
	}

	@Override
	public Integer getTotalScriptletCount() {
        return getBaseObjectDAO().getTotalScriptletCount();
	}

	@Override
	public List getPageOfScriptlets(int startingRecNo) {
        return getBaseObjectDAO().getPageOfScriptlets(startingRecNo);
	}

	@Override
	public Scriptlet getScriptletByName(Scriptlet scriptlet) {
        return getBaseObjectDAO().getScriptletByName(scriptlet);
	}

	@Override
	public List getNextScriptletRecord(String id) {
        return getBaseObjectDAO().getNextScriptletRecord(id);
	}

	@Override
	public Scriptlet getScriptletById(String scriptletId) {
        return getBaseObjectDAO().getScriptletById(scriptletId);
	}

	@Override
	public List getScriptlets(String filter) {
        return getBaseObjectDAO().getScriptlets(filter);
	}

	@Override
	public List getAllScriptlets() {
        return getBaseObjectDAO().getAllScriptlets();
	}
}
