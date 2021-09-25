package com.schedular.domain.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ApplicationId implements Serializable {

  private static final long serialVersionUID = -966867138359789528L;

  // @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ApplicationId", columnDefinition = "int(10)")
  private Integer appId;

  public Integer getAppId() {
    return appId;
  }

  public void setAppId(Integer appId) {
    this.appId = appId;
  }

  public ApplicationId withAppId(Integer appId) {
    this.appId = appId;
    return this;
  }

  @Override
  public String toString() {
    return "ApplicationId [appId=" + appId + "]";
  }

}
