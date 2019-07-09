package us.mn.state.health.lims.datasubmission.daoimpl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.datasubmission.dao.DataResourceDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataResource;

@Component
@Transactional
public class DataResourceDAOImpl extends BaseDAOImpl<DataResource, String> implements DataResourceDAO {

	public DataResourceDAOImpl() {
		super(DataResource.class);
	}

//	@Override
//	public void getData(DataResource resource) throws LIMSRuntimeException {
//		try {
//			DataResource resourceClone = entityManager.unwrap(Session.class).get(DataResource.class, resource.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
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
//			DataResource resource = entityManager.unwrap(Session.class).get(DataResource.class, id);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
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
//			String id = (String) entityManager.unwrap(Session.class).save(resource);
//			resource.setId(id);
//
//			String sysUserId = resource.getSysUserId();
//			String tableName = "DATA_VALUE";
//			auditDAO.saveNewHistory(resource, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
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
//			entityManager.unwrap(Session.class).merge(resource);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(resource);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(resource);
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("DataResourceDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in DataResource updateData()", e);
//		}
//	}

}
