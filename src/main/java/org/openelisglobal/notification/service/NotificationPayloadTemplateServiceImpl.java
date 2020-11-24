package org.openelisglobal.notification.service;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.notification.dao.NotificationPayloadTemplateDAO;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate.NotificationPayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationPayloadTemplateServiceImpl extends BaseObjectServiceImpl<NotificationPayloadTemplate, Integer>
        implements NotificationPayloadTemplateService {

    @Autowired
    private NotificationPayloadTemplateDAO baseDAO;

    public NotificationPayloadTemplateServiceImpl() {
        super(NotificationPayloadTemplate.class);
        this.auditTrailLog = false;
    }

    @Override
    protected BaseDAO<NotificationPayloadTemplate, Integer> getBaseObjectDAO() {
        return baseDAO;
    }

    @Override
    public NotificationPayloadTemplate getForNotificationPayloadType(NotificationPayloadType notificationPayloadType) {
        return baseDAO.getForNotificationPayloadType(notificationPayloadType);

    }

}
