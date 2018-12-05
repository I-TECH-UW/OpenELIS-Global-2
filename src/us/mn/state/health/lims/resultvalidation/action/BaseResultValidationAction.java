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
*                 I-TECH, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.resultvalidation.action;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.util.StringUtil;


public abstract class BaseResultValidationAction extends BaseAction {

	private String titleKey = "";
	@Override
	protected String getPageTitleKey() {
		return titleKey;
	}

	@Override
	protected String getPageSubtitleKey() {
		return titleKey;
	}
	
	protected String getMessageForKey(String messageKey) throws Exception {
		return StringUtil.getMessageForKey("validation.title", messageKey);
	}
	
	protected void setRequestType(String section) {
		if(!GenericValidator.isBlankOrNull(section)){
			titleKey = section;
		}
	}	

}
