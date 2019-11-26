package org.openelisglobal.systemmodule.daoimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.systemmodule.dao.SystemModuleUrlDAO;
import org.openelisglobal.systemmodule.valueholder.SystemModuleUrl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SystemModuleUrlDAOImpl extends BaseDAOImpl<SystemModuleUrl, String> implements SystemModuleUrlDAO {

    public SystemModuleUrlDAOImpl() {
        super(SystemModuleUrl.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemModuleUrl> getByRequest(HttpServletRequest request) {
        String urlPath = request.getRequestURI();
        urlPath = urlPath.substring(request.getContextPath().length());
        urlPath = urlPath.indexOf('.') < 0 ? urlPath : urlPath.substring(0, urlPath.indexOf('.'));

        List<SystemModuleUrl> sysModUrls = getByUrlPath(urlPath);

        return sysModUrls;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemModuleUrl> getByUrlPath(String urlPath) {
        List<SystemModuleUrl> list;
        try {
            String sql = "From SystemModuleUrl smu where smu.urlPath = :urlPath";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("urlPath", urlPath);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemModuleUrl getByUrlPath()", e);
        }

        return list;

    }

//	@Override
//	public boolean insertData(SystemModuleUrl systemModuleUrl) throws LIMSRuntimeException {
//
//		try {
//
//			String id = (String) entityManager.unwrap(Session.class).save(systemModuleUrl);
//			systemModuleUrl.setId(id);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// closeSession(); // CSL remove old
//		} catch (Exception e) {
//			// bugzilla 2154
//			e.printStackTrace();
//			LogEvent.logError("SystemModuleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemModule insertData()", e);
//		}
//
//	return true;
//}

}
