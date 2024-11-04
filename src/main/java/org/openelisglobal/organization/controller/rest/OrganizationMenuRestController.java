package org.openelisglobal.organization.controller.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import org.openelisglobal.common.controller.BaseMenuController;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.form.AdminOptionMenuForm;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.dataexchange.fhir.exception.FhirTransformationException;
import org.openelisglobal.organization.form.OrganizationMenuForm;
import org.openelisglobal.organization.service.OrganizationExportService;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.valueholder.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class OrganizationMenuRestController extends BaseMenuController<Organization> {

    private static final String[] ALLOWED_FIELDS = new String[] { "selectedIds*", "searchString" };

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationExportService organizationExportService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @GetMapping(value = { "/OrganizationMenu", "/SearchOrganizationMenu" })
    public ResponseEntity<Object> showOrganizationMenu(HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String forward;
        OrganizationMenuForm form = new OrganizationMenuForm();

        forward = performMenuAction(form, request);
        if (FWD_FAIL.equals(forward)) {
            Errors errors = new BaseErrors();
            errors.reject("error.generic");
            // redirectAttributes.addFlashAttribute(Constants.REQUEST_ERRORS, errors);
            // return findForward(forward, form);
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        } else {
            request.setAttribute("menuDefinition", "OrganizationMenuDefinition");
            addFlashMsgsToRequest(request);
            // return findForward(forward, form);
            return ResponseEntity.ok(findForward(forward, form));
        }
    }

    @GetMapping(value = "/OrganizationExport", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> exportOrganizations(
            @RequestParam(name = "active", defaultValue = "true") String active) throws FhirTransformationException {
        // return
        // organizationExportService.exportFhirOrganizationsFromOrganizations(Boolean.valueOf(active)).getBytes();
        try {
            byte[] data = organizationExportService.exportFhirOrganizationsFromOrganizations(Boolean.valueOf(active))
                    .getBytes();
            return ResponseEntity.ok().body(data);
        } catch (FhirTransformationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage().getBytes());
        }
    }

    @Override
    protected List<Organization> createMenuList(AdminOptionMenuForm<Organization> form, HttpServletRequest request)
            throws LIMSRuntimeException {

        // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "I am in
        // OrganizationMenuAction createMenuList()");

        List<Organization> organizations = new ArrayList<>();

        int startingRecNo = this.getCurrentStartingRecNo(request);

        if (YES.equals(request.getParameter("search"))) {
            organizations = organizationService.getPagesOfSearchedOrganizations(startingRecNo,
                    request.getParameter("searchString"));
        } else {
            organizations = organizationService.getOrderedPage("organizationName", false, startingRecNo);
        }

        request.setAttribute("menuDefinition", "OrganizationMenuDefinition");

        // bugzilla 1411 set pagination variables
        // bugzilla 2372 set pagination variables for searched results
        if (YES.equals(request.getParameter("search"))) {
            request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(
                    organizationService.getTotalSearchedOrganizationCount(request.getParameter("searchString"))));
            request.setAttribute(SEARCHED_STRING, request.getParameter("searchString"));
        } else {
            request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(organizationService.getCount()));
        }

        request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));
        int numOfRecs = 0;
        if (organizations != null) {
            if (organizations.size() > Integer
                    .parseInt(ConfigurationProperties.getInstance().getPropertyValue("page.defaultPageSize"))) {
                numOfRecs = Integer
                        .parseInt(ConfigurationProperties.getInstance().getPropertyValue("page.defaultPageSize"));
            } else {
                numOfRecs = organizations.size();
            }
            numOfRecs--;
        }
        int endingRecNo = startingRecNo + numOfRecs;
        request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
        // end bugzilla 1411

        // bugzilla 2372
        request.setAttribute(MENU_SEARCH_BY_TABLE_COLUMN, "organization.organizationName");
        // bugzilla 2372 set up a seraching mode so the next and previous action will
        // know
        // what to do

        if (YES.equals(request.getParameter("search"))) {

            request.setAttribute(IN_MENU_SELECT_LIST_HEADER_SEARCH, "true");
        }
        // setPaginationVariables(request, organizations);
        form.setToRecordCount(String.valueOf(endingRecNo));
        form.setFromRecordCount(String.valueOf(startingRecNo));
        form.setTotalRecordCount(String.valueOf(String.valueOf(organizationService.getCount())));

        return organizations;
    }

    @Override
    protected String getDeactivateDisabled() {
        return "false";
    }

    @Override
    protected int getPageSize() {
        return Integer.parseInt(ConfigurationProperties.getInstance().getPropertyValue("page.defaultPageSize"));
    }

    // gnr: Deactivate not Delete
    @PostMapping(value = "/DeleteOrganization")
    public ResponseEntity<Object> showDeleteOrganization(HttpServletRequest request,
            @RequestParam(value = ID, required = false) @Pattern(regexp = "[a-zA-Z0-9 -]*") String id,
            @RequestBody OrganizationMenuForm form, BindingResult result) throws LIMSRuntimeException {
        if (result.hasErrors()) {
            // redirectAttributes.addFlashAttribute(Constants.REQUEST_ERRORS, result);
            // findForward(FWD_FAIL_DELETE, form);
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        String[] IDs = id.split(",");
        List<String> selectedIDs = new ArrayList<>();
        for (int i = 0; i < IDs.length; i++) {
            selectedIDs.add(IDs[i]);
        }
        // List<String> selectedIDs = form.getSelectedIDs;
        List<Organization> organizations = new ArrayList<>();
        for (int i = 0; i < selectedIDs.size(); i++) {
            Organization organization = new Organization();
            organization.setId(selectedIDs.get(i));
            organization.setSysUserId(getSysUserId(request));
            organizations.add(organization);
        }

        try {
            // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "Going to
            // delete
            // Organization");
            organizationService.deactivateOrganizations(organizations);
            return ResponseEntity.ok(form);
            // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "Just
            // deleted
            // Organization");
        } catch (LIMSRuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e);

            String errorMsg;
            if (e.getCause() instanceof org.hibernate.StaleObjectStateException) {
                errorMsg = "errors.OptimisticLockException";
            } else {
                errorMsg = "errors.DeleteException";
            }
            result.reject(errorMsg);
            // redirectAttributes.addFlashAttribute(Constants.REQUEST_ERRORS, result);
            // return findForward(FWD_FAIL_DELETE, form);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.getAllErrors());
        }
        // redirectAttributes.addAttribute(FWD_SUCCESS, true);
        // return findForward(FWD_SUCCESS_DELETE, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "orgMasterListsPageDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "redirect:/MasterListsPage";
        } else if (FWD_SUCCESS_DELETE.equals(forward)) {
            return "redirect:/OrganizationMenu";
        } else if (FWD_FAIL_DELETE.equals(forward)) {
            return "redirect:/OrganizationMenu";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "organization.browse.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "organization.browse.title";
    }
}
