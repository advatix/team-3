package com.demo.user.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Customer implements Serializable {

	private static final long serialVersionUID = 52737804507030560L;

	private Long customerId;
	private String customerCode;
	private String customerName;
	private String customerAddress;

}
