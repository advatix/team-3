package com.schedular.dto;

import java.io.Serializable;

public class TwillioSMSCallBackDto implements Serializable {

  private static final long serialVersionUID = -6861028800205522999L;

  private String SmsSid;

  private String SmsStatus;

  private String MessageStatus;

  private String To;

  private String MessageSid;

  private String AccountSid;

  private String From;

  private String ApiVersion;

  public String getSmsSid() {
    return SmsSid;
  }

  public void setSmsSid(String smsSid) {
    SmsSid = smsSid;
  }

  public String getSmsStatus() {
    return SmsStatus;
  }

  public void setSmsStatus(String smsStatus) {
    SmsStatus = smsStatus;
  }

  public String getMessageStatus() {
    return MessageStatus;
  }

  public void setMessageStatus(String messageStatus) {
    MessageStatus = messageStatus;
  }

  public String getTo() {
    return To;
  }

  public void setTo(String to) {
    To = to;
  }

  public String getMessageSid() {
    return MessageSid;
  }

  public void setMessageSid(String messageSid) {
    MessageSid = messageSid;
  }

  public String getAccountSid() {
    return AccountSid;
  }

  public void setAccountSid(String accountSid) {
    AccountSid = accountSid;
  }

  public String getFrom() {
    return From;
  }

  public void setFrom(String from) {
    From = from;
  }

  public String getApiVersion() {
    return ApiVersion;
  }

  public void setApiVersion(String apiVersion) {
    ApiVersion = apiVersion;
  }

  @Override
  public String toString() {
    return "TwillioSMSCallBackDto [SmsSid=" + SmsSid + ", SmsStatus=" + SmsStatus
        + ", MessageStatus=" + MessageStatus + ", To=" + To + ", MessageSid=" + MessageSid
        + ", AccountSid=" + AccountSid + ", From=" + From + ", ApiVersion=" + ApiVersion + "]";
  }

}
