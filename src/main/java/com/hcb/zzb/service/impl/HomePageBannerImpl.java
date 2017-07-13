package com.hcb.zzb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.HomepageBannerMapper;
import com.hcb.zzb.dto.HomepageBanner;
import com.hcb.zzb.service.IhomePageBanner;

@Service("homePageBanner")
public class HomePageBannerImpl implements IhomePageBanner {
	@Autowired
	private HomepageBannerMapper homepageBannerMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return homepageBannerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(HomepageBanner record) {
		// TODO Auto-generated method stub
		return homepageBannerMapper.insert(record);
	}

	@Override
	public int insertSelective(HomepageBanner record) {
		// TODO Auto-generated method stub
		return homepageBannerMapper.insertSelective(record);
	}

	@Override
	public HomepageBanner selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return homepageBannerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(HomepageBanner record) {
		// TODO Auto-generated method stub
		return homepageBannerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HomepageBanner record) {
		// TODO Auto-generated method stub
		return homepageBannerMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<HomepageBanner> selectAll() {
		// TODO Auto-generated method stub
		return homepageBannerMapper.selectAll();
	}

}
