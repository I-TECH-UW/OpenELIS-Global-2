package org.openelisglobal.referencetables;

import static org.junit.Assert.*;

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
        assertNotNull(retrievedTable);
        assertEquals(tableName, retrievedTable.getTableName());
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
            assertEquals("Duplicate record exists for " + tableName, e.getMessage());
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
    public void insertReferenceTable_shouldInsertCorrectly() throws Exception {
        ReferenceTables refTable = createReferenceTable("InsertTestTable");
        referenceTablesService.insert(refTable);
        ReferenceTables retrievedTable = referenceTablesService.getReferenceTableByName("InsertTestTable");
        assertNotNull(retrievedTable);
        assertEquals("InsertTestTable", retrievedTable.getTableName());
    }

    @Test
    public void getAllReferenceTables_shouldReturnAllTables() throws Exception {
        int initialCount = referenceTablesService.getAllReferenceTables().size();

        ReferenceTables refTable1 = createReferenceTable("Table1");
        ReferenceTables refTable2 = createReferenceTable("Table2");
        referenceTablesService.insert(refTable1);
        referenceTablesService.insert(refTable2);

        List<ReferenceTables> allTables = referenceTablesService.getAllReferenceTables();
        assertEquals(initialCount + 2, allTables.size());
    }

    @Test
    public void getTotalReferenceTableCount_shouldReturnCorrectCount() throws Exception {
        int initialCount = referenceTablesService.getTotalReferenceTableCount();

        ReferenceTables refTable1 = createReferenceTable("CountTable1");
        ReferenceTables refTable2 = createReferenceTable("CountTable2");
        referenceTablesService.insert(refTable1);
        referenceTablesService.insert(refTable2);

        int newCount = referenceTablesService.getTotalReferenceTableCount();
        assertEquals(initialCount + 2, newCount);
    }

    @Test
    public void getAllReferenceTablesForHl7Encoding_shouldReturnOnlyHl7EncodedTables() throws Exception {
        ReferenceTables hl7Table = createReferenceTable("Hl7Table");
        hl7Table.setIsHl7Encoded("Y");
        referenceTablesService.insert(hl7Table);

        ReferenceTables nonHl7Table = createReferenceTable("NonHl7Table");
        nonHl7Table.setIsHl7Encoded("N");
        referenceTablesService.insert(nonHl7Table);

        List<ReferenceTables> hl7EncodedTables = referenceTablesService.getAllReferenceTablesForHl7Encoding();
        assertTrue(hl7EncodedTables.stream().allMatch(t -> "Y".equals(t.getIsHl7Encoded())));
        assertTrue(hl7EncodedTables.stream().anyMatch(t -> "Hl7Table".equals(t.getTableName())));
        Assert.assertFalse(hl7EncodedTables.stream().anyMatch(t -> "NonHl7Table".equals(t.getTableName())));
    }
}
