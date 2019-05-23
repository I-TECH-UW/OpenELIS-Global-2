package spring.service.panel;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.panel.valueholder.Panel;

public interface PanelService extends BaseObjectService<Panel> {

	List<Panel> getAllPanels();
}
