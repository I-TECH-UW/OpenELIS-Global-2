package spring.service.dictionarycategory;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;

public interface DictionaryCategoryService extends BaseObjectService<DictionaryCategory> {
	void getData(DictionaryCategory dictionaryCategory);

	void deleteData(List dictionaryCategorys);

	void updateData(DictionaryCategory dictionaryCategory);

	boolean insertData(DictionaryCategory dictionaryCategory);

	DictionaryCategory getDictionaryCategoryByName(String name);

	List getAllDictionaryCategorys();

	List getNextDictionaryCategoryRecord(String id);

	List getPageOfDictionaryCategorys(int startingRecNo);

	Integer getTotalDictionaryCategoryCount();

	List getPreviousDictionaryCategoryRecord(String id);
}
