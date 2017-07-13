package com.hcb.zzb.service;

import com.hcb.zzb.dto.RechargeRecord;

public interface IRechargeRecordService {
	int deleteByPrimaryKey(Integer id);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);
    
    RechargeRecord selectByUuid(String uuid);
}
