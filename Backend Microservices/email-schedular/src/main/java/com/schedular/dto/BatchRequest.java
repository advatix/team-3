package com.schedular.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.schedular.domain.EmailQueue;
import com.schedular.domain.SMSQueue;
import com.schedular.domain.embeddables.SMSGatewayId;
import com.schedular.transformers.BatchRequestTransformer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchRequest implements Serializable {

  private static final long serialVersionUID = 8822664879982519544L;

  @NotBlank(message = "Application id cannot empty")
  private Integer appId;

  private Integer gatewayId;

  @NotEmpty(message = "Contacts list cannot empty")
  private List<@Valid ContactsRequest> contactsList;

  private List<EmailRequest> emailList;

  public Integer getAppId() {
    return appId;
  }

  public void setAppId(Integer appId) {
    this.appId = appId;
  }

  public Integer getGatewayId() {
    return gatewayId;
  }

  public void setGatewayId(Integer gatewayId) {
    this.gatewayId = gatewayId;
  }

  public List<ContactsRequest> getContactsList() {
    return contactsList;
  }

  public void setContactsList(List<ContactsRequest> contactsList) {
    this.contactsList = contactsList;
  }

  public List<EmailRequest> getEmailList() {
    return emailList;
  }

  public void setEmailList(List<EmailRequest> emailList) {
    this.emailList = emailList;
  }

  public List<SMSQueue> toQueue(UUID batchId, SMSGatewayId configId) {
    return BatchRequestTransformer.transform(this, batchId, configId);
  }
  
  public List<EmailQueue> toEmailQueue(UUID batchId, Integer configId) {
    return BatchRequestTransformer.transformEmailQueue(this, batchId, configId);
  }

  @JsonIgnore
  public UUID getBatchId() {
    return UUID.randomUUID();
  }

  @Override
  public String toString() {
    return "BatchRequest [appId=" + appId + ", gatewayId=" + gatewayId + ", contactsList="
        + contactsList + ", emailList=" + emailList + "]";
  }

}
