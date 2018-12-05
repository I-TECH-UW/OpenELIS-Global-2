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
package us.mn.state.health.lims.common.provider.validation;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.DateValidator;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;

public class DateValidationProvider extends BaseValidationProvider {

	public static final String PAST = "past";
	public static final String FUTURE = "future";

	public DateValidationProvider() {
		super();
	}

	public DateValidationProvider(AjaxServlet ajaxServlet) {
		this.ajaxServlet = ajaxServlet;
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get id from request
		String dateString = request.getParameter("date");
		String relative = request.getParameter("relativeToNow");
		String formField = request.getParameter("field");

		String result = INVALID;

		if (DateUtil.yearSpecified(dateString)) {
			dateString = DateUtil.normalizeAmbiguousDate(dateString);
			Date date = getDate(dateString);
			result = validateDate(date, relative);
		}
		ajaxServlet.sendData(formField, result, request, response);
	}

	public Date getDate(String date) {
		Locale locale = SystemConfiguration.getInstance().getDateLocale();
		return DateValidator.getInstance().validate(date, locale);
	}

	public String validateDate(Date date, String relative) {
		String result = VALID;

		if (date == null) {
			result = INVALID;
		} else if (!GenericValidator.isBlankOrNull(relative)) {

			Calendar calendar = new GregorianCalendar();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date today = calendar.getTime();
			
			//time insensitive compare, only year month day
			Calendar calendarDate = new GregorianCalendar();
			calendarDate.setTime(date);
			calendarDate.set(Calendar.HOUR_OF_DAY, 0);
			calendarDate.set(Calendar.MINUTE, 0);
			calendarDate.set(Calendar.SECOND, 0);
			calendarDate.set(Calendar.MILLISECOND, 0);
			date = calendarDate.getTime();
			
			int dateDiff = date.compareTo(today);

			if (relative.equalsIgnoreCase(PAST) && dateDiff > 0) {
				result = IActionConstants.INVALID_TO_LARGE;
			} else if (relative.equalsIgnoreCase(FUTURE) && dateDiff < 0) {
				result = IActionConstants.INVALID_TO_SMALL;
			}
		}

		return result;
	}

}
