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
package us.mn.state.health.lims.testconfiguration.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public class SampleTypeRenameAction extends BaseAction {

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BaseActionForm dynaForm = (BaseActionForm) form;

		dynaForm.initialize(mapping);

		List<IdValuePair> sampleTypeList = DisplayListService.getList( DisplayListService.ListType.SAMPLE_TYPE_ACTIVE);
		sampleTypeList.addAll(DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE));
		IdValuePair.sortByValue(sampleTypeList);

		PropertyUtils.setProperty( form, "sampleTypeList", sampleTypeList );

		if(false) {
			List<TypeOfSample> typeOfSampleList = new TypeOfSampleDAOImpl().getAllTypeOfSamples();
			for (TypeOfSample type : typeOfSampleList) {
				SystemConfiguration.getInstance().setDefaultLocale("fr-FR");
				String nameFrench = StringUtil.getMessageForKey(type.getNameKey());
				nameFrench = nameFrench == null ? type.getDescription() : nameFrench;
				SystemConfiguration.getInstance().setDefaultLocale("en_US");
				String nameEnglish = StringUtil.getMessageForKey(type.getNameKey());
				nameEnglish = nameEnglish == null ? type.getDescription() : nameEnglish;
				System.out.println("INSERT INTO localization(  id, description, english, french, lastupdated)");
				System.out.println("VALUES(nextval('localization_seq'), 'sampleType name', '" + nameEnglish + "', '" + nameFrench + "', now());");
				System.out.println("UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = '" + type.getDescription() + "';");
			}
		}

		return mapping.findForward(FWD_SUCCESS);
	}

	protected String getPageTitleKey() {
		return "";
	}

	protected String getPageSubtitleKey() {
		return "";
	}

}
