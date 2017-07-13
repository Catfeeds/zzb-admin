package com.hcb.zzb.dto;

public class WxOrder {
private String appId;
	
	private String partnerId;
	
	private String prepayId;
	
	private String pkg;
	
	private String noncestr;
	
	private String timestamp;
	
	private String sign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId == null ? null : partnerId.trim();
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId == null ? null : prepayId.trim();
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg == null ? null : pkg.trim();
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr == null ? null : noncestr.trim();
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp == null ? null : timestamp.trim();
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign == null ? null : sign.trim();
	}
}
