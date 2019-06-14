package us.mn.state.health.lims.audittrail.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;

@Component
@Transactional 
public class HistoryDAOImpl extends BaseDAOImpl<History, String> implements HistoryDAO {
  HistoryDAOImpl() {
    super(History.class);
  }
  
  public List getHistoryByRefIdAndRefTableId(String refId, String tableId) throws LIMSRuntimeException {
	  History history = new History();
	  history.setId(refId);
	  history.setReferenceTable(tableId);
	  return getHistoryByRefIdAndRefTableId(history);
  }
  
	@Transactional(readOnly = true)
	public List getHistoryByRefIdAndRefTableId(History history) throws LIMSRuntimeException {
		String refId = history.getReferenceId(); 
		String tableId = history.getReferenceTable();
		List list;
		
		try {
			String sql = "from History h where h.referenceId = :refId and h.referenceTable = :tableId order by h.timestamp desc, h.activity desc";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("refId", Integer.parseInt(refId));
			query.setInteger("tableId", Integer.parseInt(tableId));
			list = query.list();
		} catch (HibernateException e) {
			LogEvent.logError("AuditTrailDAOImpl", "getHistoryByRefIdAndRefTableId()", e.toString());
			throw new LIMSRuntimeException("Error in AuditTrail getHistoryByRefIdAndRefTableId()", e);
		}
		return list;
	}
}
