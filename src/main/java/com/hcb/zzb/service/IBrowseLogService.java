package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.BrowseLog;

public interface IBrowseLogService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(BrowseLog record);
	public BrowseLog selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(BrowseLog record);

	public int updateByPrimaryKey(BrowseLog record);
	/**
	 * 用户浏览车辆时保存信息
	 * @param record
	 * @return
	 */
	public int insertSelectiveBrowseLog(BrowseLog record);
	/**
	 * 查询用户浏览（足迹）的车辆记录
	 * @param userUuid
	 * @return
	 */
	public List<BrowseLog> selectAllByUserUuid(Map<String, Object> map);
	
	public int selectByUserUuidCount(String user_uuid);
	/**
	 * 查询猜你喜欢
	 * @param map
	 * @return
	 */
	List<BrowseLog> selectByGuessYouLike(Map<String, Object> map);
	/**
	 * 查询猜你喜欢的总记录数
	 * @param userUuid
	 * @return
	 */
	int selectGuessYouLikeCount(String userUuid);

	public List<BrowseLog> selectByGuessYouLike1(String string);

	public List<BrowseLog> selectByrecentBrowse(String string);
}
