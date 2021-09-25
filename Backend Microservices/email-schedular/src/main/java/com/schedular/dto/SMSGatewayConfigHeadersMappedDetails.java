package com.schedular.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.schedular.domain.SMSGatewayConfig;
import com.schedular.domain.SMSGatewayConfigHeaders;
import com.schedular.domain.embeddables.SMSGatewayId;
import com.schedular.domain.embeddables.SMSGatewayInfo;

@Entity
@Table(name = SMSGatewayConfig.TABLE_NAME)
public class SMSGatewayConfigHeadersMappedDetails implements Serializable {

  private static final long serialVersionUID = 4899268133985527416L;

  @EmbeddedId
  private SMSGatewayId id;

  @Embedded
  private SMSGatewayInfo basicInfo;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "configId")
  private List<SMSGatewayConfigHeaders> configList;

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

  public List<SMSGatewayConfigHeaders> getConfigList() {
    return configList;
  }

  public void setConfigList(List<SMSGatewayConfigHeaders> configList) {
    this.configList = configList;
  }

  @Override
  public String toString() {
    return "SMSGatewayConfigHeadersMappedDetails [id=" + id + ", basicInfo=" + basicInfo
        + ", configList=" + configList + "]";
  }

}
