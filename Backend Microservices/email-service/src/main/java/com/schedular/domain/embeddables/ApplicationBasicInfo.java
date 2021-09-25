package com.schedular.domain.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.annotations.Formula;
import com.schedular.domain.AbstractDomain;
import com.schedular.domain.MasterStatus;

@Embeddable
public class ApplicationBasicInfo extends AbstractDomain {

  private static final long serialVersionUID = 4899268133985527416L;

  @Column(name = "ApplicationName", columnDefinition = "varchar(255)")
  private String applicationName;

  @Column(name = "ContactName", columnDefinition = "varchar(255)")
  private String contactName;

  @Column(name = "ContactEmail", columnDefinition = "varchar(255)")
  private String contactEmail;

  @Column(name = "Status", columnDefinition = "int(5)")
  private Integer status;

  @Formula(
      value = "(SELECT p.statusDesc FROM " + MasterStatus.TABLE_NAME + " p WHERE p.id = status)")
  private String statusDescription;

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getStatusDescription() {
    return statusDescription;
  }

  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }

  @Override
  public String toString() {
    return "ApplicationInfo [applicationName=" + applicationName + ", contactName=" + contactName
        + ", contactEmail=" + contactEmail + ", status=" + status + ", statusDescription="
        + statusDescription + "]";
  }

}
