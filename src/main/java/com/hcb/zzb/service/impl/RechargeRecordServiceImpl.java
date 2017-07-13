package com.hcb.zzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.RechargeRecordMapper;
import com.hcb.zzb.dto.RechargeRecord;
import com.hcb.zzb.service.IRechargeRecordService;
@Service("rechargeRecordService")
public class RechargeRecordServiceImpl implements IRechargeRecordService{
	@Autowired
	RechargeRecordMapper rechargeRecordMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return rechargeRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RechargeRecord record) {
		// TODO Auto-generated method stub
		return rechargeRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(RechargeRecord record) {
		// TODO Auto-generated method stub
		return rechargeRecordMapper.insertSelective(record);
	}

	@Override
	public RechargeRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return rechargeRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RechargeRecord record) {
		// TODO Auto-generated method stub
		return rechargeRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RechargeRecord record) {
		// TODO Auto-generated method stub
		return rechargeRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public RechargeRecord selectByUuid(String uuid) {
		// TODO Auto-generated method stub
		return rechargeRecordMapper.selectByUuid(uuid);
	}

}
