package spring.service.panel;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.panel.valueholder.Panel;

public interface PanelService extends BaseObjectService<Panel> {

	@Override
	String insert(Panel panel);

	void getData(Panel panel);

	void deleteData(List panels);

	void updateData(Panel panel);

	boolean insertData(Panel panel);

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
