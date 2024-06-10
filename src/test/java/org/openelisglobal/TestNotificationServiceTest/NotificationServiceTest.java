package org.openelisglobal.TestNotificationServiceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.notification.service.TestNotificationServiceImpl;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.notification.valueholder.NotificationConfigOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")
public class NotificationServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    private TestNotificationServiceImpl testNotificationService;

    @Before
    public void init() throws Exception {
        super.setUp();
    }

    @Test
    public void testCreateAndSendNotificationsToConfiguredSources() {
        Result result = new Result();
        result.setId("sampleResultId");
        result.setValue("sampleValue");

        Analysis analysis = new Analysis();
        analysis.setId("sampleAnalysisId");
        result.setAnalysis(analysis);

        result.setResultType("sampleResultType");
        result.setSortOrder("1");

        testNotificationService.createAndSendNotificationsToConfiguredSources(
                NotificationConfigOption.NotificationNature.RESULT_VALIDATION, result);

        // Add more assertions as needed
    }
}
