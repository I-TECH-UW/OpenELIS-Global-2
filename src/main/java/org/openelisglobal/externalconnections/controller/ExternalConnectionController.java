package org.openelisglobal.externalconnections.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.externalconnections.form.ExternalConnectionForm;
import org.openelisglobal.externalconnections.service.ExternalConnectionAuthenticationDataService;
import org.openelisglobal.externalconnections.service.ExternalConnectionContactService;
import org.openelisglobal.externalconnections.service.ExternalConnectionService;
import org.openelisglobal.externalconnections.valueholder.BasicAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.CertificateAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.AuthType;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection.ProgrammedConnection;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionAuthenticationData;
import org.openelisglobal.externalconnections.valueholder.ExternalConnectionContact;
import org.openelisglobal.internationalization.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExternalConnectionController extends BaseController {

    @Autowired
    private ExternalConnectionService externalConnectionService;
    @Autowired
    private ExternalConnectionContactService externalConnectionContactService;
    @Autowired
    private ExternalConnectionAuthenticationDataService externalConnectionAuthenticationDataService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    @GetMapping(value = "/ExternalConnection")
    public ModelAndView viewExternalConnection(
            @RequestParam(value = ID, required = false) Integer externalConnectionId) {
        ExternalConnectionForm form = new ExternalConnectionForm();
        form.setCancelAction("ExternalConnectionsMenu.do");
        form.setCancelMethod(RequestMethod.GET);

        form.setFormAction("ExternalConnection.do");
        form.setFormName("ExternalConnectionForm");
        form.setFormMethod(RequestMethod.POST);

        if (null == externalConnectionId || 0 == externalConnectionId) {
            prepareEmptyForm(form);
        } else {
            fillForm(externalConnectionId, form);
        }

        request.getSession().setAttribute(SAVE_DISABLED, TRUE);
        return findForward(FWD_SUCCESS, form);
    }

    @PostMapping(value = "/ExternalConnection")
    public ModelAndView addEditExternalConnection(@ModelAttribute("form") ExternalConnectionForm form,
            @RequestParam(value = ID, required = false) Integer externalConnectionId) {

        List<ExternalConnectionContact> externalConnectionContacts = form.getExternalConnectionContacts() == null
                ? new ArrayList<>()
                : form.getExternalConnectionContacts();
        ExternalConnection externalConnection = form.getExternalConnection();
        Map<AuthType, ExternalConnectionAuthenticationData> externalConnectionAuthData = new HashMap<>();
        if (form.getBasicAuthenticationData() != null) {
            externalConnectionAuthData.put(AuthType.BASIC, form.getBasicAuthenticationData());
        }
        if (form.getCertificateAuthenticationData() != null) {
            externalConnectionAuthData.put(AuthType.CERTIFICATE, form.getCertificateAuthenticationData());
        }

        if (null == externalConnectionId || 0 == externalConnectionId) {
            externalConnectionService.createNewExternalConnection(externalConnectionAuthData,
                    externalConnectionContacts, externalConnection);
        } else {
            externalConnectionService.updateExternalConnection(externalConnectionAuthData, externalConnectionContacts,
                    externalConnection);
        }
        ConfigurationProperties.forceReload();
        return findForward(FWD_SUCCESS_INSERT, form);
    }

    private void fillForm(Integer externalConnectionId, @Valid @ModelAttribute("form") ExternalConnectionForm form) {
        request.setAttribute(IActionConstants.PAGE_SUBTITLE_KEY,
                MessageUtil.getMessage("externalconnections.edit.title"));
        form.setAuthenticationTypes(Arrays.asList(AuthType.values()));
        form.setProgrammedConnections(Arrays.asList(ProgrammedConnection.values()));

        form.setExternalConnection(externalConnectionService.get(externalConnectionId));
        form.setExternalConnectionContacts(
                externalConnectionContactService.getAllMatching("externalConnection.id", externalConnectionId));
        Map<AuthType, ExternalConnectionAuthenticationData> externalConnectionAuthData = externalConnectionAuthenticationDataService
                .getForExternalConnection(externalConnectionId);
        form.setBasicAuthenticationData((BasicAuthenticationData) externalConnectionAuthData.get(AuthType.BASIC));
        form.setCertificateAuthenticationData(
                (CertificateAuthenticationData) externalConnectionAuthData.get(AuthType.CERTIFICATE));

    }

    private void prepareEmptyForm(ExternalConnectionForm form) {
        request.setAttribute(IActionConstants.PAGE_SUBTITLE_KEY,
                MessageUtil.getMessage("externalconnections.add.title"));
        form.setAuthenticationTypes(Arrays.asList(AuthType.values()));
        form.setProgrammedConnections(Arrays.asList(ProgrammedConnection.values()));

        form.setExternalConnection(new ExternalConnection());
        form.setBasicAuthenticationData(new BasicAuthenticationData());
        form.setCertificateAuthenticationData(new CertificateAuthenticationData());
        form.setExternalConnectionContacts(new ArrayList<>());
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "externalConnectionDefinition";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/ExternalConnectionsMenu.do";
        }
        return "PageNotFound";
    }

    @Override
    protected String getPageTitleKey() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getPageSubtitleKey() {
        // TODO Auto-generated method stub
        return null;
    }

}
