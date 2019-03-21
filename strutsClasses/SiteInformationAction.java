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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class SiteInformationAction extends BaseAction {

	private boolean isNew = false;
	private String addKey = null;
	private String editKey = null;
	private static final String ACCESSION_NUMBER_PREFIX = "Accession number prefix";
	
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter(ID);

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, TRUE);
		request.setAttribute(NEXT_DISABLED, TRUE);

		DynaActionForm dynaForm = (DynaActionForm) form;

		dynaForm.initialize(mapping);

		isNew = id == null || "0".equals(id);

		if (!isNew) {
			SiteInformationDAO siteInformationDAO = new SiteInformationDAOImpl();
			
			SiteInformation siteInformation = new SiteInformation();
			siteInformation.setId(id);
			siteInformationDAO.getData(siteInformation);
			request.setAttribute(ID, siteInformation.getId());


			PropertyUtils.setProperty(dynaForm, "paramName", siteInformation.getName());
			PropertyUtils.setProperty(dynaForm, "description", getInstruction( siteInformation ) );
			PropertyUtils.setProperty(dynaForm, "value", siteInformation.getValue());
            setLocalizationValues( dynaForm, siteInformation);
			PropertyUtils.setProperty(dynaForm, "encrypted", siteInformation.isEncrypted());
            PropertyUtils.setProperty(dynaForm, "valueType", siteInformation.getValueType());
            PropertyUtils.setProperty(dynaForm, "editable", isEditable(siteInformation));
            PropertyUtils.setProperty(dynaForm, "tag", siteInformation.getTag());

			if( "dictionary".equals(siteInformation.getValueType())){
				List<String> dictionaryValues = new ArrayList<String>();
				
				List<Dictionary> dictionaries = new DictionaryDAOImpl().getDictionaryEntriesByCategoryId(siteInformation.getDictionaryCategoryId());
				
				for( Dictionary dictionary : dictionaries){
					dictionaryValues.add(dictionary.getDictEntry());
				}
				
				PropertyUtils.setProperty(dynaForm, "dictionaryValues", dictionaryValues);
			}
			
			String domainName = ((DynaActionForm)form).getString("siteInfoDomainName");
			
			if( "SiteInformation".equals(domainName) ){
				addKey = "siteInformation.add.title";
				editKey = "siteInformation.edit.title";
			}else if( "ResultConfiguration".equals(domainName)){
				addKey = "resultConfiguration.add.title";
				editKey = "resultConfiguration.edit.title";
			}

		}
		return mapping.findForward(forward);
	}

    private String getInstruction( SiteInformation siteInformation ){
        String instruction = StringUtil.getMessageForKey( siteInformation.getInstructionKey() );
        if( GenericValidator.isBlankOrNull( instruction ) ){
            instruction = StringUtil.getMessageForKey( siteInformation.getDescriptionKey() );
        }

        return GenericValidator.isBlankOrNull( instruction ) ? siteInformation.getDescription() : instruction;
    }

    private void setLocalizationValues( DynaActionForm dynaForm, SiteInformation siteInformation ) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException{
        if( "localization".equals( siteInformation.getTag() )){
            LocalizationService localizationService = new LocalizationService( siteInformation.getValue() );
            Localization localization = localizationService.getLocalization();
            PropertyUtils.setProperty(dynaForm, "englishValue", localization.getEnglish());
            PropertyUtils.setProperty(dynaForm, "frenchValue", localization.getFrench());

        }
    }

    private Boolean isEditable(SiteInformation siteInformation){
		if(ACCESSION_NUMBER_PREFIX.endsWith(siteInformation.getName())){
			return new SampleDAOImpl().getTotalCount("Sample", Sample.class) == 0;
		}
		return Boolean.TRUE;
	}

	protected String getPageTitleKey() {
		return isNew ? addKey : editKey;
	}

	protected String getPageSubtitleKey() {
		return isNew ? addKey : editKey;
	}

}
