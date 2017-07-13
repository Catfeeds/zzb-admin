package com.hcb.zzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IUsersService;

import net.sf.json.JSONObject;
@Controller
public class BalanceController extends BaseControllers{
	@Autowired
	IUsersService userService;
	
	@RequestMapping(value="findBalance",method=RequestMethod.POST)
	@ResponseBody
	public String findUserBalance() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		Users user=userService.selectByUserUuid(headInfo.getString("user_uuid"));
		if(user!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("balance", user.getBalance());
			json.put("frozenBlance", user.getFrozenBalance());
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
}
