package com.hcb.zzb.dto.export;

public class TicketExport {
	private Integer serialNumber;//序号
	private String orderNumber;//订单号
	private String userName;//用户
	private String carOwner;//车主
	private String address;//违章信息
	private String money;//罚款
	private Integer points;//扣分
	private String disposeWay;//处理方式
	private String time;//时间
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getDisposeWay() {
		return disposeWay;
	}
	public void setDisposeWay(String disposeWay) {
		this.disposeWay = disposeWay;
	}
	
}
