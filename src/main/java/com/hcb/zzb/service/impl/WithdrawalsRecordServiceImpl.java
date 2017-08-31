package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.WithdrawalsRecordMapper;
import com.hcb.zzb.dto.WithdrawalsRecord;
import com.hcb.zzb.service.IWithdrawalsRecordService;
@Service("withdrawalsRecordService")
public class WithdrawalsRecordServiceImpl implements IWithdrawalsRecordService{
	@Autowired
	WithdrawalsRecordMapper withdrawalsRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WithdrawalsRecord record) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(WithdrawalsRecord record) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.insertSelective(record);
	}

	@Override
	public WithdrawalsRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WithdrawalsRecord record) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WithdrawalsRecord record) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.updateByPrimaryKey(record);
	}


	@Override
	public int selectByUserUuidCount(String userUuid) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.selectByUserUuidCount(userUuid);
	}

	@Override
	public List<WithdrawalsRecord> selectByUserUuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.selectByUserUuid(map);
	}

	@Override
	public List<WithdrawalsRecord> selectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.selectByMapLimit(map);
	}

	@Override
	public int countSelectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.countSelectByMapLimit(map);
	}

	@Override
	public Float selectAlreadyMoney(String userUuid) {
		// TODO Auto-generated method stub
		return withdrawalsRecordMapper.selectAlreadyMoney(userUuid);
	}


}
