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
package org.openelisglobal.patientidentitytype.daoimpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.patientidentitytype.dao.PatientIdentityTypeDAO;
import org.openelisglobal.patientidentitytype.valueholder.PatientIdentityType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PatientIdentityTypeDAOImpl extends BaseDAOImpl<PatientIdentityType, String>
        implements PatientIdentityTypeDAO {

    public PatientIdentityTypeDAOImpl() {
        super(PatientIdentityType.class);
    }

    @SuppressWarnings("unused")
    private static Log log = LogFactory.getLog(PatientIdentityTypeDAOImpl.class);

    @Override
    
    @Transactional(readOnly = true)
    public List<PatientIdentityType> getAllPatientIdenityTypes() throws LIMSRuntimeException {
        List<PatientIdentityType> list = null;
        try {
            String sql = "from PatientIdentityType";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (HibernateException e) {
            handleException(e, "getAllPatientIdenityTypes");
        }

        return list;
    }

//	@Override
//	public void insertData(PatientIdentityType patientIdentityType) throws LIMSRuntimeException {
//		try {
//
//			if (duplicatePatientIdentityTypeExists(patientIdentityType)) {
//				throw new LIMSDuplicateRecordException(
//						"Duplicate record exists for " + patientIdentityType.getIdentityType());
//			}
//
//			String id = (String) entityManager.unwrap(Session.class).save(patientIdentityType);
//			patientIdentityType.setId(id);
//
//			auditDAO.saveNewHistory(patientIdentityType, patientIdentityType.getSysUserId(), "PATIENT_IDENTITY_TYPE");
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (HibernateException e) {
//			handleException(e, "insertData");
//		} catch (LIMSDuplicateRecordException e) {
//			handleException(e, "insertData");
//		}
//	}

    
    @Override
    public boolean duplicatePatientIdentityTypeExists(PatientIdentityType patientIdentityType)
            throws LIMSRuntimeException {
        try {
            String sql = "from PatientIdentityType t where upper(t.identityType) = :identityType";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);

            query.setString("identityType", patientIdentityType.getIdentityType().toUpperCase());

            List<PatientIdentityType> list = query.list();
            // closeSession(); // CSL remove old

            return list.size() > 0;

        } catch (HibernateException e) {
            handleException(e, "duplicatePatientIdentityTypeExists");
        }

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public PatientIdentityType getNamedIdentityType(String name) throws LIMSRuntimeException {
        String sql = "from PatientIdentityType t where t.identityType = :identityType";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("identityType", name);
            PatientIdentityType pit = (PatientIdentityType) query.uniqueResult();
            // closeSession(); // CSL remove old
            return pit;
        } catch (HibernateException e) {
            handleException(e, "getNamedIdentityType");
        }

        return null;
    }
}