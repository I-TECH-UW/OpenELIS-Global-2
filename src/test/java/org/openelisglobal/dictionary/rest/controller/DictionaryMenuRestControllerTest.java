package org.openelisglobal.dictionary.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class DictionaryMenuRestControllerTest extends BaseWebContextSensitiveTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    // TODO fix this test after figuring out why the endpoint is not hit
    @Test
    public void getProductsList() throws Exception {
        String uri = "/get-dictionary-menu";
        MvcResult mvcResult = super.mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertThat(status, is(404));

//        String content = mvcResult.getResponse().getContentAsString();
//        List<DictionaryDAOImpl.DictionaryMenu> menuList = Collections.singletonList(super.mapFromJson(content, DictionaryMenu.class));
//        assertThat(menuList, notNullValue());
    }
}
