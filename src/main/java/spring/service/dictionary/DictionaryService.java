package spring.service.dictionary;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;

public interface DictionaryService extends BaseObjectService<Dictionary> {

	void update(Dictionary dictionary, boolean isDictionaryFrozenCheckRequired);

	List<Dictionary> getPagesOfSearchedDictionaries(int startingRecNo, String searchString);

	int getCountSearchedDictionaries(String searchString);

	List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String fieldName, String fieldValue,
			boolean orderByDictEntry);

	List<Dictionary> getDictionaryEntriesByCategoryId(String dictionaryCategoryId);
}
