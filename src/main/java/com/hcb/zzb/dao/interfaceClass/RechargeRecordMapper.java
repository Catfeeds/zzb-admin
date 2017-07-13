package com.hcb.zzb.dao.interfaceClass;

import com.hcb.zzb.dto.RechargeRecord;

public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);
    
    RechargeRecord selectByUuid(String uuid);
}