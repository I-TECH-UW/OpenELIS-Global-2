package org.openelisglobal.dictionary.rest.controller;

import java.util.List;

import org.openelisglobal.common.rest.BaseRestController;
import org.openelisglobal.dictionary.daoimpl.DictionaryDAOImpl;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.dictionarycategory.valueholder.DictionaryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@SuppressWarnings("unused")
public class DictionaryMenuRestController extends BaseRestController {

    private final Logger log = LoggerFactory.getLogger(DictionaryMenuRestController.class);

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/rest/get-dictionary-menu", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<DictionaryDAOImpl.DictionaryMenu> showDictionaryMenu() {
        return dictionaryService.showDictionaryMenu();
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
}
