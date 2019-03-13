package us.mn.state.health.lims.systemmodule.daoimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.systemmodule.dao.SystemModuleUrlDAO;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleUrl;

public class SystemModuleUrlDAOImpl extends BaseDAOImpl implements SystemModuleUrlDAO {

	@Override
	public List<SystemModuleUrl> getByRequest(HttpServletRequest request) {
		String urlPath = request.getRequestURI();
		urlPath = urlPath.substring(request.getContextPath().length());
		urlPath = urlPath.substring(0, urlPath.indexOf('.'));

		List<SystemModuleUrl> sysModUrls = getByUrlPath(urlPath);

		return sysModUrls;
	}

	@Override
	public List<SystemModuleUrl> getByUrlPath(String urlPath) {
		List<SystemModuleUrl> list;
		try {
			String sql = "From SystemModuleUrl smu where smu.urlPath = :urlPath";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("urlPath", urlPath);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			closeSession();
		} catch (Exception e) {
			e.printStackTrace();
			LogEvent.logError("SystemModuleUrlDAOImpl", "getByUrlPath()", e.toString());
			throw new LIMSRuntimeException("Error in SystemModuleUrl getByUrlPath()", e);
		}

		return list;

	}

	@Override
	public boolean insertData(SystemModuleUrl systemModuleUrl) throws LIMSRuntimeException {

		try {

			String id = (String) HibernateUtil.getSession().save(systemModuleUrl);
			systemModuleUrl.setId(id);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			closeSession();
		} catch (Exception e) {
			// bugzilla 2154
			e.printStackTrace();
			LogEvent.logError("SystemModuleDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in SystemModule insertData()", e);
		}

		return true;
	}

}
