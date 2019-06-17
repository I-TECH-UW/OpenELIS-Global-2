package spring.service.panel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.valueholder.Panel;

@Service
public class PanelServiceImpl extends BaseObjectServiceImpl<Panel, String> implements PanelService {

	@Autowired
	protected PanelDAO baseObjectDAO;

	PanelServiceImpl() {
		super(Panel.class);
	}

	@Override
	protected PanelDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public void getData(Panel panel) {
		getBaseObjectDAO().getData(panel);

	}

	@Override
	@Transactional(readOnly = true)
	public String getIdForPanelName(String name) {
		return getBaseObjectDAO().getIdForPanelName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public String getDescriptionForPanelId(String id) {
		return getBaseObjectDAO().getDescriptionForPanelId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public String getNameForPanelId(String panelId) {
		return getBaseObjectDAO().getNameForPanelId(panelId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Panel> getAllActivePanels() {
		return getBaseObjectDAO().getAllActivePanels();
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextPanelRecord(String id) {
		return getBaseObjectDAO().getNextPanelRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousPanelRecord(String id) {
		return getBaseObjectDAO().getPreviousPanelRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalPanelCount() {
		return getBaseObjectDAO().getTotalPanelCount();
	}

	@Override
	@Transactional(readOnly = true)
	public List getActivePanels(String filter) {
		return getBaseObjectDAO().getActivePanels(filter);
	}

	@Override
	@Transactional(readOnly = true)
	public Panel getPanelByName(String panelName) {
		return getBaseObjectDAO().getPanelByName(panelName);
	}

	@Override
	@Transactional(readOnly = true)
	public Panel getPanelByName(Panel panel) {
		return getBaseObjectDAO().getPanelByName(panel);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfPanels(int startingRecNo) {
		return getBaseObjectDAO().getPageOfPanels(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Panel> getAllPanels() {
		return baseObjectDAO.getAllPanels();
	}

	@Override
	@Transactional(readOnly = true)
	public Panel getPanelById(String id) {
		return baseObjectDAO.getPanelById(id);
	}

	@Override
	public String insert(Panel panel) {
		if (getBaseObjectDAO().duplicatePanelExists(panel)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + panel.getPanelName());
		}
		if (getBaseObjectDAO().duplicatePanelDescriptionExists(panel)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for panel description");
		}
		baseObjectDAO.clearIDMaps();
		return super.insert(panel);
	}

	@Override
	public Panel save(Panel panel) {
		if (getBaseObjectDAO().duplicatePanelExists(panel)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + panel.getPanelName());
		}
		if (getBaseObjectDAO().duplicatePanelDescriptionExists(panel)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for panel description");
		}
		baseObjectDAO.clearIDMaps();
		return super.save(panel);
	}

	@Override
	public Panel update(Panel panel) {
		if (getBaseObjectDAO().duplicatePanelExists(panel)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + panel.getPanelName());
		}
		if (getBaseObjectDAO().duplicatePanelDescriptionExists(panel)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for panel description");
		}
		baseObjectDAO.clearIDMaps();
		return super.update(panel);
	}

	@Override
	public void delete(Panel panel) {
		super.delete(panel);
		baseObjectDAO.clearIDMaps();
	}
}
