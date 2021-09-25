package com.schedular.domain;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.schedular.domain.embeddables.SMSGatewayId;
import com.schedular.domain.embeddables.SMSGatewayInfo;

@Entity
@Table(name = SMSGatewayConfig.TABLE_NAME)
public class SMSGatewayConfig implements Serializable {

  private static final long serialVersionUID = 4899268133985527416L;

  public static final String TABLE_NAME = "SMS_Gateway_Config";

  @EmbeddedId
  private SMSGatewayId id;

  @Embedded
  private SMSGatewayInfo basicInfo;

  public SMSGatewayId getId() {
    return id;
  }

  public void setId(SMSGatewayId id) {
    this.id = id;
  }

  public SMSGatewayInfo getBasicInfo() {
    return basicInfo;
  }

  public void setBasicInfo(SMSGatewayInfo basicInfo) {
    this.basicInfo = basicInfo;
  }

  @Override
  public String toString() {
    return "SMSGatewayConfig [id=" + id + ", basicInfo=" + basicInfo + "]";
  }

}
