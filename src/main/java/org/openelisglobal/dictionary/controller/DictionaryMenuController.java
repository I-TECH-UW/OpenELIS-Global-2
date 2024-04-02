package org.openelisglobal.dictionary.controller;

import java.util.List;

import org.openelisglobal.common.rest.BaseRestController;
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
public class DictionaryMenuController extends BaseRestController {

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping(value = "/rest/get-dictionary-menu", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> showDictionaryMenu(String dictionaryCategoryName, String dictEntry, String isActive, String localAbbreviation) {
        return dictionaryService.showDictionaryMenu(dictionaryCategoryName, dictEntry, isActive, localAbbreviation);
    }

    @RequestMapping(value = "dictionarymenu/{dictEntry}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<Dictionary>> searchByDictEntry(@PathVariable String dictEntry) {
        if (dictEntry == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return (ResponseEntity<List<Dictionary>>) dictionaryService.searchByDictEntry(dictEntry);
    }
}
