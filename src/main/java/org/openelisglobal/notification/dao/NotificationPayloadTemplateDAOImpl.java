package org.openelisglobal.notification.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate.NotificationPayloadType;
import org.springframework.stereotype.Component;

@Component
public class NotificationPayloadTemplateDAOImpl extends BaseDAOImpl<NotificationPayloadTemplate, Integer>
        implements NotificationPayloadTemplateDAO {

    public NotificationPayloadTemplateDAOImpl() {
        super(NotificationPayloadTemplate.class);
    }

    @Override
    public NotificationPayloadTemplate getForNotificationPayloadType(NotificationPayloadType notificationPayloadType) {
        NotificationPayloadTemplate data;
        try {
            String sql = "from NotificationPayloadTemplate as npt where npt.type = :type";
            Query<NotificationPayloadTemplate> query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("type", notificationPayloadType.name());
            data = query.uniqueResult();
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException(
                    "Error in NotificationPayloadTemplateDAOImpl getForNotificationPayloadType()", e);
        }

        return data;
    }

}
