package org.openelisglobal.testconfiguration.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.typeofsample.service.TypeOfSampleTestService;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;
import org.openelisglobal.typeofsample.valueholder.TypeOfSampleTest;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleTypeTestAssignServiceIntegrationTest extends BaseWebContextSensitiveTest {

    @Autowired
    private SampleTypeTestAssignService sampleTypeTestAssignService;

    @Autowired
    private TypeOfSampleService typeOfSampleService;

    @Autowired
    private TypeOfSampleTestService typeOfSampleTestService;

    @Before
    public void init() throws Exception {
        executeDataSetWithStateManagement("testdata/sample-type-test-assign-service.xml");
    }

    @Test
    public void updateShouldInsertNewTypeOfSampleTestWhenGivenValidInputs() {
        List<TypeOfSampleTest> preCondition = typeOfSampleTestService.getTypeOfSampleTestsForSampleType("9901");
        assertEquals("Fixture must start with no assignments for Urine (9901)", 0, preCondition.size());

        TypeOfSample sampleType = typeOfSampleService.get("9901");
        assertEquals("Pre-condition: sample type description must be Urine", "Urine", sampleType.getDescription());
        assertEquals("Pre-condition: sample type abbreviation must be U", "U", sampleType.getLocalAbbreviation());

        sampleTypeTestAssignService.update(sampleType, "9900", Collections.emptyList(), sampleType.getId(), false,
                false, null, "1");

        List<TypeOfSampleTest> testsForSampleType = typeOfSampleTestService.getTypeOfSampleTestsForSampleType("9901");
        assertEquals("Exactly 1 assignment must exist for Urine (9901) after insert", 1, testsForSampleType.size());
        assertEquals("Assigned test ID must be GeneXpert (9900)", "9900", testsForSampleType.get(0).getTestId());
        assertEquals("Sample type ID on the new assignment must be Urine (9901)", "9901",
                testsForSampleType.get(0).getTypeOfSampleId());
    }

    @Test
    public void updateShouldDeleteExistingTypeOfSampleTestsAndInsertNewAssignmentWhenDeleteFlagIsTrue() {
        List<TypeOfSampleTest> existingLinks = typeOfSampleTestService.getTypeOfSampleTestsForSampleType("9900");
        assertEquals("Fixture must start with exactly 1 assignment for Blood (9900)", 1, existingLinks.size());
        assertEquals("Existing assignment ID must be 9900", "9900", existingLinks.get(0).getId());
        assertEquals("Existing assignment must link to GeneXpert (9900)", "9900", existingLinks.get(0).getTestId());

        TypeOfSample sampleType = typeOfSampleService.get("9900");
        List<String> idsToDelete = new ArrayList<>();
        idsToDelete.add("9900");

        sampleTypeTestAssignService.update(sampleType, "9901", idsToDelete, sampleType.getId(), true, false, null, "1");

        List<TypeOfSampleTest> deletedLinks = typeOfSampleTestService.getTypeOfSampleTestsForSampleType("9900");
        assertEquals("Exactly 1 assignment must remain for Blood (9900) after delete+insert", 1, deletedLinks.size());
        assertEquals("Remaining assignment must link to SputumCulture (9901), not the deleted GeneXpert (9900)", "9901",
                deletedLinks.get(0).getTestId());
        assertEquals("Remaining assignment must still belong to Blood (9900)", "9900",
                deletedLinks.get(0).getTypeOfSampleId());
    }

    @Test
    public void updateShouldUpdateTypeOfSampleDescriptionWhenUpdateFlagIsTrue() {
        TypeOfSample sampleType = typeOfSampleService.get("9900");
        assertEquals("Pre-condition: description must be Blood before update", "Blood", sampleType.getDescription());

        sampleType.setDescription("Updated Blood");

        sampleTypeTestAssignService.update(sampleType, "9901", Collections.emptyList(), sampleType.getId(), false, true,
                null, "1");

        TypeOfSample updatedSampleType = typeOfSampleService.get("9900");
        assertEquals("Description must be persisted as 'Updated Blood' after update", "Updated Blood",
                updatedSampleType.getDescription());
        assertEquals("Abbreviation must remain unchanged as 'B' after description-only update", "B",
                updatedSampleType.getLocalAbbreviation());
        assertEquals("Domain must remain unchanged as 'H' after description-only update", "H",
                updatedSampleType.getDomain());
    }

    @Test
    public void updateShouldDeactivateTypeOfSampleWhenDeactivateObjectIsProvided() {
        TypeOfSample preCondition = typeOfSampleService.get("9902");
        assertEquals("Pre-condition: Legacy Sputum (9902) must be active before deactivation", true,
                preCondition.isActive());
        assertEquals("Pre-condition: Legacy Sputum description must be 'Legacy Sputum'", "Legacy Sputum",
                preCondition.getDescription());

        TypeOfSample sampleType = typeOfSampleService.get("9901");
        TypeOfSample deActivateTypeOfSample = typeOfSampleService.get("9902");
        deActivateTypeOfSample.setActive(false);

        sampleTypeTestAssignService.update(sampleType, "9900", Collections.emptyList(), sampleType.getId(), false,
                false, deActivateTypeOfSample, "1");

        TypeOfSample updatedDeactivated = typeOfSampleService.get("9902");
        assertFalse("Legacy Sputum (9902) must be inactive after deactivation", updatedDeactivated.isActive());
        assertEquals("Legacy Sputum (9902) description must remain 'Legacy Sputum' after deactivation", "Legacy Sputum",
                updatedDeactivated.getDescription());
        assertEquals("Legacy Sputum (9902) domain must remain 'E' after deactivation", "E",
                updatedDeactivated.getDomain());
    }
}