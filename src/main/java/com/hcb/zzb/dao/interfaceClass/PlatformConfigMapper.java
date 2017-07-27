package com.hcb.zzb.dao.interfaceClass;

import com.hcb.zzb.dto.PlatformConfig;

public interface PlatformConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatformConfig record);

    int insertSelective(PlatformConfig record);

    PlatformConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatformConfig record);

    int updateByPrimaryKey(PlatformConfig record);
}