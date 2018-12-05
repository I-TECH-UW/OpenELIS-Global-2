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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.patientidentitytype.daoimpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patientidentitytype.dao.PatientIdentityTypeDAO;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;


public class PatientIdentityTypeDAOImpl extends BaseDAOImpl implements PatientIdentityTypeDAO {
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(PatientIdentityTypeDAOImpl.class);

	
	@SuppressWarnings("unchecked")
	public List<PatientIdentityType> getAllPatientIdenityTypes() throws LIMSRuntimeException {
		List<PatientIdentityType> list = null;
		try {			
			String sql = "from PatientIdentityType";
			Query query = HibernateUtil.getSession().createQuery(sql);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (HibernateException e) {
			handleException(e, "getAllPatientIdenityTypes");
		} 

		return list;
	}


	public void insertData(PatientIdentityType patientIdentityType) throws LIMSRuntimeException {
		try {	
			
			if (duplicatePatientIdentityTypeExists(patientIdentityType)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for " + patientIdentityType.getIdentityType());
			}
			
			String id = (String)HibernateUtil.getSession().save(patientIdentityType);
			patientIdentityType.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveNewHistory(patientIdentityType, patientIdentityType.getSysUserId(), "PATIENT_IDENTITY_TYPE");
			
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();					
		} catch (HibernateException e) {
			handleException(e, "insertData");
		}catch ( LIMSDuplicateRecordException e){
			handleException(e, "insertData");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private boolean duplicatePatientIdentityTypeExists(PatientIdentityType patientIdentityType) throws LIMSRuntimeException {
		try {
			String sql = "from PatientIdentityType t where upper(t.identityType) = :identityType";
			Query query = HibernateUtil.getSession().createQuery(sql);

			query.setString("identityType", patientIdentityType.getIdentityType().toUpperCase());

			List<PatientIdentityType> list = query.list();
			closeSession();
			
			return list.size() > 0;

		} catch( HibernateException e){
			handleException(e, "duplicatePatientIdentityTypeExists");
		}		
		
		return false;
	}


	public PatientIdentityType getNamedIdentityType(String name) throws LIMSRuntimeException {
		String sql = "from PatientIdentityType t where t.identityType = :identityType";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("identityType", name);
			PatientIdentityType pit = (PatientIdentityType)query.uniqueResult();
			closeSession();
			return pit;
		}catch( HibernateException e){
			handleException(e, "getNamedIdentityType");
		}
		
		return null;
	}
}