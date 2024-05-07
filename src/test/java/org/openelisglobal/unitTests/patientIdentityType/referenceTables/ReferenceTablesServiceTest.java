package org.openelisglobal.unitTests.patientIdentityType.referenceTables;
import org.hl7.fhir.utilities.tests.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.referencetables.dao.ReferenceTablesDAO;
import org.openelisglobal.referencetables.service.ReferenceTablesServiceImpl;
import org.openelisglobal.referencetables.valueholder.ReferenceTables;
import org.openelisglobal.testUtils.ObjectsInitiator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, TestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public  class ReferenceTablesServiceTest {

    private  ReferenceTables expected;
    private ReferenceTables result;
    List<ReferenceTables> referenceTablesList;

    private String id;
    @Mock
    private ReferenceTablesDAO baseObjectDAO;

    @InjectMocks
    private ReferenceTablesServiceImpl referenceTablesService;

    public ReferenceTablesServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetReferenceTableByName() {
        String tableName = "testTableName";
        ReferenceTables referenceTables = new ReferenceTables();
        when(baseObjectDAO.getReferenceTableByName(tableName)).thenReturn(referenceTables);

        ReferenceTables result = referenceTablesService.getReferenceTableByName(tableName);


        assertEquals(referenceTables, result);
    }

    @Test
    public  void testGetData() {
        ReferenceTables referenceTables = new ReferenceTables();
        referenceTablesService.getData(referenceTables);
        verify(baseObjectDAO).getData(referenceTables);
    }

    @Test
    public void testGetAllReferenceTablesForHl7Encoding() {
        referenceTablesList = new ArrayList<>();
        expected = ObjectsInitiator.createReferenceTable("T name","Y","N");
        referenceTablesList.add(expected);

        when(baseObjectDAO.getAllReferenceTablesForHl7Encoding()).thenReturn(referenceTablesList);

        List<ReferenceTables> result = referenceTablesService.getAllReferenceTablesForHl7Encoding();
        assertEquals(referenceTablesList, result);
    }

    @Test
    public void testGetAllReferenceTables() {
        List<ReferenceTables> referenceTablesList = new ArrayList<>();
        when(baseObjectDAO.getAllReferenceTables()).thenReturn(referenceTablesList);

        List<ReferenceTables> result = referenceTablesService.getAllReferenceTables();

        assertEquals(referenceTablesList, result);
    }
    @Test
    public void testGetReferenceTableByNameWithReferenceTables() {
        ReferenceTables referenceTables = new ReferenceTables();
        when(baseObjectDAO.getReferenceTableByName(referenceTables)).thenReturn(referenceTables);

        ReferenceTables result = referenceTablesService.getReferenceTableByName(referenceTables);

        assertEquals(referenceTables, result);
    }

    @Test
    public void testGetTotalReferenceTableCount() {
        int count = 10;
        when(baseObjectDAO.getTotalReferenceTableCount()).thenReturn(count);

        int result = referenceTablesService.getTotalReferenceTableCount();

        assertEquals(count, result);
    }

    @Test
    public void testGetPageOfReferenceTables() {
        int startingRecNo = 0;
        List<ReferenceTables> referenceTablesList = new ArrayList<>();
        when(baseObjectDAO.getPageOfReferenceTables(startingRecNo)).thenReturn(referenceTablesList);

        List<ReferenceTables> result = referenceTablesService.getPageOfReferenceTables(startingRecNo);

        assertEquals(referenceTablesList, result);
    }

    @Test
    public void testGetTotalReferenceTablesCount() {
        int count = 5;
        when(baseObjectDAO.getTotalReferenceTablesCount()).thenReturn(count);

        int result = referenceTablesService.getTotalReferenceTablesCount();

        assertEquals(count, result);
    }

    @Test
    public void testInsert() {
        expected = ObjectsInitiator.createReferenceTable("T name","Y","N");

        String expectedReferenceTablesId = "mockedId";
        ReferenceTables referenceTablesEntity = ObjectsInitiator.createReferenceTable("T name","Y","N");
        referenceTablesEntity.setId(expectedReferenceTablesId);

        when(baseObjectDAO.insert(expected)).thenReturn(expectedReferenceTablesId);
        when(baseObjectDAO.duplicateReferenceTablesExists(expected,true)).thenReturn(false);
        when(baseObjectDAO.get(expectedReferenceTablesId)).thenReturn(Optional.ofNullable(referenceTablesEntity));

        id = referenceTablesService.insert(expected);
        result = referenceTablesService.get(id);

        assertNotNull(result);
        assertNotNull(id);
        assertEquals(expected.getTableName(),result.getTableName());
        assertEquals(expected.getIsHl7Encoded(),result.getIsHl7Encoded());
        assertEquals(expected.getKeepHistory(),result.getKeepHistory());
        assertEquals(expected.getId(),result.getId());
    }

    @Test
    public void testInsertWithDuplicate() {
        ReferenceTables referenceTables = new ReferenceTables();
        when(baseObjectDAO.duplicateReferenceTablesExists(referenceTables, true)).thenReturn(true);

        assertThrows(LIMSDuplicateRecordException.class, () -> referenceTablesService.insert(referenceTables));
    }
    @Test
    public void testSaveWithDuplicate() {
        ReferenceTables referenceTables = new ReferenceTables();
        when(baseObjectDAO.duplicateReferenceTablesExists(referenceTables, false)).thenReturn(true);

        assertThrows(LIMSDuplicateRecordException.class, () -> referenceTablesService.save(referenceTables));
    }

    @Test
    public void testUpdate() {
        expected = new ReferenceTables();
        String expectedReferenceTablesId = "mockedId";
        ReferenceTables referenceTablesEntity = new ReferenceTables();

        ReferenceTables referenceTables = new ReferenceTables();
        when(baseObjectDAO.update(referenceTablesEntity)).thenReturn(expected);

        result= referenceTablesService.update(referenceTablesEntity);
        assertNotNull(result);
        assertNotNull(referenceTablesEntity);
        assertEquals(referenceTablesEntity.getTableName(),result.getTableName());
        assertEquals(referenceTablesEntity.getKeepHistory(),result.getKeepHistory());
        assertEquals(referenceTablesEntity.getIsHl7Encoded(),result.getIsHl7Encoded());
    }

    @Test
    public  void testUpdateWithDuplicate() {
        ReferenceTables referenceTables = new ReferenceTables();
        when(baseObjectDAO.duplicateReferenceTablesExists(referenceTables, false)).thenReturn(true);
        assertThrows(LIMSDuplicateRecordException.class, () -> referenceTablesService.update(referenceTables));
    }
}