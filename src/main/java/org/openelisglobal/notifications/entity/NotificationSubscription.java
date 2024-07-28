package org.openelisglobal.notifications.entity;

import javax.persistence.*;
import org.openelisglobal.systemuser.valueholder.SystemUser;

@Entity
@Table(name = "notification_subscriptions")
public class NotificationSubscription {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "pf_endpoint", nullable = false)
    private String pfEndpoint;

    @Column(name = "pf_p256dh", nullable = false)
    private String pfP256dh;

    @Column(name = "pf_auth", nullable = false)
    private String pfAuth;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private SystemUser user;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPfEndpoint() {
        return pfEndpoint;
    }

    public void setPfEndpoint(String pfEndpoint) {
        this.pfEndpoint = pfEndpoint;
    }

    public String getPfP256dh() {
        return pfP256dh;
    }

    public void setPfP256dh(String pfP256dh) {
        this.pfP256dh = pfP256dh;
    }

    public String getPfAuth() {
        return pfAuth;
    }

    public void setPfAuth(String pfAuth) {
        this.pfAuth = pfAuth;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "NotificationSubscription{" +
                "userId=" + userId +
                ", pfEndpoint='" + pfEndpoint + '\'' +
                ", pfP256dh='" + pfP256dh + '\'' +
                ", pfAuth='" + pfAuth + '\'' +
                ", user=" + (user != null ? user.toString() : "null") +
                '}';
    }
}
