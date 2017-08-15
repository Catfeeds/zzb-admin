package com.hcb.zzb.dao.interfaceClass;

import com.hcb.zzb.dto.PushInfo;

public interface PushInfoMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(PushInfo record);

    int insertSelective(PushInfo record);

    PushInfo selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(PushInfo record);

    int updateByPrimaryKey(PushInfo record);
    
    PushInfo selectByPushUuid(String pushUuid);
}