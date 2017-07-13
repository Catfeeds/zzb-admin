package com.hcb.zzb.dto;

import java.util.Date;

public class WithdrawalsRecord {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String withdrawalsRecordUuid;

    private Float money;

    private String applyUuid;

    private Integer applyStatus;

    private Integer accountType;

    private String accountNumber;

    private String handleUuid;

    private Date handleTime;

    private String handleDsp;

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

    public String getWithdrawalsRecordUuid() {
        return withdrawalsRecordUuid;
    }

    public void setWithdrawalsRecordUuid(String withdrawalsRecordUuid) {
        this.withdrawalsRecordUuid = withdrawalsRecordUuid == null ? null : withdrawalsRecordUuid.trim();
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getApplyUuid() {
        return applyUuid;
    }

    public void setApplyUuid(String applyUuid) {
        this.applyUuid = applyUuid == null ? null : applyUuid.trim();
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public String getHandleUuid() {
        return handleUuid;
    }

    public void setHandleUuid(String handleUuid) {
        this.handleUuid = handleUuid == null ? null : handleUuid.trim();
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleDsp() {
        return handleDsp;
    }

    public void setHandleDsp(String handleDsp) {
        this.handleDsp = handleDsp == null ? null : handleDsp.trim();
    }
}