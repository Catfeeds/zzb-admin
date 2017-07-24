package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Car;

public interface CarMapper {
	
	int deleteByPrimaryKey(Integer id);

	int insert(Car record);

	int insertSelective(Car record);

	Car selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Car record);

	int updateByPrimaryKey(Car record);

	List<Car> selectByCity(String city);

	List<Car> selectByUserUuid(String useruuid);

	List<Car> selectAll();

	List<Car> selectByCarName(String carname);

	List<Car> selectByBrand(String brand);

	List<Car> selectByPrice(Float price);

	Car selectByUuid(String Caruuid);

	List<Car> selectByCreateAt(Map<String, Object> map);
	
	int selectByCreateAtCount();
	
	List<Car> selectByCarModel(Map<String, Object> map);
	
	int selectCountByCarModel(String model_character);

	List<Car> selectCarStatusAll();
	
	List<Car> selectByMapLimit(Map<String, Object> map);
	
	int countSelectByMapLimit(Map<String, Object> map);
}