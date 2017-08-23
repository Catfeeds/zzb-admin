package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Car;

public interface ICarSevice {

	int deleteByPrimaryKey(Integer id);

	int insert(Car record);

	int insertSelective(Car record);

	Car selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Car record);

	int updateByPrimaryKey(Car record);
	
	List<Car> selectAll();

	List<Car> selectByCarName(String car_name);

	List<Car> selectByBrand(String brand);
	List<Car>selectByPrice(Float price);
	
	Car selectByUuid(String Caruuid);

	List<Car> selectByCity(String city);

	List<Car> selectByUserUuid(String user_uuid);
	
	/**
	 * 查询最近更新的车辆（新鲜车型）
	 * @param map
	 * @return
	 */
	List<Car> selectByCreateAt(Map<String, Object> map);
	/**
	 * 查询最近添加的记录数
	 * @return
	 */
	int selectByCreateAtCount();
	/**
	 * 通过车型查询列表
	 * @param map
	 * @return
	 */
	List<Car> selectByCarModel(Map<String, Object> map);
	/**
	 * 通过车型查询记录数
	 * @param model_character
	 * @return
	 */
	int selectCountByCarModel(String model_character);

	List<Car> selectCarStatusAll();
	
	List<Car> selectByMapLimit(Map<String, Object> map);
	
	int countSelectByMapLimit(Map<String, Object> map);

	List<Car> selectByCarBand(String userUuid);

	int selectNum1(String userUuid);

	int selectNum2(String userUuid);
	
	List<Car> selectCarsByName(Map<String, Object> map);
}
