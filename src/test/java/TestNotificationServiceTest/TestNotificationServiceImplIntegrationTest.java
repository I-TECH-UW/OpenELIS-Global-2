package TestNotificationServiceTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openelisglobal.BaseTestConfig;
import org.openelisglobal.notification.service.TestNotificationService;
import org.openelisglobal.notification.valueholder.TestNotificationConfig;
import org.openelisglobal.result.valueholder.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.when;
import org.openelisglobal.notification.valueholder.notificationature;
import org.openelisglobal.notification.valueholder.NotificationConfigOption;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BaseTestConfig.class, TestNotificationService.class })
@TestPropertySource("classpath:common.properties")
@ActiveProfiles("test")

public class TestNotificationServiceImplIntegrationTest {
    @Autowired
    private
    TestNotificationService TestNotificationService;

    @Before
    public void init() throws Exception {
    }

    @Test
    public void testCreateAndSendNotificationsToConfiguredSources() {
        // Create a sample Result object
        Result result = new Result();
        result.setAnalysisId("sampleAnalysisId");  // Set analysis ID
        result.setTestId("sampleTestId");  // Set test ID
        result.setResultValue = new TestResult();
        // Set properties of the Result object as needed for the test

        // Mock the behavior of testNotificationConfigService to return a predefined TestNotificationConfig
        TestNotificationConfig testNotificationConfig = new TestNotificationConfig();
        // Set properties of the TestNotificationConfig object as needed for the test
        when(TestNotificationServiceConfig.getTestNotificationConfigForTestId("yourTestId"))
                .thenReturn(Optional.of(testNotificationConfig));

        // Call the method being tested
        TestNotificationService.createAndSendNotificationsToConfiguredSources(NotificationConfigOption.NotificationNature.RESULT_VALIDATION, result);
        // Perform assertions as needed
        Notification notification = // Retrieve the notification from somewhere (e.g., database, service)
                Assert.assertTrue(notification.isSent());

    }

    public org.openelisglobal.notification.service.TestNotificationService getTestNotificationService() {
        return TestNotificationService;
    }

    public void setTestNotificationService(org.openelisglobal.notification.service.TestNotificationService testNotificationService) {
        TestNotificationService = testNotificationService;
    }

    // You can add more integration test methods for other functionalities
}

