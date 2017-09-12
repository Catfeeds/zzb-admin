package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.AdminMessage;
import com.hcb.zzb.dto.HomepageBanner;
/**
 * 首页banner类
 * @author kk
 *
 */
public interface IhomePageBanner {
	int deleteByPrimaryKey(Integer id);

    int insert(HomepageBanner record);

    int insertSelective(HomepageBanner record);

    HomepageBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomepageBanner record);

    int updateByPrimaryKey(HomepageBanner record);

	List<HomepageBanner> selectAll();
	/**
	 * 根据筛选条件查询车辆
	 * @param map
	 * @return
	 */
	List<HomepageBanner> searchByMap(Map<String, Object> map);
	/**
	 * 根据筛选条件查询车辆数目
	 * @param map
	 * @return
	 */
	Integer countByMap(Map<String, Object> map);
	/**
	 * 查询上架的条数
	 * @return
	 */
	Integer countDisplay();
}
