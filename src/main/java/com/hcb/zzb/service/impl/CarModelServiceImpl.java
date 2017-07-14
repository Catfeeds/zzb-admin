package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.CarModelMapper;
import com.hcb.zzb.dto.CarModel;
import com.hcb.zzb.service.ICarModel;

@Service("carModelService")
public class CarModelServiceImpl implements ICarModel {
	@Autowired
	private CarModelMapper carModelMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carModelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CarModel record) {
		// TODO Auto-generated method stub
		return carModelMapper.insert(record);
	}

	@Override
	public int insertSelective(CarModel record) {
		// TODO Auto-generated method stub
		return carModelMapper.insertSelective(record);
	}

	@Override
	public CarModel selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carModelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CarModel record) {
		// TODO Auto-generated method stub
		return carModelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CarModel record) {
		// TODO Auto-generated method stub
		return carModelMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CarModel> selectAll() {
		// TODO Auto-generated method stub
		return carModelMapper.selectAll();
	}

	@Override
	public List<CarModel> selectByCarBrand(String brand) {
		// TODO Auto-generated method stub
		return carModelMapper.selectByCarBrand(brand);
	}

	@Override
	public List<CarModel> selectByApplyId(String userUuid) {
		// TODO Auto-generated method stub
		return carModelMapper.selectByApplyId(userUuid);
	}

	@Override
	public List<CarModel> selectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return carModelMapper.selectByMapLimit(map);
	}

	@Override
	public int countByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return carModelMapper.countByMap(map);
	}

	@Override
	public CarModel selectByUuid(String uuid) {
		// TODO Auto-generated method stub
		return carModelMapper.selectByUuid(uuid);
	}

}
