package com.hcb.zzb.controller.base;

import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

import com.hcb.zzb.dao.interfaceClass.UsersMapper;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.util.MD5Util;

import java.util.Date;

/**
 * Created by JSF on 2017/6/6.
 */

@RestController
public class PublicController extends BaseControllers{

    @Autowired
    UsersMapper usersMapper;

    public String commonMethod(){
        JSONObject json = new JSONObject();
        if(sign == 1){
            json.put("result", "1");
            json.put("description", "body中缺少参数");
            return buildReqJsonInteger(1,json);
        }
        if(sign == 2){
            json.put("result", "1");
            json.put("description", "用户信息有误，或已在其他地方登录");
            return buildReqJsonInteger(2,json);
        }
        return "1";
    }

    public String commonMethod1(){
        JSONObject json = new JSONObject();
        if(sign == 1){
            json.put("result", "1");
            json.put("description", "body中缺少参数");
            return buildReqJsonInteger(1,json);
        }
        return "1";
    }

    public ModelMap update(String wxOpenId,String avater,String userName,int gender){
        Users u = new Users();
        Users user = usersMapper.selectByWxOpenId(u.getWxOpenId());
        ModelMap model = new ModelMap();
        if(user != null){
            user.setWxOpenId(wxOpenId);
            user.setAvater(avater);
            user.setUserName(userName);
            user.setUpdateAt(new Date());
            user.setGender(gender);
            Integer rs = usersMapper.updateByPrimaryKey(user);
            if (rs == 1){
                model.put("result", "0");
                model.put("description", "登录成功");
                model.put("user_uuid", user.getUserUuid());
                model.put("has_profile", true);
            }else {
                model.put("result", "1");
                model.put("description", "登录失败");
            }
        }else {
        	model.put("result", "1");
            model.put("description", "登录失败，请先用手机号注册"); 
        }
        return model;
    }

    public ModelMap updateqq(String qqOpenId, String userName, String avater){
        Users u = new Users();
        u.setQqOpenId(qqOpenId);
        Users user = usersMapper.selectByQqOpenId(u.getQqOpenId());
        ModelMap model = new ModelMap();
        if(user != null){
            user.setQqOpenId(qqOpenId);
            user.setUserName(userName);
            user.setAvater(avater);
            user.setUpdateAt(new Date());
            Integer rs = usersMapper.updateByPrimaryKey(user);
            if(rs == 1) {
                model.put("result", "0");
                model.put("description", "登录成功");
                model.put("user_uuid", user.getUserUuid());
                model.put("has_profile", true);
                return model;
            }else{
                model.put("result", "1");
                model.put("description", "登录失败");
                return model;
            }
        }else{
        	{
            	model.put("result", "1");
                model.put("description", "登录失败，请先用手机号注册"); 
            }
            return model;
        }
    }
}
