package com.schedular.dto;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.schedular.domain.ApplicationInfo;
import com.schedular.domain.SMSGatewayConfig;
import com.schedular.domain.embeddables.SMSGatewayId;
import com.schedular.domain.embeddables.SMSGatewayInfo;

@Entity
@Table(name = SMSGatewayConfig.TABLE_NAME)
public class SMSGatewayConfigApplicationMappedDetails implements Serializable {

  private static final long serialVersionUID = 4899268133985527416L;

  @EmbeddedId
  private SMSGatewayId id;

  @Embedded
  private SMSGatewayInfo basicInfo;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "applicationId", insertable = false, updatable = false)
  @NotFound(action = NotFoundAction.IGNORE)
  private ApplicationInfo applicationDetails;

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

  public ApplicationInfo getApplicationDetails() {
    return applicationDetails;
  }

  public void setApplicationDetails(ApplicationInfo applicationDetails) {
    this.applicationDetails = applicationDetails;
  }

  @Override
  public String toString() {
    return "SMSGatewayConfigApplicationMappedDetails [id=" + id + ", basicInfo=" + basicInfo
        + ", applicationDetails=" + applicationDetails + "]";
  }

}
