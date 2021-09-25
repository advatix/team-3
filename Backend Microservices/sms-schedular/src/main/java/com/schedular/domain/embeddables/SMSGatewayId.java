package com.schedular.domain.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class SMSGatewayId implements Serializable {

  private static final long serialVersionUID = -966867138359789528L;

  // @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ConfigId", columnDefinition = "int(10) NOT NULL") // AUTO_INCREMENT")
  private Integer configId;

  public Integer getConfigId() {
    return configId;
  }

  public void setConfigId(Integer configId) {
    this.configId = configId;
  }

  public SMSGatewayId withConfigId(Integer configId) {
    this.configId = configId;
    return this;
  }

  @Override
  public String toString() {
    return "SMSGatewayId [configId=" + configId + "]";
  }

}
