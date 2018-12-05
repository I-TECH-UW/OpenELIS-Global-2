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
package us.mn.state.health.lims.analyzerimport.daoimpl;

import java.util.List;

import org.hibernate.Query;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyzerimport.dao.AnalyzerTestMappingDAO;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMappingPK;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class AnalyzerTestMappingDAOImpl extends BaseDAOImpl implements AnalyzerTestMappingDAO {

	public void deleteData(List<AnalyzerTestMapping> analyzerTestMappingList, String currentUserId) throws LIMSRuntimeException {

		try {
			for (AnalyzerTestMapping analyzerTestMapping : analyzerTestMappingList) {
				analyzerTestMapping = readAnalyzerTestMapping(analyzerTestMapping.getCompoundId());

				AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
				auditDAO.saveHistory(new Analysis(), analyzerTestMapping, currentUserId, IActionConstants.AUDIT_TRAIL_DELETE,
						"ANALYZER_TEST_MAP");

				HibernateUtil.getSession().delete(analyzerTestMapping);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}

		} catch (Exception e) {
			LogEvent.logError("AnalyzerTestMappingDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerTestMappingDAOImpl deleteData()", e);
		}
	}

	private AnalyzerTestMapping readAnalyzerTestMapping(AnalyzerTestMappingPK mappingPK) {
		AnalyzerTestMapping mapping = null;
		try {
			mapping = (AnalyzerTestMapping) HibernateUtil.getSession().get(AnalyzerTestMapping.class, mappingPK);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("AnalyzerTestMappingDAOImpl", "readAnalyzerTestMapping()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerTestMappingDAOImpl readAnalyzerTestMapping()", e);
		}

		return mapping;
	}

	@SuppressWarnings("unchecked")
	public List<AnalyzerTestMapping> getAllAnalyzerTestMappings() throws LIMSRuntimeException {
		List<AnalyzerTestMapping> list = null;
		try {
			String sql = "from AnalyzerTestMapping";
			Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("AnalyzerTestMappingDAOImpl", "getAnalyzerTestMappings()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerTestMapping getAllAnalyzerTestMappings()", e);
		}

		return list;
	}

	public void insertData(AnalyzerTestMapping analyzerTestMapping, String currentUserId) throws LIMSRuntimeException {
		try {
			HibernateUtil.getSession().save(analyzerTestMapping);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveNewHistory(analyzerTestMapping, currentUserId, "ANALYZER_TEST_MAP");
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("AnalyzerTestMappingDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerTestMappingDAOImpl insertData()", e);
		}
	}

	@Override
	public void updateMapping(AnalyzerTestMapping analyzerTestNameMapping, String currentUserId) throws LIMSRuntimeException {

		try {
			HibernateUtil.getSession().merge(analyzerTestNameMapping);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(analyzerTestNameMapping);
			HibernateUtil.getSession().refresh(analyzerTestNameMapping);
		} catch (Exception e) {
			LogEvent.logError("AnalyzerTestMappingDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerTestMapping updateData()", e);
		}
	}

}
