package org.openelisglobal.dictionary.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.form.AdminOptionMenuForm;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.rest.BaseRestController;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.dictionary.daoimpl.DictionaryDAOImpl;
import org.openelisglobal.dictionary.form.DictionaryMenuForm;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.menu.service.AdminMenuItemService;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
@SuppressWarnings("unused")
public class DictionaryMenuRestController extends BaseRestController {

    private static final int PREVIOUS = 1;

    private static final int NEXT = 2;

    private final Logger log = LoggerFactory.getLogger(DictionaryMenuRestController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/rest/dictionary-menu", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showDictionaryMenu(HttpServletRequest request) throws  IOException {
        DictionaryMenuForm form = new DictionaryMenuForm();
        String forward = performMenuAction(form, request);

        if (FWD_FAIL.equals(forward)) {
            Errors errors = new BaseErrors();
            errors.reject("error.generic");
            String jsonErrors = errorsToJson(errors);
            return new ResponseEntity<>(jsonErrors, HttpStatus.BAD_REQUEST);

        } else {
            request.setAttribute("menuDefinition", "DictionaryMenuDefinition");
            addFlashMsgsToRequest(request);
            return convertFormToJsonAndAddToResponse(form);
        }
    }

    @RequestMapping(value = "/rest/dictionary-categories/descriptions", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<DictionaryDAOImpl.DictionaryDescription> fetchDictionaryCategoryDescriptions() {
        return dictionaryService.fetchDictionaryCategoryDescriptions();
    }

    @RequestMapping(value = "/rest/create-dictionary", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createDictionaryEntry(@RequestBody Dictionary dictionary) {
        try {
            dictionaryService.saveDictionaryMenu(dictionary);
            log.info("contents of the newly created dictionary: " + dictionary);
            return new ResponseEntity<>("Dictionary created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating dictionary: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @SuppressWarnings("unchecked")
    private void addFlashMsgsToRequest(HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            Boolean success = (Boolean) inputFlashMap.get(FWD_SUCCESS);
            request.setAttribute(FWD_SUCCESS, success);

            String successMessage = (String) inputFlashMap.get(Constants.SUCCESS_MSG);
            request.setAttribute(Constants.SUCCESS_MSG, successMessage);

            Errors errors = (Errors) inputFlashMap.get(Constants.REQUEST_ERRORS);
            request.setAttribute(Constants.SUCCESS_MSG, errors);

            List<String> messages = (List<String>) inputFlashMap.get(Constants.REQUEST_MESSAGES);
            request.setAttribute(Constants.REQUEST_MESSAGES, messages);

            List<String> warnings = (List<String>) inputFlashMap.get(Constants.REQUEST_WARNINGS);
            request.setAttribute(Constants.SUCCESS_MSG, warnings);
        }
    }

    private String performMenuAction(DictionaryMenuForm form, HttpServletRequest request) {
        String forward = FWD_SUCCESS;
        int action = -1;
        if (!StringUtil.isNullorNill(request.getParameter("paging"))) {
            action = Integer.parseInt(request.getParameter("paging"));
        }

        List<Dictionary> menuList = null;
        try {
            switch (action) {
                case PREVIOUS:
                    menuList = doPreviousPage(form, request);
                    break;
                case NEXT:
                    menuList = doNextPage(form, request);
                    break;
                default:
                    menuList = doNone(form, request);
            }
        } catch (RuntimeException e) {
            LogEvent.logError(e);
            forward = FWD_FAIL;
        }

        form.setMenuList(menuList);
        form.setAdminMenuItems(SpringContext.getBean(AdminMenuItemService.class).getActiveItemsSorted());
        request.setAttribute(DEACTIVATE_DISABLED, getDeactivateDisabled());
        request.setAttribute(ADD_DISABLED, getAddDisabled());
        request.setAttribute(EDIT_DISABLED, getEditDisabled());

        List<String> selectedIDs = new ArrayList<>();
        form.setSelectedIDs(selectedIDs);
        return forward;
    }

    List<Dictionary> doNextPage(DictionaryMenuForm form, HttpServletRequest request) {
        int startingRecNo = getCurrentStartingRecNo(request);
        LogEvent.logTrace("BaseMenuAction", "performAction()", "current start " + startingRecNo);
        int nextStartingRecNo = startingRecNo + getPageSize();
        LogEvent.logTrace("BaseMenuAction", "performAction()", "next start " + nextStartingRecNo);
        String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
        request.setAttribute("startingRecNo", stringNextStartingRecNo);

        List<Dictionary> nextPageList = createMenuList(form, request);
        request.setAttribute(PREVIOUS_DISABLED, "false");

        if (getPageSize() > 0 && nextPageList.size() > getPageSize()) {
            request.setAttribute(NEXT_DISABLED, "false");
            nextPageList = nextPageList.subList(0, getPageSize());
        } else {
            request.setAttribute(NEXT_DISABLED, "true");
        }
        return nextPageList;
    }

    private List<Dictionary> doNone(DictionaryMenuForm form, HttpServletRequest request) {
        int nextStartingRecNo = getCurrentStartingRecNo(request);
        String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
        request.setAttribute("startingRecNo", stringNextStartingRecNo);

        List<Dictionary> samePageList = createMenuList(form, request);

        if (nextStartingRecNo <= 1) {
            request.setAttribute(PREVIOUS_DISABLED, "true");
        }

        if (getPageSize() > 0 && samePageList.size() > getPageSize()) {
            request.setAttribute(NEXT_DISABLED, "false");
            samePageList = samePageList.subList(0, getPageSize());
        } else {
            request.setAttribute(NEXT_DISABLED, "true");
        }
        return samePageList;
    }

    private List<Dictionary> doPreviousPage(DictionaryMenuForm form, HttpServletRequest request) {
        int startingRecNo = getCurrentStartingRecNo(request);
        int nextStartingRecNo = startingRecNo - getPageSize();
        String stringNextStartingRecNo = String.valueOf(nextStartingRecNo);
        request.setAttribute("startingRecNo", stringNextStartingRecNo);

        List<Dictionary> previousPageList = createMenuList(form, request);
        request.setAttribute(NEXT_DISABLED, "false");

        if (getPageSize() > 0 && previousPageList.size() > getPageSize()) {
            request.setAttribute(PREVIOUS_DISABLED, "false");
            previousPageList = previousPageList.subList(0, getPageSize());
        } else {
            request.setAttribute(PREVIOUS_DISABLED, "true");
        }

        if (nextStartingRecNo <= 1) {
            request.setAttribute(PREVIOUS_DISABLED, "true");
        }

        return previousPageList;
    }

    private List<Dictionary> createMenuList(AdminOptionMenuForm<Dictionary> form, HttpServletRequest request) {
        List<Dictionary> dictionaries;
        String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
        int startingRecNo = Integer.parseInt(stringStartingRecNo);
        int total;
        if (YES.equals(request.getParameter("search"))) {
            dictionaries = dictionaryService.getPagesOfSearchedDictionaries(startingRecNo,
                    request.getParameter("searchString"));
            total = dictionaryService.getCountSearchedDictionaries(request.getParameter("searchString"));
        } else {
            dictionaries = dictionaryService.getPage(startingRecNo);
            total = dictionaryService.getCount();
        }

        request.setAttribute("menuDefinition", "DictionaryMenuDefinition");
        request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(total));
        request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));

        int numOfRecs = 0;
        if (dictionaries.size() > SystemConfiguration.getInstance().getDefaultPageSize()) {
            numOfRecs = SystemConfiguration.getInstance().getDefaultPageSize();
        } else {
            numOfRecs = dictionaries.size();
        }

        numOfRecs--;
        int endingRecNo = startingRecNo + numOfRecs;
        request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
        request.setAttribute(MENU_SEARCH_BY_TABLE_COLUMN, "dictionary.dictEntry");

        if (YES.equals(request.getParameter("search"))) {
            request.setAttribute(IN_MENU_SELECT_LIST_HEADER_SEARCH, "true");
        }

        return dictionaries;
    }

    private int getPageSize() {
        return SystemConfiguration.getInstance().getDefaultPageSize();
    }

    private int getCurrentStartingRecNo(HttpServletRequest request) {
        String stringStartingRecNo = "1";
        if (!StringUtil.isNullorNill((String) request.getAttribute("startingRecNo"))) {
            stringStartingRecNo = (String) request.getAttribute("startingRecNo");
        } else if (!StringUtil.isNullorNill(request.getParameter("startingRecNo"))) {
            stringStartingRecNo = request.getParameter("startingRecNo");
        }

        int startingRecNo = Integer.parseInt(stringStartingRecNo);
        if (startingRecNo <= 0) {
            startingRecNo = 1;
        }
        return startingRecNo;
    }

//    protected ResponseEntity<String> findForwardResponseEntity(String forward, DictionaryMenuForm form) {
//        String realForward = findForwardResponseEntity(forward);
//        if (realForward.startsWith("redirect:")) {
//            return ResponseEntity.status(HttpStatus.FOUND).body(realForward);
//        } else {
//            String viewName = pageBuilderService.setupJSPPage(realForward, request);
//            Model model = new ExtendedModelMap();
//            model.addAttribute("form", form);
////            try {
////                viewResolver.render(viewName, model, request, response);
////                return ResponseEntity.ok(getResponseData());
////            } catch (Exception e) {
////                log.error("Error rendering view: " + e.getMessage(), e);
////                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error rendering view");
////            }
//        }
//        return null;
//    }

//    protected String findForwardResponseEntity(String forward) {
//        if (LOGIN_PAGE.equals(forward)) {
//            return "redirect:LoginPage";
//        }
//        if (HOME_PAGE.equals(forward)) {
//            return "redirect:Home";
//        }
//        String forwardView = findLocalForward(forward);
//
//        if (GenericValidator.isBlankOrNull(forwardView)) {
//            forwardView = "PageNotFound";
//        }
//        return forwardView;
//    }

    protected void setPageTitles(HttpServletRequest request, DictionaryMenuForm form) {
        String pageSubtitle = null;
        String pageTitle = null;
        String pageTitleKey = getPageTitleKey(request, form);
        String pageSubtitleKey = getPageSubtitleKey(request, form);
        String pageTitleKeyParameter = getPageTitleKeyParameter(request, form);
        String pageSubtitleKeyParameter = getPageSubtitleKeyParameter(request, form);

        try {
            if (StringUtil.isNullorNill(pageTitleKeyParameter)) {
                pageTitle = getMessageForKey(request, pageTitleKey);
            } else {
                pageTitle = getMessageForKey(request, pageTitleKey, pageTitleKeyParameter);
            }
        } catch (RuntimeException e) {
            LogEvent.logError("could not get message for key: " + pageTitleKey, e);
        }

        try {
            if (StringUtil.isNullorNill(pageSubtitleKeyParameter)) {
                pageSubtitle = getMessageForKey(request, pageSubtitleKey);
            } else {
                pageSubtitle = getMessageForKey(request, pageSubtitleKey, pageSubtitleKeyParameter);
            }

        } catch (RuntimeException e) {
            LogEvent.logError("could not get message for key: " + pageSubtitleKey, e);
        }

        if (null != pageTitle) {
            request.setAttribute(PAGE_TITLE_KEY, pageTitle);
        }
        if (null != pageSubtitle) {
            request.setAttribute(PAGE_SUBTITLE_KEY, pageSubtitle);
        }
    }

    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "dictionaryMasterListsPageDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "redirect:/MasterListsPage";
        } else if (FWD_SUCCESS_DELETE.equals(forward)) {
            return "redirect:/DictionaryMenu";
        } else if (FWD_FAIL_DELETE.equals(forward)) {
            return "redirect:/DictionaryMenu";
        } else {
            return "PageNotFound";
        }
    }

    private String getEditDisabled() {
        return "false";
    }

    private String getAddDisabled() {
        return "false";
    }

    private String getDeactivateDisabled() {
        return "false";
    }

    private String getPageTitleKey(HttpServletRequest request, DictionaryMenuForm form) {
        return getPageTitleKey();
    }

    private String getPageTitleKeyParameter(HttpServletRequest request, DictionaryMenuForm form) {
        return null;
    }

    private String getPageSubtitleKey(HttpServletRequest request, DictionaryMenuForm form) {
        return getPageSubtitleKey();
    }

    private String getPageSubtitleKeyParameter(HttpServletRequest request, DictionaryMenuForm form) {
        return null;
    }

    private String getPageTitleKey() {
        return "dictionary.browse.title";
    }

    private String getPageSubtitleKey() {
        return "dictionary.browse.title";
    }

    private String getActualMessage(String message) {
        return message;
    }

    private String getMessageForKey(String messageKey) {
        String message = MessageUtil.getContextualMessage(messageKey);
        return MessageUtil.messageNotFound(message, messageKey) ? getActualMessage(messageKey) : message;
    }

    private String getMessageForKey(HttpServletRequest request, String messageKey) {
        if (null == messageKey) {
            return null;
        }
        String message = MessageUtil.getMessage(messageKey);
        return MessageUtil.messageNotFound(message, messageKey) ? getActualMessage(messageKey) : message;
    }

    private String getMessageForKey(HttpServletRequest request, String messageKey, String arg0) {
        if (null == messageKey) {
            return null;
        }
        String message = MessageUtil.getMessage(messageKey);
        return MessageUtil.messageNotFound(message, messageKey) ? getActualMessage(messageKey) : message;
    }
}
