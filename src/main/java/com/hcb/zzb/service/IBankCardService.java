package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.BankCard;

public interface IBankCardService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(BankCard record);

	public int insertSelective(BankCard record);

	public BankCard selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(BankCard record);

	public int updateByPrimaryKey(BankCard record);
    
	public List<BankCard> selectByUserUuid(Map<String, Object> map);
    
	public int selectByUserUuidCount(String userUuid);

	public List<BankCard> selectByUserUuid(String userUuid);
}
