package com.hcb.zzb.dto;

import java.util.Date;

public class MessageChild {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String messageChildUuid;

    private String messageBaseUuid;

    private String tittle;

    private String content;

    private String creater;

    private String userUuid;

    private Integer isRead;

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

    public String getMessageChildUuid() {
        return messageChildUuid;
    }

    public void setMessageChildUuid(String messageChildUuid) {
        this.messageChildUuid = messageChildUuid == null ? null : messageChildUuid.trim();
    }

    public String getMessageBaseUuid() {
        return messageBaseUuid;
    }

    public void setMessageBaseUuid(String messageBaseUuid) {
        this.messageBaseUuid = messageBaseUuid == null ? null : messageBaseUuid.trim();
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle == null ? null : tittle.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}