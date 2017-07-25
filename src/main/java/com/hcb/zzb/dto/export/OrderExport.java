package com.hcb.zzb.dto.export;

public class OrderExport {
	private Integer serialNumber;//序号
	private String orderNumber;//订单号
	private String userName;//用户
	private String useCarTime;//用车时长
	private Float money;//费用
	private String status;//状态
	private String date;//下单时间
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUseCarTime() {
		return useCarTime;
	}
	public void setUseCarTime(String useCarTime) {
		this.useCarTime = useCarTime;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
