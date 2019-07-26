package org.openelisglobal.panel.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.panel.valueholder.Panel;

public interface PanelService extends BaseObjectService<Panel, String> {

	void getData(Panel panel);

	String getIdForPanelName(String name);

	String getDescriptionForPanelId(String id);

	String getNameForPanelId(String panelId);

	List<Panel> getAllActivePanels();

	List getNextPanelRecord(String id);

	List getPreviousPanelRecord(String id);

	Integer getTotalPanelCount();

	List getActivePanels(String filter);

	Panel getPanelByName(String panelName);

	Panel getPanelByName(Panel panel);

	Panel getPanelById(String id);

	List getPageOfPanels(int startingRecNo);

	List<Panel> getAllPanels();

}
