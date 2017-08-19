package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.BrowseLogMapper;
import com.hcb.zzb.dto.BrowseLog;
import com.hcb.zzb.service.IBrowseLogService;
@Service("browseLogService")
public class BrowseLogServiceImpl implements IBrowseLogService{
	@Autowired
	BrowseLogMapper browseLogMapper;
	
	@Override
	public int insertSelectiveBrowseLog(BrowseLog record) {
		// TODO Auto-generated method stub
		return browseLogMapper.insertSelective(record);
	}

	@Override
	public List<BrowseLog> selectAllByUserUuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return browseLogMapper.selectByUserUuid(map);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return browseLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BrowseLog record) {
		// TODO Auto-generated method stub
		return browseLogMapper.insert(record);
	}

	@Override
	public BrowseLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return browseLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BrowseLog record) {
		// TODO Auto-generated method stub
		return browseLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BrowseLog record) {
		// TODO Auto-generated method stub
		return browseLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public int selectByUserUuidCount(String user_uuid) {
		// TODO Auto-generated method stub
		return browseLogMapper.selectByUserUuidCount(user_uuid);
	}

	@Override
	public List<BrowseLog> selectByGuessYouLike(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return browseLogMapper.selectByGuessYouLike(map);
	}

	@Override
	public int selectGuessYouLikeCount(String userUuid) {
		// TODO Auto-generated method stub
		return browseLogMapper.selectGuessYouLikeCount(userUuid);
	}

	@Override
	public List<BrowseLog> selectByGuessYouLike1(String userUuid) {
		// TODO Auto-generated method stub
		return browseLogMapper.selectByGuessYouLike1(userUuid);
	}

	@Override
	public List<BrowseLog> selectByrecentBrowse(String userUuid) {
		// TODO Auto-generated method stub
		return browseLogMapper.selectByrecentBrowse(userUuid);
	}

	@Override
	public BrowseLog selectByUserIdAndCarId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return browseLogMapper.selectByUserIdAndCarId(map);
	}

}
