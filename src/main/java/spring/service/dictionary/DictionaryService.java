package spring.service.dictionary;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;

public interface DictionaryService extends BaseObjectService<Dictionary> {
	void getData(Dictionary dictionary);

	void deleteData(List dictionarys);

	void updateData(Dictionary dictionary, boolean isDictionaryFrozenCheckRequired);

	boolean insertData(Dictionary dictionary);

	Dictionary getDictionaryByLocalAbbrev(Dictionary dictionary);

	Dictionary getDictionaryById(String dictionaryId);

	boolean duplicateDictionaryExists(Dictionary dictionary);

	List getAllDictionarys();

	boolean isDictionaryFrozen(Dictionary dictionary);

	List getNextDictionaryRecord(String id);

	List getPreviousDictionaryRecord(String id);

	List<Dictionary> getDictionaryEntriesByCategoryId(String categoryId);

	List getPagesOfSearchedDictionarys(int startRecNo, String searchString);

	Dictionary getDictionaryByDictEntry(String dictEntry);

	Dictionary getDictionaryByDictEntry(Dictionary dictionary, boolean ignoreCase);

	Integer getTotalDictionaryCount();

	Integer getTotalSearchedDictionaryCount(String searchString);

	List getPageOfDictionarys(int startingRecNo);

	List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String dictionaryCategory);

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
