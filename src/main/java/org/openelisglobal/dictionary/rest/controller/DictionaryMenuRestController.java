package org.openelisglobal.dictionary.rest.controller;

import java.util.List;

import org.openelisglobal.common.rest.BaseRestController;
import org.openelisglobal.dictionary.daoimpl.DictionaryDAOImpl;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class DictionaryMenuRestController extends BaseRestController {

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping(value = "/rest/get-dictionary-menu", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<DictionaryDAOImpl.DictionaryMenu> showDictionaryMenu() {
        return dictionaryService.showDictionaryMenu();
    }
}

