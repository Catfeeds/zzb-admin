package com.hcb.zzb.controller;

import java.util.ArrayList;
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
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.UserCredit;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.IUsersService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="user")
public class UsersController extends BaseControllers{
	@Autowired
	IUsersService usersService;
	@Autowired
	ICarSevice carService;
	@Autowired
	IOrderService orderService;
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
				List<UserCredit> userCreditList=new ArrayList<>();
				
				for (Users users : list) {
					UserCredit userCre=new UserCredit();
					userCre.setUserId(users.getId());
					userCre.setUserUuid(users.getUserUuid());
					userCre.setUserName(users.getUserName());
					
					//身份信用
					if(users.getAvater()!=null&&users.getUserName()!=null&&users.getUserPhone()!=null
							&&users.getIdType()!=null&&users.getIdNumber()!=null&&users.getDriving()!=null
							&&users.getGender()!=null&&users.getBirthday()!=null&&users.getIdPicture()!=null
							&&users.getConstellation()!=null) {
						userCre.setIdentityScore(10);
					}else {
						userCre.setIdentityScore(0);
					}
					//外部信用
					int outScore=0;
					if(users.getQqOpenId()!=null) {
						outScore=+10;
					}
					if(users.getWxOpenId()!=null){
						outScore=+10;
					}
					if(users.getZmOpenId()!=null) {
						outScore=+10;
					}
					userCre.setOutScore(outScore);
					//用车行为
					userCre.setUseCarScore(users.getVehicleBehavior());
					//状态
					if(users.getUserStatus()==2) {
						userCre.setCreditStatus("拉黑");
					}else {
						if(users.getCreditScore()<60) {
							userCre.setCreditStatus("较差");
						}else if(users.getCreditScore()>=60&&users.getCreditScore()<70) {
							userCre.setCreditStatus("一般");
						}else if(users.getCreditScore()>=70&&users.getCreditScore()<90) {
							userCre.setCreditStatus("良好");
						}else if(users.getCreditScore()>=90&&users.getCreditScore()<=100) {
							userCre.setCreditStatus("优秀");
						}
					}
					//修改用户信用积分（用户信用积分=身份信用+外部信用+用车行为）
					users.setCreditScore(userCre.getIdentityScore()+userCre.getOutScore()+userCre.getUseCarScore());
					usersService.updateByPrimaryKeySelective(users);
					userCre.setCreditScore(users.getCreditScore());
					userCreditList.add(userCre);
				}
				json.put("result", "0");
				json.put("description", "查询成功");
				json.put("total", total);
				json.put("page", pageIndex);
				json.put("userCreditList", userCreditList);
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
		Users user=usersService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		List<Car> carList=carService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		ModelMap model=new ModelMap();
		
		if(user!=null) {
			//个人资料
			Map<String, Object> personalInfo=new HashMap<>();
			personalInfo.put("userId",user.getId());
			personalInfo.put("userUuid",user.getUserUuid());
			personalInfo.put("userName", user.getUserName());
			personalInfo.put("gender",user.getGender());
			personalInfo.put("userPhone",user.getUserPhone());
			personalInfo.put("birthday",user.getBirthday());
			personalInfo.put("avater",user.getAvater());
			personalInfo.put("idType",user.getIdType());
			personalInfo.put("idNumber",user.getIdNumber());
			personalInfo.put("idPicture",user.getIdPicture());
			personalInfo.put("driving",user.getDriving());
			personalInfo.put("constellation",user.getConstellation());
			personalInfo.put("userType", user.getUserType());
		
			model.put("result", "0");
			model.put("description", "查询成功");
			model.put("userId", user.getId());//id
			model.put("user_uuid", user.getUserUuid());//uuid
			model.put("userName", user.getUserName());//姓名
			model.put("creditScore", user.getCreditScore());//信用积分
			//身份信用
			if(user.getAvater()!=null&&user.getUserName()!=null&&user.getUserPhone()!=null
					&&user.getIdType()!=null&&user.getIdNumber()!=null&&user.getDriving()!=null
					&&user.getGender()!=null&&user.getBirthday()!=null&&user.getIdPicture()!=null
					&&user.getConstellation()!=null) {
				model.put("identityCredit", 10);//身份信息填写完整给10分
			}else {
				model.put("identityCredit", 0);
			}
			//个人资料
			model.put("personalInfo", personalInfo);
			//车辆信息
			model.put("carInfo", carList);
			//外部信用
			int outScore=0;
			if(user.getQqOpenId()!=null) {
				outScore=+10;
			}
			if(user.getWxOpenId()!=null){
				outScore=+10;
			}
			if(user.getZmOpenId()!=null) {
				outScore=+10;
			}
			model.put("outCredit", outScore);
			//已绑定
			Map<String, Object> bind=new HashMap<>();
			Map<String, Object> notbind=new HashMap<>();
			if(user.getQqOpenId()!=null) {
				bind.put("QQ", "QQ");				
			}else {
				notbind.put("QQ", "QQ");
			}
			if(user.getWxOpenId()!=null) {
				bind.put("WX", "微信");
			}else {
				notbind.put("WX", "微信");
			}
			if(user.getZmOpenId()!=null) {
				bind.put("ZMXY", "芝麻信用");
			}else {
				notbind.put("ZMXY", "芝麻信用");
			}
			model.put("isBinding", bind);//已绑定
			model.put("notBinding", notbind);//未绑定
			//用车信用
			model.put("useCarCredit", user.getVehicleBehavior());
			//订单
			List<Orders> orderList=orderService.selectByUserUuid1(user.getUserUuid());
			List<String> ordersNumber=new ArrayList<>();
			for (Orders orders : orderList) {
				ordersNumber.add(orders.getOrderNumber());
			}
			model.put("order", ordersNumber);
			//用户状态
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
