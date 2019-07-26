package org.openelisglobal.panelitem.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.panel.valueholder.Panel;
import org.openelisglobal.panelitem.valueholder.PanelItem;
import org.openelisglobal.test.valueholder.Test;

public interface PanelItemService extends BaseObjectService<PanelItem, String> {
	void getData(PanelItem panelItem);

	Integer getTotalPanelItemCount();

	List<PanelItem> getPanelItemsForPanelAndItemList(String panelId, List<Integer> testList);

	List getPreviousPanelItemRecord(String id);

	List getPageOfPanelItems(int startingRecNo);

	List getNextPanelItemRecord(String id);

	boolean getDuplicateSortOrderForPanel(PanelItem panelItem);

	List<PanelItem> getPanelItemByTestId(String id);

	List getAllPanelItems();

	List getPanelItems(String filter);

	List<PanelItem> getPanelItemsForPanel(String panelId);

	void updatePanelItems(List<PanelItem> panelItems, Panel panel, boolean updatePanel, String currentUser,
			List<Test> newTests);
}
