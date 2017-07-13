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
public class Credit_scoreController extends BaseControllers{
	@Autowired
	IUsersService usersService;
	/**
	 * 信用积分
	 * @return
	 */
	@RequestMapping(value="credit_score",method=RequestMethod.POST)
	@ResponseBody
	public String credit_score() {
		JSONObject json = new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		//JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Users user=usersService.selectByUserUuid(headInfo.getString("user_uuid"));
		if(user!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("creditScore", user.getCreditScore());
			json.put("userType", user.getUserType());
			json.put("ridersStatus", user.getRidersStatus());
			json.put("authenticationStatus", user.getAuthenticationStatus());
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据记录");
		}
		return buildReqJsonObject(json);
	}
}
