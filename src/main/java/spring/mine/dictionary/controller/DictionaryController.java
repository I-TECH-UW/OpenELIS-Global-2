package spring.mine.dictionary.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.dictionary.form.DictionaryForm;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSFrozenRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.dictionarycategory.dao.DictionaryCategoryDAO;
import us.mn.state.health.lims.dictionarycategory.daoimpl.DictionaryCategoryDAOImpl;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.valueholder.UserSessionData;

@Controller
@SessionAttributes("form")
public class DictionaryController extends BaseController {

	@ModelAttribute("form")
	public BaseForm form() {
		return new DictionaryForm();
	}

	private boolean isNew = false;

	@RequestMapping(value = { "/Dictionary", "/NextPreviousDictionary" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView showDictionary(HttpServletRequest request, @ModelAttribute("form") BaseForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form.getClass() != DictionaryForm.class) {
			form = new DictionaryForm();
			request.getSession().setAttribute("form", form);
		}
		form.setFormAction("");
		form.setCancelAction("CancelDictionary.do");
		Errors errors = new BaseErrors();

		String id = request.getParameter(ID);
		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		// bugzilla 2062
		request.setAttribute(RECORD_FROZEN_EDIT_DISABLED_KEY, "false");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		Dictionary dictionary = new Dictionary();
		dictionary.setId(id);

		// redirect to get dictionary for next or previous entry
		if (FWD_NEXT.equals(direction) || FWD_PREVIOUS.equals(direction)) {
			List dictionaries;
			dictionaryDAO.getData(dictionary);
			if (FWD_NEXT.equals(direction)) {
				dictionaries = dictionaryDAO.getNextDictionaryRecord(dictionary.getId());
			} else {
				dictionaries = dictionaryDAO.getPreviousDictionaryRecord(dictionary.getId());
			}
			if (dictionaries != null && dictionaries.size() > 0) {
				dictionary = (Dictionary) dictionaries.get(0);
			}
			String newId = dictionary.getId();
			String url = "redirect:/Dictionary.do?ID=" + newId + "&startingRecNo=" + start;
			return new ModelAndView(url, "form", form);
		}

		if ((id != null) && (!"0".equals(id))) { // this is an existing
			// dictionary
			dictionaryDAO.getData(dictionary);
			isNew = false; // this is to set correct page title

			// do we need to enable next or previous?
			List dictionarys = dictionaryDAO.getNextDictionaryRecord(dictionary.getId());
			if (dictionarys.size() > 0) {
				// enable next button
				request.setAttribute(NEXT_DISABLED, "false");
			}
			dictionarys = dictionaryDAO.getPreviousDictionaryRecord(dictionary.getId());
			if (dictionarys.size() > 0) {
				// enable next button
				request.setAttribute(PREVIOUS_DISABLED, "false");
			}
			// end of logic to enable next or previous button
		} else { // this is a new dictionary

			// default isActive to 'Y'
			dictionary.setIsActive(YES);
			isNew = true; // this is to set correct page title
		}

		if (dictionary.getId() != null && !dictionary.getId().equals("0")) {
			request.setAttribute(ID, dictionary.getId());
			// bugzilla 2062 initialize selectedDictionaryCategoryId
			if (dictionary.getDictionaryCategory() != null) {
				dictionary.setSelectedDictionaryCategoryId(dictionary.getDictionaryCategory().getId());
			}
		}

		// populate form from valueholder
		PropertyUtils.copyProperties(form, dictionary);

		DictionaryCategoryDAO dictCategorygDAO = new DictionaryCategoryDAOImpl();
		List dictCats = dictCategorygDAO.getAllDictionaryCategorys();

		PropertyUtils.setProperty(form, "categories", dictCats);

		return findForward(forward, form);
	}

	@RequestMapping(value = "/UpdateDictionary", method = RequestMethod.POST)
	public ModelAndView showUpdateDictionary(HttpServletRequest request, @ModelAttribute("form") DictionaryForm form,
			SessionStatus status) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new DictionaryForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);

		if (StringUtil.isNullorNill(id) || "0".equals(id)) {
			isNew = true;
		} else {
			isNew = false;
		}

		// server-side validation (validation.xml)
		// ActionMessages errors = form.validate(mapping, request);
		if (errors.hasErrors()) {
			// System.out.println("Server side validation errors "
			// + errors.toString());
			saveErrors(errors);
			// since we forward to jsp - not Action we don't need to repopulate
			// the lists here
			return findForward(FWD_FAIL_INSERT, form);
		}

		String start = request.getParameter("startingRecNo");
		String direction = request.getParameter("direction");

		// System.out.println("This is ID from request " + id);

		Dictionary dictionary = new Dictionary();
		// get sysUserId from login module
		UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
		String sysUserId = String.valueOf(usd.getSystemUserId());
		dictionary.setSysUserId(sysUserId);
		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();

		// populate valueholder from form
		PropertyUtils.copyProperties(dictionary, form);

		// bugzilla 2062
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		DictionaryCategoryDAO dictionaryCategoryDAO = new DictionaryCategoryDAOImpl();

		String selectedCategoryId = (String) form.get("selectedDictionaryCategoryId");
		// bugzilla 2108
		DictionaryCategory dictionaryCategory = null;
		if (!StringUtil.isNullorNill(selectedCategoryId)) {
			dictionaryCategory = new DictionaryCategory();
			dictionaryCategory.setId(selectedCategoryId);
			dictionaryCategoryDAO.getData(dictionaryCategory);
		}

		dictionary.setDictionaryCategory(dictionaryCategory);

