package org.openelisglobal.dictionary.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.dictionarycategory.service.DictionaryCategoryService;
import org.openelisglobal.dictionarycategory.valueholder.DictionaryCategory;
import org.springframework.beans.factory.annotation.Autowired;

public class DictionaryCategoryServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    DictionaryCategoryService dictionaryCategoryService;

    @Before
    public void setup() throws Exception {
        executeDataSetWithStateManagement("testdata/dictionary.xml");
    }

    @Test
    public void insert_shouldInsertNewDictionaryCategoryRecord() throws ParseException {
        DictionaryCategory dictionaryCategory = new DictionaryCategory();
        dictionaryCategory.setCategoryName("Category Description insert");
        dictionaryCategory.setDescription("For testing insert");
        dictionaryCategory.setLocalAbbreviation("TXT1");
        dictionaryCategory
                .setLastupdated(new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/1992").getTime()));

        String dictionaryCategoryId = dictionaryCategoryService.insert(dictionaryCategory);
        dictionaryCategory = dictionaryCategoryService.get(dictionaryCategoryId);

        Assert.assertEquals("443", dictionaryCategory.getId());
        Assert.assertEquals("Category Description insert", dictionaryCategory.getCategoryName());
        Assert.assertEquals("TXT1", dictionaryCategory.getLocalAbbreviation());
        Assert.assertEquals("For testing insert", dictionaryCategory.getDescription());
    }

    @Test
    public void save_shouldSaveNewDictionaryCategoryRecord() throws ParseException {
        DictionaryCategory dictionaryCategory = new DictionaryCategory();
        dictionaryCategory.setCategoryName("Category Description save");
        dictionaryCategory.setDescription("For testing save");
        dictionaryCategory.setLocalAbbreviation("TXT11");
        dictionaryCategory
                .setLastupdated(new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/1992").getTime()));

        dictionaryCategory = dictionaryCategoryService.save(dictionaryCategory);

        Assert.assertEquals("442", dictionaryCategory.getId());
        Assert.assertEquals("Category Description save", dictionaryCategory.getCategoryName());
        Assert.assertEquals("TXT11", dictionaryCategory.getLocalAbbreviation());
        Assert.assertEquals("For testing save", dictionaryCategory.getDescription());
    }

    @Test
    public void update_shouldUpdateExistingDictionaryCategoryRecord() {
        DictionaryCategory dictionaryCategory = dictionaryCategoryService.get("2");
        Assert.assertNotNull(dictionaryCategory);

        dictionaryCategory.setCategoryName("Updated for testing");
        dictionaryCategory = dictionaryCategoryService.update(dictionaryCategory);

        Assert.assertEquals("Updated for testing", dictionaryCategory.getCategoryName());
        Assert.assertEquals("Category Description 2", dictionaryCategory.getDescription());
        Assert.assertEquals("CA2", dictionaryCategory.getLocalAbbreviation());
        Assert.assertEquals("2", dictionaryCategory.getId());
    }

    @Test
    public void getDictionaryCategoryByName_shouldGetDictionaryCategoryByName() {
        DictionaryCategory dictionaryCategory = dictionaryCategoryService
                .getDictionaryCategoryByName("Category Name 2");
        Assert.assertNotNull(dictionaryCategory);
        Assert.assertEquals("Category Description 2", dictionaryCategory.getDescription());
        Assert.assertEquals("CA2", dictionaryCategory.getLocalAbbreviation());
        Assert.assertEquals("2", dictionaryCategory.getId());
    }

    private static DictionaryCategory getDictionaryCategory() throws ParseException {
        DictionaryCategory dictionaryCategory = new DictionaryCategory();
        dictionaryCategory.setCategoryName("Category Descriptionz");
        dictionaryCategory.setDescription("For testing insert");
        dictionaryCategory.setLocalAbbreviation("TXT1");
        dictionaryCategory
                .setLastupdated(new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/1992").getTime()));
        return dictionaryCategory;
    }
}
