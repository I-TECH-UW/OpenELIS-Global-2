package spring.service.panelitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
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
	@Transactional
	public void delete(List panelItems) throws LIMSRuntimeException {
		// add to audit trail
//		try {
//			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
//			for (int i = 0; i < panelItems.size(); i++) {
//				PanelItem data = (PanelItem) panelItems.get(i);
//
//				PanelItem oldData = readPanelItem(data.getId());
//				PanelItem newData = new PanelItem();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "PANEL_ITEM";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (Exception e) {
//			LogEvent.logError("PanelItemDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in PanelItem AuditTrail deleteData()", e);
//		}

		try {
			for (int i = 0; i < panelItems.size(); i++) {
				PanelItem data = (PanelItem) panelItems.get(i);
				data = readPanelItem(data.getId());
				HibernateUtil.getSession().delete(data);
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
			pi = (PanelItem) HibernateUtil.getSession().get(PanelItem.class, idString);
			// HibernateUtil.getSession().flush(); // CSL remove old
			// HibernateUtil.getSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("PanelItemDAOImpl", "readPanelItem()", e.toString());
			throw new LIMSRuntimeException("Error in PanelItem readPanelItem()", e);
		}

		return pi;
	}
}
