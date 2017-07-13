package com.hcb.zzb.service.impl;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.bean.LoginResp;
import com.hcb.zzb.dao.interfaceClass.UsersMapper;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.MD5Util;
@Service("UsersService")
public class UsersServiceImpl implements IUsersService {
	@Autowired
    UsersMapper usersMapper;
	@Override
	public LoginResp register(String phone) {
		// TODO Auto-generated method stub
		//return null;
		 LoginResp resp = new LoginResp();
	        final Users user = new Users();
	        user.setUserPhone(phone);
	        user.setCreateAt(new Date());
	        String userUuid = "";
	        try {
	        	userUuid = MD5Util.md5Digest(phone+ System.currentTimeMillis() + RandomStringUtils.random(8));
	            user.setUserUuid(userUuid);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        Integer rs = usersMapper.insertSelective(user);
	        if(rs == 1){
	            resp.setResult("0").setDescription("登录成功");
	            //Users u = new Users();
	            //u.setUserPhone(phone);
	            Users register = usersMapper.selectByUserUuid(userUuid);
	            if(register != null){
	                resp.setUser_uuid(register.getUserUuid());
	                //register.setId(160000+register.getId());
	                //usersMapper.updateByPrimaryKey(register);
	            }
	        }else{
	            resp.setResult("1").setDescription("登录失败");
	        }
	        return resp;
	}

	@Override
	public LoginResp login(Users users, String phone) {
		// TODO Auto-generated method stub
		//return null;
		 LoginResp resp = new LoginResp();
	        try {
	            if (null != users) {
	                resp.setResult("0").setDescription("登录成功");
	                resp.setUser_uuid(users.getUserUuid());
	                resp.setHas_profile(true);
	                users.setUpdateAt(new Date());
	                Integer rs =usersMapper.updateByPrimaryKey(users);
	                if(rs == 1){
	                    resp.setResult("0").setDescription("登录成功");
	                    resp.setUser_uuid(users.getUserUuid());
	                }else{
	                    resp.setResult("1").setDescription("登录失败");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resp;
	}

	@Override
	public Users selectByUserUuid(String userUuid) {
		return usersMapper.selectByUserUuid(userUuid);
	}

	@Override
	public int updateByPrimaryKey(Users user) {
		// TODO Auto-generated method stub
		return usersMapper.updateByPrimaryKey(user);
	}

	@Override
	public int updateByPrimaryKeySelective(Users record) {
		// TODO Auto-generated method stub
		return usersMapper.updateByPrimaryKeySelective(record);
	}
	

}
