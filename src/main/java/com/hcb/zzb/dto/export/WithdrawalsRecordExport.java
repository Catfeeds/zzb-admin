package com.hcb.zzb.dto.export;

public class WithdrawalsRecordExport {
	private Integer serialNumber;//序号
	private String uuid;//打款流水号
	private Integer userId;//用户id
	private String userName;//用户姓名
	private String withdrawalsType;//提现方式
	private Float money;//金额
	private String handUuid;//负责人uuid
	private String date;//操作时间
	private String status;//状态
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWithdrawalsType() {
		return withdrawalsType;
	}
	public void setWithdrawalsType(String withdrawalsType) {
		this.withdrawalsType = withdrawalsType;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public String getHandUuid() {
		return handUuid;
	}
	public void setHandUuid(String handUuid) {
		this.handUuid = handUuid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
