package com.hcb.zzb.dao.interfaceClass;

import java.util.List;

import com.hcb.zzb.dto.carSeries;


public interface carSeriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(carSeries record);

    int insertSelective(carSeries record);

    carSeries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(carSeries record);

    int updateByPrimaryKey(carSeries record);

	List<carSeries> selectByGuochan(String brand_uuid);

	List<carSeries> selectByJinkou(String brand_uuid);

	List<carSeries> selectByBrandUuid(String brandUuid);

	carSeries selectBySeries(String carSeries);

	carSeries selectByUuid(String seriesUuid);
}