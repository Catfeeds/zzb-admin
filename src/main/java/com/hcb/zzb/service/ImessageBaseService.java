package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.MessageBase;
/**
 * 系统推送消息
 * @author kk
 *
 */
public interface ImessageBaseService {
	int deleteByPrimaryKey(Integer id);

    int insert(MessageBase record);

    int insertSelective(MessageBase record);

    MessageBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageBase record);

    int updateByPrimaryKeyWithBLOBs(MessageBase record);

    int updateByPrimaryKey(MessageBase record);
    /**
	 * 根据筛选条件查询列表
	 * @param map
	 * @return
	 */
	List<MessageBase> searchByMap(Map<String, Object> map);
	/**
	 * 根据筛选条件查询数目
	 * @param map
	 * @return
	 */
	Integer countByMap(Map<String, Object> map);
}
