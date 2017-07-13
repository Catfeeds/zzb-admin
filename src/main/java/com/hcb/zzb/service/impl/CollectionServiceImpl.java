package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.CollectionMapper;
import com.hcb.zzb.dto.Collection;
import com.hcb.zzb.service.ICollectionService;
@Service("collectionService")
public class CollectionServiceImpl implements ICollectionService{
	@Autowired
	CollectionMapper collectionMapper;
	
	@Override
	public int insertSelective(Collection record) {
		
		return collectionMapper.insertSelective(record);
	}

	@Override
	public List<Collection> selectCollectionByUserUuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return collectionMapper.selectByUserUuid(map);
	}

	@Override
	public int selectByUserUuidCount(String user_uuid) {
		// TODO Auto-generated method stub
		return collectionMapper.selectByUserUuidCount(user_uuid);
	}

	@Override
	public int deleteByCarUuid(String carUuid) {
		// TODO Auto-generated method stub
		return collectionMapper.deleteByCarUuid(carUuid);
	}

}
