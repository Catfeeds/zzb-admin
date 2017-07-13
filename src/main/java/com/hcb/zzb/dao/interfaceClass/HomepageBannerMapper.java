package com.hcb.zzb.dao.interfaceClass;

import java.util.List;

import com.hcb.zzb.dto.HomepageBanner;

public interface HomepageBannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomepageBanner record);

    int insertSelective(HomepageBanner record);

    HomepageBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomepageBanner record);

    int updateByPrimaryKey(HomepageBanner record);

	List<HomepageBanner> selectAll();
}