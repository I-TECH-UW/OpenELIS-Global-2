package spring.service.dictionary;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;

public interface DictionaryService extends BaseObjectService<Dictionary, String> {
	void getData(Dictionary dictionary);

	Dictionary getDictionaryByLocalAbbrev(Dictionary dictionary);

	Dictionary getDictionaryById(String dictionaryId);

	boolean duplicateDictionaryExists(Dictionary dictionary);

	List getAllDictionarys();

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
