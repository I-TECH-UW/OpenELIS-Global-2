/**
\ * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package org.openelisglobal.analyzerimport.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.openelisglobal.analyzerimport.dao.AnalyzerTestMappingDAO;
import org.openelisglobal.analyzerimport.valueholder.AnalyzerTestMapping;
import org.openelisglobal.analyzerimport.valueholder.AnalyzerTestMappingPK;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AnalyzerTestMappingDAOImpl extends BaseDAOImpl<AnalyzerTestMapping, AnalyzerTestMappingPK>
        implements AnalyzerTestMappingDAO {

    public AnalyzerTestMappingDAOImpl() {
        super(AnalyzerTestMapping.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnalyzerTestMapping> getAllForAnalyzer(String analyzerId) {
        List<AnalyzerTestMapping> list;
        try {
            String sql = "from AnalyzerTestMapping a where a.compoundId.analyzerId = :analyzerId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("analyzerId", Integer.parseInt(analyzerId));
            list = query.list();
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in AnalyzerTestMappingDAOImpl getAllForAnalyzer()", e);
        }

        return list;
    }

//	@Override
//	public void deleteData(List<AnalyzerTestMapping> analyzerTestMappingList, String currentUserId)
//			throws LIMSRuntimeException {
//
//		try {
//			for (AnalyzerTestMapping analyzerTestMapping : analyzerTestMappingList) {
//				analyzerTestMapping = readAnalyzerTestMapping(analyzerTestMapping.getCompoundId());
//
//				auditDAO.saveHistory(new Analysis(), analyzerTestMapping, currentUserId,
//						IActionConstants.AUDIT_TRAIL_DELETE, "ANALYZER_TEST_MAP");
//
//				entityManager.unwrap(Session.class).delete(analyzerTestMapping);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerTestMappingDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerTestMappingDAOImpl deleteData()", e);
//		}
//	}

//	private AnalyzerTestMapping readAnalyzerTestMapping(AnalyzerTestMappingPK mappingPK) {
//		AnalyzerTestMapping mapping = null;
//		try {
//			mapping = entityManager.unwrap(Session.class).get(AnalyzerTestMapping.class, mappingPK);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerTestMappingDAOImpl", "readAnalyzerTestMapping()", e.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerTestMappingDAOImpl readAnalyzerTestMapping()", e);
//		}
//
//		return mapping;
//	}

//	@Override
//
//	public List<AnalyzerTestMapping> getAllAnalyzerTestMappings() throws LIMSRuntimeException {
//		List<AnalyzerTestMapping> list = null;
//		try {
//			String sql = "from AnalyzerTestMapping";
//			Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerTestMappingDAOImpl", "getAnalyzerTestMappings()", e.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerTestMapping getAllAnalyzerTestMappings()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public void insertData(AnalyzerTestMapping analyzerTestMapping, String currentUserId) throws LIMSRuntimeException {
//		try {
//			entityManager.unwrap(Session.class).save(analyzerTestMapping);
//
//			auditDAO.saveNewHistory(analyzerTestMapping, currentUserId, "ANALYZER_TEST_MAP");
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerTestMappingDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerTestMappingDAOImpl insertData()", e);
//		}
//	}

//	@Override
//	public void updateMapping(AnalyzerTestMapping analyzerTestNameMapping, String currentUserId)
//			throws LIMSRuntimeException {
//
//		try {
//			entityManager.unwrap(Session.class).merge(analyzerTestNameMapping);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove
//			// old(analyzerTestNameMapping);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(analyzerTestNameMapping);
//		} catch (RuntimeException e) {
//			LogEvent.logError("AnalyzerTestMappingDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in AnalyzerTestMapping updateData()", e);
//		}
//	}

}
