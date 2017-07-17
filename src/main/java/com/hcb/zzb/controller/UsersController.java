package com.hcb.zzb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IUsersService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="user")
public class UsersController extends BaseControllers{
	@Autowired
	IUsersService usersService;
	
	
	/**
	 * 用户信息列表
	 * @return
	 */
	@RequestMapping(value="userList",method=RequestMethod.POST)
	@ResponseBody
	public String findUsers() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		Integer pageIndex=bodyInfo.getInt("pageIndex");
		Integer pageSize=bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		}else {
			Map<String, Object> map=new HashMap<String, Object>();
			int start=(pageIndex-1)*pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			if(bodyInfo.get("userName")!=null&&!"".equals(bodyInfo.get("userName"))) {
				map.put("userName", bodyInfo.getString("userName"));
			}
			int count=usersService.countUsersByMap(map);
			if(count==0) {
				json.put("result", "1");
				json.put("description", "未查询到数据");
				return buildReqJsonObject(json);
			}
			int total=count % pageSize ==0 ? count/pageSize : count/pageSize + 1;
			if(pageIndex>total) {
				json.put("result", "1");
				json.put("description", "操作失败,请求页数大于总页数");
				return buildReqJsonObject(json);
			}
			List<Users> list=usersService.selectUsersByMap(map);
			if(!list.isEmpty()) {
				json.put("result", "0");
				json.put("description", "查询成功");
				json.put("total", total);
				json.put("page", pageIndex);
				json.put("userList", list);
			}else {
				json.put("result", "1");
				json.put("description", "未查询到数据");
			}
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 用户详情
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String findUserDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("user_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("user_uuid"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		Users user=usersService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		if(user!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("user", user);
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 查看用户账户余额
	 * @return
	 */
	@RequestMapping(value="findBalance",method=RequestMethod.POST)
	@ResponseBody
	public String findUserBalance() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("user_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("user_uuid"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		Users user=usersService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		if(user!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("balance", user.getBalance());
		}else {
			json.put("result", "1");
			json.put("description", "未查询到用户信息");
		}
		return buildReqJsonObject(json);
	}
	/**
	 * 用户驳回，冻结，通过
	 * @return
	 */
	@RequestMapping(value="operationStatus",method=RequestMethod.POST)
	@ResponseBody
	public String userReject() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("user_uuid")==null||bodyInfo.get("user_status")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("user_uuid"))||"".equals(bodyInfo.get("user_status"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		Users user=usersService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		if(user!=null) {
			user.setUserStatus(bodyInfo.getInt("user_status"));
			int rs=0;
			rs=usersService.updateByPrimaryKeySelective(user);
			if(rs==1) {
				json.put("result", "0");
				json.put("description", "操作成功");
			}else {
				json.put("result", "1");
				json.put("description", "操作失败");
			}
		}else {
			json.put("result", "1");
			json.put("description", "未查询到用户信息");
		}
		return buildReqJsonObject(json);
	}

}
