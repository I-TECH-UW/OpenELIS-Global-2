package org.openelisglobal.notification.valueholder;

public class EmailNotification implements ClientNotification {

    private String recipientEmailAddress;

    private NotificationPayload payload;

    public String getRecipientEmailAddress() {
        return recipientEmailAddress;
    }

    public void setRecipientEmailAddress(String recipientEmailAddress) {
        this.recipientEmailAddress = recipientEmailAddress;
    }

    public NotificationPayload getPayload() {
        return payload;
    }

    public void setPayload(NotificationPayload payload) {
        this.payload = payload;
    }

    @Override
    public String getMessage() {
        return payload.getMessage();
    }

    @Override
    public String getSubject() {
        return payload.getSubject();
    }

}
