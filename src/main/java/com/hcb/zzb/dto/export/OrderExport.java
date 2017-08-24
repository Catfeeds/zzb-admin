package com.hcb.zzb.dto.export;

public class OrderExport {
	private Integer serialNumber;//序号
	private String orderNumber;//订单号
	private String userName;//租客
	private String carOwnerName;//车东
	private String carUuid;//车辆
	private String usetime;//使用时间
	private String useCarTime;//使用时长
	private Float money;//租金
	private Float deposit;//押金
	private String tackCarAddress;//取车地点
	private String returnCarAddress;//还车地点
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
	public String getCarOwnerName() {
		return carOwnerName;
	}
	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}
	public String getCarUuid() {
		return carUuid;
	}
	public void setCarUuid(String carUuid) {
		this.carUuid = carUuid;
	}
	public String getUsetime() {
		return usetime;
	}
	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}
	public Float getDeposit() {
		return deposit;
	}
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}
	public String getTackCarAddress() {
		return tackCarAddress;
	}
	public void setTackCarAddress(String tackCarAddress) {
		this.tackCarAddress = tackCarAddress;
	}
	public String getReturnCarAddress() {
		return returnCarAddress;
	}
	public void setReturnCarAddress(String returnCarAddress) {
		this.returnCarAddress = returnCarAddress;
	}
	
}
