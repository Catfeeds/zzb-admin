package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.BankCardMapper;
import com.hcb.zzb.dto.BankCard;
import com.hcb.zzb.service.IBankCardService;
@Service("bankCardService")
public class BankCardServiceImpl implements IBankCardService{
	@Autowired
	BankCardMapper bankCardMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return bankCardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BankCard record) {
		// TODO Auto-generated method stub
		return bankCardMapper.insert(record);
	}

	@Override
	public int insertSelective(BankCard record) {
		// TODO Auto-generated method stub
		return bankCardMapper.insertSelective(record);
	}

	@Override
	public BankCard selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return bankCardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BankCard record) {
		// TODO Auto-generated method stub
		return bankCardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BankCard record) {
		// TODO Auto-generated method stub
		return bankCardMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BankCard> selectByUserUuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bankCardMapper.selectByUserUuid(map);
	}

	@Override
	public int selectByUserUuidCount(String userUuid) {
		// TODO Auto-generated method stub
		return bankCardMapper.selectByUserUuidCount(userUuid);
	}

}
