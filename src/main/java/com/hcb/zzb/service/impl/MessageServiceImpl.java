package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.MessageChildMapper;
import com.hcb.zzb.dto.MessageChild;
import com.hcb.zzb.service.IMessageService;
@Service("messageService")
public class MessageServiceImpl implements IMessageService{
	@Autowired
	MessageChildMapper messageChildMapper;
	@Override
	public List<MessageChild> selectAllMessageByUser(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return messageChildMapper.selectAllMessageByUser(map);
	}
	@Override
	public int selectAllMessageByUserCount(String userUuid) {
		// TODO Auto-generated method stub
		return messageChildMapper.selectAllMessageByUserCount(userUuid);
	}
	@Override
	public MessageChild selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return messageChildMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return messageChildMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(MessageChild record) {
		// TODO Auto-generated method stub
		return messageChildMapper.insert(record);
	}
	@Override
	public int insertSelective(MessageChild record) {
		// TODO Auto-generated method stub
		return messageChildMapper.insertSelective(record);
	}
	@Override
	public int updateByPrimaryKeySelective(MessageChild record) {
		// TODO Auto-generated method stub
		return messageChildMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKey(MessageChild record) {
		// TODO Auto-generated method stub
		return messageChildMapper.updateByPrimaryKey(record);
	}
	@Override
	public MessageChild selectByUserUuidMessageChildUuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return messageChildMapper.selectByUserUuidMessageChildUuid(map);
	}

}
