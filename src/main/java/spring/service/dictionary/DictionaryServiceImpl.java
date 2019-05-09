package spring.service.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
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
    return baseObjectDAO;}
}
