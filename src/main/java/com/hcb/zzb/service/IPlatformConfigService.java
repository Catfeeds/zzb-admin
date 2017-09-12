package com.hcb.zzb.service;

import java.util.List;

import com.hcb.zzb.dto.PlatformConfig;
/**
 * 平台账户
 * @author kk
 *
 */
public interface IPlatformConfigService {
	int deleteByPrimaryKey(Integer id);

    int insert(PlatformConfig record);

    int insertSelective(PlatformConfig record);

    PlatformConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatformConfig record);

    int updateByPrimaryKey(PlatformConfig record);
    
    List<PlatformConfig> selectAll();
}
