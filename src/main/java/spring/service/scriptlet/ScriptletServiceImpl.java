package spring.service.scriptlet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public void getData(Scriptlet scriptlet) {
		getBaseObjectDAO().getData(scriptlet);

	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousScriptletRecord(String id) {
		return getBaseObjectDAO().getPreviousScriptletRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalScriptletCount() {
		return getBaseObjectDAO().getTotalScriptletCount();
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfScriptlets(int startingRecNo) {
		return getBaseObjectDAO().getPageOfScriptlets(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public Scriptlet getScriptletByName(Scriptlet scriptlet) {
		return getBaseObjectDAO().getScriptletByName(scriptlet);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextScriptletRecord(String id) {
		return getBaseObjectDAO().getNextScriptletRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Scriptlet getScriptletById(String scriptletId) {
		return getBaseObjectDAO().getScriptletById(scriptletId);
	}

	@Override
	@Transactional(readOnly = true)
	public List getScriptlets(String filter) {
		return getBaseObjectDAO().getScriptlets(filter);
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllScriptlets() {
		return getBaseObjectDAO().getAllScriptlets();
	}

	@Override
	public String insert(Scriptlet scriptlet) {
		if (duplicateScriptletExists(scriptlet)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + scriptlet.getScriptletName());
		}
		return super.insert(scriptlet);
	}

	@Override
	public Scriptlet save(Scriptlet scriptlet) {
		if (duplicateScriptletExists(scriptlet)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + scriptlet.getScriptletName());
		}
		return super.save(scriptlet);
	}

	@Override
	public Scriptlet update(Scriptlet scriptlet) {
		if (duplicateScriptletExists(scriptlet)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + scriptlet.getScriptletName());
		}
		return super.update(scriptlet);
	}

	private boolean duplicateScriptletExists(Scriptlet scriptlet) {
		return baseObjectDAO.duplicateScriptletExists(scriptlet);
	}
}
