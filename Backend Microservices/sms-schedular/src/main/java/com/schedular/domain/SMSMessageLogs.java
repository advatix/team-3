package com.schedular.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import com.schedular.domain.embeddables.ApplicationId;
import com.schedular.domain.embeddables.SMSGatewayId;

@Entity
@Table(name = SMSMessageLogs.TABLE_NAME,
    indexes = {
        @Index(name = "SearchAbleIds", columnList = "BatchId, ApplicationId, ConfigId, MessageId",
            unique = false),
        @Index(name = "SortingOrder", columnList = "CreatedOn", unique = false)})
public class SMSMessageLogs extends AbstractDomain {

  private static final long serialVersionUID = 3042278127797609023L;

  public static final String TABLE_NAME = "SMS_Message_Logs";

  /** The id. */
  @Id
  @Column(name = "Id", columnDefinition = "bigint(20)")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "BatchId", columnDefinition = "varchar(255)")
  private String batchId;

  @Embedded
  private ApplicationId appId;

  @Embedded
  private SMSGatewayId configId;

  @Column(name = "MessageId", columnDefinition = "varchar(255)")
  private String messageId;

  @Column(name = "MessageStatus", columnDefinition = "varchar(255)")
  private String messageStatus;

  @Column(name = "FromContact", columnDefinition = "varchar(255)")
  private String from;

  @Column(name = "ToContact", columnDefinition = "varchar(255)")
  private String to;

  @Column(name = "ErrorCode", columnDefinition = "varchar(50)")
  private String errorCode;

  @Column(name = "ErrorMessage", columnDefinition = "varchar(255)")
  private String errorMessage;

  @Column(name = "Price", columnDefinition = "varchar(255)")
  private String price;

  @Column(name = "PriceUnit", columnDefinition = "varchar(255)")
  private String priceUnit;

  @Column(name = "MediaCount", columnDefinition = "int(10)")
  private Integer mediaCount;

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

  public ApplicationId getAppId() {
    return appId;
  }

  public void setAppId(ApplicationId appId) {
    this.appId = appId;
  }

  public SMSGatewayId getConfigId() {
    return configId;
  }

  public void setConfigId(SMSGatewayId configId) {
    this.configId = configId;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getMessageStatus() {
    return messageStatus;
  }

  public void setMessageStatus(String messageStatus) {
    this.messageStatus = messageStatus;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getPriceUnit() {
    return priceUnit;
  }

  public void setPriceUnit(String priceUnit) {
    this.priceUnit = priceUnit;
  }

  public Integer getMediaCount() {
    return mediaCount;
  }

  public void setMediaCount(Integer mediaCount) {
    this.mediaCount = mediaCount;
  }

  @Override
  public String toString() {
    return "SMSMessageLogs [id=" + id + ", batchId=" + batchId + ", appId=" + appId + ", configId="
        + configId + ", messageId=" + messageId + ", messageStatus=" + messageStatus + ", from="
        + from + ", to=" + to + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
        + ", price=" + price + ", priceUnit=" + priceUnit + ", mediaCount=" + mediaCount + "]";
  }

}
