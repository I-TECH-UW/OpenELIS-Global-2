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
 */
package us.mn.state.health.lims.resultlimits.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.resultlimits.dao.ResultLimitDAO;
import us.mn.state.health.lims.resultlimits.daoimpl.ResultLimitDAOImpl;
import us.mn.state.health.lims.resultlimits.form.ResultLimitsLink;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.unitofmeasure.dao.UnitOfMeasureDAO;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

public class ResultLimitsUpdateAction extends BaseAction {

	private boolean isNew = false;
	private static TestDAO testDAO = new TestDAOImpl();

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);
		isNew = id == null || id.equals("0") || id.equalsIgnoreCase("null");

		String forward = FWD_SUCCESS;

		BaseActionForm dynaForm = (BaseActionForm) form;

		String start = (String) request.getParameter("startingRecNo");
		String direction = (String) request.getParameter("direction");


		forward = validateAndUpdateResultLimits(mapping, request, dynaForm, isNew);

		return getForward(mapping.findForward(forward), id, start, direction);

	}

	public String validateAndUpdateResultLimits(ActionMapping mapping, HttpServletRequest request,
			BaseActionForm dynaForm, boolean newLimit) {
		String forward;
		// server-side validation (validation.xml)
		ActionMessages errors = dynaForm.validate(mapping, request);
		// if (errors != null && errors.size() > 0) {
		// saveErrors(request, errors);

		// return mapping.findForward(FWD_FAIL);
		// }


		ResultLimitsLink limitsLink = (ResultLimitsLink) dynaForm.get("limit");
		ResultLimit resultLimit = limitsLink.populateResultLimit(null);
		Test test = getTest( limitsLink);

		//the session.merge is not loading the resultLimit from the DB correctly
		//I don't understand why but this block is the workaround until I do.
		if (!newLimit) {
			ResultLimitDAO resultLimitDAO = new ResultLimitDAOImpl();
			resultLimitDAO.getData(resultLimit);
			limitsLink.populateResultLimit(resultLimit);
		}

		resultLimit.setSysUserId(getSysUserId(request));

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			persistLimit(resultLimit);
			persistTest(test);

			tx.commit();

			forward = FWD_SUCCESS_INSERT;
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			errors = new ActionMessages();
			ActionError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {

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

	private Test getTest(ResultLimitsLink limitsLink) {
		Test test = new Test();
		test.setId(limitsLink.getTestId());
		testDAO.getData(test);
		UnitOfMeasureDAO uofDAO = new UnitOfMeasureDAOImpl();
		UnitOfMeasure uom = new UnitOfMeasure();
		uom = uofDAO.getUnitOfMeasureByName(uom);

		if( test.getId() != null &&
			((test.getUnitOfMeasure() != null && !test.getUnitOfMeasure().getId().equals(uom.getId()))||
			test.getUnitOfMeasure() == null)	){
			test.setUnitOfMeasure(uom);
			return test;
		}

		return null;
	}

	private void persistLimit(ResultLimit resultLimit) {
		ResultLimitDAO resultLimitDAO = new ResultLimitDAOImpl();
		if (GenericValidator.isBlankOrNull(resultLimit.getId())) {
			resultLimitDAO.insertData(resultLimit);
		} else {
			resultLimitDAO.updateData(resultLimit);
		}
	}

	private void persistTest(Test test) {
		if( test != null){
			test.setSysUserId(currentUserId);
			testDAO.updateData(test);
		}

	}
	protected String getPageTitleKey() {
		return isNew ? "resultlimits.add.title" : "resultlimits.edit.title";
	}

	protected String getPageSubtitleKey() {
		return isNew ? "resultlimits.add.title" : "resultlimits.edit.title";
	}
}