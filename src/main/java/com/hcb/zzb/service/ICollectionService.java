package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Collection;

public interface ICollectionService {
	/**
	 * 点击收藏，增加收藏信息
	 * @param record
	 * @return
	 */
	public int insertSelective(Collection record);
	/**
	 * 通过用户uuid查询我的收藏信息
	 * @param uuid
	 * @return
	 */
	public List<Collection> selectCollectionByUserUuid(Map<String, Object> map);
	/**
	 * 查询用户收藏的总记录数
	 * @param user_uuid
	 * @return
	 */
	public int selectByUserUuidCount(String user_uuid);
	/**
	 * 通过车辆uuid删除收藏
	 * @param carUuid
	 * @return
	 */
	public int deleteByCarUuid(String carUuid);
}
