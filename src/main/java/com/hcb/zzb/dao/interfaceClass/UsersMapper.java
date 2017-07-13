package com.hcb.zzb.dao.interfaceClass;

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

}