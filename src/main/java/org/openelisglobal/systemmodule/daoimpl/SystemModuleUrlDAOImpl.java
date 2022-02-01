package org.openelisglobal.systemmodule.daoimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.URLUtil;
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
        String pathNoSuffix = URLUtil.getReourcePathFromRequest(request);

        List<SystemModuleUrl> sysModUrls = getByUrlPath(pathNoSuffix);

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
        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemModuleUrl getByUrlPath()", e);
        }

        return list;

    }

    @Override
    public SystemModuleUrl getByModuleAndUrl(String moduleId, String urlPath) {
        SystemModuleUrl moduleUrl = null;
        if (GenericValidator.isBlankOrNull(moduleId)) {
            return moduleUrl;
        }
        try {
            String sql = "From SystemModuleUrl smu where smu.urlPath = :urlPath AND smu.systemModule = :systemModuleId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("urlPath", urlPath);
            query.setInteger("systemModuleId", Integer.parseInt(moduleId));
            moduleUrl = (SystemModuleUrl) query.getResultStream().findFirst().orElse(null);
        } catch (RuntimeException e) {
            LogEvent.logDebug(e);
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SystemModuleUrl getByUrlPath()", e);
        }

        return moduleUrl;
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
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logDebug(e);
//			LogEvent.logError("SystemModuleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SystemModule insertData()", e);
//		}
//
//	return true;
//}

}
