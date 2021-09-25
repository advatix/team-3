package com.schedular.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UpdateInventoryCommonDto implements Serializable {

	private static final long serialVersionUID = -6650569105744567611L;

	private Long productId;

	private String upc;

	private Integer quantity;

	private Boolean isOk;

	@JsonIgnore
	private Long updatedBy;

	private String orderNumber;

	private String licensePlateNumber;

	private String containerBarcode;

	private String trackingId;

	private Long fromReceivedId;

	private Long fromLotNumber;

	private Long fromWarehouseLocation;

	private String fromCustomerId;

	private Integer fromPackageType;

	private String fromLocation;

	private Integer fromReceivedStatus;

	private Integer fromInventoryStage;

	private Date fromExpirationDate;

	private Integer fromReservedQuantity;

	private Long toReceivedId;

	private Long toLotNumber;

	private Long toWarehouseLocation;

	private String toCustomerId;

	private Integer toPackageType;

	private String toLocation;

	private Integer toReceivedStatus;

	private Integer toInventoryStage;

	private Date toExpirationDate;

	private Integer toReservedQuantity;

	private Integer transactionType;

	private String reason;

	private Boolean addIntoShrinkage = Boolean.TRUE;

	private Long parentReceivedInventoryId = 0L;

	private String productBatchId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsOk() {
		return isOk;
	}

	public void setIsOk(Boolean isOk) {
		this.isOk = isOk;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public String getContainerBarcode() {
		return containerBarcode;
	}

	public void setContainerBarcode(String containerBarcode) {
		this.containerBarcode = containerBarcode;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public Long getFromReceivedId() {
		return fromReceivedId;
	}

	public void setFromReceivedId(Long fromReceivedId) {
		this.fromReceivedId = fromReceivedId;
	}

	public Long getFromLotNumber() {
		return fromLotNumber;
	}

	public void setFromLotNumber(Long fromLotNumber) {
		this.fromLotNumber = fromLotNumber;
	}

	public Long getFromWarehouseLocation() {
		return fromWarehouseLocation;
	}

	public void setFromWarehouseLocation(Long fromWarehouseLocation) {
		this.fromWarehouseLocation = fromWarehouseLocation;
	}

	public String getFromCustomerId() {
		return fromCustomerId;
	}

	public void setFromCustomerId(String fromCustomerId) {
		this.fromCustomerId = fromCustomerId;
	}

	public Integer getFromPackageType() {
		return fromPackageType;
	}

	public void setFromPackageType(Integer fromPackageType) {
		this.fromPackageType = fromPackageType;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public Integer getFromReceivedStatus() {
		return fromReceivedStatus;
	}

	public void setFromReceivedStatus(Integer fromReceivedStatus) {
		this.fromReceivedStatus = fromReceivedStatus;
	}

	public Integer getFromInventoryStage() {
		return fromInventoryStage;
	}

	public void setFromInventoryStage(Integer fromInventoryStage) {
		this.fromInventoryStage = fromInventoryStage;
	}

	public Date getFromExpirationDate() {
		return fromExpirationDate;
	}

	public void setFromExpirationDate(Date fromExpirationDate) {
		this.fromExpirationDate = fromExpirationDate;
	}

	public Integer getFromReservedQuantity() {
		return fromReservedQuantity;
	}

	public void setFromReservedQuantity(Integer fromReservedQuantity) {
		this.fromReservedQuantity = fromReservedQuantity;
	}

	public Long getToReceivedId() {
		return toReceivedId;
	}

	public void setToReceivedId(Long toReceivedId) {
		this.toReceivedId = toReceivedId;
	}

	public Long getToLotNumber() {
		return toLotNumber;
	}

	public void setToLotNumber(Long toLotNumber) {
		this.toLotNumber = toLotNumber;
	}

	public Long getToWarehouseLocation() {
		return toWarehouseLocation;
	}

	public void setToWarehouseLocation(Long toWarehouseLocation) {
		this.toWarehouseLocation = toWarehouseLocation;
	}

	public String getToCustomerId() {
		return toCustomerId;
	}

	public void setToCustomerId(String toCustomerId) {
		this.toCustomerId = toCustomerId;
	}

	public Integer getToPackageType() {
		return toPackageType;
	}

	public void setToPackageType(Integer toPackageType) {
		this.toPackageType = toPackageType;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public Integer getToReceivedStatus() {
		return toReceivedStatus;
	}

	public void setToReceivedStatus(Integer toReceivedStatus) {
		this.toReceivedStatus = toReceivedStatus;
	}

	public Integer getToInventoryStage() {
		return toInventoryStage;
	}

	public void setToInventoryStage(Integer toInventoryStage) {
		this.toInventoryStage = toInventoryStage;
	}

	public Date getToExpirationDate() {
		return toExpirationDate;
	}

	public void setToExpirationDate(Date toExpirationDate) {
		this.toExpirationDate = toExpirationDate;
	}

	public Integer getToReservedQuantity() {
		return toReservedQuantity;
	}

	public void setToReservedQuantity(Integer toReservedQuantity) {
		this.toReservedQuantity = toReservedQuantity;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getAddIntoShrinkage() {
		return addIntoShrinkage;
	}

	public void setAddIntoShrinkage(Boolean addIntoShrinkage) {
		this.addIntoShrinkage = addIntoShrinkage;
	}

	public Long getParentReceivedInventoryId() {
		return parentReceivedInventoryId;
	}

	public void setParentReceivedInventoryId(Long parentReceivedInventoryId) {
		this.parentReceivedInventoryId = parentReceivedInventoryId;
	}

	public String getProductBatchId() {
		return productBatchId;
	}

	public void setProductBatchId(String productBatchId) {
		this.productBatchId = productBatchId;
	}

	@Override
	public String toString() {
		return "UpdateInventoryCommonDto [productId=" + productId + ", upc=" + upc + ", quantity=" + quantity
				+ ", isOk=" + isOk + ", updatedBy=" + updatedBy + ", orderNumber=" + orderNumber
				+ ", licensePlateNumber=" + licensePlateNumber + ", containerBarcode=" + containerBarcode
				+ ", trackingId=" + trackingId + ", fromReceivedId=" + fromReceivedId + ", fromLotNumber="
				+ fromLotNumber + ", fromWarehouseLocation=" + fromWarehouseLocation + ", fromCustomerId="
				+ fromCustomerId + ", fromPackageType=" + fromPackageType + ", fromLocation=" + fromLocation
				+ ", fromReceivedStatus=" + fromReceivedStatus + ", fromInventoryStage=" + fromInventoryStage
				+ ", fromExpirationDate=" + fromExpirationDate + ", fromReservedQuantity=" + fromReservedQuantity
				+ ", toReceivedId=" + toReceivedId + ", toLotNumber=" + toLotNumber + ", toWarehouseLocation="
				+ toWarehouseLocation + ", toCustomerId=" + toCustomerId + ", toPackageType=" + toPackageType
				+ ", toLocation=" + toLocation + ", toReceivedStatus=" + toReceivedStatus + ", toInventoryStage="
				+ toInventoryStage + ", toExpirationDate=" + toExpirationDate + ", toReservedQuantity="
				+ toReservedQuantity + ", transactionType=" + transactionType + ", reason=" + reason
				+ ", addIntoShrinkage=" + addIntoShrinkage + ", parentReceivedInventoryId=" + parentReceivedInventoryId
				+ ", productBatchId=" + productBatchId + "]";
	}

}
