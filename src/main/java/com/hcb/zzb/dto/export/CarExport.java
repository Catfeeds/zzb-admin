package com.hcb.zzb.dto.export;

public class CarExport {
	private Integer serialNumber;//序号
	private Integer carId;//车辆id
	private String modelCharacter;//车型
	private Integer orderQuantity;//接单量
	private Integer ticketQuantity;//罚单数量
	private String status;//状态
	
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getModelCharacter() {
		return modelCharacter;
	}
	public void setModelCharacter(String modelCharacter) {
		this.modelCharacter = modelCharacter;
	}
	public Integer getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public Integer getTicketQuantity() {
		return ticketQuantity;
	}
	public void setTicketQuantity(Integer ticketQuantity) {
		this.ticketQuantity = ticketQuantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}