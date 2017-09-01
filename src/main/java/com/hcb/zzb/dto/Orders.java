package com.hcb.zzb.dto;

import java.util.Date;

public class Orders {
	private Integer id;

	private Date createAt;

	private Date updateAt;

	private Date deleteAt;

	private String orderUuid;

	private String orderNumber;

	private String userUuid;

	private String carOwnerUuid;

	private String carUuid;

	private Date startTime;

	private Date endTime;

	private Date takeCarTime;

	private String takeCarAddress;

	private Date returnCarTime;

	private String returnCarAddress;

	private Integer isDamage;

	private String damageDsp;

	private String damagePicture;
	private String createChange;

	public String getCreateChange() {
		return createChange;
	}

	public void setCreateChange(String createChange) {
		this.createChange = createChange;
	}

	private Float compensateMoney;

	private Date closingTime;

	private Float deposit;

	private Integer depositStatus;

	private Float leasePrice;

	private Float insurancePrice;

	private Float otherPrice;

	private Float totalPrice;
	private Float transfer;

	public Float getTransfer() {
		return transfer;
	}

	public void setTransfer(Float transfer) {
		this.transfer = transfer;
	}

	private Date payTime;

	private Integer orderStatus;

	private Integer payType;

	private String orderRemark;

	private Integer evaluateScore;

	private String evaluateDsp;
	private String takeCarAddressMap;
	private String returnCarAddressMap;
	
	//用于后台显示
	private String userName;//用户名
	private String useCarTime;//用车时长
	
	public String getTakeCarAddressMap() {
		return takeCarAddressMap;
	}

	public void setTakeCarAddressMap(String takeCarAddressMap) {
		this.takeCarAddressMap = takeCarAddressMap;
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

	public String getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid == null ? null : orderUuid.trim();
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber == null ? null : orderNumber.trim();
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid == null ? null : userUuid.trim();
	}

	public String getCarOwnerUuid() {
		return carOwnerUuid;
	}

	public void setCarOwnerUuid(String carOwnerUuid) {
		this.carOwnerUuid = carOwnerUuid == null ? null : carOwnerUuid.trim();
	}

	public String getCarUuid() {
		return carUuid;
	}

	public void setCarUuid(String carUuid) {
		this.carUuid = carUuid == null ? null : carUuid.trim();
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getTakeCarTime() {
		return takeCarTime;
	}

	public void setTakeCarTime(Date takeCarTime) {
		this.takeCarTime = takeCarTime;
	}

	public String getTakeCarAddress() {
		return takeCarAddress;
	}

	public void setTakeCarAddress(String takeCarAddress) {
		this.takeCarAddress = takeCarAddress == null ? null : takeCarAddress.trim();
	}

	public Date getReturnCarTime() {
		return returnCarTime;
	}

	public void setReturnCarTime(Date returnCarTime) {
		this.returnCarTime = returnCarTime;
	}

	public String getReturnCarAddress() {
		return returnCarAddress;
	}

	public void setReturnCarAddress(String returnCarAddress) {
		this.returnCarAddress = returnCarAddress == null ? null : returnCarAddress.trim();
	}

	public Integer getIsDamage() {
		return isDamage;
	}

	public void setIsDamage(Integer isDamage) {
		this.isDamage = isDamage;
	}

	public String getDamageDsp() {
		return damageDsp;
	}

	public void setDamageDsp(String damageDsp) {
		this.damageDsp = damageDsp == null ? null : damageDsp.trim();
	}

	public String getDamagePicture() {
		return damagePicture;
	}

	public void setDamagePicture(String damagePicture) {
		this.damagePicture = damagePicture == null ? null : damagePicture.trim();
	}

	public Float getCompensateMoney() {
		return compensateMoney;
	}

	public void setCompensateMoney(Float compensateMoney) {
		this.compensateMoney = compensateMoney;
	}

	public Date getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	public Float getDeposit() {
		return deposit;
	}

	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}

	public Integer getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(Integer depositStatus) {
		this.depositStatus = depositStatus;
	}

	public Float getLeasePrice() {
		return leasePrice;
	}

	public void setLeasePrice(Float leasePrice) {
		this.leasePrice = leasePrice;
	}

	public Float getInsurancePrice() {
		return insurancePrice;
	}

	public void setInsurancePrice(Float insurancePrice) {
		this.insurancePrice = insurancePrice;
	}

	public Float getOtherPrice() {
		return otherPrice;
	}

	public void setOtherPrice(Float otherPrice) {
		this.otherPrice = otherPrice;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public String getReturnCarAddressMap() {
		return returnCarAddressMap;
	}

	public void setReturnCarAddressMap(String returnCarAddressMap) {
		this.returnCarAddressMap = returnCarAddressMap;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark == null ? null : orderRemark.trim();
	}

	public Integer getEvaluateScore() {
		return evaluateScore;
	}

	public void setEvaluateScore(Integer evaluateScore) {
		this.evaluateScore = evaluateScore;
	}

	public String getEvaluateDsp() {
		return evaluateDsp;
	}

	public void setEvaluateDsp(String evaluateDsp) {
		this.evaluateDsp = evaluateDsp == null ? null : evaluateDsp.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUseCarTime() {
		return useCarTime;
	}

	public void setUseCarTime(String useCarTime) {
		this.useCarTime = useCarTime;
	}
	
}