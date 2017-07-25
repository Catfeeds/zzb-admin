package com.hcb.zzb.dto.export;

public class UserCreditExport {
	private Integer serialNumber;//序号
	private String userName;//姓名
	private Integer userID;//用户ID
	private Integer creditScore;//信用分
	private Integer identity;//身份信息
	private Integer outCredit;//外部信用
	private Integer useCarScore;//用车行为
	private String status;//状态
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}
	public Integer getIdentity() {
		return identity;
	}
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	public Integer getOutCredit() {
		return outCredit;
	}
	public void setOutCredit(Integer outCredit) {
		this.outCredit = outCredit;
	}
	public Integer getUseCarScore() {
		return useCarScore;
	}
	public void setUseCarScore(Integer useCarScore) {
		this.useCarScore = useCarScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
