package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.ManagerMapper;
import com.hcb.zzb.dto.Manager;
import com.hcb.zzb.service.IManagerService;

@Service("ManagerService")
public class ManagerServiceImpl implements IManagerService {
	@Autowired
	ManagerMapper managerMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return managerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Manager record) {
		return managerMapper.insert(record);
	}

	@Override
	public int insertSelective(Manager record) {
		return managerMapper.insertSelective(record);
	}

	@Override
	public Manager selectByPrimaryKey(Integer id) {
		return managerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Manager record) {
		return managerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Manager record) {
		return managerMapper.updateByPrimaryKey(record);
	}

	@Override
	public Manager selectByAccount(String account) {
		return managerMapper.selectByAccount(account);
	}

	@Override
	public List<Manager> searchByMap(Map<String, Object> map) {
		return managerMapper.searchByMap(map);
	}

	@Override
	public int countByMap(Map<String, Object> map) {
		return managerMapper.countByMap(map);
	}

	@Override
	public Manager selectByAccountUuid(String string) {
		// TODO Auto-generated method stub
		return managerMapper.selectByAccountUuid(string);
	}
}
