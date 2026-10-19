package org.openelisglobal.configuration.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openelisglobal.test.service.LegacyTestVariantFinder;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.testresultcomponent.service.TestResultComponentService;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.unitofmeasure.service.UnitOfMeasureService;

/**
 * A configuration file names a test the way the catalogue shows it. A test
 * created in the Test Catalogue Editor keeps that name in its localised name
 * and in {@code test.name} while {@code test.description} holds the editor's
 * free-text description, so a lookup by description alone never finds it and
 * every file that names such a test queues an unresolved reference
 * (release-test run 2026-10-19, "test 'RT Calc Result' not found").
 */
@RunWith(MockitoJUnitRunner.class)
public class CatalogReferenceResolverTest {

    @Mock
    private TestService testService;

    @Mock
    private TestSectionService testSectionService;

    @Mock
    private TypeOfSampleService typeOfSampleService;

    @Mock
    private UnitOfMeasureService unitOfMeasureService;

    @Mock
    private TestResultComponentService componentService;

    @Mock
    private LegacyTestVariantFinder legacyVariantFinder;

    @Mock
    private ReferenceAliasService aliasService;

    @InjectMocks
    private CatalogReferenceResolver resolver;

    private org.openelisglobal.test.valueholder.Test editorCreatedTest;

    @Before
    public void setUp() {
        editorCreatedTest = mock(org.openelisglobal.test.valueholder.Test.class);
        lenient().when(editorCreatedTest.getId()).thenReturn("403");
        lenient().when(testService.getTestByDescription(anyString())).thenReturn(null);
        lenient().when(testService.getTestByNormalizedDescription(anyString())).thenReturn(null);
        lenient().when(testService.getTestByLocalCode(anyString())).thenReturn(null);
        lenient().when(testService.getActiveTestByName(anyString())).thenReturn(Collections.emptyList());
        lenient().when(aliasService.resolve(anyString(), anyString())).thenReturn(null);
        lenient().when(legacyVariantFinder.find(anyString())).thenReturn(Collections.emptyList());
    }

    @Test
    public void aTestNamedByItsCatalogueNameIsFoundWhenItsDescriptionDiffers() {
        lenient().when(testService.getActiveTestByName("RT Calc Result"))
                .thenReturn(Collections.singletonList(editorCreatedTest));

        assertSame(editorCreatedTest, resolver.resolveTest("RT Calc Result", "Serum", "rt-reflex-rules-2.csv:2"));
    }

    @Test
    public void aTestKnownByDescriptionStillWinsOverTheNameLookup() {
        org.openelisglobal.test.valueholder.Test loaderCreatedTest = mock(
                org.openelisglobal.test.valueholder.Test.class);
        lenient().when(testService.getTestByDescription("RT Glucose Dual")).thenReturn(loaderCreatedTest);
        lenient().when(testService.getActiveTestByName("RT Glucose Dual"))
                .thenReturn(Collections.singletonList(editorCreatedTest));

        assertSame(loaderCreatedTest, resolver.resolveTest("RT Glucose Dual", "Serum", "rt-tests.csv:2"));
    }

    @Test
    public void anUnknownNameStillResolvesToNothing() {
        assertNull(resolver.resolveTest("RT Nowhere", "Serum", "rt-reflex-rules-2.csv:3"));
    }
}
