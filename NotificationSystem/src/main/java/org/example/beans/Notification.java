package org.example.beans;

import org.example.NotificationType;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class Notification {


    public enum Status {
        PENDING, IN_PROGRESS, SENT, FAILED, DLQ
    }



    private String id =  UUID.randomUUID().toString();
    private String requestId;
    private NotificationType type;
    private String templateId;
    private Map<String, String> metadata;
    private Status status = Status.PENDING;
    private String subject;
    private String body;

    private int noOfAttempts;
    private Instant lastUpdatedAt = Instant.now();
    private String lastError;
    private Instant createdAt = Instant.now();


    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getNoOfAttempts() {
        return noOfAttempts;
    }

    public void setNoOfAttempts(int noOfAttempts) {
        this.noOfAttempts = noOfAttempts;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Notification(String requestId, NotificationType type,
                        String templateId,
                        Status status, String subject, String body,
                        Map<String, String> metadata) {
        this.requestId = requestId;
        this.type = type;
        this.templateId = templateId;
        this.metadata = metadata;
        this.status = status;
        this.subject = subject;
        this.body = body;
    }


    public void incrementNoOfAttempts() {
        this.noOfAttempts++;
        this.lastUpdatedAt = Instant.now();
    }

    public void markInProgress() {
        this.status = Status.IN_PROGRESS;
        this.lastUpdatedAt = Instant.now();
    }
    public void markFailed(String error) {
        this.status = Status.FAILED;
        this.lastUpdatedAt = Instant.now();
        this.lastError = error;
    }

    public void markSent() {
        this.status = Status.SENT;
        this.lastUpdatedAt = Instant.now();
    }
    public void markDlq(String error) {
        this.status = Status.DLQ;
        this.lastUpdatedAt = Instant.now();
        this.lastError = error;
    }


}
