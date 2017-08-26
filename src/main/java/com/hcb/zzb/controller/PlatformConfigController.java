package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.PlatformConfig;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.dto.platformPo;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IFinanceRecordService;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.IPlatformConfigService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.DateUtil;
import com.hcb.zzb.util.StringUtil;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("platform")
public class PlatformConfigController extends BaseControllers{
	@Autowired
	IFinanceRecordService financeRecordService;
	@Autowired
	IPlatformConfigService platformConfigService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IUsersService usersService;
	@Autowired
	private ICarSevice carService;
	/**
	 * 平台账户收支明细列表
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String platFormList() {
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
		if("".equals(bodyInfo.get("pageIndex"))||"".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Integer pageIndex=bodyInfo.getInt("pageIndex");
		if(pageIndex<=0) {
			json.put("result", "1");
			json.put("description", "请求页不能小于0");
			return buildReqJsonObject(json);
		}
		Integer pageSize=bodyInfo.getInt("pageSize");
		Integer start=(pageIndex-1)*pageSize;
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", pageSize);
		map.put("recordType", 4);
		if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
			map.put("orderBy", bodyInfo.getInt("orderBy"));
		}else {
			map.put("orderBy", 2);
		}
		int count = financeRecordService.countSelectByRecordType(map);
		if(count ==0) {
			json.put("result", "1");
			json.put("description", "未查询到平台账户的收支明细");
			return buildReqJsonObject(json);
		}
		int total=count % pageSize ==0 ? count/pageSize : count/pageSize + 1;
		if(pageIndex>total) {
			json.put("result", "1");
			json.put("description", "操作失败,请求页数大于总页数");
			return buildReqJsonObject(json);
		}
		
		ModelMap model=new ModelMap();
		//总营收（历史成功订单总数）
		Float totalmoney =orderService.selectMoney();
		model.put("totalmoney", 0 * totalmoney);
		//历史新高（日期-金额）
		Float highmoney =orderService.selectHighMoney();
		
		model.put("highmoney", 0 * highmoney);
		//押金池，
		Float poolmoney =orderService.selectPoolMoney();
		model.put("poolmoney", poolmoney);
		//平台账户收支明细列表
		//订单状态（预定成功，服务中，已还车，已结案）
		//租客/车东/车款/用车时间/付款时间/
		//付款渠道/付款金额/押金金额/城市/
		List<FinanceRecord> financeList=new ArrayList<>();
		financeList=financeRecordService.selectByRecordType(map);
		
		//Map<String, Object>mapp1=new HashMap<String, Object>();
		//mapp1.put("platformList", financeList);
		if(!financeList.isEmpty()) {
			
			model.put("result", "0");
			model.put("description", "查询成功");
			model.put("total",total );
			model.put("page", pageIndex);
			//平台账户今日收入
			Map<String, Object> tmap=new HashMap<>();
			tmap.put("recordType", 4);//记录类型；1：充值；2：提现；3：订单；4：平台收费
			tmap.put("financeType", 1);//1收入 2支出
			List<FinanceRecord> todayIncomeList= financeRecordService.selectIncomeAndExpenditureByToday(tmap);
			if(todayIncomeList!=null&&!todayIncomeList.isEmpty()) {
				float todayIncomeTotal=0;
				for (FinanceRecord financeRecord : todayIncomeList) {
					todayIncomeTotal= todayIncomeTotal + financeRecord.getMoney();
				}
				
				model.put("income", (float)(Math.round(todayIncomeTotal*100))/100);
			}else {
				model.put("income", 0);
			}	
			//平台账户今日支出
			Map<String, Object> tmap1=new HashMap<>();
			tmap1.put("recordType", 4);//记录类型；1：充值；2：提现；3：订单；4：平台收费
			tmap1.put("financeType", 2);//1收入 2支出
			List<FinanceRecord> todayExpenditureList= financeRecordService.selectIncomeAndExpenditureByToday(tmap1);
			if(todayExpenditureList!=null&&!todayExpenditureList.isEmpty()) {
				float todayExpenditureTotal = 0;
				for (FinanceRecord financeRecord : todayExpenditureList) {
					todayExpenditureTotal = todayExpenditureTotal + financeRecord.getMoney();
				}
				model.put("expenditure", (float)(Math.round(todayExpenditureTotal*100))/100);
				
			}else {
				model.put("expenditure", 0);
			}
			
			/*PlatformConfig platform = platformConfigService.selectByPrimaryKey(39);
			if(platform==null) {
				json.put("result", "1");
				json.put("description", "没有平台账号");
				return buildReqJsonObject(json);
			}
			model.put("balance", platform.getBalance()==null?0:platform.getBalance());*/
			//model.put("platformList", financeList);
			
			
			
		}else {
			model.put("result", "1");
			model.put("description", "没有查询到平台账户的收支记录");
		}
		
		//列表明细
		//List<Map<String, Object>> listlist=new ArrayList<Map<String, Object>>();
		//listlist.add(mapp1);
		
		if(financeList.size()>0){
			for (FinanceRecord financeRecord : financeList) {
				//Map<String, Object>map2=new HashMap<String, Object>();
				if(financeRecord!=null&&financeRecord.getOrderUuid()!=null){
					String orderUuid=financeRecord.getOrderUuid();
					Orders order = orderService.selectByOrdersUuid(orderUuid);
					if(order!=null){
						platformPo newpo=new platformPo();
						//int orderStatus =order.getOrderStatus();
						String userUuid=order.getUserUuid();
						String ownerUuid=order.getCarOwnerUuid();
						String carUuid=order.getCarUuid();
						Users user = usersService.selectByUserUuid(userUuid);
						Users userOwner = usersService.selectByUserOwnerUuid(ownerUuid);
						if(user!=null){
							//json.put("user", user);
							//map2.put("user", user);
							newpo.setUserName(user.getUserName()==null?"":user.getUserName());
							newpo.setPhone(user.getUserPhone()==null?"":user.getUserPhone());
						}else{
							//json.put("user", "");
							//map2.put("user", user);
							newpo.setUserName("");
							newpo.setPhone("");
						}
						if(userOwner!=null){
							//json.put("userOwner", userOwner);
							//map2.put("userOwner", userOwner);
							newpo.setOwnerName(userOwner.getUserName()==null?"":userOwner.getUserName());
							newpo.setOwnerPhone(userOwner.getUserPhone()==null?"":userOwner.getUserPhone());
						}else{
							//json.put("userOwner", "");
							//map2.put("userOwner", "");
							newpo.setOwnerName("");
							newpo.setOwnerPhone("");
						}
						Car car = carService.selectByUuid(carUuid);
						if(car!=null){
							//车款/用车时间/付款时间 /付款渠道/付款金额/押金金额/城市/订单链接
							//json.put("carBrand", car.getBrand());
							//json.put("city", car.getCity());
							//map2.put("carBrand", car.getBrand());
							//map2.put("city", car.getCity());
							newpo.setCarBrand(car.getBrand()==null?"":car.getBrand());
							newpo.setCity(car.getCity()==null?"":car.getCity());
						}
						//（预定成功，服务中，已还车，已结案）
						//if(orderStatus==3){}
						//json.put("orderNumber", order.getOrderNumber()==null?"":order.getOrderNumber());
						//json.put("orderStatus", order.getOrderStatus()==null?"":order.getOrderStatus());
						//json.put("takeCarTime", DateUtil.getDate(order.getTakeCarTime()));
						//json.put("payTime", DateUtil.getDate(order.getPayTime()));
						//json.put("payType", order.getPayType()==null?1:order.getPayType());
						//json.put("totalPrice", String.valueOf(order.getTotalPrice()==null?"":order.getTotalPrice()));
						//json.put("deposit", String.valueOf(order.getDeposit()==null?"":order.getDeposit()));
						/*map2.put("orderNumber", order.getOrderNumber()==null?"":order.getOrderNumber());
						map2.put("orderStatus", order.getOrderStatus()==null?"":order.getOrderStatus());
						map2.put("takeCarTime", DateUtil.getDate(order.getTakeCarTime()));
						map2.put("payTime", DateUtil.getDate(order.getPayTime()));
						map2.put("payType", order.getPayType()==null?1:order.getPayType());
						map2.put("totalPrice", String.valueOf(order.getTotalPrice()==null?"":order.getTotalPrice()));
						map2.put("deposit", String.valueOf(order.getDeposit()==null?"":order.getDeposit()));*/
						newpo.setOrderNumber(order.getOrderNumber()==null?"":order.getOrderNumber());
						newpo.setOrderStatus(order.getOrderStatus()==null?0:order.getOrderStatus());
						newpo.setTakeCarTime(order.getTakeCarTime()==null?new Date():order.getTakeCarTime());
						newpo.setPayTime(order.getPayTime()==null?new Date():order.getPayTime());
						newpo.setPayType(order.getPayType()==null?1:order.getPayType());
						newpo.setTotalPrice(order.getTotalPrice()==null?0f:order.getTotalPrice());
						newpo.setDeposit(order.getDeposit()==null?0f:order.getDeposit());
						newpo.setDepositStatus(order.getDepositStatus()==null?0:order.getDepositStatus());
						financeRecord.setPlat(newpo);
					}
					//listlist.add(map2);
				}
			}
			//model.put("listlist", listlist);
			model.put("platformList", financeList);
		}
		
		return buildReqJsonObject(model);
	}
	
	/**
	 * 查看平台账户收支详情
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String findDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("financeRecordUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("financeRecordUuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		FinanceRecord financeRecord = financeRecordService.selectByUuid(bodyInfo.getString("financeRecordUuid"));
		if(financeRecord!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("financeRecord", financeRecord);
		}else {
			json.put("result", "1");
			json.put("description", "操作失败,未查询到该交易记录");
		}
		return buildReqJsonObject(json);
		
	}
}
