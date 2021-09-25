package com.schedular.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = EmailQueue.TABLE_NAME,
    indexes = {@Index(name = "BatchId", columnList = "BatchId", unique = false),
        @Index(name = "ApplicationId", columnList = "ApplicationId", unique = false)})
public class EmailQueue extends AbstractDomain {

  private static final long serialVersionUID = 6613529316546261564L;

  public static final String TABLE_NAME = "EMAIL_QUEUE";

  @Id
  @Column(name = "Id", columnDefinition = "bigint(20)")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "BatchId", columnDefinition = "varchar(255)")
  private String batchId;

  @Column(name = "ApplicationId", columnDefinition = "int(10)")
  private Integer applicationid;

  @Column(name = "ConfigId", columnDefinition = "int(10) NOT NULL")
  private Integer configId;

  @Column(name = "QueueStatus", columnDefinition = "int(5)")
  private Integer queueStatus;

  @Column(name = "MessageSent")
  private Boolean messageSent;

  @Column(name = "MailTo", columnDefinition = "text")
  private String mailTo;

  @Column(name = "MailToCC", columnDefinition = "text")
  private String mailTocc;

  @Column(name = "MailToBCC", columnDefinition = "text")
  private String mailTobcc;

  @Column(name = "Subject", columnDefinition = "text")
  private String subject;

  @Column(name = "Message", columnDefinition = "longtext")
  private String message;

  @Column(name = "ContentType", columnDefinition = "varchar(100)")
  private String contentType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBatchId() {
    return batchId;
  }

  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }

  public Integer getApplicationid() {
    return applicationid;
  }

  public void setApplicationid(Integer applicationid) {
    this.applicationid = applicationid;
  }

  public Integer getConfigId() {
    return configId;
  }

  public void setConfigId(Integer configId) {
    this.configId = configId;
  }

  public Integer getQueueStatus() {
    return queueStatus;
  }

  public void setQueueStatus(Integer queueStatus) {
    this.queueStatus = queueStatus;
  }

  public Boolean getMessageSent() {
    return messageSent;
  }

  public void setMessageSent(Boolean messageSent) {
    this.messageSent = messageSent;
  }

  public String getMailTo() {
    return mailTo;
  }

  public void setMailTo(String mailTo) {
    this.mailTo = mailTo;
  }

  public String getMailTocc() {
    return mailTocc;
  }

  public void setMailTocc(String mailTocc) {
    this.mailTocc = mailTocc;
  }

  public String getMailTobcc() {
    return mailTobcc;
  }

  public void setMailTobcc(String mailTobcc) {
    this.mailTobcc = mailTobcc;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  @Override
  public String toString() {
    return "EmailQueue [id=" + id + ", batchId=" + batchId + ", applicationid=" + applicationid
        + ", configId=" + configId + ", queueStatus=" + queueStatus + ", messageSent=" + messageSent
        + ", mailTo=" + mailTo + ", mailTocc=" + mailTocc + ", mailTobcc=" + mailTobcc
        + ", subject=" + subject + ", message=" + message + ", contentType=" + contentType + "]";
  }

}
