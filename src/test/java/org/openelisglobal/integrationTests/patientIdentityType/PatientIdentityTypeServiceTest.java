package org.openelisglobal.integrationTests.patientIdentityType;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.config.TestConfig;
import org.openelisglobal.patientidentitytype.service.PatientIdentityTypeService;
import org.openelisglobal.patientidentitytype.valueholder.PatientIdentityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, TestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class PatientIdentityTypeServiceTest {

    @Autowired
    private PatientIdentityTypeService patientIdentityTypeService;

    private  PatientIdentityType expected;
    private  PatientIdentityType result;


    private String id;
    private String identityType;
    private String description;

    @Before
    public void setup() {
    }

    @Test
    public void insert_shouldInsertPatientIdentityType() {
        identityType = "M identity";
        description = "M description";

        expected = new PatientIdentityType();
        expected.setIdentityType(identityType);
        expected.setDescription(description);

        id =  patientIdentityTypeService.insert(expected);

        result = patientIdentityTypeService.get(id);
        assertEquals(expected.getIdentityType(), result.getIdentityType());
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void testGetAllPatientIdenityTypes() {

        identityType = "Global Id";
        description = "Global description";

        expected = new PatientIdentityType();
        expected.setIdentityType(identityType);
        expected.setDescription(description);

        id =  patientIdentityTypeService.insert(expected);

        List<PatientIdentityType> resultList = patientIdentityTypeService.getAllPatientIdenityTypes();
        assertTrue(resultList.size() > 0);
        patientIdentityTypeService.delete(patientIdentityTypeService.get(id));
    }

    @Test
    public void testInsertDuplicatePatientIdentityType() {
        identityType = "DuplicateName";
        description = "DuplicateName";

        PatientIdentityType patientIdentityType = new PatientIdentityType();
        patientIdentityType.setIdentityType(identityType);
        patientIdentityType.setDescription(description);

        patientIdentityTypeService.insert(patientIdentityType);

        assertThrows(LIMSDuplicateRecordException.class, () -> patientIdentityTypeService.insert(patientIdentityType));
    }

}
