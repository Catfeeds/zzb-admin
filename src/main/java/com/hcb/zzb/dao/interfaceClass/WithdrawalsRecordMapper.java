package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.WithdrawalsRecord;

public interface WithdrawalsRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawalsRecord record);

    int insertSelective(WithdrawalsRecord record);

    WithdrawalsRecord selectByPrimaryKey(Integer id);
    
    List<WithdrawalsRecord> selectByUserUuid(Map<String, Object> map);
    
    int selectByUserUuidCount(String userUuid);
    
    int updateByPrimaryKeySelective(WithdrawalsRecord record);

    int updateByPrimaryKey(WithdrawalsRecord record);
    
    List<WithdrawalsRecord> selectByMapLimit(Map<String, Object> map);
    
    int countSelectByMapLimit(Map<String, Object> map);

	Float selectAlreadyMoney(String userUuid);
}