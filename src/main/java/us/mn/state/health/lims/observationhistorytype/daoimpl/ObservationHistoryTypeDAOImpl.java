package us.mn.state.health.lims.observationhistorytype.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
 
@Component
@Transactional
public class ObservationHistoryTypeDAOImpl extends BaseDAOImpl<ObservationHistoryType>
		implements ObservationHistoryTypeDAO {

	public ObservationHistoryTypeDAOImpl() {
		super(ObservationHistoryType.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ObservationHistoryType getByName(String name) throws LIMSRuntimeException {
		List<ObservationHistoryType> historyTypeList;

		try {
			String sql = "from ObservationHistoryType oht where oht.typeName = :name";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", name);
			historyTypeList = query.list();
			// HibernateUtil.getSession().flush(); // CSL remove old
			// HibernateUtil.getSession().clear(); // CSL remove old

			return historyTypeList.size() > 0 ? historyTypeList.get(0) : null;

		} catch (Exception e) {
			LogEvent.logError("ObservationHistoryTypeDAOImpl ", "getByName()", e.toString());
			throw new LIMSRuntimeException("Error in ObservationHistoryTypeDAOImpl  getByName()", e);
		}
	}

	/**
	 * Read all entities from the database.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ObservationHistoryType> getAll() throws LIMSRuntimeException {
		List<ObservationHistoryType> entities;
		try {
			String sql = "from ObservationHistoryType";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			entities = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

		return entities;
	}

}
