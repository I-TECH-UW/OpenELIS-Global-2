package org.openelisglobal.login.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.login.form.LoginForm;
import org.openelisglobal.role.service.RoleService;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.openelisglobal.userrole.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginPageController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] { "loginName", "password" };

    @Value("${org.itech.login.saml:false}")
    private Boolean useSAML;
    @Value("${org.itech.login.oauth:false}")
    private Boolean useOAUTH;

    private static String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    @Autowired(required = false)
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    SystemUserService systemUserService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/LoginPage", method = RequestMethod.GET)
    public ModelAndView showLoginPage(HttpServletRequest request, Principal principal) {
        if (principal != null) {
            return new ModelAndView(findForward(HOME_PAGE));
        }

        String forward = FWD_SUCCESS;
        LoginForm form = new LoginForm();
        if (useSAML) {
            form.setUseSAML(useSAML);
        }
        if (useOAUTH) {
            Iterable<ClientRegistration> clientRegistrations = null;
            ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
            if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
                clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
            }

            clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(),
                    authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
            form.setOauthUrls(oauth2AuthenticationUrls);
        }

        // add flash attributes from other controllers into request
        addFlashMsgsToRequest(request);

        // add error messages from authentication fail controller
        Errors errors = (Errors) request.getSession().getAttribute(Constants.LOGIN_ERRORS);
        if (errors != null) {
            request.setAttribute(Constants.REQUEST_ERRORS, errors);
            request.getSession().removeAttribute(Constants.LOGIN_ERRORS);
        }

        form.setFormAction("ValidateLogin");

        return findForward(forward, form);
    }

    @GetMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSesssionDetails(HttpServletRequest request ,CsrfToken token) {
        boolean authenticated = !userModuleService.isSessionExpired(request);
        JSONObject sessionDetails = new JSONObject().put("authenticated", authenticated);
        sessionDetails.put("sessionId", request.getSession().getId());
        if (authenticated) {
            SystemUser user = systemUserService.get(getSysUserId(request));
            sessionDetails.put("userId", user.getId());
            sessionDetails.put("loginName", user.getLoginName());
            sessionDetails.put("firstName", user.getFirstName());
            sessionDetails.put("lastName", user.getLastName());
            sessionDetails.put("CSRF", token.getToken());

            JSONArray roleArray = new JSONArray();
            for (String roleId : userRoleService.getRoleIdsForUser(user.getId())) {
                roleArray.put(roleService.getRoleById(roleId).getName().trim());
            }
            sessionDetails.put("roles", roleArray);
        }
        return sessionDetails.toString();
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "loginPageDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "login.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "login.subTitle";
    }
}
