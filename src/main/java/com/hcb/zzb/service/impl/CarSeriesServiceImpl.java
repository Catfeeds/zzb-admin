package com.hcb.zzb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.carSeriesMapper;
import com.hcb.zzb.dto.carSeries;
import com.hcb.zzb.service.ICarSeriesService;

@Service("carSeriess")
public class CarSeriesServiceImpl implements ICarSeriesService {
	@Autowired
	private carSeriesMapper carSeriesMappers;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carSeriesMappers.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(carSeries record) {
		// TODO Auto-generated method stub
		return carSeriesMappers.insert(record);
	}

	@Override
	public int insertSelective(carSeries record) {
		// TODO Auto-generated method stub
		return carSeriesMappers.insertSelective(record);
	}

	@Override
	public carSeries selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carSeriesMappers.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(carSeries record) {
		// TODO Auto-generated method stub
		return carSeriesMappers.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(carSeries record) {
		// TODO Auto-generated method stub
		return carSeriesMappers.updateByPrimaryKey(record);
	}

	@Override
	public List<carSeries> selectByGuochan(String brand_uuid) {
		// TODO Auto-generated method stub
		return carSeriesMappers.selectByGuochan(brand_uuid);
	}

	@Override
	public List<carSeries> selectByJinkou(String brand_uuid) {
		// TODO Auto-generated method stub
		return carSeriesMappers.selectByJinkou(brand_uuid);
	}

	@Override
	public List<carSeries> selectByBrandUuid(String brandUuid) {
		// TODO Auto-generated method stub
		return carSeriesMappers.selectByBrandUuid(brandUuid);
	}

	@Override
	public carSeries selectBySeries(String carSeries) {
		// TODO Auto-generated method stub
		return carSeriesMappers.selectBySeries(carSeries);
	}

}
