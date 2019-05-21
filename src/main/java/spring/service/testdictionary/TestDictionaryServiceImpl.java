package spring.service.testdictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.testdictionary.dao.TestDictionaryDAO;
import us.mn.state.health.lims.testdictionary.valueholder.TestDictionary;

@Service
public class TestDictionaryServiceImpl extends BaseObjectServiceImpl<TestDictionary> implements TestDictionaryService {
  @Autowired
  protected TestDictionaryDAO baseObjectDAO;

  TestDictionaryServiceImpl() {
    super(TestDictionary.class);
  }

  @Override
  protected TestDictionaryDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
