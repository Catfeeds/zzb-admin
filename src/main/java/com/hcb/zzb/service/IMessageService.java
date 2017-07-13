package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;


import com.hcb.zzb.dto.MessageChild;

public interface IMessageService {
	int deleteByPrimaryKey(Integer id);

    int insert(MessageChild record);

    int insertSelective(MessageChild record);
    
    int updateByPrimaryKeySelective(MessageChild record);

    int updateByPrimaryKey(MessageChild record);
    /**
     * 通过消息uuid与用户uuid查询消息详情
     * @param map
     * @return
     */
    MessageChild selectByUserUuidMessageChildUuid(Map<String,Object> map);
	/**
	 * 分页查询用户所有消息
	 * @param map
	 * @return
	 */
	public List<MessageChild> selectAllMessageByUser(Map<String,Object> map);
	/**
	 * 查询用户消息总记录数
	 * @param userUuid
	 * @return
	 */
	public int selectAllMessageByUserCount(String userUuid);
	/**
	 * 消息详情
	 * @param id
	 * @return
	 */
	public MessageChild selectByPrimaryKey(Integer id);
}
