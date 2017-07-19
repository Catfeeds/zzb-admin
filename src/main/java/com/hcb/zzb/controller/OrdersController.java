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
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.ITicketService;
import com.hcb.zzb.service.IUsersService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="order")
public class OrdersController extends BaseControllers{
	@Autowired
	IOrderService orderService;
	@Autowired
	IUsersService userService;
	@Autowired
	ITicketService ticketService;
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
		
		int count=orderService.countselectByMapLimit(map);
		if(count==0) {
			json.put("result", "1");
			json.put("description", "没有查询到数据记录");
			return buildReqJsonObject(json);
		}
		int total=count%pageSize==0?count/pageSize:count/pageSize+1;
		List<Orders> list=orderService.selectByMapLimit(map);
		List<Orders> orderList=new ArrayList<Orders>();
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			
			for (Orders orders : list) {	
				if(orders.getUserUuid()==null) {
					json.put("result", "1");
					json.put("description", "错误,没有查询到该订单的用户");
					return buildReqJsonObject(json);
				}
				Users user=userService.selectByUserUuid(orders.getUserUuid());
				if(user==null) {
					json.put("result", "1");
					json.put("description", "错误,没有查询到该订单的用户");
					return buildReqJsonObject(json);
				}
				orders.setUserName(user.getUserName());
				if(orders.getReturnCarTime()!=null&&orders.getTakeCarTime()!=null) {
					orders.setUseCarTime(getDatePoor(orders.getReturnCarTime(),orders.getTakeCarTime()));
				}else {
					orders.setUseCarTime(null);
				}	
				orderList.add(orders);
			}
			json.put("orderList", orderList);
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
			model.put("orderNumber", order.getOrderNumber());
			model.put("userName", user.getUserName());
			model.put("totalPrice", order.getTotalPrice());
			model.put("takeCarTime", order.getTakeCarTime());
			model.put("returnCarTime", order.getReturnCarTime());
			if(order.getReturnCarTime()!=null&&order.getTakeCarTime()!=null) {
				model.put("userCarTime", getDatePoor(order.getReturnCarTime(),order.getTakeCarTime()));
			}else {
				model.put("userCarTime",null);
			}
			model.put("takeCarAddress", order.getTakeCarAddress());
			model.put("returnCarAddress", order.getReturnCarAddress());
			model.put("orderStatus", order.getOrderStatus());
			model.put("leasePrice", order.getLeasePrice());
			model.put("insurancePrice", order.getInsurancePrice());
			model.put("otherPrice", order.getOtherPrice());
			model.put("isDamage", order.getIsDamage());
			model.put("damagePicture", order.getDamagePicture());
			model.put("damageDsp", order.getDamageDsp());
			model.put("compensateMoney", order.getCompensateMoney());
		}else {
			model.put("result", "1");
			model.put("description", "未查询到该订单信息,order_uuid不存在");
		}
		return buildReqJsonObject(model);
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
