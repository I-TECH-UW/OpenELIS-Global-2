package org.openelisglobal.dictionary.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.dictionary.daoimpl.DictionaryDAOImpl;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.dictionarycategory.valueholder.DictionaryCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface DictionaryService extends BaseObjectService<Dictionary, String> {
    void getData(Dictionary dictionary);

    Dictionary getDictionaryByLocalAbbrev(Dictionary dictionary);
    
    public Dictionary getDictionaryByDictEntry(String dictEntry);

    Dictionary getDictionaryById(String dictionaryId);

    boolean duplicateDictionaryExists(Dictionary dictionary);

    boolean isDictionaryFrozen(Dictionary dictionary);

    List<Dictionary> getDictionaryEntriesByCategoryId(String categoryId);

    List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String fieldName, String fieldValue,
            boolean orderByDictEntry);

    List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String filter, String dictionaryCategory);

    Dictionary getDictionaryEntrysByNameAndCategoryDescription(String dictionaryName, String categoryDescription);

    List<Dictionary> getDictionaryEntrysByCategoryNameLocalizedSort(String dictionaryCategoryName);

    Dictionary getDataForId(String dictId);

    void update(Dictionary dictionary, boolean isDictionaryFrozenCheckRequired);

    int getCountSearchedDictionaries(String searchString);

    List<Dictionary> getPagesOfSearchedDictionaries(int startingRecNo, String searchString);
}
