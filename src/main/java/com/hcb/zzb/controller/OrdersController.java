package com.hcb.zzb.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.PlatformConfig;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IFinanceRecordService;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.IPlatformConfigService;
import com.hcb.zzb.service.ITicketService;
import com.hcb.zzb.service.IUsersService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="order")
public class OrdersController extends BaseControllers{
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IUsersService userService;
	//@Autowired
	//private ITicketService ticketService;
	@Autowired
	private IPlatformConfigService platformConfigService;
	@Autowired
	private IFinanceRecordService financeRecordService;
	/**
	 * 订单列表（分页）
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String orderList() {
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
		Integer pageSize=bodyInfo.getInt("pageSize");
		Integer start=(pageIndex-1)*pageSize;
		Integer end=pageSize;
		if(pageIndex<=0) {
			json.put("result", "1");
			json.put("description", "请求页不能小于0");
			return buildReqJsonObject(json);
		}
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		if(bodyInfo.get("orderNumber")!=null&&!"".equals(bodyInfo.get("orderNumber"))) {
			map.put("orderNumber", bodyInfo.getString("orderNumber"));
		}
		if(bodyInfo.get("userName")!=null&&!"".equals(bodyInfo.get("userName"))) {
			map.put("userName", bodyInfo.getString("userName"));
		}
		if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
			map.put("orderBy", bodyInfo.getInt("orderBy"));
		}else {
			map.put("orderBy", 2);
		}
		int count=orderService.countselectByMapLimit(map);
		if(count==0) {
			json.put("result", "1");
			json.put("description", "没有查询到数据记录");
			return buildReqJsonObject(json);
		}
		int total=count%pageSize==0?count/pageSize:count/pageSize+1;
		List<Map<String, Object>> list=orderService.selectByMapLimit(map);
		//List<Map<String, Object>> orderList=new ArrayList<Map<String, Object>>();
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			
			
			json.put("orderList", list);
		}else {
			json.put("result", "1");
			json.put("description", "没有查询到数据记录");
		}
		return buildReqJsonObject(json);
	}
	
	
	/**
	 * 订单详情
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String orderDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("order_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("order_uuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确");
			return buildReqJsonObject(json);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ModelMap model=new ModelMap();
		Orders order=orderService.selectByOrdersUuid(bodyInfo.getString("order_uuid"));
		if(order!=null) {
			if(order.getUserUuid()==null) {
				json.put("result", "1");
				json.put("description", "操作失败,这个订单没有属于用户");
				return buildReqJsonObject(json);
			}
			Users user=userService.selectByUserUuid(order.getUserUuid());
			if(user==null) {
				json.put("result", "1");
				json.put("description", "操作失败,没有查询到订单属于哪个用户");
				return buildReqJsonObject(json);	
			}
			
			model.put("result", "0");
			model.put("description", "查询成功");
			if(order.getOrderNumber()!=null) {
				model.put("orderNumber", order.getOrderNumber());
			}else {
				model.put("orderNumber", "");
			}
			if(user.getUserName()!=null) {
				model.put("userName", user.getUserName());
			}else {
				model.put("userName", "");
			}
			
			model.put("totalPrice", order.getTotalPrice());
			if(order.getTakeCarTime()!=null) {	
				model.put("takeCarTime",sdf.format(order.getTakeCarTime()));
			}else {
				model.put("takeCarTime","");
			}
			if(order.getReturnCarTime()!=null) {
				model.put("returnCarTime", sdf.format(order.getReturnCarTime()));
			}else {
				model.put("returnCarTime", "");
			}
			
			if(order.getReturnCarTime()!=null&&order.getTakeCarTime()!=null) {
				model.put("userCarTime", getDatePoor(order.getReturnCarTime(),order.getTakeCarTime()));
				
			}else {
				model.put("userCarTime","");
			}			
			model.put("takeCarAddress", order.getTakeCarAddress());
			model.put("returnCarAddress", order.getReturnCarAddress());
			model.put("orderStatus", order.getOrderStatus());
			model.put("leasePrice", order.getLeasePrice());
			model.put("insurancePrice", order.getInsurancePrice());
			model.put("otherPrice", order.getOtherPrice());
			model.put("isDamage", order.getIsDamage());
			if(order.getDamagePicture()!=null) {
				model.put("damagePicture", order.getDamagePicture());
			}else {
				model.put("damagePicture", "");
			}
			if(order.getDamageDsp()!=null) {
				model.put("damageDsp", order.getDamageDsp());
			}else {
				model.put("damageDsp", "");
			}
			
			model.put("compensateMoney", order.getCompensateMoney());
		}else {
			model.put("result", "1");
			model.put("description", "未查询到该订单信息,order_uuid不存在");
		}
		return buildReqJsonObject(model);
	}
	
	
	/**
	 * 退款
	 * @return
	 */
	@RequestMapping(value="refund",method=RequestMethod.POST)
	@ResponseBody
	public String refund() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("order_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确或者完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("order_uuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确");
			return buildReqJsonObject(json);
		}
		Orders order = orderService.selectByOrdersUuid(bodyInfo.getString("order_uuid"));
		if(order!=null) {
			order.setOrderStatus(8);//设置订单状态为8已退款
			order.setUpdateAt(new Date());
			int rs = orderService.updateByPrimaryKeySelective(order);
			if(rs==1) {
				json.put("result", "0");
				json.put("description", "退款成功");
			}else {
				json.put("result", "1");
				json.put("description", "退款失败");
			}
		}else {
			json.put("result", "1");
			json.put("description", "参数order_uuid不正确，没有查询到该订单");
		}
		
