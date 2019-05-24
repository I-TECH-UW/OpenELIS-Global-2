package spring.service.panelitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.dao.PanelItemDAO;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;

@Service
public class PanelItemServiceImpl extends BaseObjectServiceImpl<PanelItem> implements PanelItemService {
	@Autowired
	protected PanelItemDAO baseObjectDAO;

	PanelItemServiceImpl() {
		super(PanelItem.class);
	}

	@Override
	protected PanelItemDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<PanelItem> getPanelItemsForPanel(String panelId) {
		return baseObjectDAO.getAllMatching("panel.id", panelId);
	}

	@Override
	public void getData(PanelItem panelItem) {
        getBaseObjectDAO().getData(panelItem);

	}

	@Override
	public void deleteData(List panelItems) {
        getBaseObjectDAO().deleteData(panelItems);

	}

	@Override
	public void updateData(PanelItem panelItem) {
        getBaseObjectDAO().updateData(panelItem);

	}

	@Override
	public boolean insertData(PanelItem panelItem) {
        return getBaseObjectDAO().insertData(panelItem);
	}

	@Override
	public Integer getTotalPanelItemCount() {
        return getBaseObjectDAO().getTotalPanelItemCount();
	}

	@Override
	public List<PanelItem> getPanelItemsForPanelAndItemList(String panelId, List<Integer> testList) {
        return getBaseObjectDAO().getPanelItemsForPanelAndItemList(panelId,testList);
	}

	@Override
	public List getPreviousPanelItemRecord(String id) {
        return getBaseObjectDAO().getPreviousPanelItemRecord(id);
	}

	@Override
	public List getPageOfPanelItems(int startingRecNo) {
        return getBaseObjectDAO().getPageOfPanelItems(startingRecNo);
	}

	@Override
	public List getPanelItemByPanel(Panel panel, boolean onlyTestsFullySetup) {
        return getBaseObjectDAO().getPanelItemByPanel(panel,onlyTestsFullySetup);
	}

	@Override
	public List getNextPanelItemRecord(String id) {
        return getBaseObjectDAO().getNextPanelItemRecord(id);
	}

	@Override
	public boolean getDuplicateSortOrderForPanel(PanelItem panelItem) {
        return getBaseObjectDAO().getDuplicateSortOrderForPanel(panelItem);
	}

	@Override
	public List<PanelItem> getPanelItemByTestId(String id) {
        return getBaseObjectDAO().getPanelItemByTestId(id);
	}

	@Override
	public List getAllPanelItems() {
        return getBaseObjectDAO().getAllPanelItems();
	}

	@Override
	public List getPanelItems(String filter) {
        return getBaseObjectDAO().getPanelItems(filter);
	}
}
