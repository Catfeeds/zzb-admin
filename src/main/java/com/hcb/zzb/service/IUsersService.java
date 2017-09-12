package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.bean.LoginResp;
import com.hcb.zzb.bean.base.BaseResp;
import com.hcb.zzb.dto.Users;

/**
 * 用户和车主
 * 
 * @author kk
 *
 */
public interface IUsersService {

	LoginResp register(String phone);

	BaseResp login(Users users, String phone);

	/**
	 * 根据uuid查询用户
	 * 
	 * @param userUuid
	 * @return
	 */
	Users selectByUserUuid(String userUuid);

	int updateByPrimaryKey(Users user);

	int updateByPrimaryKeySelective(Users record);
	/**
	 * 根据筛选条件查询列表
	 * @param map
	 * @return
	 */
	List<Users> selectUsersByMap(Map<String, Object> map);

	/**
	 * 根据筛选条件查询数目
	 * @param map
	 * @return
	 */
	int countUsersByMap(Map<String, Object> map);

	Users selectByPrimaryKey(Integer id);

	Users selectByUserOwnerUuid(String carOwnerUuid);
	/**
	 * 根据筛选条件查询列表
	 * @param map
	 * @return
	 */
	List<Users> selectUsers(Map<String, Object> map);
	/**
	 * 根据筛选条件查询数目
	 * @param map
	 * @return
	 */
	int countUsersOwnerByMap(Map<String, Object> map);
	/**
	 * 根据筛选条件查询列表
	 * @param map
	 * @return
	 */
	List<Users> selectUsersOwnerByMap(Map<String, Object> map);
}
