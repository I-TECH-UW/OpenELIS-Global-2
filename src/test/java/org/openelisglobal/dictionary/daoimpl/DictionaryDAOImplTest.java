package org.openelisglobal.dictionary.daoimpl;

import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.dictionary.dao.DictionaryDAO;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.dictionarycategory.valueholder.DictionaryCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class DictionaryDAOImplTest extends BaseWebContextSensitiveTest {

    @Autowired
    private DictionaryDAO dao;

    @Test
    public void showDictionaryMenu_shouldReturnDictionaryMenu() {
        List<DictionaryDAOImpl.DictionaryMenu> menuList = dao.showDictionaryMenu();

        assertThat(menuList, notNullValue());
        assertThat(menuList.get(0).getId(),is("2"));
        assertThat(menuList.get(0).getCategoryName(),is("CG"));
        assertThat(menuList.get(0).getDictEntry(),is("INFLUENZA VIRUS A/H1 RNA DETECTED"));
        assertThat(menuList.get(0).getIsActive(),is("Y"));
        assertThat(menuList.get(0).getLocalAbbreviation(),is("CG"));
    }

    @Test
    public void saveDictionaryCategory_shouldCreateDictionaryMenu() {
        Dictionary dictionary = new Dictionary();
        dictionary.setId("99566");

        DictionaryCategory category = new DictionaryCategory();
        category.setId("10234");
        dictionary.setSortOrder(124);
        category.setLocalAbbreviation("HEC");
        category.setCategoryName("herman category");
        dao.saveDictionaryCategory(category);

        dictionary.setDictionaryCategory(category);
        dictionary.setDictEntry("herman entry");
        dictionary.setIsActive("N");
        dictionary.setLocalAbbreviation("HEC");

        dao.saveDictionaryMenu(dictionary);
        Dictionary savedMenu = dao.saveDictionaryMenu(dictionary);
        assertEquals("herman category", savedMenu.getDictionaryCategory().getCategoryName());
    }

    @Test
    public void fetchDictionaryCategoryDescriptions_shouldReturnDictionaryCategoryDescriptions() {
        List<String> menuList = dao.fetchDictionaryCategoryDescriptions();

        assertThat(menuList, notNullValue());
        assertThat(menuList.get(0),is("VIROLOGY"));
    }
}