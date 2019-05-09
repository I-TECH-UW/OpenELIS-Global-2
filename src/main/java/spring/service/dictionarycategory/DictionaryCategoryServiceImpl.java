package spring.service.dictionarycategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dictionarycategory.dao.DictionaryCategoryDAO;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;

@Service
public class DictionaryCategoryServiceImpl extends BaseObjectServiceImpl<DictionaryCategory> implements DictionaryCategoryService {
  @Autowired
  protected DictionaryCategoryDAO baseObjectDAO;

  DictionaryCategoryServiceImpl() {
    super(DictionaryCategory.class);
  }

  @Override
  protected DictionaryCategoryDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
