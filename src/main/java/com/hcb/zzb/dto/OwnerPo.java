package com.hcb.zzb.dto;

import java.util.List;

public class OwnerPo {
	 private Integer carnum1;
	 private Integer carnum2;
	 private Integer sureordercount;
	 private float gdp;
	 private float avg;
	 private float chajialirun;
	 private float ketixianjiner;
	 private float cashbalance;
	 private float alreadybalance;
	 private List<Car> cars;
	public Integer getCarnum1() {
		return carnum1;
	}
	public Integer getCarnum2() {
		return carnum2;
	}
	public Integer getSureordercount() {
		return sureordercount;
	}
	public float getGdp() {
		return gdp;
	}
	public float getAvg() {
		return avg;
	}
	public float getChajialirun() {
		return chajialirun;
	}
	public float getKetixianjiner() {
		return ketixianjiner;
	}
	public float getCashbalance() {
		return cashbalance;
	}
	public float getAlreadybalance() {
		return alreadybalance;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCarnum1(Integer carnum1) {
		this.carnum1 = carnum1;
	}
	public void setCarnum2(Integer carnum2) {
		this.carnum2 = carnum2;
	}
	public void setSureordercount(Integer sureordercount) {
		this.sureordercount = sureordercount;
	}
	public void setGdp(float gdp) {
		this.gdp = gdp;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public void setChajialirun(float chajialirun) {
		this.chajialirun = chajialirun;
	}
	public void setKetixianjiner(float ketixianjiner) {
		this.ketixianjiner = ketixianjiner;
	}
	public void setCashbalance(float cashbalance) {
		this.cashbalance = cashbalance;
	}
	public void setAlreadybalance(float alreadybalance) {
		this.alreadybalance = alreadybalance;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	 
	 
}
