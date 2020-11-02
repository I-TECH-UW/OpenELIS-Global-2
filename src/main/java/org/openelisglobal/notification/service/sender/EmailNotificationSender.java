package org.openelisglobal.notification.service.sender;

import org.openelisglobal.common.util.ConfigurationListener;
import org.openelisglobal.notification.valueholder.EmailNotification;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender implements ClientNotificationSender<EmailNotification>, ConfigurationListener {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Class<EmailNotification> forClass() {
        return EmailNotification.class;
    }


    @Override
    public void send(EmailNotification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notification.getRecipientEmailAddress());
        message.setSubject(notification.getSubject());
        message.setText(notification.getMessage());
        javaMailSender.send(message);
    }

    @Override
    public void refreshConfiguration() {
        javaMailSender = SpringContext.getBean(JavaMailSender.class);
    }

}
