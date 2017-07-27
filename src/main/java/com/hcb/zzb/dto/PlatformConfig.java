package com.hcb.zzb.dto;

import java.util.Date;

public class PlatformConfig {
    private Integer id;

    private Date createAt;

    private Date deteleAt;

    private String platformUuid;

    private String platformName;

    private Float frozenAmount;

    private Float balance;

    private Float royaltyRatio;

    private String wxOpenPlatformAccount;

    private String wxPublicPlatformAccount;

    private String alipayOpenPlatformAccount;

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

    public Date getDeteleAt() {
        return deteleAt;
    }

    public void setDeteleAt(Date deteleAt) {
        this.deteleAt = deteleAt;
    }

    public String getPlatformUuid() {
        return platformUuid;
    }

    public void setPlatformUuid(String platformUuid) {
        this.platformUuid = platformUuid == null ? null : platformUuid.trim();
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName == null ? null : platformName.trim();
    }

    public Float getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(Float frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getRoyaltyRatio() {
        return royaltyRatio;
    }

    public void setRoyaltyRatio(Float royaltyRatio) {
        this.royaltyRatio = royaltyRatio;
    }

    public String getWxOpenPlatformAccount() {
        return wxOpenPlatformAccount;
    }

    public void setWxOpenPlatformAccount(String wxOpenPlatformAccount) {
        this.wxOpenPlatformAccount = wxOpenPlatformAccount == null ? null : wxOpenPlatformAccount.trim();
    }

    public String getWxPublicPlatformAccount() {
        return wxPublicPlatformAccount;
    }

    public void setWxPublicPlatformAccount(String wxPublicPlatformAccount) {
        this.wxPublicPlatformAccount = wxPublicPlatformAccount == null ? null : wxPublicPlatformAccount.trim();
    }

    public String getAlipayOpenPlatformAccount() {
        return alipayOpenPlatformAccount;
    }

    public void setAlipayOpenPlatformAccount(String alipayOpenPlatformAccount) {
        this.alipayOpenPlatformAccount = alipayOpenPlatformAccount == null ? null : alipayOpenPlatformAccount.trim();
    }
}