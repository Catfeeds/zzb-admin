package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.bean.LoginResp;
import com.hcb.zzb.bean.base.BaseResp;
import com.hcb.zzb.dto.Users;

public interface IUsersService {

	LoginResp register(String phone);

	BaseResp login(Users users, String phone);

	Users selectByUserUuid(String userUuid);
	int updateByPrimaryKey(Users user);
	
	int updateByPrimaryKeySelective(Users record);
	
	
	List<Users> selectUsersByMap(Map<String, Object> map);
	
	int countUsersByMap(Map<String, Object> map);
}
