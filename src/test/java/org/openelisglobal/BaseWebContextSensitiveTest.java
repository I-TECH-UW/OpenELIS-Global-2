package org.openelisglobal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = { BaseTestConfig.class, AppTestConfig.class })
@WebAppConfiguration
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public abstract class BaseWebContextSensitiveTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    private DataSource dataSource;

    protected MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jsonConverter.getObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jsonConverter.getObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return objectMapper.readValue(json, clazz);
    }

    /**
     * Executes a dataset from an XML file and inserts the data into the database.
     *
     * <p>
     * This method loads the specified dataset file from the classpath, establishes
     * a connection to the database, and performs a CLEAN_INSERT operation using the
     * dataset. CLEAN_INSERT first clears the existing data in the tables referenced
     * by the dataset and then inserts the new data.
     * </p>
     *
     * @param datasetFilename the name of the XML dataset file to load from the
     *                        classpath.
     * @throws IllegalArgumentException if the specified dataset file cannot be
     *                                  found in the classpath.
     *
     * @throws Exception                if any error occurs during database
     *                                  operations, including: Database connection
     *                                  failures, XML parsing errors, Data insertion
     *                                  failures, Resource cleanup issues
     *
     *                                  <p>
     *                                  <strong>Usage:</strong>
     *                                  </p>
     * 
     *                                  <pre>{@code
     *     executeDataSet("test-dataset.xml");
     * }</pre>
     *
     *                                  <p>
     *                                  The dataset file must be in a Flat XML
     *                                  format compatible with DBUnit.
     *                                  </p>
     *
     *                                  <p>
     *                                  <strong>Example Dataset File:</strong>
     *                                  </p>
     * 
     *                                  <pre>{@code
     * <dataset>
     *     <table_name column1="value1" column2="value2" />
     *     <table_name column1="value3" column2="value4" />
     * </dataset>
     * }</pre>
     *
     *                                  <p>
     *                                  <strong>Note:</strong>
     *                                  </p>
     *                                  <ul>
     *                                  <li>The connection is configured to allow
     *                                  empty fields.</li>
     *                                  <li>Always closes the input stream and
     *                                  database connection to prevent resource
     *                                  leaks.</li>
     *                                  </ul>
     */
    protected void executeDataSet(String datasetFilename) throws Exception {
        if (datasetFilename == null) {
            throw new NullPointerException("please provide test dataset file to execute!");
        }

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(datasetFilename);
        try (inputStream) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Dataset file '" + datasetFilename + "' not found in classpath");
            }
            IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());

            DatabaseConfig config = connection.getConfig();
            config.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
            IDataSet dataset = new FlatXmlDataSet(inputStream);

            try {
                DatabaseOperation.REFRESH.execute(connection, dataset);
            } finally {
                connection.close();
            }
        }
    }

    /**
     * Useful method to clear all data from specific tables
     */
    protected void clearTable(String... tableNames) throws Exception {
        for (String tableName : tableNames) {
            jdbcTemplate.execute("DELETE FROM " + tableName);
        }
    }
}
