package com.hcb.zzb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.PlatformConfigMapper;
import com.hcb.zzb.dto.PlatformConfig;
import com.hcb.zzb.service.IPlatformConfigService;
@Service("platformConfigService")
public class PlatformConfigServiceImpl implements IPlatformConfigService{
	@Autowired
	PlatformConfigMapper platformConfigMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return platformConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PlatformConfig record) {
		// TODO Auto-generated method stub
		return platformConfigMapper.insert(record);
	}

	@Override
	public int insertSelective(PlatformConfig record) {
		// TODO Auto-generated method stub
		return platformConfigMapper.insertSelective(record);
	}

	@Override
	public PlatformConfig selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return platformConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PlatformConfig record) {
		// TODO Auto-generated method stub
		return platformConfigMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PlatformConfig record) {
		// TODO Auto-generated method stub
		return platformConfigMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PlatformConfig> selectAll() {
		// TODO Auto-generated method stub
		return platformConfigMapper.selectAll();
	}

}
