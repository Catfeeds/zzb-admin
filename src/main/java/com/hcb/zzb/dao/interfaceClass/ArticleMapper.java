package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

	List<Article> selectAll();
	
	List<Article> selectAllByGuess_like(Map<String, Object> map);
	
	List<Article> selectAllByTypeIsPremium_experience(Map<String, Object> map);
	
	int selectAllByArticleTypeCount(int articleType);
	
	Article selectByArticleUuid(String articleUuid);

	List<Article> selectTypeAll();
	
	List<Article> selectByMapLimit(Map<String, Object> map);
	
	int countSelectByMapLimit(Map<String, Object> map);
}