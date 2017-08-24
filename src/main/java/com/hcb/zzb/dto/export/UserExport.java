package com.hcb.zzb.dto.export;

public class UserExport {
																
	private Integer serialNumber;//序号
	private Integer userID;//ID
	private String userName;//姓名
	private String phone;//手机
	private String sex;//性别
	private int driverAge;//驾龄
	private String date;//注册时间
	private String lastTime;//最后一次登录时间
	private int loginTimes;//登录次数\
	private int xiaofeiTimes;//消费次数
	private Float xiaofeiMoney;//消费金额
	private String lilun;//利润
	private String lilunLv;//利润率
	private int creditScore;//信用分
	private int xiaofeiJifen;//消费积分
	private int level;//会员等级
	private Float balance;//账户余额
	private Float xBalance;//现金余额
	private Float sBalance;//赠送余额
	
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getDriverAge() {
		return driverAge;
	}
	public void setDriverAge(int driverAge) {
		this.driverAge = driverAge;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public int getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}
	public int getXiaofeiTimes() {
		return xiaofeiTimes;
	}
	public void setXiaofeiTimes(int xiaofeiTimes) {
		this.xiaofeiTimes = xiaofeiTimes;
	}
	public Float getXiaofeiMoney() {
		return xiaofeiMoney;
	}
	public void setXiaofeiMoney(Float xiaofeiMoney) {
		this.xiaofeiMoney = xiaofeiMoney;
	}
	public String getLilun() {
		return lilun;
	}
	public void setLilun(String lilun) {
		this.lilun = lilun;
	}
	public String getLilunLv() {
		return lilunLv;
	}
	public void setLilunLv(String lilunLv) {
		this.lilunLv = lilunLv;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public int getXiaofeiJifen() {
		return xiaofeiJifen;
	}
	public void setXiaofeiJifen(int xiaofeiJifen) {
		this.xiaofeiJifen = xiaofeiJifen;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public Float getxBalance() {
		return xBalance;
	}
	public void setxBalance(Float xBalance) {
		this.xBalance = xBalance;
	}
	public Float getsBalance() {
		return sBalance;
	}
	public void setsBalance(Float sBalance) {
		this.sBalance = sBalance;
	}
	
}
