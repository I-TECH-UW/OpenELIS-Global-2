package us.mn.state.health.lims.datasubmission.daoimpl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.datasubmission.dao.DataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class DataIndicatorDAOImpl implements DataIndicatorDAO {
	
	@Override
	public void getData(DataIndicator indicator) throws LIMSRuntimeException {
		try{
			DataIndicator indicatorClone = (DataIndicator)HibernateUtil.getSession().get(DataIndicator.class, indicator.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if(indicatorClone != null){
				PropertyUtils.copyProperties(indicator, indicatorClone);
			}else{
				indicator.setId(null);
			}
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataIndicatorDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in DataIndicator getData()", e);
		}
	}
	
	@Override
	public DataIndicator getIndicator(String id) throws LIMSRuntimeException {
		try{
			DataIndicator indicator = (DataIndicator)HibernateUtil.getSession().get(DataIndicator.class, id);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return indicator;
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataIndicatorDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in DataIndicator getData()", e);
		}
	}
	
	@Override
	public DataIndicator getIndicatorByTypeYearMonth(TypeOfDataIndicator type, int year, int month) throws LIMSRuntimeException {
		String sql = "From DataIndicator di where di.typeOfIndicator.id = :todiid and di.year = :year and di.month = :month";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("todiid", Integer.parseInt(type.getId()));
			query.setInteger("year", year);
			query.setInteger("month", month);
			DataIndicator indicator = (DataIndicator) query.uniqueResult();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (indicator == null) {
				return null;
			}
			return indicator;
		}catch (HibernateException e){
			LogEvent.logError("DataIndicatorDAOImpl", "getIndicatorByTypeYearMonth()", e.toString());
			throw new LIMSRuntimeException("Error in DataIndicator getIndicatorByTypeYearMonth()", e);
		}
	}
	
	@Override
	public List<DataIndicator> getIndicatorsByStatus(String status) throws LIMSRuntimeException {
		String sql = "From DataIndicator di where di.status = :status";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("status", status);
			List<DataIndicator> indicators = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			return indicators;
		}catch (HibernateException e){
			LogEvent.logError("DataIndicatorDAOImpl", "getIndicatorByStatus()", e.toString());
			throw new LIMSRuntimeException("Error in DataIndicator getIndicatorByStatus()", e);
		}
	}
	
	@Override
	public boolean insertData(DataIndicator dataIndicator) throws LIMSRuntimeException{
		try{ 
			String id = (String)HibernateUtil.getSession().save(dataIndicator);
			dataIndicator.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = dataIndicator.getSysUserId();
			String tableName = "DATA_INDICATOR";
			//auditDAO.saveNewHistory(dataIndicator, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataIndicatorDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in DataIndicator insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(DataIndicator dataIndicator) throws LIMSRuntimeException{

		DataIndicator oldData = getIndicator(dataIndicator.getId());

		try{
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = dataIndicator.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "DATA_INDICATOR";
		//	auditDAO.saveHistory(dataIndicator, oldData, sysUserId, event, tableName);
		}catch(Exception e){
			LogEvent.logError("DataIndicatorDAOImpl", "AuditTrail updateData()", e.toString());
			throw new LIMSRuntimeException("Error in DataIndicator AuditTrail updateData()", e);
		}

		try{
			HibernateUtil.getSession().merge(dataIndicator);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(dataIndicator);
			HibernateUtil.getSession().refresh(dataIndicator);
		}catch(Exception e){
			// bugzilla 2154
			LogEvent.logError("DataIndicatorDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in DataIndicator updateData()", e);
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
