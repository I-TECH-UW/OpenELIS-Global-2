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
package us.mn.state.health.lims.siteinformation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.services.PhoneNumberService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationSideEffects;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDomainDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformationDomain;

public class SiteInformationUpdateAction extends BaseAction {
	private static final SiteInformationDomain SITE_IDENTITY_DOMAIN;
	private static final SiteInformationDomain RESULT_CONFIG_DOMAIN;

	private boolean isNew = false;
	private String addKey = null;
	private String editKey = null;


	static{
		SITE_IDENTITY_DOMAIN = new SiteInformationDomainDAOImpl().getByName("siteIdentity");
		RESULT_CONFIG_DOMAIN = new SiteInformationDomainDAOImpl().getByName("resultConfiguration");
	}

    @SuppressWarnings("unchecked")
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);
		isNew = id == null || id.equals("0");

		String forward;

		BaseActionForm dynaForm = (BaseActionForm) form;

		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

        String tag = dynaForm.getString( "tag" );

        //N.B. The reason for this branch is that localization does not actually update site information, it updates the
        //localization table
        if("localization".equals( tag )){
            String localizationId = dynaForm.getString( "value" );
            forward = validateAndUpdateLocalization(request,
                                                    localizationId,
                                                    dynaForm.getString( "englishValue" ),
                                                    dynaForm.getString( "frenchValue" ));
        } else{
            forward = validateAndUpdateSiteInformation( request, dynaForm, isNew );
        }
		//makes the changes take effect immediately
		ConfigurationProperties.forceReload();
		
		return FWD_FAIL.equals(forward) ? mapping.findForward(forward) : getForward(mapping.findForward(forward), id, start, direction);

	}

    private String validateAndUpdateLocalization( HttpServletRequest request, String localizationId, String english, String french ){
        LocalizationService localizationService = new LocalizationService(localizationId);
        localizationService.setCurrentUserId(currentUserId);

        String forward = FWD_SUCCESS_INSERT;
        if( localizationService.updateLocalizationIfNeeded(english, french )){

            ActionMessages errors;
            Transaction tx = HibernateUtil.getSession().beginTransaction();
            try{
                new LocalizationDAOImpl().updateData( localizationService.getLocalization() );
                tx.commit();
            }catch (LIMSRuntimeException lre) {
                tx.rollback();
                errors = new ActionMessages();
                ActionError error = new ActionError("errors.UpdateException", null, null);
                errors.add(ActionMessages.GLOBAL_MESSAGE, error);
                saveErrors(request, errors);
                request.setAttribute(Globals.ERROR_KEY, errors);

                forward = FWD_FAIL;

            } finally {
                HibernateUtil.closeSession();
            }

        }

        return forward;
    }

    public String validateAndUpdateSiteInformation( HttpServletRequest request,
			BaseActionForm dynaForm, boolean newSiteInformation) {

		String name = dynaForm.getString("paramName");
        String value = dynaForm.getString( "value" );
		ActionMessages errors = new ActionMessages();

		if( !isValid(request, name, value, errors)){
			return FWD_FAIL;
		}

        String forward = FWD_SUCCESS_INSERT;
		SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
		SiteInformation siteInformation = new SiteInformation();
		
		if( newSiteInformation){
			siteInformation.setName(name);
			siteInformation.setDescription(dynaForm.getString("description"));
			siteInformation.setValueType("text");
			siteInformation.setEncrypted((Boolean) dynaForm.get("encrypted"));
			siteInformation.setDomain(SITE_IDENTITY_DOMAIN);
		}else{
			siteInformation.setId(request.getParameter(ID));
			siteInformationDAO.getData(siteInformation);
		}
		
		siteInformation.setValue( value );
		siteInformation.setSysUserId(currentUserId);
		
		String domainName = dynaForm.getString("siteInfoDomainName");

		if( "SiteInformation".equals(domainName) ){
			siteInformation.setDomain(SITE_IDENTITY_DOMAIN);
			addKey = "siteInformation.add.title";
			editKey = "siteInformation.edit.tile";
		}else if( "ResultConfiguration".equals(domainName)){
			siteInformation.setDomain(RESULT_CONFIG_DOMAIN);
			addKey = "resultConfiguration.add.title";
			editKey = "resultConfiguration.edit.tile";
		}
		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			

			if( newSiteInformation){
				siteInformationDAO.insertData(siteInformation);
			}else{
				siteInformationDAO.updateData(siteInformation);
			}

			new ConfigurationSideEffects().siteInformationChanged(siteInformation);
			
			tx.commit();

			forward = FWD_SUCCESS_INSERT;
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			errors = new ActionMessages();
			ActionError error;
			if (lre.getException() instanceof StaleObjectStateException ) {

				error = new ActionError("errors.OptimisticLockException", null, null);

			} else {
				error = new ActionError("errors.UpdateException", null, null);
			}

			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);

			// disable previous and next
			request.setAttribute(PREVIOUS_DISABLED, TRUE);
			request.setAttribute(NEXT_DISABLED, TRUE);
			forward = FWD_FAIL;

		} finally {
			HibernateUtil.closeSession();
		}

		return forward;
	}

	private boolean isValid(HttpServletRequest request, String name, String value, ActionMessages errors) {
		if (GenericValidator.isBlankOrNull(name)) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("error.SiteInformation.name.required"));
            request.setAttribute(Globals.ERROR_KEY, errors);
			saveErrors(request, errors);

            return false;
        }

		if( "phone format".equals( name ) && !PhoneNumberService.validatePhoneFormat(value) ){
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("error.SiteInformation.phone.format"));
            request.setAttribute(Globals.ERROR_KEY, errors);
            saveErrors( request, errors );

            return false;
        }

		return true;
	}


	protected String getPageTitleKey() {
		return isNew ? addKey : editKey;
	}

	protected String getPageSubtitleKey() {
		return isNew ? addKey : editKey;
	}
}