package spring.mine.systemuser.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.systemuser.form.UnifiedSystemUserMenuForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.systemuser.valueholder.UnifiedSystemUser;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

@Controller
public class DeleteUnifiedSystemUserController extends BaseController {

	static private String FWD_CLOSE = "close";

	@RequestMapping(value = "/DeleteUnifiedSystemUser", method = RequestMethod.POST)
	public ModelAndView showDeleteUnifiedSystemUser(HttpServletRequest request,
			@ModelAttribute("form") UnifiedSystemUserMenuForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new UnifiedSystemUserMenuForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		String[] selectedIDs = form.getSelectedIDs();
		List<Login> loginUsers = new ArrayList<>();
		List<SystemUser> systemUsers = new ArrayList<>();
		List<UserRole> userRoles = new ArrayList<>();

		String sysUserId = getSysUserId(request);

		for (int i = 0; i < selectedIDs.length; i++) {
			String systemUserId = UnifiedSystemUser.getSystemUserIDFromCombinedID(selectedIDs[i]);

			if (!GenericValidator.isBlankOrNull(systemUserId)) {
				SystemUser systemUser = new SystemUser();
				systemUser.setId(systemUserId);
				systemUser.setSysUserId(sysUserId);
				systemUsers.add(systemUser);
			}

			String loginUserId = UnifiedSystemUser.getLoginUserIDFromCombinedID(selectedIDs[i]);

			if (!GenericValidator.isBlankOrNull(loginUserId)) {
				Login loginUser = new Login();
				loginUser.setId(loginUserId);
				loginUser.setSysUserId(sysUserId);
				loginUsers.add(loginUser);
			}
		}

		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

		for (SystemUser systemUser : systemUsers) {
			List<String> roleIds = userRoleDAO.getRoleIdsForUser(systemUser.getId());

			for (String roleId : roleIds) {
				UserRole userRole = new UserRole();
				userRole.setSystemUserId(systemUser.getId());
				userRole.setRoleId(roleId);
				userRole.setSysUserId(sysUserId);
				userRoles.add(userRole);
			}
		}

		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		LoginDAO loginDAO = new LoginDAOImpl();

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {

			userRoleDAO.deleteData(userRoles);

			for (SystemUser systemUser : systemUsers) {
				// we're not going to actually delete them to preserve auditing
				systemUserDAO.getData(systemUser);
				systemUser.setSysUserId(sysUserId);
				systemUser.setIsActive("N");
				systemUserDAO.updateData(systemUser);
			}

			loginDAO.deleteData(loginUsers);

			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				errors.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
			} else {
				errors.reject("errors.DeleteException", "errors.DeleteException");
			}
			saveErrors(errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			forward = FWD_FAIL;

		} finally {
			HibernateUtil.closeSession();
		}
		if (forward.equals(FWD_FAIL)) {
			return findForward(forward, form);
		}

		if (TRUE.equalsIgnoreCase(request.getParameter("close"))) {
			forward = FWD_CLOSE;
		}

		request.setAttribute("menuDefinition", "RoleMenuDefinition");

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("redirect:/UnifiedSystemUserMenu.do", "form", form);
		} else if (FWD_FAIL.equals(forward)) {
			return new ModelAndView("redirect:/UnifiedSystemUserMenu.do", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}
}
