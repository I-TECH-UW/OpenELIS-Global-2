package us.mn.state.health.lims.datasubmission.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.datasubmission.dao.DataResourceDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataResource;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class DataResourceDAOImpl implements DataResourceDAO {
	
	@Override
	public void getData(DataResource resource) throws LIMSRuntimeException {
		try{
			DataResource resourceClone = (DataResource)HibernateUtil.getSession().get(DataResource.class, resource.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if(resourceClone != null){
				PropertyUtils.copyProperties(resource, resourceClone);
			}else{
				resource.setId(null);
			}
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataResourceDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in DataResource getData()", e);
		}
	}
	
	@Override
	public DataResource getDataResource(String id) throws LIMSRuntimeException {
		try{
			DataResource resource = (DataResource)HibernateUtil.getSession().get(DataResource.class, id);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return resource;
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataResourceDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in DataResource getData()", e);
		}
	}
	
	@Override
	public boolean insertData(DataResource resource) throws LIMSRuntimeException{

		try{ 
			String id = (String)HibernateUtil.getSession().save(resource);
			resource.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = resource.getSysUserId();
			String tableName = "DATA_VALUE";
			auditDAO.saveNewHistory(resource, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataResourceDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in DataResource insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(DataResource resource) throws LIMSRuntimeException{

		DataResource oldData = getDataResource(resource.getId());

		try{
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = resource.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "DATA_VALUE";
		//	auditDAO.saveHistory(resource, oldData, sysUserId, event, tableName);
		}catch(Exception e){
			LogEvent.logError("DataResourceDAOImpl", "AuditTrail updateData()", e.toString());
			throw new LIMSRuntimeException("Error in DataResource AuditTrail updateData()", e);
		}

		try{
			HibernateUtil.getSession().merge(resource);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(resource);
			HibernateUtil.getSession().refresh(resource);
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataResourceDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in DataResource updateData()", e);
		}
	}
	

	@Override
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalCount(String table, Class clazz) throws LIMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
