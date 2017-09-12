package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hcb.zzb.dto.Article;
/**
 * 运营文章
 * @author kk
 *
 */
@Service("ArticleService")
public interface IArticleService {
	int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

	List<Article> selectAll();
	
	/**
	 * 通过文章类型查询猜你喜欢（废除，猜你喜欢不查询这个，查询car表）
	 * @param map
	 * @return
	 */
	public List<Article> selectAllByGuess_like(Map<String, Object> map);
	/**
	 * 通过文章类型查询所有的超值体验
	 * @param map
	 * @return
	 */
	public List<Article> selectAllByTypeIsPremium_experience(Map<String, Object> map);
	/**
	 * 通过文章uuid查询
	 * @param articleUuid
	 * @return
	 */
	public Article selectByArticleUuid(String articleUuid);
	/**
	 * 通过类型查询总记录数
	 * @param articleType
	 * @return
	 */
	int selectAllByArticleTypeCount(int articleType);

	List<Article> selectTypeAll();
	/**
     * 根据输入内容查询列表
     * @param map
     * @return
     */
	List<Article> selectByMapLimit(Map<String, Object> map);
	/**
     * 根据输入内容查询条数
     * @param map
     * @return
     */
	int countSelectByMapLimit(Map<String, Object> map);
}