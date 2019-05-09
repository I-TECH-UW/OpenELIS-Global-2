package spring.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.valueholder.Person;

@Service
public class PersonServiceImpl extends BaseObjectServiceImpl<Person> implements PersonService {
  @Autowired
  protected PersonDAO baseObjectDAO;

  PersonServiceImpl() {
    super(Person.class);
  }

  @Override
  protected PersonDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
