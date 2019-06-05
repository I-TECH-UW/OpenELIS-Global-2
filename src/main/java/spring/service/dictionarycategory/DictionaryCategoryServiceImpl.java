package spring.service.dictionarycategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dictionarycategory.dao.DictionaryCategoryDAO;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;

@Service
public class DictionaryCategoryServiceImpl extends BaseObjectServiceImpl<DictionaryCategory, String> implements DictionaryCategoryService {
	@Autowired
	protected DictionaryCategoryDAO baseObjectDAO;

	DictionaryCategoryServiceImpl() {
		super(DictionaryCategory.class);
	}

	@Override
	protected DictionaryCategoryDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(DictionaryCategory dictionaryCategory) {
        getBaseObjectDAO().getData(dictionaryCategory);

	}

	@Override
	public void deleteData(List dictionaryCategorys) {
        getBaseObjectDAO().deleteData(dictionaryCategorys);

	}

	@Override
	public void updateData(DictionaryCategory dictionaryCategory) {
        getBaseObjectDAO().updateData(dictionaryCategory);

	}

	@Override
	public boolean insertData(DictionaryCategory dictionaryCategory) {
        return getBaseObjectDAO().insertData(dictionaryCategory);
	}

	@Override
	public DictionaryCategory getDictionaryCategoryByName(String name) {
        return getBaseObjectDAO().getDictionaryCategoryByName(name);
	}

	@Override
	public List getAllDictionaryCategorys() {
        return getBaseObjectDAO().getAllDictionaryCategorys();
	}

	@Override
	public List getNextDictionaryCategoryRecord(String id) {
        return getBaseObjectDAO().getNextDictionaryCategoryRecord(id);
	}

	@Override
	public List getPageOfDictionaryCategorys(int startingRecNo) {
        return getBaseObjectDAO().getPageOfDictionaryCategorys(startingRecNo);
	}

	@Override
	public Integer getTotalDictionaryCategoryCount() {
        return getBaseObjectDAO().getTotalDictionaryCategoryCount();
	}

	@Override
	public List getPreviousDictionaryCategoryRecord(String id) {
        return getBaseObjectDAO().getPreviousDictionaryCategoryRecord(id);
	}
}
