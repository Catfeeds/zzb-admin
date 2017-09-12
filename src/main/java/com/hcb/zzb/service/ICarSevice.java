package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Car;

/**
 * 车辆car
 * 
 * @author kk
 *
 */
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

	List<Car> selectByPrice(Float price);

	Car selectByUuid(String Caruuid);

	List<Car> selectByCity(String city);

	/**
	 * 根据uuid查询car
	 * @param user_uuid
	 * @return
	 */
	List<Car> selectByUserUuid(String user_uuid);

	/**
	 * 查询最近更新的车辆（新鲜车型）
	 * 
	 * @param map
	 * @return
	 */
	List<Car> selectByCreateAt(Map<String, Object> map);

	/**
	 * 查询最近添加的记录数
	 * 
	 * @return
	 */
	int selectByCreateAtCount();

	/**
	 * 通过车型查询列表
	 * 
	 * @param map
	 * @return
	 */
	List<Car> selectByCarModel(Map<String, Object> map);

	/**
	 * 通过车型查询记录数
	 * 
	 * @param model_character
	 * @return
	 */
	int selectCountByCarModel(String model_character);

	List<Car> selectCarStatusAll();

	/**
	 * 根据筛选条件筛选车辆
	 * 
	 * @param map
	 * @return
	 */
	List<Car> selectByMapLimit(Map<String, Object> map);

	/**
	 * 根据筛选条件筛选车辆数量
	 * 
	 * @param map
	 * @return
	 */
	int countSelectByMapLimit(Map<String, Object> map);

	List<Car> selectByCarBand(String userUuid);

	/**
	 * 有效车辆数目
	 * 
	 * @param userUuid
	 * @return
	 */
	int selectNum1(String userUuid);

	/**
	 * 历史车辆数
	 * 
	 * @param userUuid
	 * @return
	 */
	int selectNum2(String userUuid);

	/**
	 * 根据车名查询车辆
	 * 
	 * @param map
	 * @return
	 */
	List<Car> selectCarsByName(Map<String, Object> map);
}
