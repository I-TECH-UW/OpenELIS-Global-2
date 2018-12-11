package us.mn.state.health.lims.dataexchange.orderresult.DAO;

import java.math.BigInteger;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.dataexchange.orderresult.valueholder.HL7MessageOut;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class HL7MessageOutDAOImpl extends BaseDAOImpl {
	
	public int getNextIdNoIncrement() {
		try {
			String sql = "select is_called from clinlims.hl7_message_out_seq";
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			boolean isCalled = (Boolean) query.uniqueResult();
			if (!isCalled) {// first time called
				return 1;
			}
			
			sql = "select last_value from clinlims.hl7_message_out_seq";
			query = HibernateUtil.getSession().createSQLQuery(sql);
			BigInteger id = (BigInteger) query.uniqueResult();
			closeSession();
			return id.intValue() + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getNextIdIncrement() {
		try {
			String sql = "select nextval('clinlims.hl7_message_out_seq')";
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			BigInteger id = (BigInteger) query.uniqueResult();
			closeSession();
			return id.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void insertData(HL7MessageOut messageOut) throws LIMSRuntimeException {
		try{
			String id = (String)HibernateUtil.getSession().save(messageOut);
			messageOut.setId(id);
			//new AuditTrailDAOImpl().saveNewHistory(messageOut, messageOut.getSysUserId(), "HL7_MESSAGE_OUT");
			closeSession();
		}catch(HibernateException e){
			handleException(e, "insertData");
		}
	}
	
	public HL7MessageOut getByData(String data) throws LIMSRuntimeException {
		try{
			String sql = "from HL7MessageOut message where data = :data";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("data", data);
			HL7MessageOut message = (HL7MessageOut) query.uniqueResult();
			closeSession();
			return message;
		}catch(HibernateException e){
			handleException(e, "getByData");
		}
		return null;
	}

	public void update(HL7MessageOut hl7Message) throws LIMSRuntimeException {
		try{
			HibernateUtil.getSession().merge(hl7Message);
		}catch(HibernateException e){
			handleException(e, "update");
		}
	}

}
