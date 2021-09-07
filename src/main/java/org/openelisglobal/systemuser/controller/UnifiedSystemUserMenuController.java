package org.openelisglobal.systemuser.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.controller.BaseMenuController;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.form.AdminOptionMenuForm;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.login.service.LoginUserService;
import org.openelisglobal.login.valueholder.LoginUser;
import org.openelisglobal.systemuser.form.UnifiedSystemUserMenuForm;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.service.UnifiedSystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.openelisglobal.systemuser.valueholder.UnifiedSystemUser;
import org.openelisglobal.userrole.service.UserRoleService;
import org.openelisglobal.userrole.valueholder.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UnifiedSystemUserMenuController extends BaseMenuController<UnifiedSystemUser> {

    private static final String[] ALLOWED_FIELDS = new String[] { "selectedIDs*" };

    @Autowired
    SystemUserService systemUserService;
    @Autowired
    LoginUserService loginService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UnifiedSystemUserService unifiedSystemUserService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/UnifiedSystemUserMenu", method = RequestMethod.GET)
    public ModelAndView showUnifiedSystemUserMenu(HttpServletRequest request, RedirectAttributes redirectAttributes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String forward = FWD_SUCCESS;
        UnifiedSystemUserMenuForm form = new UnifiedSystemUserMenuForm();

        form.setFormAction("UnifiedSystemUserMenu.do");
        forward = performMenuAction(form, request);
        if (FWD_FAIL.equals(forward)) {
            Errors errors = new BaseErrors();
            errors.reject("error.generic");
            redirectAttributes.addFlashAttribute(Constants.REQUEST_ERRORS, errors);
            return findForward(FWD_FAIL, form);
        } else {
            return findForward(forward, form);
        }
    }

    @Override
    protected List<UnifiedSystemUser> createMenuList(AdminOptionMenuForm<UnifiedSystemUser> form,
            HttpServletRequest request) {
        List<SystemUser> systemUsers = new ArrayList<>();

        String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
        int startingRecNo = Integer.parseInt(stringStartingRecNo);

        systemUsers = systemUserService.getPage(startingRecNo);

        List<UnifiedSystemUser> unifiedUsers = getUnifiedUsers(systemUsers);

        request.setAttribute("menuDefinition", "UnifiedSystemUserMenuDefinition");

        request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(systemUserService.getCount()));
        request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));

        int numOfRecs = 0;
        if (systemUsers.size() != 0) {
            numOfRecs = Math.min(systemUsers.size(), getPageSize());

            numOfRecs--;
        }

        int endingRecNo = startingRecNo + numOfRecs;
        request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));

        return unifiedUsers;
    }

    private List<UnifiedSystemUser> getUnifiedUsers(List<SystemUser> systemUsers) {

        List<UnifiedSystemUser> unifiedUsers = new ArrayList<>();

        List<LoginUser> loginUsers = loginService.getAll();

        HashMap<String, LoginUser> loginMap = createLoginMap(loginUsers);

        for (SystemUser user : systemUsers) {
            UnifiedSystemUser unifiedUser = createUnifiedSystemUser(loginMap, user);
            unifiedUsers.add(unifiedUser);
        }

        return unifiedUsers;
    }

    private UnifiedSystemUser createUnifiedSystemUser(HashMap<String, LoginUser> loginMap, SystemUser user) {

        UnifiedSystemUser unifiedUser = new UnifiedSystemUser();
        unifiedUser.setFirstName(user.getFirstName());
        unifiedUser.setLastName(user.getLastName());
        unifiedUser.setLoginName(user.getLoginName());
        unifiedUser.setSystemUserId(user.getId());
        unifiedUser.setActive(user.getIsActive());

        LoginUser login = loginMap.get(user.getLoginName());

        if (login != null) {
            unifiedUser.setExpDate(DateUtil.formatDateAsText(login.getPasswordExpiredDate()));
            unifiedUser.setDisabled(login.getAccountDisabled());
            unifiedUser.setLocked(login.getAccountLocked());
            unifiedUser.setTimeout(login.getUserTimeOut());
            unifiedUser.setLoginUserId(Integer.toString(login.getId()));
        }
        return unifiedUser;
    }

    private HashMap<String, LoginUser> createLoginMap(List<LoginUser> loginUsers) {
        HashMap<String, LoginUser> loginMap = new HashMap<>();

        for (LoginUser login : loginUsers) {
            loginMap.put(login.getLoginName(), login);
        }

        return loginMap;
    }

    @Override
    protected String getDeactivateDisabled() {
        return "false";
    }

    @Override
    protected int getPageSize() {
        return SystemConfiguration.getInstance().getDefaultPageSize();
    }

    @RequestMapping(value = "/DeleteUnifiedSystemUser", method = RequestMethod.POST)
    public ModelAndView showDeleteUnifiedSystemUser(HttpServletRequest request,
            @ModelAttribute("form") UnifiedSystemUserMenuForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            saveErrors(result);
            return findForward(FWD_FAIL_DELETE, form);
        }
        List<String> selectedIDs = form.getSelectedIDs();
        List<LoginUser> loginUsers = new ArrayList<>();
        List<SystemUser> systemUsers = new ArrayList<>();
        List<UserRole> userRoles = new ArrayList<>();

        String sysUserId = getSysUserId(request);

        for (int i = 0; i < selectedIDs.size(); i++) {
            String systemUserId = UnifiedSystemUser.getSystemUserIDFromCombinedID(selectedIDs.get(i));

            if (!GenericValidator.isBlankOrNull(systemUserId)) {
                SystemUser systemUser = new SystemUser();
                systemUser.setId(systemUserId);
                systemUser.setSysUserId(sysUserId);
                systemUsers.add(systemUser);
            }

            Integer loginUserId = UnifiedSystemUser.getLoginUserIDFromCombinedID(selectedIDs.get(i));

            if (null != loginUserId) {
                LoginUser loginUser = new LoginUser();
                loginUser.setId(loginUserId);
                loginUser.setSysUserId(sysUserId);
                loginUsers.add(loginUser);
            }
        }

        for (SystemUser systemUser : systemUsers) {
            List<String> roleIds = userRoleService.getRoleIdsForUser(systemUser.getId());

            for (String roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setSystemUserId(systemUser.getId());
                userRole.setRoleId(roleId);
                userRole.setSysUserId(sysUserId);
                userRoles.add(userRole);
            }
        }

        try {
            unifiedSystemUserService.deleteData(userRoles, systemUsers, loginUsers, getSysUserId(request));
        } catch (LIMSRuntimeException e) {

            if (e.getException() instanceof org.hibernate.StaleObjectStateException) {
                result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
            } else {
                result.reject("errors.DeleteException", "errors.DeleteException");
            }
            saveErrors(result);
            return findForward(FWD_FAIL_DELETE, form);

        }

        return findForward(FWD_SUCCESS_DELETE, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "haitiMasterListsPageDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "redirect:/MasterListsPage.do";
        } else if (FWD_SUCCESS_DELETE.equals(forward)) {
            return "redirect:/UnifiedSystemUserMenu.do";
        } else if (FWD_FAIL_DELETE.equals(forward)) {
            return "redirect:/UnifiedSystemUserMenu.do";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "unifiedSystemUser.browser.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "unifiedSystemUser.browser.title";
    }
}
