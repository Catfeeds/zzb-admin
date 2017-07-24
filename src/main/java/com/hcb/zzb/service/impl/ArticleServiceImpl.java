package com.hcb.zzb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.ArticleMapper;
import com.hcb.zzb.dto.Article;
import com.hcb.zzb.service.IArticleService;

@Service("ArticleService")
public class ArticleServiceImpl implements IArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Article> selectAll() {
		return articleMapper.selectAll();
	}
	
	@Override
	public List<Article> selectAllByGuess_like(Map<String, Object> map) {
		return articleMapper.selectAllByGuess_like(map);
	}
	@Override
	public List<Article> selectAllByTypeIsPremium_experience(Map<String, Object> map) {
		return articleMapper.selectAllByTypeIsPremium_experience(map);
	}
	@Override
	public Article selectByArticleUuid(String articleUuid) {
		return articleMapper.selectByArticleUuid(articleUuid);
	}
	@Override
	public int selectAllByArticleTypeCount(int articleType) {
		return articleMapper.selectAllByArticleTypeCount(articleType);
	}

	@Override
	public List<Article> selectTypeAll() {
		// TODO Auto-generated method stub
		return articleMapper.selectTypeAll();
	}

	@Override
	public List<Article> selectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return articleMapper.selectByMapLimit(map);
	}

	@Override
	public int countSelectByMapLimit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return articleMapper.countSelectByMapLimit(map);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.insert(record);
	}

	@Override
	public int insertSelective(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.insertSelective(record);
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKey(record);
	}
}
