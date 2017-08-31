package com.hcb.zzb.dto;

import java.util.Date;

public class Car {
    private Integer id;

    private Date createAt;

    private Date updateAt;

    private Date deleteAt;

    private String carUuid;

    private String banner;

    private String carName;

    private Integer carType;

    private Integer carStatus;

    private String licensePlateNumber;

    private String brand;

    private String carSeries;

    private String modelCharacter;

    private String model;

    private String displacement;

    private Integer manualOrAutomatic;

    private Integer mileage;

    private String addressMap;

    private String address;

    private String carDsp;

    private String carCodeUrl;

    private Integer orderQuantity;

    private Integer collection;

    private Float workingDayPrice;

    private Integer vehicleAge;

    private Integer isNavigation;

    private Integer isMp3;

    private String vehicleIdentificationNumber;

    private Float weekendPrice;

    private Date shelvesStartTime;

    private String engineNumber;

    private Integer seatNumber;

    private String color;

    private Date registerTime;

    private String city;

    private String drivingLicensePhoto;

    private String manCarPhoto;

    private Integer vehicleDeliveryMode;

    private String carOwnerName;

    private String userUuid;

    private Date shelvesEndTime;

    private String leaseTime;

    private Integer isSail;
    
    public Integer getIsSail() {
		return isSail;
	}

	public void setIsSail(Integer isSail) {
		this.isSail = isSail;
	}

	private Integer orderTotalQuantity;
    private Float orderRate;
    private Integer carUseType;
    private Integer closeSubway;
    
    private String modelYear;
    public Integer getOrderTotalQuantity() {
		return orderTotalQuantity;
	}

	public void setOrderTotalQuantity(Integer orderTotalQuantity) {
		this.orderTotalQuantity = orderTotalQuantity;
	}

	public Float getOrderRate() {
		return orderRate;
	}

	public void setOrderRate(Float orderRate) {
		this.orderRate = orderRate;
	}

	public Integer getCarUseType() {
		return carUseType;
	}

	public void setCarUseType(Integer carUseType) {
		this.carUseType = carUseType;
	}

	public Integer getCloseSubway() {
		return closeSubway;
	}

	public void setCloseSubway(Integer closeSubway) {
		this.closeSubway = closeSubway;
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

    public String getCarUuid() {
        return carUuid;
    }

    public void setCarUuid(String carUuid) {
        this.carUuid = carUuid == null ? null : carUuid.trim();
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber == null ? null : licensePlateNumber.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries == null ? null : carSeries.trim();
    }

    public String getModelCharacter() {
        return modelCharacter;
    }

    public void setModelCharacter(String modelCharacter) {
        this.modelCharacter = modelCharacter == null ? null : modelCharacter.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement == null ? null : displacement.trim();
    }

    public Integer getManualOrAutomatic() {
        return manualOrAutomatic;
    }

    public void setManualOrAutomatic(Integer manualOrAutomatic) {
        this.manualOrAutomatic = manualOrAutomatic;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getAddressMap() {
        return addressMap;
    }

    public void setAddressMap(String addressMap) {
        this.addressMap = addressMap == null ? null : addressMap.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCarDsp() {
        return carDsp;
    }

    public void setCarDsp(String carDsp) {
        this.carDsp = carDsp == null ? null : carDsp.trim();
    }

    public String getCarCodeUrl() {
        return carCodeUrl;
    }

    public void setCarCodeUrl(String carCodeUrl) {
        this.carCodeUrl = carCodeUrl == null ? null : carCodeUrl.trim();
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Float getWorkingDayPrice() {
        return workingDayPrice;
    }

    public void setWorkingDayPrice(Float workingDayPrice) {
        this.workingDayPrice = workingDayPrice;
    }

    public Integer getVehicleAge() {
        return vehicleAge;
    }

    public void setVehicleAge(Integer vehicleAge) {
        this.vehicleAge = vehicleAge;
    }

    public Integer getIsNavigation() {
        return isNavigation;
    }

    public void setIsNavigation(Integer isNavigation) {
        this.isNavigation = isNavigation;
    }

    public Integer getIsMp3() {
        return isMp3;
    }

    public void setIsMp3(Integer isMp3) {
        this.isMp3 = isMp3;
    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber == null ? null : vehicleIdentificationNumber.trim();
    }

    public Float getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(Float weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    public Date getShelvesStartTime() {
        return shelvesStartTime;
    }

    public void setShelvesStartTime(Date shelvesStartTime) {
        this.shelvesStartTime = shelvesStartTime;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber == null ? null : engineNumber.trim();
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDrivingLicensePhoto() {
        return drivingLicensePhoto;
    }

    public void setDrivingLicensePhoto(String drivingLicensePhoto) {
        this.drivingLicensePhoto = drivingLicensePhoto == null ? null : drivingLicensePhoto.trim();
    }

    public String getManCarPhoto() {
        return manCarPhoto;
    }

    public void setManCarPhoto(String manCarPhoto) {
        this.manCarPhoto = manCarPhoto == null ? null : manCarPhoto.trim();
    }

    public Integer getVehicleDeliveryMode() {
        return vehicleDeliveryMode;
    }

    public void setVehicleDeliveryMode(Integer vehicleDeliveryMode) {
        this.vehicleDeliveryMode = vehicleDeliveryMode;
    }

    public String getCarOwnerName() {
        return carOwnerName;
    }

    public void setCarOwnerName(String carOwnerName) {
        this.carOwnerName = carOwnerName == null ? null : carOwnerName.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public Date getShelvesEndTime() {
        return shelvesEndTime;
    }

    public void setShelvesEndTime(Date shelvesEndTime) {
        this.shelvesEndTime = shelvesEndTime;
    }

    public String getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(String leaseTime) {
        this.leaseTime = leaseTime == null ? null : leaseTime.trim();
    }

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    //为了展示数据，不为实体car的属性
	//驾驶时长 int time;
	//驾驶车型数 int modelNum;
	//历史总成交 int totalNum;
	//驾驶次数 int driverCount;
	//平均响应时间 int res ;
	//平均接单量 int avg;
	 private Integer time;
	 private Integer modelNum;
	 private Integer totalNum;
	 private Integer driverCount;
	 private Integer res;
	 private Integer avg;
	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getModelNum() {
		return modelNum;
	}

	public void setModelNum(Integer modelNum) {
		this.modelNum = modelNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getDriverCount() {
		return driverCount;
	}

	public void setDriverCount(Integer driverCount) {
		this.driverCount = driverCount;
	}

	public Integer getRes() {
		return res;
	}

	public void setRes(Integer res) {
		this.res = res;
	}

	public Integer getAvg() {
		return avg;
	}

	public void setAvg(Integer avg) {
		this.avg = avg;
	}
	
    
}