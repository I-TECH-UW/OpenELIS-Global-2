package org.openelisglobal.notifications.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Table
@Entity
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date", nullable = false)
    private OffsetDateTime createdDate;

    @Column(name = "read_at")
    private OffsetDateTime readAt;

    @Column(name = "message", nullable = false)
    private String message;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public OffsetDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(OffsetDateTime readAt) {
        this.readAt = readAt;
    }

    @Override
    public String toString() {
        return "Notifications{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", readAt=" + readAt +
                ", message='" + message + '\'' +
                '}';
    }
}
