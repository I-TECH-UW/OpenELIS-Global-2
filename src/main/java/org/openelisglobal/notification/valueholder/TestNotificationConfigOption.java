package org.openelisglobal.notification.valueholder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "test_notification_config_option")
public class TestNotificationConfigOption extends BaseObject<Integer> {

    private static final long serialVersionUID = -6242849348547228319L;

    public enum NotificationNature {
        RESULT_VALIDATION
    }

    public enum NotificationPersonType {
        CLIENT, PROVIDER
    }

    public enum NotificationMethod {
        SMS, EMAIL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_notification_config_option_generator")
    @SequenceGenerator(name = "test_notification_config_option_generator", sequenceName = "test_notification_config_option_seq", allocationSize = 1)
    private Integer id;

    @Valid
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "test_notification_config_id", referencedColumnName = "id")
    private TestNotificationConfig testNotificationConfig;

    // persistence
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_nature")
    // validation
    @NotNull
    private NotificationNature notificationNature;

    // persistence
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_person_type")
    // validation
    @NotNull
    private NotificationPersonType notificationPersonType;

    // persistence
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_method")
    // validation
    @NotNull
    private NotificationMethod notificationMethod;

    @Valid
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "payload_template_id", referencedColumnName = "id")
    private NotificationPayloadTemplate payloadTemplate;

    @Column(name = "active")
    private boolean active;

    public TestNotificationConfigOption(TestNotificationConfig testNotificationConfig,
            NotificationMethod methodType, NotificationPersonType personType, NotificationNature notificationNature,
            boolean active) {
        this.testNotificationConfig = testNotificationConfig;
        this.notificationMethod = methodType;
        this.notificationPersonType = personType;
        this.notificationNature = notificationNature;
        this.active = active;
    }

    public TestNotificationConfigOption() {

    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public TestNotificationConfig getTestNotificationConfig() {
        return testNotificationConfig;
    }

    public void setTestNotificationConfig(TestNotificationConfig testNotificationConfig) {
        this.testNotificationConfig = testNotificationConfig;
    }

    public NotificationNature getNotificationNature() {
        return notificationNature;
    }

    public void setNotificationNature(NotificationNature notificationNature) {
        this.notificationNature = notificationNature;
    }

    public NotificationPersonType getNotificationPersonType() {
        return notificationPersonType;
    }

    public void setNotificationPersonType(NotificationPersonType notificationPersonType) {
        this.notificationPersonType = notificationPersonType;
    }

    public NotificationMethod getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(NotificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    public NotificationPayloadTemplate getPayloadTemplate() {
        return payloadTemplate;
    }

    public void setPayloadTemplate(NotificationPayloadTemplate payloadTemplate) {
        this.payloadTemplate = payloadTemplate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
