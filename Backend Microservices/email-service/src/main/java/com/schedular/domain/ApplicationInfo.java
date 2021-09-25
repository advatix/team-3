package com.schedular.domain;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.schedular.domain.embeddables.ApplicationBasicInfo;
import com.schedular.domain.embeddables.ApplicationId;

@Entity
@Table(name = ApplicationInfo.TABLE_NAME)
public class ApplicationInfo implements Serializable {

  private static final long serialVersionUID = -1796480177233750266L;

  public static final String TABLE_NAME = "Application_Info";

  @EmbeddedId
  @AttributeOverrides(
      value = {@AttributeOverride(name = "appId", column = @Column(name = "ApplicationId"))})
  private ApplicationId id;

  @Embedded
  private ApplicationBasicInfo basicInfo;

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

  @Override
  public String toString() {
    return "ApplicationInfo [id=" + id + ", basicInfo=" + basicInfo + "]";
  }

}
