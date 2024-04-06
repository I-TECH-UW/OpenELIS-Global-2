package org.openelisglobal.dictionary.daoimpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.AppTestConfig;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.dictionary.dao.DictionaryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTestConfig.class, AppTestConfig.class})
@ActiveProfiles("test")
public class DictionaryDAOImplTest {

    @Autowired
    private DictionaryDAO dao;

    @Test
    public void showDictionaryMenu() {
        List<DictionaryDAOImpl.DictionaryMenu> menuList = dao.showDictionaryMenu();

        assertThat(menuList, notNullValue());
        assertThat(menuList.get(0).getCategoryName(),is("CG"));
        assertThat(menuList.get(0).getDictEntry(),is("INFLUENZA VIRUS A/H1 RNA DETECTED"));
        assertThat(menuList.get(0).getIsActive(),is("Y"));
        assertThat(menuList.get(0).getLocalAbbreviation(),is("CG"));
    }
}