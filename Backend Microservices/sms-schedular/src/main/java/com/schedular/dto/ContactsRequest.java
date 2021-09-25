package com.schedular.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Validated
public class ContactsRequest implements Serializable {

  private static final long serialVersionUID = -3750351642840187368L;

  @NotBlank(message = "Contact number cannot empty")
  private String contact;

  @NotBlank(message = "Message cannot empty")
  private String message;

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ContactsRequest [contact=" + contact + ", message=" + message + "]";
  }

}
