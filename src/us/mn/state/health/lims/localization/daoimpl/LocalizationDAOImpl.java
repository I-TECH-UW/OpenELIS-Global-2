/*
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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package us.mn.state.health.lims.localization.daoimpl;

import org.hibernate.HibernateException;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.dao.LocalizationDAO;
import us.mn.state.health.lims.localization.valueholder.Localization;

/**
 */
public class LocalizationDAOImpl extends BaseDAOImpl implements LocalizationDAO{
    @Override
    public Localization getLocalizationById( String id ) throws LIMSRuntimeException{
        try {
            Localization localization = (Localization) HibernateUtil.getSession().get( Localization.class, id );
            closeSession();
            return localization;
        } catch (HibernateException e) {
            handleException( e, "getLocalizationById" );
        }
        return null;
    }

    @Override
    public void updateData( Localization localization ) throws LIMSRuntimeException{
        Localization oldData = readLocalization( localization.getId() );

        try {
            AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
            auditDAO.saveHistory(localization,oldData,localization.getSysUserId(),IActionConstants.AUDIT_TRAIL_UPDATE,"LOCALIZATION");
        }  catch (Exception e) {
            handleException( e, "Error in Localization AuditTrail updateData()" );
        }


        try {
            HibernateUtil.getSession().merge(localization);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
            HibernateUtil.getSession().evict(localization);
            HibernateUtil.getSession().refresh(localization);
        } catch (HibernateException e) {
            handleException( e, "updateData" );
        }
    }

    @Override
    public void insert(Localization localization) throws LIMSRuntimeException {
        try {
            String id = (String) HibernateUtil.getSession().save(localization);
            localization.setId(id);

            new AuditTrailDAOImpl().saveNewHistory(localization, localization.getSysUserId(), "LOCALIZATION");

            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (Exception e) {
            handleException(e, "insert");
        }
    }


    public Localization readLocalization(String idString) {
        try {
            Localization localization = (Localization) HibernateUtil.getSession().get(Localization.class,  idString);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
            return localization;
        } catch (Exception e) {
            handleException( e, "readLocalization" );
        }

        return null;
    }

}
