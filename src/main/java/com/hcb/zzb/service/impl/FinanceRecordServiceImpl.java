package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.FinanceRecordMapper;
import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.service.IFinanceRecordService;
@Service("financeRecordService")
public class FinanceRecordServiceImpl implements IFinanceRecordService{
	@Autowired
	FinanceRecordMapper financeRecordMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return financeRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FinanceRecord record) {
		// TODO Auto-generated method stub
		return financeRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(FinanceRecord record) {
		// TODO Auto-generated method stub
		return financeRecordMapper.insertSelective(record);
	}

	@Override
	public FinanceRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return financeRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FinanceRecord record) {
		// TODO Auto-generated method stub
		return financeRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FinanceRecord record) {
		// TODO Auto-generated method stub
		return financeRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<FinanceRecord> selectAllByUserUuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return financeRecordMapper.selectAllByUserUuid(map);
	}

	@Override
	public int selectCountByUserUuid(String userUuid) {
		// TODO Auto-generated method stub
		return financeRecordMapper.selectCountByUserUuid(userUuid);
	}

	@Override
	public List<FinanceRecord> selectIncomeAndExpenditureByToday(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return financeRecordMapper.selectIncomeAndExpenditureByToday(map);
	}

	@Override
	public FinanceRecord selectByUuid(String uuid) {
		// TODO Auto-generated method stub
		return financeRecordMapper.selectByUuid(uuid);
	}

	@Override
	public List<FinanceRecord> selectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return financeRecordMapper.selectByMapLimit(map);
	}

	@Override
	public int countSelectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return financeRecordMapper.countSelectByMapLimit(map);
	}

}
