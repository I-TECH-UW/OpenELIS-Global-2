package TestNotificationServiceTest;
import org.mockito.Mockito;
import org.openelisglobal.notification.dao.AnalysisNotificationConfigDAO;
import org.openelisglobal.notification.dao.NotificationPayloadTemplateDAO;
import org.openelisglobal.notification.service.AnalysisNotificationConfigService;
import org.openelisglobal.notification.service.AnalysisNotificationConfigServiceImpl;
import org.openelisglobal.notification.service.NotificationPayloadTemplateService;
import org.openelisglobal.notification.service.NotificationPayloadTemplateServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class TestNotificationServiceConfig {
    @Bean
    public AnalysisNotificationConfigService analysisNotificationConfigService() {
        return Mockito.mock(AnalysisNotificationConfigServiceImpl.class);
    }

    @Bean
    public AnalysisNotificationConfigDAO analysisNotificationConfigDAO() {
        return Mockito.mock(AnalysisNotificationConfigDAO.class);
    }

    @Bean
    public NotificationPayloadTemplateService notificationPayloadTemplateService() {
        return Mockito.mock(NotificationPayloadTemplateServiceImpl.class);
    }

    @Bean
    public NotificationPayloadTemplateDAO notificationPayloadTemplateDAO() {
        return Mockito.mock(NotificationPayloadTemplateDAO.class);
    }
}
