package com.schedular.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Where;
import com.schedular.domain.ApplicationInfo;
import com.schedular.domain.SMSGatewayConfig;
import com.schedular.domain.embeddables.ApplicationBasicInfo;
import com.schedular.domain.embeddables.ApplicationId;

@Entity
@Table(name = ApplicationInfo.TABLE_NAME)
public class ApplicationInfoConfigMappedDetails implements Serializable {

  private static final long serialVersionUID = -1796480177233750266L;

  @EmbeddedId
  private ApplicationId id;

  @Embedded
  private ApplicationBasicInfo basicInfo;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "applicationId", insertable = false, updatable = false)
  @Where(clause = "status = 1")
  private List<SMSGatewayConfig> configList;

  public ApplicationId getId() {
    return id;
  }

  public void setId(ApplicationId id) {
    this.id = id;
  }

  public ApplicationBasicInfo getBasicInfo() {
    return basicInfo;
  }

  public void setBasicInfo(ApplicationBasicInfo basicInfo) {
    this.basicInfo = basicInfo;
  }

  public List<SMSGatewayConfig> getConfigList() {
    return configList;
  }

  public void setConfigList(List<SMSGatewayConfig> configList) {
    this.configList = configList;
  }

  @Override
  public String toString() {
    return "ApplicationInfoConfigMappedDetails [id=" + id + ", basicInfo=" + basicInfo
        + ", configList=" + configList + "]";
  }

}
