package org.openelisglobal.referencetables;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.referencetables.service.ReferenceTablesService;
import org.openelisglobal.referencetables.valueholder.ReferenceTables;
import org.springframework.beans.factory.annotation.Autowired;

public class ReferenceTablesServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    ReferenceTablesService referenceTablesService;

    @Before
    public void init() throws Exception {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getReferenceTableByName_shouldReturnCorrectReferenceTable() throws Exception {
        String tableName = "TestTable";

        ReferenceTables refTable = createReferenceTable(tableName);
        referenceTablesService.insert(refTable);

        ReferenceTables retrievedTable = referenceTablesService.getReferenceTableByName(tableName);
        Assert.assertNotNull(retrievedTable);
        Assert.assertEquals(tableName, retrievedTable.getTableName());
    }

    @Test
    public void duplicateReferenceTable_shouldThrowException() throws Exception {
        String tableName = "DuplicateTable";

        ReferenceTables refTable1 = createReferenceTable(tableName);
        referenceTablesService.insert(refTable1);

        ReferenceTables refTable2 = createReferenceTable(tableName);

        try {
            referenceTablesService.insert(refTable2);
            Assert.fail("Expected LIMSDuplicateRecordException to be thrown");
        } catch (LIMSDuplicateRecordException e) {
            Assert.assertEquals("Duplicate record exists for " + tableName, e.getMessage());
        }
    }

    private ReferenceTables createReferenceTable(String tableName) {
        ReferenceTables refTable = new ReferenceTables();
        refTable.setTableName(tableName);
        refTable.setIsHl7Encoded("N");
        refTable.setKeepHistory("Y");
        return refTable;
    }

    @Test
    public void updateReferenceTable_shouldUpdateCorrectly() throws Exception {
        ReferenceTables refTable = createReferenceTable("UpdateTestTable");
        referenceTablesService.insert(refTable);
        refTable.setKeepHistory("N");
        referenceTablesService.update(refTable);
        ReferenceTables updatedTable = referenceTablesService.getReferenceTableByName("UpdateTestTable");
        Assert.assertEquals("N", updatedTable.getKeepHistory());
    }

    @Test
    public void insertReferenceTable_shouldInsertCorrectly() throws Exception {
        ReferenceTables refTable = createReferenceTable("InsertTestTable");
        referenceTablesService.insert(refTable);
        ReferenceTables retrievedTable = referenceTablesService.getReferenceTableByName("InsertTestTable");
        Assert.assertNotNull(retrievedTable);
        Assert.assertEquals("InsertTestTable", retrievedTable.getTableName());
    }

    @Test
    public void getAllReferenceTables_shouldReturnAllTables() throws Exception {
        int initialCount = referenceTablesService.getAllReferenceTables().size();

        ReferenceTables refTable1 = createReferenceTable("Table1");
        ReferenceTables refTable2 = createReferenceTable("Table2");
        referenceTablesService.insert(refTable1);
        referenceTablesService.insert(refTable2);

        List<ReferenceTables> allTables = referenceTablesService.getAllReferenceTables();
        Assert.assertEquals(initialCount + 2, allTables.size());
    }
}