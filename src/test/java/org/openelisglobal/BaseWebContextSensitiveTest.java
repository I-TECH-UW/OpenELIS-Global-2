package org.openelisglobal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTestConfig.class, TestConfig.class})
@WebAppConfiguration
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public abstract class BaseWebContextSensitiveTest {
    
    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .alwaysExpect(forwardedUrl(null)).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    public <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
