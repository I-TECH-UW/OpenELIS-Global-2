package us.mn.state.health.lims.observationhistorytype.daoImpl;

import java.util.List;

import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.GenericDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;

public class ObservationHistoryTypeDAOImpl extends GenericDAOImpl<String, ObservationHistoryType> implements ObservationHistoryTypeDAO {

	public ObservationHistoryTypeDAOImpl() {
		super(ObservationHistoryType.class, "OBSERVATION_HISTORY_TYPE");
	}

	public void delete(List<ObservationHistoryType> entities) throws LIMSRuntimeException {
		super.delete(entities, new ObservationHistoryType());
	}

	@SuppressWarnings("unchecked")
	public ObservationHistoryType getByName(String name) throws LIMSRuntimeException {
		List<ObservationHistoryType> historyTypeList;

		try {
			String sql = "from ObservationHistoryType oht where oht.typeName = :name";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("name", name);
			historyTypeList = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return historyTypeList.size() > 0 ? historyTypeList.get(0) : null;

		} catch (Exception e) {
			LogEvent.logError("ObservationHistoryTypeDAOImpl ", "getByName()", e.toString());
			throw new LIMSRuntimeException("Error in ObservationHistoryTypeDAOImpl  getByName()", e);
		}
	}
}
