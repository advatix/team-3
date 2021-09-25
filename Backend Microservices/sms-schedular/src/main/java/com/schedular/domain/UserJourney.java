/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import com.schedular.utils.Utils;

/**
 * The Class UserJourney.
 */
@Entity
@Table(name = UserJourney.TABLE_NAME)
public class UserJourney implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -4403715072952135805L;

  /** The Constant TABLE_NAME. */
  public static final String TABLE_NAME = "User_Journey";

  /** The id. */
  @Id
  @Column(name = "Id", columnDefinition = "bigint(15)")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "RequestDate", nullable = false, updatable = false)
  @CreatedDate
  @CreationTimestamp
  private Date requestDate;

  /** The api id. */
  @Column(name = "ApiId", columnDefinition = "varchar(255)")
  private String apiId;

  /** The user id. */
  @Column(name = "UserId", columnDefinition = "bigint(15)")
  private Long userId;

  /** The user name. */
  @Column(name = "UserName", columnDefinition = "varchar(50)")
  private String userName;

  /** The module. */
  @Column(name = "Module", columnDefinition = "varchar(100)")
  private String module;

  /** The url. */
  @Column(name = "URL", columnDefinition = "text")
  private String url;

  /** The request. */
  @Column(name = "Request", columnDefinition = "longtext")
  private String request;

  /** The response code. */
  @Column(name = "ResponseCode", columnDefinition = "varchar(50)")
  private String responseCode;

  /** The ip address. */
  @Column(name = "IpAddress", columnDefinition = "varchar(50)")
  private String ipAddress;

  /** The user agent. */
  @Column(name = "UserAgent", columnDefinition = "varchar(255)")
  private String userAgent;

  @Column(name = "Headers", columnDefinition = "text")
  private String headers;

  /**
   * Pre persist.
   */
  @PrePersist
  public void prePersist() {
    Date now = Calendar.getInstance().getTime();
    requestDate = now;
  }

  @PreUpdate
  public void preUpdate() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getApiId() {
    return apiId;
  }

  public void setApiId(String apiId) {
    this.apiId = apiId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = Utils.truncate(module, 100);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getHeaders() {
    return headers;
  }

  public void setHeaders(String headers) {
    this.headers = headers;
  }

  public Date getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(Date requestDate) {
    this.requestDate = requestDate;
  }

  @Override
  public String toString() {
    return "UserJourney [id=" + id + ", apiId=" + apiId + ", userId=" + userId + ", userName="
        + userName + ", module=" + module + ", url=" + url + ", request=" + request
        + ", responseCode=" + responseCode + ", ipAddress=" + ipAddress + ", userAgent=" + userAgent
        + ", headers=" + headers + "]";
  }

}
