package com.hcb.zzb.dto;

import java.util.Date;

public class BrowseLog {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String browseLogUuid;

    private String carUuid;

    private String userUuid;

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

    public String getBrowseLogUuid() {
        return browseLogUuid;
    }

    public void setBrowseLogUuid(String browseLogUuid) {
        this.browseLogUuid = browseLogUuid == null ? null : browseLogUuid.trim();
    }

    public String getCarUuid() {
        return carUuid;
    }

    public void setCarUuid(String carUuid) {
        this.carUuid = carUuid == null ? null : carUuid.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }
}