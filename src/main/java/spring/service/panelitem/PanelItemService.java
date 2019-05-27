package spring.service.panelitem;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;

public interface PanelItemService extends BaseObjectService<PanelItem> {
	void getData(PanelItem panelItem);

	void deleteData(List panelItems);

	void updateData(PanelItem panelItem);

	boolean insertData(PanelItem panelItem);

	Integer getTotalPanelItemCount();

	List<PanelItem> getPanelItemsForPanelAndItemList(String panelId, List<Integer> testList);

	List getPanelItemsForPanel(String panelId);

	List getPreviousPanelItemRecord(String id);

	List getPageOfPanelItems(int startingRecNo);

	List getPanelItemByPanel(Panel panel, boolean onlyTestsFullySetup);

	List getNextPanelItemRecord(String id);

	boolean getDuplicateSortOrderForPanel(PanelItem panelItem);

	List<PanelItem> getPanelItemByTestId(String id);

	List getAllPanelItems();

	List getPanelItems(String filter);

	void delete(List panelItems) throws LIMSRuntimeException;
}
