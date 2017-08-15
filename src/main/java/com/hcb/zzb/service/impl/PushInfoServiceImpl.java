package com.hcb.zzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.PushInfoMapper;
import com.hcb.zzb.dto.PushInfo;
import com.hcb.zzb.service.IPushInfoService;
@Service("PushInfoService")
public class PushInfoServiceImpl implements IPushInfoService {
	@Autowired
	PushInfoMapper pushInfoMapper;
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return pushInfoMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(PushInfo record) {
		// TODO Auto-generated method stub
		return pushInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(PushInfo record) {
		// TODO Auto-generated method stub
		return pushInfoMapper.insertSelective(record);
	}

	@Override
	public PushInfo selectByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return pushInfoMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(PushInfo record) {
		// TODO Auto-generated method stub
		return pushInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PushInfo record) {
		// TODO Auto-generated method stub
		return pushInfoMapper.updateByPrimaryKey(record);
	}


	@Override
	public PushInfo selectByPushUuid(String pushUuid) {
		return pushInfoMapper.selectByPushUuid(pushUuid);
	}

}
