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
package us.mn.state.health.lims.common.provider.validation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.services.PhoneNumberService;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.StringUtil;

/**
 * The QuickEntryAccessionNumberValidationProvider class is used to validate,
 * via AJAX.
 * 
 */
public class PhoneNumberValidationProvider extends BaseValidationProvider {

	public PhoneNumberValidationProvider() {
		super();
	}

	public PhoneNumberValidationProvider( AjaxServlet ajaxServlet ) {
		this.ajaxServlet = ajaxServlet;
	}

	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String field = request.getParameter("fieldId");
		String phoneNumber = request.getParameter("value");

        PhoneNumberService numberService = new PhoneNumberService();
        boolean valid = numberService.validatePhoneNumber( phoneNumber );

		String returnData = VALID;
        if( !valid){
			returnData = StringUtil.getMessageForKey("phone.number.format.error", PhoneNumberService.getPhoneFormat());
		}

		response.setCharacterEncoding("UTF-8");
		ajaxServlet.sendData(field, returnData, request, response);
	}
}
