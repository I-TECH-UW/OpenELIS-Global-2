package us.mn.state.health.lims.datasubmission.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.datasubmission.dao.DataValueDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataValue;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class DataValueDAOImpl implements DataValueDAO {
	
	@Override
	public void getData(DataValue dataValue) throws LIMSRuntimeException {
		try{
			DataValue dataValueClone = (DataValue)HibernateUtil.getSession().get(DataValue.class, dataValue.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if(dataValueClone != null){
				PropertyUtils.copyProperties(dataValue, dataValueClone);
			}else{
				dataValue.setId(null);
			}
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataValueDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in DataValue getData()", e);
		}
	}
	
	@Override
	public DataValue getDataValue(String id) throws LIMSRuntimeException {
		try{
			DataValue dataValue = (DataValue)HibernateUtil.getSession().get(DataValue.class, id);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return dataValue;
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataValueDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in DataValue getData()", e);
		}
	}
	
	@Override
	public boolean insertData(DataValue dataValue) throws LIMSRuntimeException{

		try{ 
			String id = (String)HibernateUtil.getSession().save(dataValue);
			dataValue.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = dataValue.getSysUserId();
			String tableName = "DATA_VALUE";
			auditDAO.saveNewHistory(dataValue, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataValueDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in DataValue insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(DataValue dataValue) throws LIMSRuntimeException{

		DataValue oldData = getDataValue(dataValue.getId());

		try{
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = dataValue.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "DATA_VALUE";
		//	auditDAO.saveHistory(dataValue, oldData, sysUserId, event, tableName);
		}catch(Exception e){
			LogEvent.logError("DataValueDAOImpl", "AuditTrail updateData()", e.toString());
			throw new LIMSRuntimeException("Error in DataValue AuditTrail updateData()", e);
		}

		try{
			HibernateUtil.getSession().merge(dataValue);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(dataValue);
			HibernateUtil.getSession().refresh(dataValue);
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataValueDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in DataValue updateData()", e);
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
