package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Users;

public interface UsersMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Users record);

	int insertSelective(Users record);

	Users selectByPrimaryKey(Integer id);

	Users selectByPhone(String phone);

	Users selectByQqOpenId(String qqOpenId);

	Users selectByWxOpenId(String wxOpenId);

	int updateByPrimaryKeySelective(Users record);

	int updateByPrimaryKey(Users record);

	Users selectByUserUuid(String userUuid);
	
	List<Users> selectUsersByMap(Map<String, Object> map);
	
	int countUsersByMap(Map<String, Object> map);

	Users selectByUserOwnerUuid(String carOwnerUuid);

	List<Users> selectUsers(Map<String, Object> map);

	int countUsersOwnerByMap(Map<String, Object> map);

	List<Users> selectUsersOwnerByMap(Map<String, Object> map);

}