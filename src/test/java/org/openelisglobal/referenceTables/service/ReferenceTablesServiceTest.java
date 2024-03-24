package org.openelisglobal.referenceTables.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.config.TestConfig;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.referencetables.service.ReferenceTablesService;
import org.openelisglobal.referencetables.valueholder.ReferenceTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertThrows;

/**
 * @author Valens NIYONSENGA
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, TestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class ReferenceTablesServiceTest {

    @Autowired
    ReferenceTablesService referenceTablesService;

    @Autowired
    PatientService patientService;
    ReferenceTables createdReferenceTable;
    ReferenceTables referenceTablesEntity;


// Initializing objects and variables to use in testing
    @Before
    public void setUp(){
        referenceTablesEntity = new ReferenceTables();
        createdReferenceTable = new ReferenceTables();
        referenceTablesEntity.setTableName("VILLAGE history");
        referenceTablesEntity.setKeepHistory("Y");
        referenceTablesEntity.setIsHl7Encoded("N");
    }

//    Saving referenceTable test
    @Test
    public void save_shouldSaveReferenceTable() {
        createdReferenceTable =  referenceTablesService.save(referenceTablesEntity);
        Assert.assertNotEquals(null,referenceTablesService.get(createdReferenceTable.getId()));
        Assert.assertEquals(createdReferenceTable.getId(),createdReferenceTable.getId());
        Assert.assertEquals(referenceTablesEntity.getTableName(), createdReferenceTable.getTableName());
        Assert.assertEquals(referenceTablesEntity.getIsHl7Encoded(),createdReferenceTable.getIsHl7Encoded());
        Assert.assertEquals(referenceTablesEntity.getKeepHistory(),createdReferenceTable.getKeepHistory());

    }

//    Inserting reference table test, checking for duplication
    @Test
    public void insert_shouldInsertNewReferenceTables() {
        assertThrows(LIMSRuntimeException.class, () -> {
            referenceTablesService.insert(referenceTablesEntity);
        });
    }

//    Updating reference table test
    @Test
    public void update_shouldUpdateReferenceTable() {
        referenceTablesEntity.setTableName("CELL history");
        referenceTablesEntity.setKeepHistory("N");
        referenceTablesEntity.setIsHl7Encoded("Y");

        createdReferenceTable.setTableName(referenceTablesEntity.getTableName());
        createdReferenceTable.setKeepHistory(referenceTablesEntity.getKeepHistory());
        createdReferenceTable.setIsHl7Encoded(referenceTablesEntity.getIsHl7Encoded());
        createdReferenceTable = referenceTablesService.update(createdReferenceTable);

        Assert.assertEquals(referenceTablesEntity.getTableName(),createdReferenceTable.getTableName());
        Assert.assertEquals(referenceTablesEntity.getKeepHistory(), createdReferenceTable.getKeepHistory());
        Assert.assertEquals(referenceTablesEntity.getIsHl7Encoded(), createdReferenceTable.getIsHl7Encoded());
    }

//    Getting all reference tables for Hl7Encoding test
    @Test
    public void getAllReferenceTablesForHl7Encoding_shouldGetAllTablesForHl7Encoding() {
        Assert.assertTrue(referenceTablesService.getAllReferenceTablesForHl7Encoding().size() > 0 );
    }

// Get all reference tables test
    @Test
    public void getAllReferenceTables_shouldGetAll() {
        Assert.assertTrue(referenceTablesService.getAllReferenceTables().size() > 0);
    }

//    Get all reference tables count test
    @Test
    public void getTotalReferenceTableCount_shouldReturnTotalTablesCount() {
        Assert.assertTrue(referenceTablesService.getTotalReferenceTableCount() > 0);
    }

//    get page of reference tables
    @Test
    public void getPageOfReferenceTables_shouldReturnPageOfReferenceTables() {
        Assert.assertTrue(referenceTablesService.getPageOfReferenceTables(1).size() > 0);
    }
}
