package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.MessageBase;

public interface ImessageBaseService {
	int deleteByPrimaryKey(Integer id);

    int insert(MessageBase record);

    int insertSelective(MessageBase record);

    MessageBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageBase record);

    int updateByPrimaryKeyWithBLOBs(MessageBase record);

    int updateByPrimaryKey(MessageBase record);

	List<MessageBase> searchByMap(Map<String, Object> map);

	Integer countByMap(Map<String, Object> map);
}
