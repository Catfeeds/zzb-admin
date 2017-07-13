package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.CarMapper;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.service.ICarSevice;
@Service("CarSevice")
public class CarServiceImpl implements ICarSevice {
@Autowired
CarMapper carMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return carMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Car record) {
		return carMapper.insert(record);
	}

	@Override
	public int insertSelective(Car record) {
		return carMapper.insertSelective(record);
	}

	@Override
	public Car selectByPrimaryKey(Integer id) {
		return carMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Car record) {
		return carMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Car record) {
		return carMapper.updateByPrimaryKey(record);
	}

	@Override
	public Car selectByUuid(String Caruuid) {
		return carMapper.selectByUuid(Caruuid);
	}

	@Override
	public List<Car> selectByCity(String city) {
		return carMapper.selectByCity(city);
	}

	@Override
	public List<Car> selectByUserUuid(String user_uuid) {
		return carMapper.selectByUserUuid(user_uuid);
	}

	@Override
	public List<Car> selectAll() {
		return carMapper.selectAll();
	}

	@Override
	public List<Car> selectByCarName(String carname) {
		return carMapper.selectByCarName(carname);
	}

	@Override
	public List<Car> selectByBrand(String brand) {
		return carMapper.selectByBrand(brand);
	}

	@Override
	public List<Car> selectByPrice(Float price) {
		return carMapper.selectByPrice(price);
	}

	@Override
	public List<Car> selectByCreateAt(Map<String, Object> map) {
		return carMapper.selectByCreateAt(map);
	}

	@Override
	public int selectByCreateAtCount() {
		return carMapper.selectByCreateAtCount();
	}

	@Override
	public List<Car> selectByCarModel(Map<String, Object> map) {
		return carMapper.selectByCarModel(map);
	}

	@Override
	public int selectCountByCarModel(String model_character) {
		return carMapper.selectCountByCarModel(model_character);
	}

	@Override
	public List<Car> selectCarStatusAll() {
		// TODO Auto-generated method stub
		return carMapper.selectCarStatusAll();
	}
}
