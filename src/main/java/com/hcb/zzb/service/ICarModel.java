package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.CarModel;

public interface ICarModel {
	int deleteByPrimaryKey(Integer id);

    int insert(CarModel record);

    int insertSelective(CarModel record);

    CarModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarModel record);

    int updateByPrimaryKey(CarModel record);

	List<CarModel> selectAll();

	List<CarModel> selectByCarBrand(String brand);

	List<CarModel> selectByApplyId(String userUuid);
	
	List<CarModel> selectByMapLimit(Map<String, Object> map);
	
	int countByMap(Map<String, Object> map);
	
	CarModel selectByUuid(String uuid);
}
