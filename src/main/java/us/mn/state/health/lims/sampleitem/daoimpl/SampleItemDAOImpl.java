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
package us.mn.state.health.lims.sampleitem.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sourceofsample.valueholder.SourceOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

/**
 * @author diane benz
 */
public class SampleItemDAOImpl extends BaseDAOImpl implements SampleItemDAO {

	public void deleteData(List<SampleItem> sampleItems) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < sampleItems.size(); i++) {
				SampleItem data = (SampleItem)sampleItems.get(i);

				SampleItem oldData = (SampleItem)readSampleItem(data.getId());
				SampleItem newData = new SampleItem();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "SAMPLE_ITEM";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			
			LogEvent.logError("SampleItemDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < sampleItems.size(); i++) {
				SampleItem data = (SampleItem) sampleItems.get(i);

				data = (SampleItem)readSampleItem(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			LogEvent.logError("SampleItemDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem deleteData()", e);
		}
	}

	public boolean insertData(SampleItem sampleItem) throws LIMSRuntimeException {
		if( sampleItem == null){
			return false;
		}

		try {
			String id = (String)HibernateUtil.getSession().save(sampleItem);
			sampleItem.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleItem.getSysUserId();
			String tableName = "SAMPLE_ITEM";
			auditDAO.saveNewHistory(sampleItem,sysUserId,tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("SampleItemDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem insertData()", e);
		}

		return true;
	}

	public void updateData(SampleItem sampleItem) throws LIMSRuntimeException {

		SampleItem oldData = (SampleItem)readSampleItem(sampleItem.getId());
		SampleItem newData = sampleItem;

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleItem.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "SAMPLE_ITEM";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			LogEvent.logError("SampleItemDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(sampleItem);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(sampleItem);
			HibernateUtil.getSession().refresh(sampleItem);
		} catch (Exception e) {
			LogEvent.logError("SampleItemDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem updateData()", e);
		}
	}

	public void getData(SampleItem sampleItem) throws LIMSRuntimeException {
		try {
			SampleItem sampleIt = (SampleItem)HibernateUtil.getSession().get(SampleItem.class, sampleItem.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (sampleIt != null) {
				PropertyUtils.copyProperties(sampleItem, sampleIt);
			} else {
				sampleItem.setId(null);
			}
		} catch (Exception e) {
			LogEvent.logError("SampleItemDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem getData()", e);
		}
	}
	
	@Override
	public SampleItem getData(String sampleItemId) throws LIMSRuntimeException {
		try {
			SampleItem sampleItem = (SampleItem)HibernateUtil.getSession().get(SampleItem.class, sampleItemId);
			closeSession();
			return sampleItem;
		} catch (Exception e) {
			handleException(e, "getData");
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SampleItem> getAllSampleItems() throws LIMSRuntimeException {
		List<SampleItem> list;
		try {
			String sql = "from SampleItem";
    		org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("SampleItemDAOImpl","getAllSampleItems()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem getAllSampleItems()", e);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SampleItem> getPageOfSampleItems(int startingRecNo) throws LIMSRuntimeException {
		List<SampleItem> list;
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from SampleItem s order by s.id";
    		org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			
			LogEvent.logError("SampleItemDAOImpl","getPageOfSampleItems()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem getPageOfSampleItems()", e);
		}

		return list;
	}

	public SampleItem readSampleItem(String idString) {
		SampleItem samp = null;
		try {
			samp = (SampleItem)HibernateUtil.getSession().get(SampleItem.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			
			LogEvent.logError("SampleItemDAOImpl","readSampleItem()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem readSampleItem()", e);
		}

		return samp;

	}

	@SuppressWarnings("unchecked")
	public List<SampleItem> getNextSampleItemRecord(String id)
			throws LIMSRuntimeException {

		return getNextRecord(id, "SampleItem", SampleItem.class);

	}

	@SuppressWarnings("unchecked")
	public List<SampleItem> getPreviousSampleItemRecord(String id)
			throws LIMSRuntimeException {

		return getPreviousRecord(id, "SampleItem", SampleItem.class);
	}

	public void getDataBySample(SampleItem sampleItem) throws LIMSRuntimeException {
		// Use an expression to read in the Sample_Item by SAMP_ID
		try {
		String sql = "from SampleItem si where samp_id = :param";
		Query query = HibernateUtil.getSession().createQuery(sql);
	
		query.setInteger("param", Integer.parseInt(sampleItem.getSample().getId()));
		@SuppressWarnings("unchecked")
		List<SampleItem> list = query.list();
		HibernateUtil.getSession().flush();
		HibernateUtil.getSession().clear();
		SampleItem si = null;
		if ( !list.isEmpty() ) {
			si = list.get(0);

			TypeOfSample tos = null;
			if ( si.getTypeOfSampleId() != null ) {
				tos = (TypeOfSample)HibernateUtil.getSession().get(TypeOfSample.class, si.getTypeOfSampleId());
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
				si.setTypeOfSample(tos);
			}
			SourceOfSample sos = null;
			if ( si.getSourceOfSampleId() != null ) {
				sos = (SourceOfSample)HibernateUtil.getSession().get(SourceOfSample.class, si.getSourceOfSampleId());
				si.setSourceOfSample(sos);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
			PropertyUtils.copyProperties(sampleItem, si);
		}
		}catch (Exception e) {
			LogEvent.logError("SampleItemDAOImpl","getDataBySample()",e.toString());
			throw new LIMSRuntimeException("Error in SampleItem getDataBySample()", e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<SampleItem> getSampleItemsBySampleId(String id) throws LIMSRuntimeException {

		try{
			String sql = "from SampleItem sampleItem where sampleItem.sample.id = :sampleId order by sampleItem.sortOrder";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(id));
			List<SampleItem> list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return list;

		}catch(HibernateException he){
			LogEvent.logError("SampleItemDAOImpl","getSampleItemsBySampleId()",he.toString());
			throw new LIMSRuntimeException("Error in SampleItem getSampleItemsBySampleId()", he);
		}

	}

    /**
     * @see us.mn.state.health.lims.sampleitem.dao.SampleItemDAO#getSampleItemsBySampleIdAndType(java.lang.String, us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample)
     */
    @SuppressWarnings("unchecked")
    public List<SampleItem> getSampleItemsBySampleIdAndType(String sampleId, TypeOfSample typeOfSample) {
        try{
            String sql = "from SampleItem si where si.sample.id = :sampleId and si.typeOfSample.id = :typeOfSampleId";
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("sampleId", Integer.parseInt(sampleId));
            query.setInteger("typeOfSampleId", Integer.parseInt(typeOfSample.getId()));
            List<SampleItem> list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            return list;

        }catch(HibernateException he){
            LogEvent.logError("SampleItemDAOImpl","getSampleItemsBySampleIdAndType()",he.toString());
            throw new LIMSRuntimeException("Error in SampleItem getSampleItemsBySampleId()", he);
        }
   }

	@Override
	public List<SampleItem> getSampleItemsBySampleIdAndStatus(String id, Set<Integer> includedStatusList) throws LIMSRuntimeException {
		if( includedStatusList.isEmpty()){
			return new ArrayList<SampleItem>();
		}
		
		try{
			String sql = "from SampleItem sampleItem where sampleItem.sample.id = :sampleId and sampleItem.statusId in ( :statusIds ) order by sampleItem.sortOrder";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(id));
			query.setParameterList("statusIds", includedStatusList);
			@SuppressWarnings("unchecked")
			List<SampleItem> list = query.list();
			closeSession();

			return list;

		}catch(HibernateException he){
			handleException(he, "getSampleItemsBySampleIdAndStatus");
		}

		return null;
	}

}