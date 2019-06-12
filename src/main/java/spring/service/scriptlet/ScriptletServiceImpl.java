package spring.service.scriptlet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.scriptlet.dao.ScriptletDAO;
import us.mn.state.health.lims.scriptlet.valueholder.Scriptlet;

@Service
public class ScriptletServiceImpl extends BaseObjectServiceImpl<Scriptlet, String> implements ScriptletService {
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

	@Override
	public String insert(Scriptlet scriptlet) {
		if (baseObjectDAO.duplicateScriptletExists(scriptlet)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + scriptlet.getScriptletName());
		}
		return super.insert(scriptlet);
	}

	@Override
	public Scriptlet save(Scriptlet scriptlet) {
		if (baseObjectDAO.duplicateScriptletExists(scriptlet)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + scriptlet.getScriptletName());
		}
		return super.save(scriptlet);
	}

	@Override
	public Scriptlet update(Scriptlet scriptlet) {
		if (baseObjectDAO.duplicateScriptletExists(scriptlet)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + scriptlet.getScriptletName());
		}
		return super.update(scriptlet);
	}
}