		return buildReqJsonObject(json);
	}
	
	/**
	 * 取消订单
	 * @return
	 */
	@RequestMapping(value="cancelOrder",method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String cancelOrder() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("order_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确或者完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("order_uuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确");
			return buildReqJsonObject(json);
		}
		Orders order = orderService.selectByOrdersUuid(bodyInfo.getString("order_uuid"));
		if(order!=null) {
			if(order.getTakeCarTime()==null) {
				json.put("result", "1");
				json.put("description", "错误,该订单没有取车时间");
				return buildReqJsonObject(json);
			}
			Users user = userService.selectByUserUuid(order.getUserUuid());
			if(user==null) {
				json.put("result", "1");
				json.put("description", "错误,该订单的用户不存在");
				return buildReqJsonObject(json);
			}
			Date beginTime=new Date();
			Date endTime=order.getTakeCarTime();
			long min=(endTime.getTime()-beginTime.getTime())/1000 / 60 ;
			
			//车辆租赁费
			float lease_price=order.getLeasePrice()==null?0:order.getLeasePrice();
			//押金
			float deposit=order.getDeposit()==null?0:order.getDeposit();
			//罚金
			float penalty=0;
			if(min>=72*60) {
				//不承担罚金
				penalty=0;
			}else if(min < 72*60 && min > 0) {
				//承担租赁费50%的罚金
				DecimalFormat decf=new DecimalFormat("##0.00");
				penalty =Float.parseFloat(decf.format(lease_price*0.5f));
				
			}else{
				//承担租赁费100%的罚金
				penalty=lease_price;
			}
			//退还剩余的钱
			
			float userBalance = user.getBalance()==null?0:user.getBalance();
			user .setBalance(userBalance+(deposit-penalty));
			userService.updateByPrimaryKeySelective(user);
			
			//收支明细
			FinanceRecord finance1=new FinanceRecord();
			finance1.setCreateAt(new Date());
			finance1.setFinanceRecordUuid(UUID.randomUUID().toString().replaceAll("-", ""));
			finance1.setFinanceType(1);//交易类型；1：收入；2：支出
			finance1.setMoney(deposit-penalty);
			finance1.setOrderRecordType(1);//订单记录类型；1：押金；2：租车费用；3：赔偿费用;
			finance1.setOrderUuid(order.getOrderUuid());
			finance1.setRecordType(3);//记录类型；1：充值；2：提现；3：订单；4：平台收费
			finance1.setUserUuid(order.getUserUuid());
			finance1.setPayType(1);//支付方式：1：余额；2：支付宝；3：微信；4：银行卡
			financeRecordService.insertSelective(finance1);
			
			
			
			//平台账户减去
			List<PlatformConfig> platforms = new ArrayList<>();
			platforms = platformConfigService.selectAll();
			if(!platforms.isEmpty()) {
				PlatformConfig platform=platforms.get(0);
				float blan = platform.getBalance();
				platform.setBalance(blan-(deposit-penalty));
				platformConfigService.updateByPrimaryKeySelective(platform);
			}
			//收支明细
			FinanceRecord finance=new FinanceRecord();
			finance.setCreateAt(new Date());
			finance.setFinanceRecordUuid(UUID.randomUUID().toString().replaceAll("-", ""));
			finance.setFinanceType(2);//交易类型；1：收入；2：支出
			finance.setMoney(deposit-penalty);
			finance.setOrderRecordType(1);//订单记录类型；1：押金；2：租车费用；3：赔偿费用;
			finance.setOrderUuid(order.getOrderUuid());
			finance.setRecordType(4);//记录类型；1：充值；2：提现；3：订单；4：平台收费；
			finance.setUserUuid(order.getUserUuid());
			finance.setPayType(1);//支付方式：1：余额；2：支付宝；3：微信；4：银行卡
			financeRecordService.insertSelective(finance);
			
			order.setOrderStatus(7);//状态改为已取消
			orderService.updateByPrimaryKeySelective(order);
			
			json.put("result", "0");
			json.put("description", "取消成功");
			
		}
		return buildReqJsonObject(json);
	}
	
	
	
	
	
	public static String getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
	}
}
