package com.hcb.zzb.service;

import com.hcb.zzb.bean.LoginResp;
import com.hcb.zzb.bean.base.BaseResp;
import com.hcb.zzb.dto.Users;

public interface IUsersService {

	LoginResp register(String phone);

	BaseResp login(Users users, String phone);

	Users selectByUserUuid(String userUuid);
	int updateByPrimaryKey(Users user);
	
	int updateByPrimaryKeySelective(Users record);
}
