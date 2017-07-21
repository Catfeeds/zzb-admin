package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.MessageBaseMapper;
import com.hcb.zzb.dto.MessageBase;
import com.hcb.zzb.service.ImessageBaseService;
@Service("messageBaseService")
public class MessageBaseServiceImpl implements ImessageBaseService {
@Autowired
private MessageBaseMapper messageBaseMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return messageBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MessageBase record) {
		// TODO Auto-generated method stub
		return messageBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(MessageBase record) {
		// TODO Auto-generated method stub
		return messageBaseMapper.insertSelective(record);
	}

	@Override
	public MessageBase selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return messageBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MessageBase record) {
		// TODO Auto-generated method stub
		return messageBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(MessageBase record) {
		// TODO Auto-generated method stub
		return messageBaseMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(MessageBase record) {
		// TODO Auto-generated method stub
		return messageBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MessageBase> searchByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return messageBaseMapper.searchByMap(map);
	}

	@Override
	public Integer countByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return messageBaseMapper.countByMap(map);
	}

}
