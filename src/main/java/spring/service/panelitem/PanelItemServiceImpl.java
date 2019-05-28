package spring.service.panelitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.panel.PanelService;
import spring.service.test.TestService;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.dao.PanelItemDAO;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;

@Service
public class PanelItemServiceImpl extends BaseObjectServiceImpl<PanelItem> implements PanelItemService {
	@Autowired
	protected PanelItemDAO baseObjectDAO;
	@Autowired
	TestService testService;
	@Autowired
	PanelService panelService;

	PanelItemServiceImpl() {
		super(PanelItem.class);
	}

	@Override
	protected PanelItemDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public List<PanelItem> getPanelItemsForPanel(String panelId) {
		return baseObjectDAO.getPanelItemsForPanel(panelId);
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
		return getBaseObjectDAO().getPanelItemsForPanelAndItemList(panelId, testList);
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
		return getBaseObjectDAO().getPanelItemByPanel(panel, onlyTestsFullySetup);
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

	@Override
	@Transactional
	public void delete(List panelItems) throws LIMSRuntimeException {
		// add to audit trail
		try {
//			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < panelItems.size(); i++) {
				PanelItem data = (PanelItem) panelItems.get(i);

				PanelItem oldData = readPanelItem(data.getId());
				PanelItem newData = new PanelItem();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "PANEL_ITEM";
				auditTrailDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		} catch (Exception e) {
			LogEvent.logError("PanelItemDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in PanelItem AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < panelItems.size(); i++) {
				PanelItem data = (PanelItem) panelItems.get(i);
				data = readPanelItem(data.getId());
				baseObjectDAO.delete(data);
				// HibernateUtil.getSession().flush(); // CSL remove old
				// HibernateUtil.getSession().clear(); // CSL remove old
			}
		} catch (Exception e) {
			LogEvent.logError("PanelItemDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in PanelItem deleteData()", e);
		}
	}

	public PanelItem readPanelItem(String idString) {
		PanelItem pi;
		try {
			pi = get(idString);
//			pi = HibernateUtil.getSession().get(PanelItem.class, idString);
			// HibernateUtil.getSession().flush(); // CSL remove old
			// HibernateUtil.getSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("PanelItemDAOImpl", "readPanelItem()", e.toString());
			throw new LIMSRuntimeException("Error in PanelItem readPanelItem()", e);
		}

		return pi;
	}
	
	@Override
	@Transactional
	public void updatePanelItems(List<PanelItem> panelItems, Panel panel, boolean updatePanel, String currentUser, List<String> newTests) {
	
	    for (PanelItem oldPanelItem : panelItems) {
	    	oldPanelItem.setSysUserId(currentUser);
	    }
	    delete(panelItems);
        
	    for (String testId : newTests) {
	    	PanelItem panelItem = new PanelItem();
	    	panelItem.setPanel(panel);
	    	panelItem.setTest(testService.getTestById(testId));
	    	panelItem.setLastupdatedFields();
	    	panelItem.setSysUserId(currentUser);
	    	insert(panelItem);
	    }
        
	    if ("N".equals(panel.getIsActive())) {
	    	panel.setIsActive("Y");
	    	panel.setSysUserId(currentUser);
	    	panelService.update(panel);
	    } else {
			panel.setIsActive("N");
			panel.setSysUserId(currentUser);
			panelService.update(panel);
		}
	}
}
