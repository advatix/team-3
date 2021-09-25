package com.example.demo.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "email_search")
public class EmailModel {
	private String batchid;
	private String mailto;
	private String mailtocc;
	private String contenttype;
	private String message;
	private Long id;
	private Integer configid;
	private String mailtobcc;
	private String subject;
	private Boolean messagesent;
	private String updatedon;

	public String getBatchid() {
		return batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	public String getMailto() {
		return mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

	public String getMailtocc() {
		return mailtocc;
	}

	public void setMailtocc(String mailtocc) {
		this.mailtocc = mailtocc;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getConfigid() {
		return configid;
	}

	public void setConfigid(Integer configid) {
		this.configid = configid;
	}

	public String getMailtobcc() {
		return mailtobcc;
	}

	public void setMailtobcc(String mailtobcc) {
		this.mailtobcc = mailtobcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Boolean getMessagesent() {
		return messagesent;
	}

	public void setMessagesent(Boolean messagesent) {
		this.messagesent = messagesent;
	}

	public String getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(String updatedon) {
		this.updatedon = updatedon;
	}

}
