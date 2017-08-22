package com.hcb.zzb.dto;

import java.util.Date;

public class Manager {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String managerUuid;

    private String account;

    private String password;

    private String contacts;

    private String contactsPhone;

    private Integer managerStatus;

    private Integer managerType;

    private String managerPower;

    private String creater;
    
    private String managerPowerChild;

    

	public String getManagerPowerChild() {
		return managerPowerChild;
	}

	public void setManagerPowerChild(String managerPowerChild) {
		this.managerPowerChild = managerPowerChild;
	}

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

    public String getManagerUuid() {
        return managerUuid;
    }

    public void setManagerUuid(String managerUuid) {
        this.managerUuid = managerUuid == null ? null : managerUuid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    public Integer getManagerStatus() {
        return managerStatus;
    }

    public void setManagerStatus(Integer managerStatus) {
        this.managerStatus = managerStatus;
    }

    public Integer getManagerType() {
        return managerType;
    }

    public void setManagerType(Integer managerType) {
        this.managerType = managerType;
    }

    public String getManagerPower() {
        return managerPower;
    }

    public void setManagerPower(String managerPower) {
        this.managerPower = managerPower == null ? null : managerPower.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }
}