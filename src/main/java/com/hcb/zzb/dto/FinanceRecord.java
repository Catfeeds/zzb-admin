package com.hcb.zzb.dto;

import java.util.Date;

public class FinanceRecord {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String financeRecordUuid;

    private Integer recordType;

    private Integer orderRecordType;

    private String orderUuid;

    private String rechargeRecordUuid;

    private String withdrawalsRecordUuid;

    private Float money;

    private Integer financeType;

    private String userUuid;
    
    private Integer payType;
    
    private Integer payWay;

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

    public String getFinanceRecordUuid() {
        return financeRecordUuid;
    }

    public void setFinanceRecordUuid(String financeRecordUuid) {
        this.financeRecordUuid = financeRecordUuid == null ? null : financeRecordUuid.trim();
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getOrderRecordType() {
        return orderRecordType;
    }

    public void setOrderRecordType(Integer orderRecordType) {
        this.orderRecordType = orderRecordType;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid == null ? null : orderUuid.trim();
    }

    public String getRechargeRecordUuid() {
        return rechargeRecordUuid;
    }

    public void setRechargeRecordUuid(String rechargeRecordUuid) {
        this.rechargeRecordUuid = rechargeRecordUuid == null ? null : rechargeRecordUuid.trim();
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

    public Integer getFinanceType() {
        return financeType;
    }

    public void setFinanceType(Integer financeType) {
        this.financeType = financeType;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
    
}