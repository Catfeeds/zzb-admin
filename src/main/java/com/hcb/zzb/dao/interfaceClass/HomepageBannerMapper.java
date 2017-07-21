package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.AdminMessage;
import com.hcb.zzb.dto.HomepageBanner;

public interface HomepageBannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomepageBanner record);

    int insertSelective(HomepageBanner record);

    HomepageBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomepageBanner record);

    int updateByPrimaryKey(HomepageBanner record);

	List<HomepageBanner> selectAll();

	List<HomepageBanner> searchByMap(Map<String, Object> map);

	Integer countByMap(Map<String, Object> map);
}