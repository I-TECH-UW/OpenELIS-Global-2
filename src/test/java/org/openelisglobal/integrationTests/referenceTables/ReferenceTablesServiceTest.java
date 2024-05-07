package org.openelisglobal.integrationTests.referenceTables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.config.TestConfig;
import org.openelisglobal.referencetables.service.ReferenceTablesService;
import org.openelisglobal.referencetables.valueholder.ReferenceTables;
import org.openelisglobal.testUtils.ObjectsInitiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, TestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class ReferenceTablesServiceTest {

    @Autowired
    private ReferenceTablesService referenceTablesService;

    private String id;

    private ReferenceTables expected;
    private ReferenceTables result;


    @Test
    public void insert_shouldInsertReferenceTable(){
        expected = ObjectsInitiator.createReferenceTable("X-Table","N","Y");
        id = referenceTablesService.insert(expected);

        result = referenceTablesService.get(id);
        assertEquals(expected.getIsHl7Encoded(),result.getIsHl7Encoded());

    }

    @Test
    public void update_shouldUpdateReferenceTable(){

        expected = ObjectsInitiator.createReferenceTable("New Table","N","Y");

        expected.setTableName("Update Table");
        expected.setIsHl7Encoded("Y");
        expected.setKeepHistory("N");

        id = referenceTablesService.insert(expected);
        expected = referenceTablesService.get(id);

        result = referenceTablesService.update(expected);

        assertEquals(expected.getTableName(), result.getTableName());
        assertEquals(expected.getKeepHistory(), result.getKeepHistory());
        assertEquals(expected.getIsHl7Encoded(), result.getIsHl7Encoded());
        assertEquals(expected.getId(),result.getId());
    }


    @Test
    public void getReferenceTablesByName_shouldGetReferenceTableByName() {
        expected = ObjectsInitiator.createReferenceTable("X-Table-Test","N","Y");
        id = referenceTablesService.insert(expected);
        result = referenceTablesService.getReferenceTableByName(expected.getTableName());

        assertNotNull(result);
        assertEquals(expected.getTableName(), result.getTableName());
        assertEquals(expected.getKeepHistory(), result.getKeepHistory());
        assertEquals(expected.getIsHl7Encoded(), result.getIsHl7Encoded());
    }
    @Test
    public void getAllForHl7Encoding_shouldGetAllReferenceTablesForHl7Encoding() {
        List<ReferenceTables> referenceTablesList = referenceTablesService.getAllReferenceTablesForHl7Encoding();
        assertNotNull(referenceTablesList);
        assertTrue(referenceTablesList.size() > 0);
    }

    @Test
    public void getAll_shouldGetAllReferenceTables() {
        List<ReferenceTables> referenceTablesList = referenceTablesService.getAllReferenceTables();
        assertNotNull(referenceTablesList);
        assertTrue(referenceTablesList.size() > 0);
    }

    @Test
    public void getTotalTablesCount_shouldGetTotalReferenceTablesCount() {
        Integer totalCount = referenceTablesService.getTotalReferenceTablesCount();
        assertNotNull(totalCount);
    }

}