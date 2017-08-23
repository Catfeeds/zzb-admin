package com.hcb.zzb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.carBrandMapper;
import com.hcb.zzb.dto.carBrand;
import com.hcb.zzb.service.ICarBrand;
@Service("carBrands")
public class CarBrandImpl implements ICarBrand {
	@Autowired
	 private carBrandMapper carBrandMappers;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carBrandMappers.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(carBrand record) {
		// TODO Auto-generated method stub
		return carBrandMappers.insert(record);
	}

	@Override
	public int insertSelective(carBrand record) {
		// TODO Auto-generated method stub
		return carBrandMappers.insertSelective(record);
	}

	@Override
	public carBrand selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carBrandMappers.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(carBrand record) {
		// TODO Auto-generated method stub
		return carBrandMappers.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(carBrand record) {
		// TODO Auto-generated method stub
		return carBrandMappers.updateByPrimaryKey(record);
	}

	@Override
	public List<carBrand> selectAll() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAll();
	}

	@Override
	public List<carBrand> selectAllB() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllB();
	}

	@Override
	public List<carBrand> selectAllC() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllC();
	}

	@Override
	public List<carBrand> selectAllD() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllD();
	}

	@Override
	public List<carBrand> selectAllE() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllE();
	}

	@Override
	public List<carBrand> selectAllF() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllF();
	}

	@Override
	public List<carBrand> selectAllG() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllG();
	}

	@Override
	public List<carBrand> selectAllH() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllH();
	}

	@Override
	public List<carBrand> selectAllI() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllI();
	}

	@Override
	public List<carBrand> selectAllJ() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllJ();
	}

	@Override
	public List<carBrand> selectAllK() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllK();
	}

	@Override
	public List<carBrand> selectAllL() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllL();
	}

	@Override
	public List<carBrand> selectAllM() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllM();
	}

	@Override
	public List<carBrand> selectAllN() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllN();
	}

	@Override
	public List<carBrand> selectAllO() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllO();
	}

	@Override
	public List<carBrand> selectAllP() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllP();
	}

	@Override
	public List<carBrand> selectAllQ() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllQ();
	}

	@Override
	public List<carBrand> selectAllR() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllR();
	}

	@Override
	public List<carBrand> selectAllS() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllS();
	}

	@Override
	public List<carBrand> selectAllT() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllT();
	}

	@Override
	public List<carBrand> selectAllU() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllU();
	}

	@Override
	public List<carBrand> selectAllV() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllV();
	}

	@Override
	public List<carBrand> selectAllW() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllW();
	}

	@Override
	public List<carBrand> selectAllX() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllX();
	}

	@Override
	public List<carBrand> selectAllY() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllY();
	}

	@Override
	public List<carBrand> selectAllZ() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllZ();
	}

	@Override
	public List<carBrand> selectAllA() {
		// TODO Auto-generated method stub
		return carBrandMappers.selectAllA();
	}

	@Override
	public carBrand selectByBrand(String brand) {
		// TODO Auto-generated method stub
		return carBrandMappers.selectByBrand(brand);
	}

}
