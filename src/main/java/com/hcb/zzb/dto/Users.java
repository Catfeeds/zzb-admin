package com.hcb.zzb.dto;

import java.util.Date;

public class Users {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String userUuid;

    private String userName;

    private Integer gender;

    private String userPhone;

    private Date birthday;

    private String avater;

    private Integer idType;

    private String idNumber;

    private String idPicture;

    private Integer driving;

    private String constellation;

    private String wxOpenId;

    private String qqOpenId;

    private String zmOpenId;

    private Float balance;

    private Float frozenBalance;

    private Integer authenticationStatus;

    private Integer creditScore;

    private String payPassword;

    private Integer userType;

    private Integer ridersStatus;
    
    private Integer userStatus;
    
    private Integer vehicleBehavior;
    
    
    private String takeCarAddressMap;
    private String return_car_address_map;
    
    private Integer loginCount;
    
    public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getTakeCarAddressMap() {
		return takeCarAddressMap;
	}

	public void setTakeCarAddressMap(String takeCarAddressMap) {
		this.takeCarAddressMap = takeCarAddressMap;
	}

	public String getReturn_car_address_map() {
		return return_car_address_map;
	}

	public void setReturn_car_address_map(String return_car_address_map) {
		this.return_car_address_map = return_car_address_map;
	}

	private Float deposit;
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

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater == null ? null : avater.trim();
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(String idPicture) {
        this.idPicture = idPicture == null ? null : idPicture.trim();
    }

    public Integer getDriving() {
        return driving;
    }

    public void setDriving(Integer driving) {
        this.driving = driving;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation == null ? null : constellation.trim();
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId == null ? null : wxOpenId.trim();
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId == null ? null : qqOpenId.trim();
    }

    public String getZmOpenId() {
        return zmOpenId;
    }

    public void setZmOpenId(String zmOpenId) {
        this.zmOpenId = zmOpenId == null ? null : zmOpenId.trim();
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(Float frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public Integer getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(Integer authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getRidersStatus() {
        return ridersStatus;
    }

    public void setRidersStatus(Integer ridersStatus) {
        this.ridersStatus = ridersStatus;
    }

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getVehicleBehavior() {
		return vehicleBehavior;
	}

	public void setVehicleBehavior(Integer vehicleBehavior) {
		this.vehicleBehavior = vehicleBehavior;
	}

	public Float getDeposit() {
		return deposit;
	}

	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}
	
	//注意：下面的set不是po的属性值 为了展现数据方便 ，才进行设置
	 
	 private Integer consume;
	 private Float money;
	 private Float profit;
	 private Float profitRate;
	 private Integer consumeIntegration;
	 private Integer Grade;
	 private Float cashbalance;
	 private Float givebalance;

	public Integer getConsume() {
		return consume;
	}

	public Float getMoney() {
		return money;
	}

	public Float getProfit() {
		return profit;
	}

	public Float getProfitRate() {
		return profitRate;
	}

	public Integer getConsumeIntegration() {
		return consumeIntegration;
	}

	public Integer getGrade() {
		return Grade;
	}

	public Float getCashbalance() {
		return cashbalance;
	}

	public Float getGivebalance() {
		return givebalance;
	}

	public void setConsume(Integer consume) {
		this.consume = consume;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public void setProfit(Float profit) {
		this.profit = profit;
	}

	public void setProfitRate(Float profitRate) {
		this.profitRate = profitRate;
	}

	public void setConsumeIntegration(Integer consumeIntegration) {
		this.consumeIntegration = consumeIntegration;
	}

	public void setGrade(Integer grade) {
		Grade = grade;
	}

	public void setCashbalance(Float cashbalance) {
		this.cashbalance = cashbalance;
	}

	public void setGivebalance(Float givebalance) {
		this.givebalance = givebalance;
	}
	//车主注入
	private OwnerPo ownerPo;

	public OwnerPo getOwnerPo() {
		return ownerPo;
	}

	public void setOwnerPo(OwnerPo ownerPo) {
		this.ownerPo = ownerPo;
	}
	
	 
    
}