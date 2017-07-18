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
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IUsersService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="user")
public class UsersController extends BaseControllers{
	@Autowired
	IUsersService usersService;
	@Autowired
	ICarSevice carService;
	
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
	
	/**
	 * 用户信用列表
	 * @return
	 */
	@RequestMapping(value="creditList",method=RequestMethod.POST)
	@ResponseBody
	public String findUserCreditList() {
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
	 * 用户信用详情
	 * @return
	 */
	@RequestMapping(value="creditDetail",method=RequestMethod.POST)
	@ResponseBody
	public String findUserCreditDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("user_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("user_uuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model=new ModelMap();
		Users user=usersService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		if(user!=null) {
			model.put("result", "0");
			model.put("description", "查询成功");
			model.put("userId", user.getId());
			model.put("user_uuid", user.getUserUuid());
			model.put("userName", user.getUserName());
			model.put("creditScore", user.getCreditScore());
			model.put("identityCredit", 10);
			
			if(user.getQqOpenId()!=null&&!"".equals(user.getQqOpenId())) {
				model.put("outCredit", 10);
			}else {
				model.put("outCredit", 0);
			}
			if(user.getQqOpenId()!=null&&!"".equals(user.getQqOpenId())&&
					user.getWxOpenId()!=null&&!"".equals(user.getWxOpenId())
					&&user.getZmOpenId()!=null&&!"".equals(user.getZmOpenId())) {
				model.put("isBinding", "QQ,微信,芝麻信用");
			}else {
				model.put("notBinding", "");
			}
			model.put("useCarCredit", user.getVehicleBehavior());
			List<Car> userCarlist=carService.selectByUserUuid(user.getUserUuid());
			if(userCarlist.isEmpty()) {
				model.put("carInfo", 0);
			}else {
				model.put("carInfo", 20);
			}
			model.put("userStatus", user.getUserStatus());
		
		}else {
			model.put("result", "1");
			model.put("description", "该用户不存在!");
		}
		return buildReqJsonObject(model);
	}
	
	/**
	 * 用户拉黑/激活
	 * @return
	 */
	@RequestMapping(value="blackAndActivate",method=RequestMethod.POST)
	@ResponseBody
	public String userStatus() {
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
			if(user.getUserStatus()!=null) {
				if(user.getUserStatus()==2&&bodyInfo.getInt("user_status")==2) {
					json.put("result", "1");
					json.put("description", "用户已拉黑,请不要重复拉黑");
					return buildReqJsonObject(json);
				}
				if(user.getUserStatus()==1&&bodyInfo.getInt("user_status")==1) {
					json.put("result", "1");
					json.put("description", "用户已激活,请不要重复激活");
					return buildReqJsonObject(json);
				}
			}
			user.setUserStatus(bodyInfo.getInt("user_status"));
			int rs=0;
			rs=usersService.updateByPrimaryKeySelective(user);
			if(rs==1&&bodyInfo.getInt("user_status")==2) {
				json.put("result", "0");
				json.put("description", "拉黑成功");
			}else if(rs==1&&bodyInfo.getInt("user_status")==1){
				json.put("result", "0");
				json.put("description", "激活成功");
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
