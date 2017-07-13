package com.hcb.zzb.service;

import java.util.List;

import com.hcb.zzb.dto.HomepageBanner;

public interface IhomePageBanner {
	int deleteByPrimaryKey(Integer id);

    int insert(HomepageBanner record);

    int insertSelective(HomepageBanner record);

    HomepageBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomepageBanner record);

    int updateByPrimaryKey(HomepageBanner record);

	List<HomepageBanner> selectAll();
}
