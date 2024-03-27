package org.openelisglobal.unitTests.patientIdentityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.config.TestConfig;
import org.openelisglobal.patientidentitytype.dao.PatientIdentityTypeDAO;
import org.openelisglobal.patientidentitytype.service.PatientIdentityTypeServiceImpl;
import org.openelisglobal.patientidentitytype.valueholder.PatientIdentityType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, TestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class PatientIdentityTypeServiceTest {

    @InjectMocks
    private PatientIdentityTypeServiceImpl identityTypeService;

    @Mock
    private PatientIdentityTypeDAO mockDAO;

    private String identityType;
    private String identityDescription;
    private PatientIdentityType testingPatientIdentityType;
    private String identityTypeId;

    @Before
    public void init(){
        String identityType = "MoN Description";
        String identityDescription = "MoN number";
    }
    @Test
    public void insert_shouldInsertIdentityType() {
        PatientIdentityType testingPatientIdentityType = new PatientIdentityType(identityType, identityDescription);
        String expectedIdentityTypeId = "mockedId"; // Assuming a mocked ID

        when(mockDAO.insert(testingPatientIdentityType)).thenReturn(expectedIdentityTypeId);

        PatientIdentityTypeServiceImpl service = new PatientIdentityTypeServiceImpl();
        service.setBaseObjectDAO(mockDAO);

        String identityTypeId = service.insert(testingPatientIdentityType);

        assertEquals(expectedIdentityTypeId, identityTypeId);
    }

    @Test
    public void update_shouldUpdateIndentityType(){
        testingPatientIdentityType = new PatientIdentityType(identityType,identityDescription);
        PatientIdentityType updatedIdentityType = testingPatientIdentityType;
        updatedIdentityType.setDescription("New description");

        when(mockDAO.update(testingPatientIdentityType)).thenReturn(updatedIdentityType);
        when(mockDAO.duplicatePatientIdentityTypeExists(updatedIdentityType)).thenReturn(false);

        PatientIdentityType patientIdentityType = identityTypeService.update(testingPatientIdentityType);
        Assert.assertEquals(testingPatientIdentityType.getIdentityType(),patientIdentityType.getIdentityType());
        Assert.assertEquals(testingPatientIdentityType.getDescription(), patientIdentityType.getDescription());
    }


    @Test
    public void getAllPatientIdenityTypes_ShouldReturnAllTypes() {
        List<PatientIdentityType> mockList = Arrays.asList(new PatientIdentityType(identityType,identityDescription), new PatientIdentityType("Type 1","Type 1 description"));
        PatientIdentityTypeDAO mockDAO = Mockito.mock(PatientIdentityTypeDAO.class);
        when(mockDAO.getAllPatientIdenityTypes()).thenReturn(mockList);
        PatientIdentityTypeServiceImpl service = new PatientIdentityTypeServiceImpl();
        service.setBaseObjectDAO(mockDAO);

        List<PatientIdentityType> retrievedList = service.getAllPatientIdenityTypes();

        Assert.assertNotNull(retrievedList);
        assertEquals(mockList.size(), retrievedList.size());
    }
    @Test(expected = LIMSDuplicateRecordException.class)
    public void insert_DuplicateIdentityType_ShouldThrowException() {
        PatientIdentityType duplicateType = new PatientIdentityType("Primary insurance number","NSURANCE Description");
        duplicateType.setIdentityType("National D");
        PatientIdentityTypeDAO mockDAO = Mockito.mock(PatientIdentityTypeDAO.class);
        when(mockDAO.duplicatePatientIdentityTypeExists(duplicateType)).thenReturn(true);

        PatientIdentityTypeServiceImpl service = new PatientIdentityTypeServiceImpl();
        service.setBaseObjectDAO(mockDAO);

        service.insert(duplicateType);
    }

}
