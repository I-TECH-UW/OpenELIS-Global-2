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
public class DictionaryServiceImpl extends BaseObjectServiceImpl<Dictionary> implements DictionaryService {
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
		Dictionary oldData = baseObjectDAO.get(id)
				.orElseThrow(() -> new ObjectNotFoundException(id, Dictionary.class.getName()));
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
	public List<Dictionary> getDictionaryEntrysByCategoryAbbreviation(String fieldName, String fieldValue,
			boolean orderByDictEntry) {
		return baseObjectDAO.getDictionaryEntrysByCategoryAbbreviation(fieldName, fieldValue, orderByDictEntry);
	}

	@Override
	@Transactional 
	public List<Dictionary> getDictionaryEntriesByCategoryId(String dictionaryCategoryId) {
		return baseObjectDAO.getAllMatching("dictionaryCategory.id", dictionaryCategoryId);
	}
}
