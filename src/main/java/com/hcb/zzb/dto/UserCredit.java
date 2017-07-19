package com.hcb.zzb.dto;

public class UserCredit {
	/**
	 * 用于封装用户信用信息,不对应数据库表
	 */
	private int userId;//用户id
	private String userUuid;//用户uuid
	private String userName;//姓名
	private int creditScore;//信用积分
	private int identityScore;//身份信用得分
	private int outScore;//外部信用得分
	private int useCarScore;//用车行为得分
	private String creditStatus;//信用状态 60-70一般；70-90良好；90-100优秀
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public int getIdentityScore() {
		return identityScore;
	}
	public void setIdentityScore(int identityScore) {
		this.identityScore = identityScore;
	}
	public int getOutScore() {
		return outScore;
	}
	public void setOutScore(int outScore) {
		this.outScore = outScore;
	}
	public int getUseCarScore() {
		return useCarScore;
	}
	public void setUseCarScore(int useCarScore) {
		this.useCarScore = useCarScore;
	}
	public String getCreditStatus() {
		return creditStatus;
	}
	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}
}
 