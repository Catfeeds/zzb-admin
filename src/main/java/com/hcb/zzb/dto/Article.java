package com.hcb.zzb.dto;

import java.util.Date;

public class Article {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String articleUuid;

    private String tittle;

    private Integer articleType;

    private String articlePicture;

    private String carIdList;

    private String creater;

    private Integer isDisplay;

    private Integer activityCat;

    private Float activityPrice;

    private Date activityStartTime;

    private Date activityEndTime;

    private String articleContent;
    
    private Integer browseTime;
    
    private Integer forwardTime;
    
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

    public String getArticleUuid() {
        return articleUuid;
    }

    public void setArticleUuid(String articleUuid) {
        this.articleUuid = articleUuid == null ? null : articleUuid.trim();
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle == null ? null : tittle.trim();
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public String getArticlePicture() {
        return articlePicture;
    }

    public void setArticlePicture(String articlePicture) {
        this.articlePicture = articlePicture == null ? null : articlePicture.trim();
    }

    public String getCarIdList() {
        return carIdList;
    }

    public void setCarIdList(String carIdList) {
        this.carIdList = carIdList == null ? null : carIdList.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getActivityCat() {
        return activityCat;
    }

    public void setActivityCat(Integer activityCat) {
        this.activityCat = activityCat;
    }

    public Float getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Float activityPrice) {
        this.activityPrice = activityPrice;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
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
}