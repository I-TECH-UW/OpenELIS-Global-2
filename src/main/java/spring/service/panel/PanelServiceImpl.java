package spring.service.panel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.valueholder.Panel;

@Service
public class PanelServiceImpl extends BaseObjectServiceImpl<Panel> implements PanelService {
  
  @Autowired
  protected PanelDAO baseObjectDAO;

  PanelServiceImpl() {
    super(Panel.class);
  }

  @Override
  protected PanelDAO getBaseObjectDAO() {
    return baseObjectDAO;}

  @Override
  public List<Panel> getAllPanels() {
  	return baseObjectDAO.getAllPanels();
  }

  @Override
  public Panel getPanelById(String id) {
  	return baseObjectDAO.getPanelById(id);
  }

  @Override
  public Panel update(Panel panel) {
	  if (baseObjectDAO.duplicatePanelExists(panel)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + panel.getPanelName());
		}
		// AIS - bugzilla 1563
		if (baseObjectDAO.duplicatePanelDescriptionExists(panel)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for panel description");
		}
      panel = super.update(panel);
  	  baseObjectDAO.clearIDMaps();
  	  return panel;
  }
}
