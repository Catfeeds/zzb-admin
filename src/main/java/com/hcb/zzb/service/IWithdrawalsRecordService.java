package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.WithdrawalsRecord;

public interface IWithdrawalsRecordService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(WithdrawalsRecord record);

	public int insertSelective(WithdrawalsRecord record);

	public WithdrawalsRecord selectByPrimaryKey(Integer id);
    
	public List<WithdrawalsRecord> selectByUserUuid(Map<String, Object> map);
	
    public int selectByUserUuidCount(String userUuid);
    
	public int updateByPrimaryKeySelective(WithdrawalsRecord record);

	public int updateByPrimaryKey(WithdrawalsRecord record);
	
	List<WithdrawalsRecord> selectByMapLimit(Map<String, Object> map);
    
    int countSelectByMapLimit(Map<String, Object> map);
}
