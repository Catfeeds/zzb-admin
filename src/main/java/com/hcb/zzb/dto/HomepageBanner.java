package com.hcb.zzb.dto;

import java.util.Date;

public class HomepageBanner {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String operationInfo;

    private String operationPicture;

    private Integer isDisplay;
    
    private String link;

    private String creater;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getOperationInfo() {
        return operationInfo;
    }

    public void setOperationInfo(String operationInfo) {
        this.operationInfo = operationInfo == null ? null : operationInfo.trim();
    }

    public String getOperationPicture() {
        return operationPicture;
    }

    public void setOperationPicture(String operationPicture) {
        this.operationPicture = operationPicture == null ? null : operationPicture.trim();
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }
    
    public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }
}