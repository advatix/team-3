package com.schedular.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = SMTPConfig.TABLE_NAME)
public class SMTPConfig extends AbstractDomain {

  private static final long serialVersionUID = 4899268133985527416L;

  public static final String TABLE_NAME = "SMTP_Config";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ConfigId", columnDefinition = "int(10) NOT NULL") // AUTO_INCREMENT")
  private Integer configId;

  @Column(name = "ApplicationId", columnDefinition = "int(10)")
  private Integer applicationId;
  
  @Column(name = "CustomerId", columnDefinition = "varchar(20)")
  private String customerId;

  @Column(name = "Host", columnDefinition = "varchar(255)")
  private String host;

  @Column(name = "Port", columnDefinition = "int(10)")
  private Integer port;

  @Column(name = "Username", columnDefinition = "varchar(255)")
  private String username;

  @Column(name = "Password", columnDefinition = "varchar(255)")
  private String password;

  @Column(name = "Status", columnDefinition = "int(5)")
  private Integer status;

  @Column(name = "MailFrom", columnDefinition = "varchar(255)")
  private String mailFrom;

  @Column(name = "MailFromName", columnDefinition = "varchar(255)")
  private String mailFromName;

  public Integer getConfigId() {
    return configId;
  }

  public void setConfigId(Integer configId) {
    this.configId = configId;
  }

  public Integer getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Integer applicationId) {
    this.applicationId = applicationId;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMailFrom() {
    return mailFrom;
  }

  public void setMailFrom(String mailFrom) {
    this.mailFrom = mailFrom;
  }

  public String getMailFromName() {
    return mailFromName;
  }

  public void setMailFromName(String mailFromName) {
    this.mailFromName = mailFromName;
  }

  @Override
  public String toString() {
    return "SMTPConfig [configId=" + configId + ", applicationId=" + applicationId + ", host="
        + host + ", port=" + port + ", username=" + username + ", password=" + password
        + ", status=" + status + ", mailFrom=" + mailFrom + ", mailFromName=" + mailFromName + "]";
  }

}
