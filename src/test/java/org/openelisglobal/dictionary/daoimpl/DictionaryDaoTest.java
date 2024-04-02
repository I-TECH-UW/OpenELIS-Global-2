package org.openelisglobal.dictionary;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.dictionary.daoimpl.DictionaryDAOImpl;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.dictionarycategory.valueholder.DictionaryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { BaseTestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class DictionaryDaoTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DictionaryDAOImpl dictionaryDAO;

    @BeforeEach
    public void runBeforeEachTest() {
        dictionaryDAO = new DictionaryDAOImpl();
        dictionaryDAO.setSessionFactory(sessionFactory);
        //dictionary-category
        DictionaryCategory category = new DictionaryCategory();
        category.setId("1202");
        category.setCategoryName("Dao Test");
        category.setDescription("it is just for testing purposes!");
        category.setLocalAbbreviation("DT");
        category.setSysUserId("1");
        category.setLastupdated(new Timestamp(500));

        //dictionary
        Dictionary dictionary = new Dictionary();
        dictionary.setId("1000");
        dictionary.setDictionaryCategory(category);
        dictionary.setSysUserId("1");
        dictionary.setIsActive("Y");
        dictionary.setSortOrder(1);
        dictionary.setDictEntry("Just For Tests");
    }

    @Test
    public void showDictionaryMenu_shouldShowAllMenu() {
        Collection<Dictionary> dictionaries = dictionaryDAO.showDictionaryMenu("Dao Test","Just For Tests","Y","DT");
        assertEquals(1, dictionaries.size());
    }
}
