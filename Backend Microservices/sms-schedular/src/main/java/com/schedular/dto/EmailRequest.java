package com.schedular.dto;

import java.io.Serializable;

public class EmailRequest implements Serializable {

  private static final long serialVersionUID = -3750351642840187368L;

  private String mailTo;

  private String mailToCC;

  private String mailToBCC;

  private String subject;

  private String message;

  private String contentType;

  public String getMailTo() {
    return mailTo;
  }

  public void setMailTo(String mailTo) {
    this.mailTo = mailTo;
  }

  public String getMailToCC() {
    return mailToCC;
  }

  public void setMailToCC(String mailToCC) {
    this.mailToCC = mailToCC;
  }

  public String getMailToBCC() {
    return mailToBCC;
  }

  public void setMailToBCC(String mailToBCC) {
    this.mailToBCC = mailToBCC;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  @Override
  public String toString() {
    return "EmailRequest [mailTo=" + mailTo + ", mailToCC=" + mailToCC + ", mailToBCC=" + mailToBCC
        + ", subject=" + subject + ", message=" + message + ", contentType=" + contentType + "]";
  }

}
