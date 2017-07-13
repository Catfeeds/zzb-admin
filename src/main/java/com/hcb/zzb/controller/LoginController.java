package com.hcb.zzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Manager;
import com.hcb.zzb.service.IManagerService;

import net.sf.json.JSONObject;

@Controller
public class LoginController extends BaseControllers {
	
	@Autowired
	IManagerService managerService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String phoneLogin() {
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("account") == null || bodyInfo.get("password") == null) {
			json.put("result", 1);
			json.put("description", "请输入账号和密码");
			return buildReqJsonObject(json);
		}
		Manager managerLogin = managerService.selectByAccount(bodyInfo.getString("account"));
		if(managerLogin == null){
			json.put("result", 1);
			json.put("description", "账号错误");
		}else{
			if(!bodyInfo.getString("password").equals(managerLogin.getPassword())){
				json.put("result", 1);
				json.put("description", "密码错误");
			}else{
				if(managerLogin.getManagerStatus()==2){
					json.put("result", 1);
					json.put("description", "登录失败，账户已冻结");
				}else{
					json.put("result", 0);
					json.put("description", "登录成功");
				}
			}
		}
		return buildReqJsonObject(json);
	}
}	
