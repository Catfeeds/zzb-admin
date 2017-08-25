package com.hcb.zzb.dto.export;

public class PlatformExport {
													
	private Integer serialNumber;//序号
	private String orderNumber;//订单号
	private int status;//订单状态
	private String zuKe;//租客
	private String carOwner;//车东
	private String carmodel;//车款
	private String useCarTime;//用车时间
	private String payTime;//付款时间
	private String chanel;//付款渠道
	private Float payMoney;//付款金额
	private Float deposit;//押金金额
	private String city;//城市
	private String link;//订单链接
	private String date;//时间
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getZuKe() {
		return zuKe;
	}
	public void setZuKe(String zuKe) {
		this.zuKe = zuKe;
	}
	public String getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}
	public String getCarmodel() {
		return carmodel;
	}
	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}
	public String getUseCarTime() {
		return useCarTime;
	}
	public void setUseCarTime(String useCarTime) {
		this.useCarTime = useCarTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getChanel() {
		return chanel;
	}
	public void setChanel(String chanel) {
		this.chanel = chanel;
	}
	public Float getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Float payMoney) {
		this.payMoney = payMoney;
	}
	public Float getDeposit() {
		return deposit;
	}
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
