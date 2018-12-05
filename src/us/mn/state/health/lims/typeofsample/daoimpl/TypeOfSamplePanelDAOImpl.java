/**
 * The contents of this file are subject to the Mozilla Public License
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
package us.mn.state.health.lims.typeofsample.daoimpl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSamplePanelDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

public class TypeOfSamplePanelDAOImpl extends BaseDAOImpl implements TypeOfSamplePanelDAO {

	public void deleteData(String[] typeOfSamplesPanelIDs, String currentUserId) throws LIMSRuntimeException {

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (String id : typeOfSamplesPanelIDs) {
				TypeOfSamplePanel data = (TypeOfSamplePanel) readTypeOfSamplePanel(id);
				
				auditDAO.saveHistory(new TypeOfSamplePanel(), data, currentUserId, IActionConstants.AUDIT_TRAIL_DELETE, "SAMPLETYPE_PANEL");
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}

		} catch (Exception e) {
			LogEvent.logError("TypeOfSampleDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSampleTest deleteData()", e);
		}
	}

	public boolean insertData(TypeOfSamplePanel typeOfSamplePanel) throws LIMSRuntimeException {

		try {

			String id = (String)HibernateUtil.getSession().save(typeOfSamplePanel);

			typeOfSamplePanel.setId(id);
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveNewHistory(typeOfSamplePanel, typeOfSamplePanel.getSysUserId(), "SAMPLETYPE_PANEL");
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("TypeOfSamplePanelDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSamplePanel insertData()", e);
		}

		return true;
	}

	public void getData(TypeOfSamplePanel typeOfSamplePanel) throws LIMSRuntimeException {

		try {
			TypeOfSamplePanel tos = (TypeOfSamplePanel) HibernateUtil.getSession().get(TypeOfSamplePanel.class,
					typeOfSamplePanel.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (tos != null) {
				PropertyUtils.copyProperties(typeOfSamplePanel, tos);
			} else {
				typeOfSamplePanel.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("TypeOfSamplePanelDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSamplePanel getData()", e);
		}
	}

	public List getAllTypeOfSamplePanels() throws LIMSRuntimeException {

		List list = new Vector();
		try {
			String sql = "from TypeOfSamplePanel";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			// query.setMaxResults(10);
			// query.setFirstResult(3);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TypeOfSamplePanelDAOImpl", "getAllTypeOfSamples()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSamplePanel getAllTypeOfSamplePanels()", e);
		}

		return list;
	}

	public List getPageOfTypeOfSamplePanel(int startingRecNo) throws LIMSRuntimeException {

		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + DEFAULT_PAGE_SIZE + 1;

			String sql = "from TypeOfSamplePanel t order by t.typeOfSampleId, t.panelId";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("TypeOfSamplePanelDAOImpl", "getPageOfTypeOfSamplePanels()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSamplePanel getPageOfTypeOfSamples()", e);
		}

		return list;
	}

	public TypeOfSamplePanel readTypeOfSamplePanel(String idString) {
		TypeOfSamplePanel tos = null;
		try {
			tos = (TypeOfSamplePanel) HibernateUtil.getSession().get(TypeOfSamplePanel.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("TypeOfSamplePanelDAOImpl", "readTypeOfSample()", e.toString());
			throw new LIMSRuntimeException("Error in TypeOfSamplePanel readTypeOfSample()", e);
		}

		return tos;
	}

	public List getNextTypeOfSamplePanelRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "TypeOfSamplePanel", TypeOfSamplePanel.class);
	}

	public List getPreviousTypeOfSamplePanelRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "TypeOfSamplePanel", TypeOfSampleTest.class);
	}

	public Integer getTotalTypeOfSamplePanelCount() throws LIMSRuntimeException {
		return getTotalCount("TypeOfSamplePanel", TypeOfSamplePanel.class);
	}

	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		int rrn = 0;
		try {
			// bugzilla 1908 cannot use named query for postgres because of
			// oracle ROWNUM
			// instead get the list in this sortorder and determine the index of
			// record with id = currentId
			String sql = "select tos.id from TypeOfSampleTest tos " + " order by tos.domain, tos.description";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
					.setMaxResults(2).list();

		} catch (Exception e) {
			LogEvent.logError("TypeOfSampleDAOImpl", "getNextRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();

		int rrn = 0;
		try {
			// bugzilla 1908 cannot use named query for postgres because of
			// oracle ROWNUM
			// instead get the list in this sortorder and determine the index of
			// record with id = currentId
			String sql = "select tos.id from TypeOfSampleTest tos " + " order by tos.domain desc, tos.description desc";

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
					.setMaxResults(2).list();

		} catch (Exception e) {
			LogEvent.logError("TypeOfSampleDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TypeOfSamplePanel> getTypeOfSamplePanelsForSampleType(String sampleType) {
			List<TypeOfSamplePanel> list;

			String sql = "from TypeOfSamplePanel tp where tp.typeOfSampleId = :sampleId order by tp.panelId";

			try {
				Query query = HibernateUtil.getSession().createQuery(sql);
				query.setInteger("sampleId", Integer.parseInt(sampleType));
				list = query.list();
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			} catch (Exception e) {
				LogEvent.logError("TypeOfSamplePanelDAOImpl", "getTypeOfSamplePanelsForSampleType", e.toString());
				throw new LIMSRuntimeException("Error in TypeOfSamplePanelDAOImpl getTypeOfSamplePanelsForSampleType", e);
			}

			return list;
		}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeOfSamplePanel> getTypeOfSamplePanelsForPanel(String panelId) throws LIMSRuntimeException{
		String sql = "from TypeOfSamplePanel tosp where tosp.panelId = :panelId";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("panelId", Integer.parseInt(panelId));
			List<TypeOfSamplePanel> typeOfSamplePanels = query.list();
			closeSession();
			return typeOfSamplePanels;
		}catch(HibernateException e){
			handleException(e, "getTypeOfSamplePanelsForPanel");
		}
		
		return null;
	}

}