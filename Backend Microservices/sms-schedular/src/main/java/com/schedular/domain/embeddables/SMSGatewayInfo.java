package com.schedular.domain.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.hibernate.annotations.Formula;
import com.schedular.domain.AbstractDomain;
import com.schedular.domain.MasterStatus;
import com.schedular.domain.enums.Gateway;
import com.schedular.domain.enums.RequestType;

@Embeddable
public class SMSGatewayInfo extends AbstractDomain {

  private static final long serialVersionUID = 4899268133985527416L;

  @Column(name = "ApplicationId", columnDefinition = "int(10)")
  private Integer applicationId;

  @Column(name = "Url", columnDefinition = "text")
  private String url;

  @Column(name = "Sid", columnDefinition = "varchar(255)")
  private String sid;

  @Column(name = "FromNumber", columnDefinition = "varchar(50)")
  private String fromNumber;

  @Column(name = "Status", columnDefinition = "int(5)")
  private Integer status;

  @Formula(
      value = "(SELECT p.statusDesc FROM " + MasterStatus.TABLE_NAME + " p WHERE p.id = status)")
  private String statusDesc;

  @Column(name = "Retries", columnDefinition = "int(5)")
  private Integer retries;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "RequestType")
  private RequestType requestType;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "Gateway")
  private Gateway gateway;

  public Integer getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Integer applicationId) {
    this.applicationId = applicationId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSid() {
    return sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }

  public String getFromNumber() {
    return fromNumber;
  }

  public void setFromNumber(String fromNumber) {
    this.fromNumber = fromNumber;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getStatusDesc() {
    return statusDesc;
  }

  public void setStatusDesc(String statusDesc) {
    this.statusDesc = statusDesc;
  }

  public Integer getRetries() {
    return retries;
  }

  public void setRetries(Integer retries) {
    this.retries = retries;
  }

  public RequestType getRequestType() {
    return requestType;
  }

  public void setRequestType(RequestType requestType) {
    this.requestType = requestType;
  }

  public Gateway getGateway() {
    return gateway;
  }

  public void setGateway(Gateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public String toString() {
    return "SMSGatewayInfo [applicationId=" + applicationId + ", url=" + url + ", sid=" + sid
        + ", fromNumber=" + fromNumber + ", status=" + status + ", statusDesc=" + statusDesc
        + ", retries=" + retries + ", requestType=" + requestType + ", gateway=" + gateway + "]";
  }

}
