package com.hcb.zzb.service;

import java.util.List;

import com.hcb.zzb.dto.carBrand;
/**
 * 品牌
 * @author kk
 *
 */
public interface ICarBrand {
	int deleteByPrimaryKey(Integer id);

    int insert(carBrand record);

    int insertSelective(carBrand record);

    carBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(carBrand record);

    int updateByPrimaryKey(carBrand record);

	List<carBrand> selectAll();

	List<carBrand> selectAllB();
	List<carBrand> selectAllC();
	List<carBrand> selectAllD();
	List<carBrand> selectAllE();
	List<carBrand> selectAllF();
	List<carBrand> selectAllG();
	List<carBrand> selectAllH();
	List<carBrand> selectAllI();
	List<carBrand> selectAllJ();
	List<carBrand> selectAllK();
	List<carBrand> selectAllL();
	List<carBrand> selectAllM();
	List<carBrand> selectAllN();
	List<carBrand> selectAllO();
	List<carBrand> selectAllP();
	List<carBrand> selectAllQ();
	List<carBrand> selectAllR();
	List<carBrand> selectAllS();
	List<carBrand> selectAllT();
	List<carBrand> selectAllU();
	List<carBrand> selectAllV();
	List<carBrand> selectAllW();
	List<carBrand> selectAllX();
	List<carBrand> selectAllY();
	List<carBrand> selectAllZ();

	List<carBrand> selectAllA();

	carBrand selectByBrand(String brand);

	carBrand selectByUuid(String brandUuid);
}
