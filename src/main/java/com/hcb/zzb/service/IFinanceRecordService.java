package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.FinanceRecord;

public interface IFinanceRecordService {
	int deleteByPrimaryKey(Integer id);

    int insert(FinanceRecord record);

    int insertSelective(FinanceRecord record);

    FinanceRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceRecord record);

    int updateByPrimaryKey(FinanceRecord record);
    
    List<FinanceRecord> selectAllByUserUuid(Map<String, Object> map);
    
    int selectCountByUserUuid(String userUuid);
    
    List<FinanceRecord> selectIncomeAndExpenditureByToday(Map<String, Object> map);

    FinanceRecord selectByUuid(String uuid);
}
