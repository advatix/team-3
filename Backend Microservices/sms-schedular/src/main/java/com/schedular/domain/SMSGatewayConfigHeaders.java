package com.schedular.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.schedular.domain.embeddables.SMSGatewayId;

@Entity
@Table(name = SMSGatewayConfigHeaders.TABLE_NAME)
public class SMSGatewayConfigHeaders extends AbstractDomain {

  private static final long serialVersionUID = 4899268133985527416L;

  public static final String TABLE_NAME = "SMS_Gateway_Config_Header";

  @Id
  @Column(name = "Id", columnDefinition = "int(10)")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Embedded
  private SMSGatewayId configId;

  @Column(name = "HeaderKey", columnDefinition = "varchar(255)")
  private String headerKey;

  @Column(name = "HeaderValue", columnDefinition = "varchar(255)")
  private String headerValue;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public SMSGatewayId getConfigId() {
    return configId;
  }

  public void setConfigId(SMSGatewayId configId) {
    this.configId = configId;
  }

  public String getHeaderKey() {
    return headerKey;
  }

  public void setHeaderKey(String headerKey) {
    this.headerKey = headerKey;
  }

  public String getHeaderValue() {
    return headerValue;
  }

  public void setHeaderValue(String headerValue) {
    this.headerValue = headerValue;
  }

  @Override
  public String toString() {
    return "SMSGatewayConfigHeaders [id=" + id + ", configId=" + configId + ", headerKey="
        + headerKey + ", headerValue=" + headerValue + "]";
  }

}
