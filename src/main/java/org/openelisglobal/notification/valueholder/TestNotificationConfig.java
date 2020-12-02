package org.openelisglobal.notification.valueholder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

import org.openelisglobal.common.valueholder.BaseObject;
import org.openelisglobal.notification.valueholder.TestNotificationConfigOption.NotificationMethod;
import org.openelisglobal.notification.valueholder.TestNotificationConfigOption.NotificationNature;
import org.openelisglobal.notification.valueholder.TestNotificationConfigOption.NotificationPersonType;
import org.openelisglobal.test.valueholder.Test;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "test_notification_config")
public class TestNotificationConfig extends BaseObject<Integer> {

    private static final long serialVersionUID = -3516692281036957868L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_notification_config_generator")
    @SequenceGenerator(name = "test_notification_config_generator", sequenceName = "test_notification_config_seq", allocationSize = 1)
    private Integer id;

    @Valid
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    @JsonIgnore
    private Test test;

    @Valid
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "default_payload_template_id", referencedColumnName = "id")
    @JsonIgnore
    private NotificationPayloadTemplate defaultPayloadTemplate;

    // could implement defaults for individual method types and person types types
    // as well
//    @OneToMany
//    @JoinTable(name = "test_notification_default_method", joinColumns = @JoinColumn(name = "test_notification_config_id"), //
//            inverseJoinColumns = @JoinColumn(name = "notification_payload_template_id")) //
//    @MapKeyColumn(name = "notification_method")
//    private Map<NotificationMethod, NotificationPayloadTemplate> defaultMethodPayloadTemplate;
//
//    @OneToMany
//    @JoinTable(name = "test_notification_default_person_type", joinColumns = @JoinColumn(name = "test_notification_config_id"), //
//            inverseJoinColumns = @JoinColumn(name = "notification_payload_template_id")) //
//    @MapKeyColumn(name = "notification_person_type")
//    private Map<NotificationPersonType, NotificationPayloadTemplate> defaultPersonPayloadTemplate;

    @Valid
    @OneToMany(mappedBy = "testNotificationConfig", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TestNotificationConfigOption> options;

    public TestNotificationConfig() {
//        options = new ArrayList<>();
//        for (NotificationNature nature : NotificationNature.values()) {
//            for (NotificationMethod methodType : NotificationMethod.values()) {
//            for (NotificationPersonType personType : NotificationPersonType.values()) {
//                options.add(new TestNotificationConfigOption(this, methodType, personType,
//                            nature, false));
//            }
//        }
//        }
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    @JsonGetter
    public String getTestId() {
        return test.getId();
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public NotificationPayloadTemplate getDefaultPayloadTemplate() {
        return defaultPayloadTemplate;
    }

    public void setDefaultPayloadTemplate(NotificationPayloadTemplate defaultPayloadTemplate) {
        this.defaultPayloadTemplate = defaultPayloadTemplate;
    }

    public List<TestNotificationConfigOption> getOptions() {
        return options;
    }

    public void setOptions(List<TestNotificationConfigOption> options) {
        this.options = options;
    }

    public TestNotificationConfigOption getOptionFor(NotificationNature nature, NotificationMethod methodType,
            NotificationPersonType personType) {
        if (options == null) {
            options = new ArrayList<>();
        }
        return options.stream().filter(opt -> opt.getNotificationMethod().equals(methodType)
                && opt.getNotificationPersonType().equals(personType) && opt.getNotificationNature().equals(nature))
                .findAny().orElseGet(() -> getAndAddNewConfigOption(nature, methodType, personType));

    }

    private TestNotificationConfigOption getAndAddNewConfigOption(NotificationNature nature,
            NotificationMethod methodType, NotificationPersonType personType) {
        TestNotificationConfigOption configOption = new TestNotificationConfigOption(this, methodType, personType,
                nature, false);
        options.add(configOption);
        return configOption;
    }

    // used in jsps

    public TestNotificationConfigOption getPatientEmail() {
        return getOptionFor(NotificationNature.RESULT_VALIDATION, NotificationMethod.EMAIL,
                NotificationPersonType.CLIENT);
    }

    public TestNotificationConfigOption getPatientSMS() {
        return getOptionFor(NotificationNature.RESULT_VALIDATION, NotificationMethod.SMS,
                NotificationPersonType.CLIENT);
    }

    public TestNotificationConfigOption getProviderEmail() {
        return getOptionFor(NotificationNature.RESULT_VALIDATION, NotificationMethod.EMAIL,
                NotificationPersonType.PROVIDER);
    }

    public TestNotificationConfigOption getProviderSMS() {
        return getOptionFor(NotificationNature.RESULT_VALIDATION, NotificationMethod.SMS,
                NotificationPersonType.PROVIDER);
    }

}
