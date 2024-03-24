package org.openelisglobal.referenceTables.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.config.TestConfig;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.referencetables.service.ReferenceTablesService;
import org.openelisglobal.referencetables.valueholder.ReferenceTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    private String tableName;
    private String keepHistory;
    private String isHl7Encoded;


    @Before
    public void setUp(){
        referenceTablesEntity = new ReferenceTables();
        createdReferenceTable = new ReferenceTables();
        tableName = "VILLAGE history";
        keepHistory = "Y";
        isHl7Encoded = "N";
    }


    @Test
    public void insert_shouldInsertReferenceTable() {
        referenceTablesEntity.setTableName(tableName);
        referenceTablesEntity.setKeepHistory(keepHistory);
        referenceTablesEntity.setIsHl7Encoded(isHl7Encoded);
        String insertMessage =  referenceTablesService.insert(referenceTablesEntity);
        System.out.println("Insert Method ==== : " + insertMessage);
    }

    @Test
    public void save_shouldSaveNewReferenceTables() {
        referenceTablesEntity.setTableName(tableName);
        referenceTablesEntity.setKeepHistory(keepHistory);
        referenceTablesEntity.setIsHl7Encoded(isHl7Encoded);
        Assert.assertThrows()
        createdReferenceTable =  referenceTablesService.save(referenceTablesEntity);
        Assert.assertNotEquals(null,referenceTablesService.get(createdReferenceTable.getId()));
        Assert.assertEquals(createdReferenceTable.getId(),createdReferenceTable.getId());
        Assert.assertEquals(tableName, createdReferenceTable.getTableName());
        Assert.assertEquals(isHl7Encoded,createdReferenceTable.getIsHl7Encoded());
        Assert.assertEquals(keepHistory,createdReferenceTable.getKeepHistory());
    }



    @Test
    public void update_shouldUpdateReferenceTable() {
        tableName = "CELL history";
        keepHistory = "N";
        isHl7Encoded = "Y";
        createdReferenceTable.setTableName(tableName);
        createdReferenceTable.setKeepHistory(keepHistory);
        createdReferenceTable.setIsHl7Encoded(isHl7Encoded);
        createdReferenceTable = referenceTablesService.update(createdReferenceTable);

        Assert.assertEquals(tableName,createdReferenceTable.getTableName());
        Assert.assertEquals(keepHistory, createdReferenceTable.getKeepHistory());
        Assert.assertEquals(isHl7Encoded, createdReferenceTable.getIsHl7Encoded());
    }

    @Test
    public void getAllReferenceTablesForHl7Encoding_shouldGetAllTablesForHl7Encoding() {
        Assert.assertTrue(referenceTablesService.getAllReferenceTablesForHl7Encoding().size() > 0 );
    }

    @Test
    public void getAllReferenceTables_shouldGetAll() {
        Assert.assertTrue(referenceTablesService.getAllReferenceTables().size() > 0);
    }

//    @Test
//    public void getReferenceTableByName() {
//        Assert.assertNotEquals(null,referenceTablesService.getReferenceTableByName(createdReferenceTable));
//    }

    @Test
    public void getTotalReferenceTableCount() {
        Assert.assertTrue(referenceTablesService.getTotalReferenceTableCount() > 0);
    }

    @Test
    public void getPageOfReferenceTables() {
        Assert.assertTrue(referenceTablesService.getPageOfReferenceTables(1).size() > 0);
    }

    @Test
    public void getTotalReferenceTablesCount() {
        Assert.assertTrue(referenceTablesService.getCount() > 0);
    }




}
