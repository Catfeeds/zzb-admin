package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.BankCard;

public interface BankCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);
    
    List<BankCard> selectByUserUuid(Map<String, Object> map);
    
    int selectByUserUuidCount(String userUuid);

	List<BankCard> selectByUserUuid1(String userUuid);
}