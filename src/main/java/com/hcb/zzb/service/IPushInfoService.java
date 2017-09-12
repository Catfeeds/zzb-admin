package com.hcb.zzb.service;

import com.hcb.zzb.dto.PushInfo;
/**
 * 消息推送类
 * @author kk
 *
 */
public interface IPushInfoService {
	int deleteByPrimaryKey(Integer fakeId);

    int insert(PushInfo record);

    int insertSelective(PushInfo record);

    PushInfo selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(PushInfo record);

    int updateByPrimaryKey(PushInfo record);
    
    PushInfo selectByPushUuid(String pushUuid);
}