		try {

			if (!isNew) {
				// UPDATE

				// bugzilla 2062
				boolean isDictionaryFrozenCheckRequired = false;

				// there is an exception to rule of checking whether dictionary record
				// is frozen (can no longer be updated):
				// currenly if only isActive has changed and
				// the current value is 'Y'
				// OR
				// bugzilla 1847: also the local abbreviation can be deleted/updated/inserted at
				// anytime
				String dirtyFormFields = (String) form.get("dirtyFormFields");
				String isActiveValue = (String) form.get("isActive");

				String[] dirtyFields = dirtyFormFields.split(SystemConfiguration.getInstance().getDefaultIdSeparator(),
						-1);
				List listOfDirtyFields = new ArrayList();

				for (int i = 0; i < dirtyFields.length; i++) {
					String dirtyField = dirtyFields[i];
					if (!StringUtil.isNullorNill(dirtyField)) {
						listOfDirtyFields.add(dirtyField);
					}
				}

				List listOfDirtyFieldsNoFrozenCheckRequired = new ArrayList();
				listOfDirtyFieldsNoFrozenCheckRequired.add("isActive");
				listOfDirtyFieldsNoFrozenCheckRequired.add("localAbbreviation");

				// bugzilla 1847 : added to exception for frozen check required
				// isActive changed to Y (no frozen check required)
				// localAbbreviation has changed (no frozen check required)
				if (!listOfDirtyFields.isEmpty()) {
					for (int i = 0; i < listOfDirtyFields.size(); i++) {
						String dirtyField = (String) listOfDirtyFields.get(i);
						if (!listOfDirtyFieldsNoFrozenCheckRequired.contains(dirtyField)) {
							isDictionaryFrozenCheckRequired = true;
						} else {
							// in case of isActive: need to make sure it changed to YES to be able
							// to skip isFrozenCheck
							if (dirtyField.equals("isActive") && !isActiveValue.equals(YES)) {
								isDictionaryFrozenCheckRequired = true;
							}
						}
					}
				} else {
					isDictionaryFrozenCheckRequired = false;
				}

				dictionaryDAO.updateData(dictionary, isDictionaryFrozenCheckRequired);

			} else {
				// INSERT

				dictionaryDAO.insertData(dictionary);
			}
			tx.commit();
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("DictionaryUpdateAction", "performAction()", lre.toString());
			tx.rollback();
			// 1482
			java.util.Locale locale = (java.util.Locale) request.getSession()
					.getAttribute("org.apache.struts.action.LOCALE");
			ActionError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				// how can I get popup instead of struts error at the top of
				// page?
				// ActionMessages errors = form.validate(mapping, request);
				error = new ActionError("errors.OptimisticLockException", null, null);

			} else {
				// bugzilla 1386
				if (lre.getException() instanceof LIMSDuplicateRecordException) {
					String messageKey = "dictionary.dictEntryByCategory";
					String msg = ResourceLocator.getInstance().getMessageResources().getMessage(locale, messageKey);
					errors.reject("errors.DuplicateRecord.activate", new String[] { msg },
							"errors.DuplicateRecord.activate");

				} else if (lre.getException() instanceof LIMSFrozenRecordException) {
					String messageKey = "dictionary.dictEntry";
					String msg = ResourceLocator.getInstance().getMessageResources().getMessage(locale, messageKey);
					errors.reject("errors.FrozenRecord", new String[] { msg }, "errors.FrozenRecord");
					// Now disallow further edits RECORD_FROZEN_EDIT_DISABLED_KEY
					// in this case User needs to Exit and come back to refresh form
					// for further updates (this is to restore isDirty() functionality
					// that relies on defaultValues of form
					// --this is needed to determine whether frozen check is required
					request.setAttribute(RECORD_FROZEN_EDIT_DISABLED_KEY, "true");
				} else {
					errors.reject("errors.UpdateException");
				}
			}
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			// bugzilla 1485: allow change and try updating again (enable save button)
			// request.setAttribute(IActionConstants.ALLOW_EDITS_KEY, "false");
			// disable previous and next
			request.setAttribute(PREVIOUS_DISABLED, "true");
			request.setAttribute(NEXT_DISABLED, "true");
			forward = FWD_FAIL_INSERT;

		} finally {
			HibernateUtil.closeSession();
		}
		if (forward.equals(FWD_FAIL_INSERT)) {
			return findForward(forward, form);
		}
		// repopulate the form from valueholder
		PropertyUtils.copyProperties(form, dictionary);

		if ("true".equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}

		if (dictionary.getId() != null && !dictionary.getId().equals("0")) {
			request.setAttribute(ID, dictionary.getId());

		}

		forward = FWD_SUCCESS_INSERT;

		status.setComplete();
		// bugzilla 1467 added direction for redirect to NextPreviousAction
		return getForward(findForward(forward, form), id, start, direction);
	}

	@RequestMapping(value = "/CancelDictionary", method = RequestMethod.GET)
	public ModelAndView cancelDictionary(HttpServletRequest request, @ModelAttribute("form") DictionaryForm form,
			SessionStatus status) {
		status.setComplete();
		return findForward(FWD_CANCEL, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("dictionaryDefinition", "form", form);
		} else if (FWD_FAIL.equals(forward)) {
			return new ModelAndView("redirect:/MasterListsPage.do", "form", form);
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return new ModelAndView("redirect:/DictionaryMenu.do", "form", form);
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return new ModelAndView("dictionaryDefinition", "form", form);
		} else if (FWD_CANCEL.equals(forward)) {
			return new ModelAndView("redirect:/DictionaryMenu.do", "form", form);
		} else {

			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		if (isNew) {
			return "dictionary.add.title";
		} else {
			return "dictionary.edit.title";
		}
	}

	@Override
	protected String getPageSubtitleKey() {
		if (isNew) {
			return "dictionary.add.title";
		} else {
			return "dictionary.edit.title";
		}
	}
}
