package com.hcb.zzb.dto.export;

public class CarExport {
	//"序号","车辆ID","用户ID","最后登录时间","车型","颜色","取车位置","需求热度","状态","订单数","差价利润","上架价格"
	private Integer serialNumber;//序号
	private String carId;//车辆id
	private String userId;//用户ID
	private String lastLoginTime;//最后登录时间
	private String modelCharacter;//车型
	private String color;//颜色
	private String address;//取车位置
	private String hot;//需求热度
	private String status;//状态
	private String orderQuantity;//订单数
	private String profit;//差价利润
	private String price;//上架价格
	
	
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getModelCharacter() {
		return modelCharacter;
	}
	public void setModelCharacter(String modelCharacter) {
		this.modelCharacter = modelCharacter;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHot() {
		return hot;
	}
	public void setHot(String hot) {
		this.hot = hot;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}