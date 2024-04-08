package org.openelisglobal.dictionary.daoimpl;

import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.dictionary.dao.DictionaryDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DictionaryDAOImplTest extends BaseWebContextSensitiveTest {

    @Autowired
    private DictionaryDAO dao;

    @Test
    public void showDictionaryMenu() {
        List<DictionaryDAOImpl.DictionaryMenu> menuList = dao.showDictionaryMenu();

        assertThat(menuList, notNullValue());
        assertThat(menuList.get(0).getId(),is("2"));
        assertThat(menuList.get(0).getCategoryName(),is("CG"));
        assertThat(menuList.get(0).getDictEntry(),is("INFLUENZA VIRUS A/H1 RNA DETECTED"));
        assertThat(menuList.get(0).getIsActive(),is("Y"));
        assertThat(menuList.get(0).getLocalAbbreviation(),is("CG"));
    }
}