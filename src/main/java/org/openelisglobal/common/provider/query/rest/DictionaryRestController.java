package org.openelisglobal.common.provider.query.rest;

import java.util.Collections;
import java.util.List;

import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class DictionaryRestController {

	@Autowired
	private DisplayListService displayListService;

	@GetMapping(value = "dictionary", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<IdValuePair> getDictionaryItemByCategory(@RequestParam String category) {
		if (GenericValidator.isBlankOrNull(category)) {
			return Collections.emptyList();
		}
		List<IdValuePair> dictionaryEntries = displayListService.createFromDictionaryCategoryLocalizedSort(category);
		if (dictionaryEntries.isEmpty()) {
			return Collections.emptyList();
		} else {
			return dictionaryEntries;
		}
	}
}
