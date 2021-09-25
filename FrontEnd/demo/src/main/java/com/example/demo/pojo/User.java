package com.example.demo.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
	public static final String TABLE_NAME = "users";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY")
	private Long id;

	@Column(name = "userName", columnDefinition = "varchar(30)")
	private String userName;

	@Column(name = "password", columnDefinition = "varchar(30)")
	private String password;

	@Column(name = "email", columnDefinition = "varchar(30)")
	private String email;

	@Column(name = "phoneNumber", columnDefinition = "varchar(30)")
	private String phoneNumber;

	@UpdateTimestamp
	private Date modificationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

}
