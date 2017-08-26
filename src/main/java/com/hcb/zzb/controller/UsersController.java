package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.model.Owner;
import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.BankCard;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.OwnerPo;
import com.hcb.zzb.dto.UserCredit;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IBankCardService;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.DateUtil;

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
	@Autowired
	IBankCardService bankCardService;
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
			if(bodyInfo.get("id")!=null&&!"".equals(bodyInfo.get("id"))) {
				map.put("id", bodyInfo.getInt("id"));
			}
			if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
				map.put("orderBy", bodyInfo.getInt("orderBy"));
			}else {
				map.put("orderBy", 2);
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
			//List<Users> list=usersService.selectUsersByMap(map);
		/*	List<Users> list=usersService.selectUsers(map);
			Map<String, Object> mappp=new HashMap<>();
			mappp.put("list", list);
			List<Map<String, Object>>list2=new ArrayList<Map<String, Object>>();
			list2.add(mappp);
			int consume=0;//消费次数
			Float money;//消费金额
			if(!list.isEmpty()) {
				for (Users user : list) {
					Map<String, Object> map2=new HashMap<>();
					if(user!=null){
						String userUuid=user.getUserUuid();
						if(userUuid!=null){
							 consume=orderService.selectCountByConsume(userUuid);
							 money=orderService.selectMoneyByConsume(userUuid);
							 map2.put("consume", consume);
							 map2.put("money", money);
							 map2.put("profit", 0);
							 map2.put("profitRate", 0);
							 map2.put("consumeIntegration", 0);//消费积分
							 map2.put("Grade", 0);//会员等级
							 map2.put("cashbalance", 0);//现金余额
							 map2.put("givebalance", 0);//赠送余额
						}else{
							map2.put("consume", 0);
							map2.put("money", 0f);
							map2.put("profit", 0f);//利润
							map2.put("profitRate", 0);//比例
							map2.put("consumeIntegration", 0);//消费积分
							map2.put("Grade", 0);//会员等级
							map2.put("cashbalance", 0);//现金余额
							map2.put("givebalance", 0);//赠送余额
						}
						
					}
					list2.add(map2);
				}
				json.put("result", "0");
				json.put("description", "查询成功");
				json.put("total", total);
				json.put("page", pageIndex);
				//json.put("userList", list);
				json.put("userList1", list2);
			}else {
				json.put("result", "1");
				json.put("description", "未查询到数据");
			}*/
			
			
			List<Users> list=usersService.selectUsers(map);
			//Map<String, Object> mappp=new HashMap<>();
			//mappp.put("list", list);
			//List<Map<String, Object>>list2=new ArrayList<Map<String, Object>>();
			//list2.add(mappp);
			int consume=0;//消费次数
			Float money;//消费金额
			if(!list.isEmpty()) {
				for (Users user : list) {
					Map<String, Object> map2=new HashMap<>();
					if(user!=null){
						String userUuid=user.getUserUuid();
						if(userUuid!=null){
							 consume=orderService.selectCountByConsume(userUuid);
							 money=orderService.selectMoneyByConsume(userUuid);
							 String mone="";
							 if(money==null){
								//消费积分=该用户下总计充值并消费金额
								  //String sstr=String.valueOf(Math.floor(money));
								 money=0f;
								 mone=money.toString().substring(0, 1);
								 int parseInt = Integer.parseInt(mone);
								 user.setConsumeIntegration(parseInt);
							 }else{
								 mone=money.toString().substring(0, 1);
								 int parseInt = Integer.parseInt(mone);
								 user.setConsumeIntegration(parseInt);
							 }
							 //System.out.println(mo);
							//double floor = Math.floor(money);
							 user.setMoney(money);
							 user.setConsume(consume);
							 user.setProfit(0f);
							 user.setProfitRate(0f);
							 //会员等级=订单数（1单连续30天，算30单） / 5单。说明：即5个订单为1级。500天订单为100级
							 int grade= consume/5;
							 user.setGrade(grade);
							 user.setCashbalance(0f);
							 user.setGivebalance(0f);
							 /*map2.put("consume", consume);
							 map2.put("money", money);
							 map2.put("profit", 0);
							 map2.put("profitRate", 0);
							 map2.put("consumeIntegration", 0);//消费积分
							 map2.put("Grade", 0);//会员等级
							 map2.put("cashbalance", 0);//现金余额
							 map2.put("givebalance", 0);//赠送余额
*/						}else{
							//map2.put("consume", 0);
							//map2.put("money", 0f);
							//map2.put("profit", 0f);//利润
							//map2.put("profitRate", 0);//比例
							//map2.put("consumeIntegration", 0);//消费积分
							//map2.put("Grade", 0);//会员等级
							//map2.put("cashbalance", 0);//现金余额
							//map2.put("givebalance", 0);//赠送余额
						}
						
					}
					//list2.add(map2);
				}
				System.out.println(list);
				json.put("result", "0");
				json.put("description", "查询成功");
				json.put("total", total);
				json.put("page", pageIndex);
				json.put("userList", list);
				//json.put("userList1", list2);
			}else {
				json.put("result", "1");
				json.put("description", "未查询到数据");
			}
			
		}
		return buildReqJsonObject(json);
	}
	/**
	 * 车主信息列表
	 * @return
	 */
	@RequestMapping(value="userOwnerList",method=RequestMethod.POST)
	@ResponseBody
	public String findUsersOwner() {
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
			if(bodyInfo.get("id")!=null&&!"".equals(bodyInfo.get("id"))) {
				map.put("id", bodyInfo.getInt("id"));
			}
			if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
				map.put("orderBy", bodyInfo.getInt("orderBy"));
			}else {
				map.put("orderBy", 2);
			}
			int count=usersService.countUsersOwnerByMap(map);
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
			List<Users> list=usersService.selectUsersOwnerByMap(map);
			//Map<String, Object> mapppp=new HashMap<>();
			//mapppp.put("list", list);
			//List<Map<String, Object>>list2=new ArrayList<Map<String, Object>>();
			//list2.add(mapppp);
			int carnum1=0;//有效车辆数
			int carnum2=0;//历史车辆数
			int sureordercount;//接单次数
			//Float money;//消费金额
			if(!list.isEmpty()) {
				for (Users user : list) {
					Map<String, Object> map2=new HashMap<>();
					if(user!=null){
						String userUuid=user.getUserUuid();
						if(userUuid!=null){
							sureordercount=orderService.selectSureOrder(userUuid);
							List<Car> car=carService.selectByCarBand(userUuid);
							carnum1=carService.selectNum1(userUuid);
							carnum2=carService.selectNum2(userUuid);
							 //订单GDP
							//平台分佣、
							 //差价利润
							 ///可提现金额
							 //提现中金额
							//已提现金额
							 OwnerPo ownerPo=new OwnerPo();
							 ownerPo.setAlreadybalance(0);
							 ownerPo.setAvg(0f);
							 ownerPo.setCarnum1(carnum1);
							 ownerPo.setCarnum2(carnum2);
							 ownerPo.setCars(car);
							 ownerPo.setCashbalance(0f);
							 ownerPo.setChajialirun(0f);
							 ownerPo.setGdp(0f);
							 ownerPo.setKetixianjiner(0f);
							 ownerPo.setSureordercount(sureordercount);
							 user.setOwnerPo(ownerPo);
						}else{
							 //map2.put("car", "");
							 //map2.put("carnum", 0);
							 //map2.put("historycarnum", 0);
							 //map2.put("sureordercount", 0);
							 //map2.put("GDP", 0);//订单GDP
							 //map2.put("avg", 0);//平台分佣、
							 //map2.put("chajialirun", 0);//差价利润
							 //map2.put("ketixianjiner", 0);///可提现金额
							 //map2.put("cashbalance", 0);//提现中金额
							 //map2.put("alreadybalance", 0);//已提现金额
							}
					}
					//list2.add(map2);
				}
				
				System.out.println(list);
				json.put("result", "0");
				json.put("description", "查询成功");
				json.put("total", total);
				json.put("page", pageIndex);
				json.put("userList", list);
				//json.put("userList1", list2);
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
			String userUuid = user.getUserUuid();
			//绑定卡号
			List<BankCard> Bank= bankCardService.selectByUserUuid(userUuid);
			if(Bank.size()>0){
				json.put("bank", Bank);
			}else{
				json.put("bank", "");
			}
			//驾驶时长 int time;
			//驾驶车型数 int modelNum;
			//历史总成交 int totalNum;
			//驾驶次数
			//平均响应时间
			//平均接单量
			List<Car> cars = carService.selectByUserUuid(userUuid);
			if(cars!=null){
				//上传车辆
				for (Car car : cars) {
					car.setTime(0);
					car.setModelNum(0);
					car.setTotalNum(0);
					car.setDriverCount(0);
					car.setRes(0);
					car.setAvg(0);
				}
				json.put("car", cars);
				
			}else{
				json.put("car", "");
			}
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
			if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
				map.put("orderBy", bodyInfo.getInt("orderBy"));
			}else {
				map.put("orderBy", 2);
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
					userCre.setUseCarScore(users.getVehicleBehavior()==null?20:users.getVehicleBehavior());
					//状态
					int sta=users.getUserStatus()==null?1:users.getUserStatus();
					if(sta==2) {
						userCre.setCreditStatus("拉黑");
					}else {
						int credits=users.getCreditScore()==null?20:users.getCreditScore();
						
						if(credits<60) {
							userCre.setCreditStatus("较差");
						}else if(credits>=60 && credits<70) {
							userCre.setCreditStatus("一般");
						}else if(credits >= 70 && credits < 90) {
							userCre.setCreditStatus("良好");
						}else if(credits >= 90 && credits<=100) {
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
