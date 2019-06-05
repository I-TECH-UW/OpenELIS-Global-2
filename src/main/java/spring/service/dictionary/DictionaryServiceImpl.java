package spring.service.dictionary;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSFrozenRecordException;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;

@Service
public class DictionaryServiceImpl extends BaseObjectServiceImpl<Dictionary, String> implements DictionaryService {
	@Autowired
	protected DictionaryDAO baseObjectDAO;

	DictionaryServiceImpl() {
		super(Dictionary.class);
	}

	@Override
	protected DictionaryDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public Dictionary update(Dictionary dictionary) {
		if (baseObjectDAO.duplicateDictionaryExists(dictionary)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + dictionary.getDictEntry());
		} else {
			return super.update(dictionary);
		}

	}

	@Override
	@Transactional
	public void delete(Dictionary dictionary) {
		delete(dictionary.getId(), dictionary.getSysUserId());
	}

	@Override
	@Transactional
	public void delete(String id, String sysUserId) {
		Dictionary oldData = baseObjectDAO.get(id).orElseThrow(() -> new ObjectNotFoundException(id, Dictionary.class.getName()));
		oldData.setIsActive(IActionConstants.NO);
		oldData.setSysUserId(sysUserId);
		super.update(oldData);
	}

	@Override
	@Transactional
	public void deleteAll(List<Dictionary> dictionaries) {
		for (Dictionary dictionary : dictionaries) {
			delete(dictionary);
		}
	}

	@Override
	@Transactional
	public void deleteAll(List<String> ids, String sysUserId) {
		for (String id : ids) {
			delete(id, sysUserId);
		}
	}

	@Override
	@Transactional
	public void update(Dictionary dictionary, boolean isDictionaryFrozenCheckRequired) {
		if (baseObjectDAO.duplicateDictionaryExists(dictionary)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + dictionary.getDictEntry());
		}
		if (isDictionaryFrozenCheckRequired && baseObjectDAO.isDictionaryFrozen(dictionary)) {
			throw new LIMSFrozenRecordException("Dictionary Entry is referenced " + dictionary.getDictEntry());
		}
		super.update(dictionary);
	}

	@Override
	@Transactional
	public List<Dictionary> getPagesOfSearchedDictionaries(int startingRecNo, String searchString) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add("dictionaryCategory.categoryName");
		orderProperties.add("dictEntry");
		return baseObjectDAO.getLikeOrderedPage("dictEntry", searchString, orderProperties, false, startingRecNo);
	}

	@Override
	@Transactional
	public int getCountSearchedDictionaries(String searchString) {
		return getCountLike("dictEntry", searchString);
	}

	@Override
	@Transactional
	public List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String fieldName, String fieldValue, boolean orderByDictEntry) {
		return baseObjectDAO.getDictionaryEntrysByCategoryAbbreviation(fieldName, fieldValue, orderByDictEntry);
	}

	@Override
	@Transactional
	public List<Dictionary> getDictionaryEntriesByCategoryId(String dictionaryCategoryId) {
		return baseObjectDAO.getAllMatching("dictionaryCategory.id", dictionaryCategoryId);
	}

	@Override
	public void getData(Dictionary dictionary) {
        getBaseObjectDAO().getData(dictionary);

	}

	@Override
	public void deleteData(List dictionarys) {
        getBaseObjectDAO().deleteData(dictionarys);

	}

	@Override
	public void updateData(Dictionary dictionary, boolean isDictionaryFrozenCheckRequired) {
        getBaseObjectDAO().updateData(dictionary,isDictionaryFrozenCheckRequired);

	}

	@Override
	public boolean insertData(Dictionary dictionary) {
        return getBaseObjectDAO().insertData(dictionary);
	}

	@Override
	public Dictionary getDictionaryByLocalAbbrev(Dictionary dictionary) {
        return getBaseObjectDAO().getDictionaryByLocalAbbrev(dictionary);
	}

	@Override
	public Dictionary getDictionaryById(String dictionaryId) {
        return getBaseObjectDAO().getDictionaryById(dictionaryId);
	}

	@Override
	public boolean duplicateDictionaryExists(Dictionary dictionary) {
        return getBaseObjectDAO().duplicateDictionaryExists(dictionary);
	}

	@Override
	public List getAllDictionarys() {
        return getBaseObjectDAO().getAllDictionarys();
	}

	@Override
	public boolean isDictionaryFrozen(Dictionary dictionary) {
        return getBaseObjectDAO().isDictionaryFrozen(dictionary);
	}

	@Override
	public List getNextDictionaryRecord(String id) {
        return getBaseObjectDAO().getNextDictionaryRecord(id);
	}

	@Override
	public List getPreviousDictionaryRecord(String id) {
        return getBaseObjectDAO().getPreviousDictionaryRecord(id);
	}

	@Override
	public List getPagesOfSearchedDictionarys(int startRecNo, String searchString) {
        return getBaseObjectDAO().getPagesOfSearchedDictionarys(startRecNo,searchString);
	}

	@Override
	public Dictionary getDictionaryByDictEntry(String dictEntry) {
        return getBaseObjectDAO().getDictionaryByDictEntry(dictEntry);
	}

	@Override
	public Dictionary getDictionaryByDictEntry(Dictionary dictionary, boolean ignoreCase) {
        return getBaseObjectDAO().getDictionaryByDictEntry(dictionary,ignoreCase);
	}

	@Override
	public Integer getTotalDictionaryCount() {
        return getBaseObjectDAO().getTotalDictionaryCount();
	}

	@Override
	public Integer getTotalSearchedDictionaryCount(String searchString) {
        return getBaseObjectDAO().getTotalSearchedDictionaryCount(searchString);
	}

	@Override
	public List getPageOfDictionarys(int startingRecNo) {
        return getBaseObjectDAO().getPageOfDictionarys(startingRecNo);
	}

	@Override
	public List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String dictionaryCategory) {
        return getBaseObjectDAO().getDictionaryEntrysByCategoryAbbreviation(dictionaryCategory);
	}

	@Override
	public List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String filter, String dictionaryCategory) {
        return getBaseObjectDAO().getDictionaryEntrysByCategoryAbbreviation(filter,dictionaryCategory);
	}

	@Override
	public Dictionary getDictionaryEntrysByNameAndCategoryDescription(String dictionaryName, String categoryDescription) {
        return getBaseObjectDAO().getDictionaryEntrysByNameAndCategoryDescription(dictionaryName,categoryDescription);
	}

	@Override
	public List<Dictionary> getDictionaryEntrysByCategoryNameLocalizedSort(String dictionaryCategoryName) {
        return getBaseObjectDAO().getDictionaryEntrysByCategoryNameLocalizedSort(dictionaryCategoryName);
	}

	@Override
	public Dictionary getDataForId(String dictId) {
        return getBaseObjectDAO().getDataForId(dictId);
	}
}
