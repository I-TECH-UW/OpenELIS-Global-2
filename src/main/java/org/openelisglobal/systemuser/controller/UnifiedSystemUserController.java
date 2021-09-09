package org.openelisglobal.systemuser.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.provider.validation.PasswordValidationFactory;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.login.dao.UserModuleService;
import org.openelisglobal.login.service.LoginUserService;
import org.openelisglobal.login.valueholder.LoginUser;
import org.openelisglobal.role.action.bean.DisplayRole;
import org.openelisglobal.role.service.RoleService;
import org.openelisglobal.role.valueholder.Role;
import org.openelisglobal.systemuser.form.UnifiedSystemUserForm;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.service.UserService;
import org.openelisglobal.systemuser.validator.UnifiedSystemUserFormValidator;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.openelisglobal.systemuser.valueholder.UnifiedSystemUser;
import org.openelisglobal.userrole.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UnifiedSystemUserController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] { "systemUserId", "loginUserId", "userLoginName",
            "userPassword", "confirmPassword", "userFirstName", "userLastName", "expirationDate", "timeout",
            "accountLocked", "accountDisabled", "accountActive", "selectedRoles*" };

    @Autowired
    private UnifiedSystemUserFormValidator formValidator;

    @Autowired
    private LoginUserService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private UserModuleService userModuleService;
    @Autowired
    private UserService userService;
    private static final String RESERVED_ADMIN_NAME = "admin";

    private static final String MAINTENANCE_ADMIN = "Maintenance Admin";
    private static String MAINTENANCE_ADMIN_ID;
    public static final char DEFAULT_OBFUSCATED_CHARACTER = '@';

    @PostConstruct
    private void initialize() {
        List<Role> roles = roleService.getAll();
        for (Role role : roles) {
            if (MAINTENANCE_ADMIN.equals(role.getName().trim())) {
                MAINTENANCE_ADMIN_ID = role.getId();
                break;
            }
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/UnifiedSystemUser", method = RequestMethod.GET)
    public ModelAndView showUnifiedSystemUser(HttpServletRequest request,
            @RequestParam(name = "ID", defaultValue = "") String id)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        UnifiedSystemUserForm form = new UnifiedSystemUserForm();
        form.setFormAction("UnifiedSystemUser.do");
        form.setCancelAction("UnifiedSystemUserMenu.do");

        boolean doFiltering = true;
        request.setAttribute(ALLOW_EDITS_KEY, "true");
        request.setAttribute(PREVIOUS_DISABLED, "true");
        request.setAttribute(NEXT_DISABLED, "true");

        boolean isNew = GenericValidator.isBlankOrNull(id) || "0".equals(id);

        setDefaultProperties(form);
        if (!isNew) {
            setPropertiesForExistingUser(form, id, doFiltering);
        }
        setupRoles(form, request, doFiltering);

        addFlashMsgsToRequest(request);
        return findForward(FWD_SUCCESS, form);
    }

    private void setupRoles(UnifiedSystemUserForm form, HttpServletRequest request, boolean doFiltering) {
        List<Role> roles = getAllRoles();
        doFiltering &= !userModuleService.isUserAdmin(request);

        if (doFiltering) {
            roles = doRoleFiltering(roles, getSysUserId(request));
        }

        List<DisplayRole> displayRoles = convertToDisplayRoles(roles);
        displayRoles = sortAndGroupRoles(displayRoles);

        form.setRoles(displayRoles);
    }

    private List<DisplayRole> convertToDisplayRoles(List<Role> roles) {
        int elementCount = 0;

        List<DisplayRole> displayRoles = new ArrayList<>();

        for (Role role : roles) {
            elementCount++;
            displayRoles.add(convertToDisplayRole(role, elementCount));
        }

        return displayRoles;
    }

    private DisplayRole convertToDisplayRole(Role role, int count) {
        DisplayRole displayRole = new DisplayRole();

        displayRole.setRoleName(role.getLocalizedName());
        displayRole.setElementID(String.valueOf(count));
        displayRole.setRoleId(role.getId());
        displayRole.setGroupingRole(role.getGroupingRole());
        displayRole.setParentRole(role.getGroupingParent());

        return displayRole;
    }

    private List<DisplayRole> sortAndGroupRoles(List<DisplayRole> roles) {
        /*
         * The sorting we want to end up with is first alphabetical and then by groups
         * What makes things a little more difficult is that we may have roles which
         * have parents which don't exist, we shouldn't but we might. So... First sweep
         * is to find all the orphaned roles and set their parents to null Then move all
         * the first generation groups to a new list. Then scan for all for all groups
         * and move their members, repeat until the first list is empty, which is why we
         * didn't want orphans. Lastly we will add the role ID as a child to all of it's
         * parents
         */

        Collections.sort(roles, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                DisplayRole role1 = (DisplayRole) obj1;
                DisplayRole role2 = (DisplayRole) obj2;
                return role1.getRoleName().toUpperCase().compareTo(role2.getRoleName().toUpperCase());
            }
        });

        /*
         * The reason we're not making a map is that we want to preserve the order
         * during this whole process
         */
        List<String> groupIds = new ArrayList<>();

        for (DisplayRole role : roles) {
            if (role.isGroupingRole()) {
                groupIds.add(role.getRoleId());
            }
        }

        for (DisplayRole role : roles) {
            if (!GenericValidator.isBlankOrNull(role.getParentRole()) && !groupIds.contains(role.getParentRole())) {
                role.setParentRole(null);
            }
        }

        List<DisplayRole> mergeList = new ArrayList<>();
        List<DisplayRole> currentWorkingList = new ArrayList<>();
        List<DisplayRole> unplacedList = new ArrayList<>();

        for (DisplayRole role : roles) {
            if (GenericValidator.isBlankOrNull(role.getParentRole())) {
                role.setNestingLevel(0);
                currentWorkingList.add(role);
            } else {
                unplacedList.add(role);
            }
        }

        int indentCount = 0;
        while (unplacedList.size() > 0) {
            indentCount++;
            for (DisplayRole placedRole : currentWorkingList) {
                mergeList.add(placedRole);

                if (placedRole.isGroupingRole()) {
                    List<DisplayRole> removeList = new ArrayList<>();
                    for (DisplayRole unplacedRole : unplacedList) {
                        if (unplacedRole.getParentRole().equals(placedRole.getRoleId())) {
                            unplacedRole.setNestingLevel(indentCount);
                            mergeList.add(unplacedRole);
                            removeList.add(unplacedRole);
                            placedRole.addChildID(unplacedRole.getRoleId());
                        }
                    }
                    unplacedList.removeAll(removeList);
                }
            }

            currentWorkingList = mergeList;
            mergeList = new ArrayList<>();
        }

        /*
         * For finding all parents we are going to iterate backwards since all parents
         * are in front of children role
         */
        for (int i = currentWorkingList.size() - 1; i > 0; i--) {
            DisplayRole role = currentWorkingList.get(i);

            if (!GenericValidator.isBlankOrNull(role.getParentRole())) {
                String roleID = role.getRoleId();
                String currentParentID = role.getParentRole();

                for (int parent = i - 1; parent >= 0; parent--) {
                    if (currentWorkingList.get(parent).getRoleId().equals(currentParentID)) {
                        DisplayRole parentRole = currentWorkingList.get(parent);

                        parentRole.addChildID(roleID);

                        if (GenericValidator.isBlankOrNull(parentRole.getParentRole())) {
                            break;
                        } else {
                            currentParentID = parentRole.getParentRole();
                        }
                    }
                }
            }
        }

        return currentWorkingList;
    }

    private List<Role> doRoleFiltering(List<Role> roles, String loggedInUserId) {

        List<String> rolesForLoggedInUser = userRoleService.getRoleIdsForUser(loggedInUserId);

        if (!rolesForLoggedInUser.contains(MAINTENANCE_ADMIN_ID)) {
            List<Role> tmpRoles = new ArrayList<>();

            for (Role role : roles) {
                if (!MAINTENANCE_ADMIN_ID.equals(role.getId())) {
                    tmpRoles.add(role);
                }
            }

            roles = tmpRoles;
        }

        return roles;
    }

    private void setDefaultProperties(UnifiedSystemUserForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String expireDate = getYearsFromNow(10);
        form.setExpirationDate(expireDate);
        form.setTimeout("480");
        form.setSystemUserLastupdated(new Timestamp(System.currentTimeMillis()));
    }

    private void setPropertiesForExistingUser(UnifiedSystemUserForm form, String id, boolean doFiltering)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        LoginUser login = getLoginFromCombinedId(id);
        SystemUser systemUser = getSystemUserFromCombinedId(id);

        if (login != null) {
            String proxyPassword = getProxyPassword(login);
            form.setLoginUserId(Integer.toString(login.getId()));
            form.setAccountDisabled(login.getAccountDisabled());
            form.setAccountLocked(login.getAccountLocked());
            form.setUserLoginName(login.getLoginName());
            form.setUserPassword(proxyPassword);
            form.setConfirmPassword(proxyPassword);
            form.setExpirationDate(login.getPasswordExpiredDateForDisplay());
            form.setTimeout(login.getUserTimeOut());
        }

        if (systemUser != null) {
            form.setSystemUserId(systemUser.getId());
            form.setUserFirstName(systemUser.getFirstName());
            form.setUserLastName(systemUser.getLastName());
            form.setAccountActive(systemUser.getIsActive());
            form.setSystemUserLastupdated(systemUser.getLastupdated());

            List<String> roleIds = userRoleService.getRoleIdsForUser(systemUser.getId());
            form.setSelectedRoles(roleIds);

            // is this meant to be returned?
//            doFiltering = !roleIds.contains(MAINTENANCE_ADMIN_ID);
        }

    }

    private String getProxyPassword(LoginUser login) {
        char[] chars = new char[9];
        Arrays.fill(chars, DEFAULT_OBFUSCATED_CHARACTER);
        return new String(chars);
        // return StringUtil.replaceAllChars(login.getPassword(),
        // DEFAULT_PASSWORD_FILLER);
    }

    private LoginUser getLoginFromCombinedId(String id) {
        LoginUser login = null;
        Integer loginId = UnifiedSystemUser.getLoginUserIDFromCombinedID(id);

        if (null != loginId) {
            login = loginService.get(loginId);
        }

        return login;
    }

    private SystemUser getSystemUserFromCombinedId(String id) {
        SystemUser systemUser = null;
        String systemUserId = UnifiedSystemUser.getSystemUserIDFromCombinedID(id);

        if (!GenericValidator.isBlankOrNull(systemUserId)) {
            systemUser = systemUserService.get(systemUserId);
        }

        return systemUser;
    }

    private String getYearsFromNow(int years) {
        Calendar today = Calendar.getInstance();

        today.add(Calendar.YEAR, years);

        return DateUtil.formatDateAsText(today.getTime());
    }

    private List<Role> getAllRoles() {
        return roleService.getAllActiveRoles();
    }

    @RequestMapping(value = "/UnifiedSystemUser", method = RequestMethod.POST)
    public ModelAndView showUpdateUnifiedSystemUser(HttpServletRequest request,
            @ModelAttribute("form") @Valid UnifiedSystemUserForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        boolean doFiltering = true;
        formValidator.validate(form, result);
        if (result.hasErrors()) {
            saveErrors(result);
            setupRoles(form, request, doFiltering);
            return findForward(FWD_FAIL_INSERT, form);
        }

        request.setAttribute(ALLOW_EDITS_KEY, "true");
        request.setAttribute(PREVIOUS_DISABLED, "false");
        request.setAttribute(NEXT_DISABLED, "false");

        if (form.getUserLoginName() != null) {
            form.setUserLoginName(form.getUserLoginName().trim());
        } else {
            form.setUserLoginName("");
        }

        String forward = validateAndUpdateSystemUser(request, form);

        if (forward.equals(FWD_SUCCESS_INSERT)) {
            redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
            Map<String, String> params = new HashMap<>();
            params.put("forward", FWD_SUCCESS);
            return getForwardWithParameters(findForward(forward, form), params);
        } else {
            setupRoles(form, request, doFiltering);
            return findForward(forward, form);
        }
    }

    private String validateAndUpdateSystemUser(HttpServletRequest request, UnifiedSystemUserForm form) {
        String loginUserId = form.getLoginUserId();
        String systemUserId = form.getSystemUserId();

        Errors errors = new BaseErrors();

        boolean loginUserNew = GenericValidator.isBlankOrNull(loginUserId);
        boolean systemUserNew = GenericValidator.isBlankOrNull(systemUserId);
        boolean passwordUpdated = false;

        passwordUpdated = passwordHasBeenUpdated(loginUserNew, form);
        validateUser(form, errors, loginUserNew, passwordUpdated, loginUserId);

        if (errors.hasErrors()) {
            saveErrors(errors);
            return FWD_FAIL_INSERT;
        }

        String loggedOnUserId = getSysUserId(request);

        LoginUser loginUser = createLoginUser(form, loginUserId, loginUserNew, passwordUpdated, loggedOnUserId);
        SystemUser systemUser = createSystemUser(form, systemUserId, systemUserNew, loggedOnUserId);

        try {
            userService.updateLoginUser(loginUser, loginUserNew, systemUser, systemUserNew, form.getSelectedRoles(),
                    loggedOnUserId);
        } catch (LIMSRuntimeException e) {
            if (e.getException() instanceof org.hibernate.StaleObjectStateException) {
                errors.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
            } else if (e.getException() instanceof LIMSDuplicateRecordException) {
                errors.reject("errors.DuplicateRecordException", "errors.DuplicateRecordException");
            } else {
                errors.reject("errors.UpdateException", "errors.UpdateException");
            }

            saveErrors(errors);
            disableNavigationButtons(request);
            return FWD_FAIL_INSERT;
        }

        return FWD_SUCCESS_INSERT;
    }

    private boolean passwordHasBeenUpdated(boolean loginUserNew, UnifiedSystemUserForm form) {
        if (loginUserNew) {
            return true;
        }

        String password = form.getUserPassword();

        return !StringUtil.containsOnly(password, DEFAULT_OBFUSCATED_CHARACTER);
    }

    private void validateUser(UnifiedSystemUserForm form, Errors errors, boolean loginUserIsNew,
            boolean passwordUpdated, String loginUserId) {
        boolean checkForDuplicateName = loginUserIsNew || userNameChanged(loginUserId, form.getUserLoginName());
        // check login name

        if (GenericValidator.isBlankOrNull(form.getUserLoginName())) {
            errors.reject("errors.loginName.required", "errors.loginName.required");
        } else if (checkForDuplicateName) {
            LoginUser login = loginService.getMatch("loginName", form.getUserLoginName()).orElse(null);
            if (login != null) {
                errors.reject("errors.loginName.duplicated");
            }
        }

        // check first and last name
        if (GenericValidator.isBlankOrNull(form.getUserFirstName())
                || GenericValidator.isBlankOrNull(form.getUserLastName())) {
            errors.reject("errors.userName.required", "errors.userName.required");
        }

        if (passwordUpdated) {
            // check passwords match
            if (GenericValidator.isBlankOrNull(form.getUserPassword())
                    || !form.getUserPassword().equals(form.getConfirmPassword())) {
                errors.reject("errors.password.match", "errors.password.match");
            } else if (!passwordValid(form.getUserPassword())) { // validity
                errors.reject("login.error.password.requirement");
            }
        }

        // check timeout
        if (!timeoutValidAndInRange(form.getTimeout())) {
            errors.reject("errors.timeout.range", "errors.timeout.range");
        }
    }

    private boolean userNameChanged(String loginUserId, String newName) {
        if (GenericValidator.isBlankOrNull(loginUserId)) {
            return false;
        }

        LoginUser login = loginService.get(Integer.parseInt(loginUserId));

        return !newName.equals(login.getLoginName());
    }

    private boolean timeoutValidAndInRange(String timeout) {
        try {
            int timeInMin = Integer.parseInt(timeout);
            return timeInMin > 0 && timeInMin < 601;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean passwordValid(String password) {
        return PasswordValidationFactory.getPasswordValidator().passwordValid(password);
    }

    private LoginUser createLoginUser(UnifiedSystemUserForm form, String loginUserId, boolean loginUserNew,
            boolean passwordUpdated, String loggedOnUserId) {

        LoginUser login = new LoginUser();

        if (!loginUserNew) {
            login = loginService.get(Integer.parseInt(form.getLoginUserId()));
        }
        login.setAccountDisabled(form.getAccountDisabled());
        login.setAccountLocked(form.getAccountLocked());
        login.setLoginName(form.getUserLoginName());
        if (passwordUpdated) {
            login.setPassword(form.getUserPassword());
            loginService.hashPassword(login, login.getPassword());
        }
        login.setPasswordExpiredDateForDisplay(form.getExpirationDate());
        if (RESERVED_ADMIN_NAME.equals(form.getUserLoginName())) {
            login.setIsAdmin("Y");
        } else {
            login.setIsAdmin("N");
        }
        login.setUserTimeOut(form.getTimeout());
        login.setSysUserId(loggedOnUserId);

        return login;
    }

    private SystemUser createSystemUser(UnifiedSystemUserForm form, String systemUserId, boolean systemUserNew,
            String loggedOnUserId) {

        SystemUser systemUser = new SystemUser();

        if (!systemUserNew) {
            systemUser = systemUserService.get(systemUserId);
        }

        systemUser.setFirstName(form.getUserFirstName());
        systemUser.setLastName(form.getUserLastName());
        systemUser.setLoginName(form.getUserLoginName());
        systemUser.setIsActive(form.getAccountActive());
        systemUser.setIsEmployee("Y");
        systemUser.setExternalId("1");
        String initial = systemUser.getFirstName().substring(0, 1) + systemUser.getLastName().substring(0, 1);
        systemUser.setInitials(initial);
        systemUser.setSysUserId(loggedOnUserId);

        return systemUser;
    }

    private void disableNavigationButtons(HttpServletRequest request) {
        request.setAttribute(PREVIOUS_DISABLED, TRUE);
        request.setAttribute(NEXT_DISABLED, TRUE);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "unifiedSystemUserDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "redirect:/MasterListsPage.do";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/UnifiedSystemUser.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "unifiedSystemUserDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        String id = request.getParameter(ID);
        boolean isNew = GenericValidator.isBlankOrNull(id) || "0".equals(id);
        return isNew ? "unifiedSystemUser.add.title" : "unifiedSystemUser.edit.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        String id = request.getParameter(ID);
        boolean isNew = GenericValidator.isBlankOrNull(id) || "0".equals(id);
        return isNew ? "unifiedSystemUser.add.title" : "unifiedSystemUser.edit.title";
    }
}
