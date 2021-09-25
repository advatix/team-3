package com.schedular.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.Formula;
import com.schedular.domain.embeddables.SMSGatewayId;
import com.schedular.utils.Utils;

@Entity
@Table(name = SMSQueue.TABLE_NAME, indexes = {
    @Index(name = "SearchAbleIds", columnList = "BatchId,ApplicationId", unique = false)})
public class SMSQueue extends AbstractDomain {

  private static final long serialVersionUID = 6613529316546261564L;

  public static final String TABLE_NAME = "SMS_QUEUE";

  @Id
  @Column(name = "Id", columnDefinition = "bigint(20)")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "BatchId", columnDefinition = "varchar(255)")
  private String batchId;

  @Column(name = "ApplicationId", columnDefinition = "int(10)")
  private Integer applicationid;

  @Embedded
  private SMSGatewayId configId;

  @Column(name = "ContactNumber", columnDefinition = "varchar(20)")
  private String contactNumber;

  @Column(name = "Message", columnDefinition = "text")
  private String message;

  @Column(name = "QueueStatus", columnDefinition = "int(5)")
  private Integer queueStatus;

  @Formula(value = "(SELECT p.statusDesc FROM " + MasterQueueStatus.TABLE_NAME
      + " p WHERE p.id = queueStatus)")
  private String queueStatusDesc;

  @Column(name = "MessageSent")
  private Boolean messageSent;

  @Column(name = "Attempts", columnDefinition = "int(5)")
  private Integer attempts;

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

  public SMSGatewayId getConfigId() {
    return configId;
  }

  public void setConfigId(SMSGatewayId configId) {
    this.configId = configId;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getQueueStatus() {
    return queueStatus;
  }

  public void setQueueStatus(Integer queueStatus) {
    this.queueStatus = queueStatus;
  }

  public String getQueueStatusDesc() {
    return queueStatusDesc;
  }

  public void setQueueStatusDesc(String queueStatusDesc) {
    this.queueStatusDesc = queueStatusDesc;
  }

  public Boolean getMessageSent() {
    return messageSent;
  }

  public void setMessageSent(Boolean messageSent) {
    this.messageSent = messageSent;
  }

  public Integer getAttempts() {
    return attempts;
  }

  public void setAttempts(Integer attempts) {
    this.attempts = attempts;
  }

  @Override
  public String toString() {
    return "SMSQueue [id=" + id + ", batchId=" + batchId + ", applicationid=" + applicationid
        + ", configId=" + configId + ", contactNumber=" + contactNumber + ", message=" + message
        + ", queueStatus=" + queueStatus + ", queueStatusDesc=" + queueStatusDesc + ", messageSent="
        + messageSent + ", attempts=" + attempts + "]";
  }

  public SMSQueue copyProperties() {
    SMSQueue queue = new SMSQueue();
    Utils.copyProperties(this, queue);
    return queue;
  }

  public SMSQueue withContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
    return this;
  }

}
