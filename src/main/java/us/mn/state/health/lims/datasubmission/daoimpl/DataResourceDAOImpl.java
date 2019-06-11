package us.mn.state.health.lims.datasubmission.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.datasubmission.dao.DataResourceDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataResource;

@Component
@Transactional
public class DataResourceDAOImpl extends BaseDAOImpl<DataResource, String> implements DataResourceDAO {

	public DataResourceDAOImpl() {
		super(DataResource.class);
	}

	@Autowired
	private AuditTrailDAO auditDAO;

//	@Override
//	public void getData(DataResource resource) throws LIMSRuntimeException {
//		try {
//			DataResource resourceClone = sessionFactory.getCurrentSession().get(DataResource.class, resource.getId());
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			if (resourceClone != null) {
//				PropertyUtils.copyProperties(resource, resourceClone);
//			} else {
//				resource.setId(null);
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("DataResourceDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in DataResource getData()", e);
//		}
//	}

//	@Override
//	public DataResource getDataResource(String id) throws LIMSRuntimeException {
//		try {
//			DataResource resource = sessionFactory.getCurrentSession().get(DataResource.class, id);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			return resource;
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("DataResourceDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in DataResource getData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(DataResource resource) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) sessionFactory.getCurrentSession().save(resource);
//			resource.setId(id);
//
//			String sysUserId = resource.getSysUserId();
//			String tableName = "DATA_VALUE";
//			auditDAO.saveNewHistory(resource, sysUserId, tableName);
//
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("DataResourceDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in DataResource insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(DataResource resource) throws LIMSRuntimeException {
//
//		DataResource oldData = getDataResource(resource.getId());
//
//		try {
//
//			String sysUserId = resource.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "DATA_VALUE";
//			// auditDAO.saveHistory(resource, oldData, sysUserId, event, tableName);
//		} catch (Exception e) {
//			LogEvent.logError("DataResourceDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in DataResource AuditTrail updateData()", e);
//		}
//
//		try {
//			sessionFactory.getCurrentSession().merge(resource);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(resource);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(resource);
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("DataResourceDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in DataResource updateData()", e);
//		}
//	}

}
