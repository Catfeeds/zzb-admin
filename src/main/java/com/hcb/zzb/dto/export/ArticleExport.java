package com.hcb.zzb.dto.export;

public class ArticleExport {
	private Integer serialNumber;//序号
	private String title;//标题
	private String content;//内容
	private String status;//状态
	private Integer browseTime;//浏览次数
	private Integer forwardTime;//转发次数
	private String date;//发表时间
	
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getBrowseTime() {
		return browseTime;
	}
	public void setBrowseTime(Integer browseTime) {
		this.browseTime = browseTime;
	}
	public Integer getForwardTime() {
		return forwardTime;
	}
	public void setForwardTime(Integer forwardTime) {
		this.forwardTime = forwardTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
