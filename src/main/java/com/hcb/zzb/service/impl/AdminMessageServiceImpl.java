package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.AdminMessageMapper;
import com.hcb.zzb.dto.AdminMessage;
import com.hcb.zzb.service.IAdminMessageService;
@Service("AdminMessageService")
public class AdminMessageServiceImpl implements IAdminMessageService{
	@Autowired
	AdminMessageMapper adminMessageMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return adminMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AdminMessage record) {
		return adminMessageMapper.insert(record);
	}

	@Override
	public int insertSelective(AdminMessage record) {
		return adminMessageMapper.insertSelective(record);
	}

	@Override
	public AdminMessage selectByPrimaryKey(Integer id) {
		return adminMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminMessage record) {
		return adminMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminMessage record) {
		return adminMessageMapper.updateByPrimaryKey(record);
	}
}
