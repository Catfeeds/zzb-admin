package com.hcb.zzb.dto.export;

public class CarOwnerExport {
	//"序号","ID","姓名","手机","性别","车辆品牌","有效车辆数","历史车辆数","注册时间","最后一次登陆时间","登录次数","接单次数","订单GDP","平台分佣","差价利润","信用分","账户余额","可提现金额","提现中金额","已提现金额"	
	private Integer serialNumber;//序号
	private String id;
	private String name;
	private String phone;
	private String sex;
	private String carBrand;
	private String validCarQuantity;
	private String historyCarQuantity;
	private String registerTime;
	private String lastLoginTime;
	private String loginQuantity;
	private String orderQuantity;
	private String orderGDP;
	private String platformBrokerage;
	private String profit;
	private String creditScore;
	private String balance;
	private String canWithdrawMoney;
	private String withdrawingMoney;
	private String withdrawnMoney;
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getSex() {
		return sex;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public String getValidCarQuantity() {
		return validCarQuantity;
	}
	public String getHistoryCarQuantity() {
		return historyCarQuantity;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public String getLoginQuantity() {
		return loginQuantity;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public String getOrderGDP() {
		return orderGDP;
	}
	public String getPlatformBrokerage() {
		return platformBrokerage;
	}
	public String getProfit() {
		return profit;
	}
	public String getCreditScore() {
		return creditScore;
	}
	public String getBalance() {
		return balance;
	}
	public String getCanWithdrawMoney() {
		return canWithdrawMoney;
	}
	public String getWithdrawingMoney() {
		return withdrawingMoney;
	}
	public String getWithdrawnMoney() {
		return withdrawnMoney;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public void setValidCarQuantity(String validCarQuantity) {
		this.validCarQuantity = validCarQuantity;
	}
	public void setHistoryCarQuantity(String historyCarQuantity) {
		this.historyCarQuantity = historyCarQuantity;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public void setLoginQuantity(String loginQuantity) {
		this.loginQuantity = loginQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public void setOrderGDP(String orderGDP) {
		this.orderGDP = orderGDP;
	}
	public void setPlatformBrokerage(String platformBrokerage) {
		this.platformBrokerage = platformBrokerage;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public void setCanWithdrawMoney(String canWithdrawMoney) {
		this.canWithdrawMoney = canWithdrawMoney;
	}
	public void setWithdrawingMoney(String withdrawingMoney) {
		this.withdrawingMoney = withdrawingMoney;
	}
	public void setWithdrawnMoney(String withdrawnMoney) {
		this.withdrawnMoney = withdrawnMoney;
	}
	
}
