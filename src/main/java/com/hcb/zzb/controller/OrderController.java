package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.util.DateUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("order")
public class OrderController extends BaseControllers {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private ICarSevice CarSevice;
	/*@RequestMapping(value = "searchList", method = RequestMethod.POST)
	@ResponseBody
	//订单列表
	public String orderList(){
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null||bodyinfo.get("order_status")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		//String order_status = bodyinfo.getString("order_status");
		//int orderStatus = Integer.parseInt(order_status);
		int orderStatus=bodyinfo.getInt("order_status");
		List<Orders> orders = new ArrayList<Orders>();
		orders = orderService.selectByOrderStatus(orderStatus);
		List<Map<String, Object>>list=new ArrayList<Map<String, Object>>();
		//判断订单状态：1：未接单；2：进行中；3：已完成；4：已取消 
		for (Orders order : orders) {
			//System.out.println(order.getOrderStatus());
			//System.out.println(order.getUserUuid());
			//System.out.println(headInfo.get("user_uuid").toString());
			//2
			if (order != null && order.getOrderStatus() == 2 && order.getUserUuid()==headInfo.get("user_uuid").toString()) {
				Car car = CarSevice.selectByUuid(order.getCarUuid());
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("order_status", order.getOrderStatus());
				map1.put("car_name", car.getCarName());
				map1.put("take_car_address", order.getTakeCarAddress());
				map1.put("return_car_address", order.getReturnCarAddress());
				map1.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
				map1.put("deposit", order.getDeposit());
				map1.put("total_price", order.getTotalPrice());
				map1.put("is_damage", order.getIsDamage());
				list.add(map1);
				jsonObject.put("result", "0");
				jsonObject.put("description", "成功");
				jsonObject.put("list", list);
			}
			//3
			else if (order != null && order.getOrderStatus() == 3&&order.getUserUuid()==headInfo.get("user_uuid").toString()) {
				Car car = CarSevice.selectByUuid(order.getCarUuid());
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("order_status", order.getOrderStatus());
				map2.put("car_name", car.getCarName());
				map2.put("take_car_address", order.getTakeCarAddress());
				map2.put("return_car_address", order.getReturnCarAddress());
				map2.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
				map2.put("deposit", order.getDeposit());
				map2.put("total_price", order.getTotalPrice());
				map2.put("is_damage", order.getIsDamage());
				list.add(map2);
				jsonObject.put("result", "0");
				jsonObject.put("description", "成功");
				jsonObject.put("list", list);
			} 
			//4
			else if (order != null && order.getOrderStatus() == 4&&order.getUserUuid()==headInfo.get("user_uuid").toString()) {
				Car car = CarSevice.selectByUuid(order.getCarUuid());
				Map<String, Object> map3 = new HashMap<String, Object>();
				map3.put("order_status", order.getOrderStatus());
				map3.put("car_name", car.getCarName());
				map3.put("take_car_address", order.getTakeCarAddress());
				map3.put("return_car_address", order.getReturnCarAddress());
				map3.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
				map3.put("deposit", order.getDeposit());
				map3.put("total_price", order.getTotalPrice());
				map3.put("is_damage", order.getIsDamage());
				list.add(map3);
				jsonObject.put("result", "0");
				jsonObject.put("description", "成功");
				jsonObject.put("list", list);
			}else {
				jsonObject.put("result", "1");
				jsonObject.put("description", "失败");
			}
		}

		return buildReqJsonObject(jsonObject);
	}*/
	//用户订单列表
	@RequestMapping(value = "progressList", method = RequestMethod.POST)
	@ResponseBody
	//1：进行时的订单
	public String orderList1(){
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		List<Orders> orders = new ArrayList<Orders>();
		orders = orderService.selectByUserUuid(headInfo.getString("user_uuid"));
		List<Map<String, Object>>list=new ArrayList<Map<String, Object>>();
		if(orders.size()>0){
			for (Orders order : orders) {
				if (order != null) {
					Car car = CarSevice.selectByUuid(order.getCarUuid());
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("order_status", order.getOrderStatus());
					map1.put("car_name", car.getCarName());
					map1.put("take_car_address", order.getTakeCarAddress());
					map1.put("return_car_address", order.getReturnCarAddress());
					map1.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
					map1.put("deposit", order.getDeposit());
					map1.put("total_price", order.getTotalPrice());
					map1.put("is_damage", order.getIsDamage());
					list.add(map1);
				}
			}
			jsonObject.put("result", "0");
			jsonObject.put("description", "成功");
			jsonObject.put("progresslist", list);
			
		}else{
			jsonObject.put("result", "1");
			jsonObject.put("description", "失败");
		}

		return buildReqJsonObject(jsonObject);
	}
	
	@RequestMapping(value = "finishList", method = RequestMethod.POST)
	@ResponseBody
	//2：完成时的订单
	public String orderList2(){
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		List<Orders> orders = new ArrayList<Orders>();
		orders = orderService.selectByUserUuid1(headInfo.getString("user_uuid"));
		List<Map<String, Object>>list=new ArrayList<Map<String, Object>>();
		if(orders.size()>0){
			for (Orders order : orders) {
				if (order != null) {
					Car car = CarSevice.selectByUuid(order.getCarUuid());
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("order_status", order.getOrderStatus());
					map1.put("car_name", car.getCarName());
					map1.put("take_car_address", order.getTakeCarAddress());
					map1.put("return_car_address", order.getReturnCarAddress());
					map1.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
					map1.put("deposit", order.getDeposit());
					map1.put("total_price", order.getTotalPrice());
					map1.put("is_damage", order.getIsDamage());
					list.add(map1);
				}
			}
			jsonObject.put("result", "0");
			jsonObject.put("description", "成功");
			jsonObject.put("finishlist", list);
			
		}else{
			jsonObject.put("result", "1");
			jsonObject.put("description", "失败");
		}

		return buildReqJsonObject(jsonObject);
	}
	
	@RequestMapping(value = "cancelList", method = RequestMethod.POST)
	@ResponseBody
	//3：取消时订单
	public String orderList3(){
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		List<Orders> orders = new ArrayList<Orders>();
		orders = orderService.selectByUserUuid2(headInfo.getString("user_uuid"));
		List<Map<String, Object>>list=new ArrayList<Map<String, Object>>();
		if(orders.size()>0){
			for (Orders order : orders) {
				if (order != null) {
					Car car = CarSevice.selectByUuid(order.getCarUuid());
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("order_status", order.getOrderStatus());
					map1.put("car_name", car.getCarName());
					map1.put("take_car_address", order.getTakeCarAddress());
					map1.put("return_car_address", order.getReturnCarAddress());
					map1.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
					map1.put("deposit", order.getDeposit());
					map1.put("total_price", order.getTotalPrice());
					map1.put("is_damage", order.getIsDamage());
					list.add(map1);
				}
			}
			jsonObject.put("result", "0");
			jsonObject.put("description", "成功");
			jsonObject.put("cancellist", list);
			
		}else{
			jsonObject.put("result", "1");
			jsonObject.put("description", "失败");
		}

		return buildReqJsonObject(jsonObject);
	}
	//车主订单列表
		@RequestMapping(value = "ownerprogressList", method = RequestMethod.POST)
		@ResponseBody
		//1：进行时的订单
		public String orderListowner1(){
			JSONObject jsonObject = new JSONObject();
			if (sign == 1 || sign == 2) {
				jsonObject.put("result", "1");
				jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
				return buildReqJsonInteger(1, jsonObject);
			}
			JSONObject headInfo = JSONObject.fromObject(headString);
			JSONObject bodyinfo = JSONObject.fromObject(bodyString);
			if(headInfo.get("user_uuid")==null) {
				jsonObject.put("result", "1");
				jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			}
			List<Orders> orders = new ArrayList<Orders>();
			orders = orderService.selectByOwnerUuid(headInfo.getString("user_uuid"));
			List<Map<String, Object>>list=new ArrayList<Map<String, Object>>();
			if(orders.size()>0){
				for (Orders order : orders) {
					if (order != null) {
						Car car = CarSevice.selectByUuid(order.getCarUuid());
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("order_status", order.getOrderStatus());
						map1.put("car_name", car.getCarName());
						map1.put("take_car_address", order.getTakeCarAddress());
						map1.put("return_car_address", order.getReturnCarAddress());
						map1.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
						map1.put("deposit", order.getDeposit());
						map1.put("total_price", order.getTotalPrice());
						map1.put("is_damage", order.getIsDamage());
						list.add(map1);
					}
				}
				jsonObject.put("result", "0");
				jsonObject.put("description", "成功");
				jsonObject.put("ownerprogresslist", list);
				
			}else{
				jsonObject.put("result", "1");
				jsonObject.put("description", "失败");
			}

			return buildReqJsonObject(jsonObject);
		}
		@RequestMapping(value = "ownerfinishList", method = RequestMethod.POST)
		@ResponseBody
		//2：完成时的订单
		public String ownerorderList2(){
			JSONObject jsonObject = new JSONObject();
			if (sign == 1 || sign == 2) {
				jsonObject.put("result", "1");
				jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
				return buildReqJsonInteger(1, jsonObject);
			}
			JSONObject headInfo = JSONObject.fromObject(headString);
			JSONObject bodyinfo = JSONObject.fromObject(bodyString);
			if(headInfo.get("user_uuid")==null) {
				jsonObject.put("result", "1");
				jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			}
			List<Orders> orders = new ArrayList<Orders>();
			orders = orderService.selectByOwnerUuid2(headInfo.getString("user_uuid"));
			List<Map<String, Object>>list=new ArrayList<Map<String, Object>>();
			if(orders.size()>0){
				for (Orders order : orders) {
					if (order != null) {
						Car car = CarSevice.selectByUuid(order.getCarUuid());
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("order_status", order.getOrderStatus());
						map1.put("car_name", car.getCarName());
						map1.put("take_car_address", order.getTakeCarAddress());
						map1.put("return_car_address", order.getReturnCarAddress());
						map1.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
						map1.put("deposit", order.getDeposit());
						map1.put("total_price", order.getTotalPrice());
						map1.put("is_damage", order.getIsDamage());
						list.add(map1);
					}
				}
				jsonObject.put("result", "0");
				jsonObject.put("description", "成功");
				jsonObject.put("ownerfinishlist", list);
				
			}else{
				jsonObject.put("result", "1");
				jsonObject.put("description", "失败");
			}

			return buildReqJsonObject(jsonObject);
		}
		@RequestMapping(value = "ownercancelList", method = RequestMethod.POST)
		@ResponseBody
		//3：取消时的订单
		public String ownerorderList3(){
			JSONObject jsonObject = new JSONObject();
			if (sign == 1 || sign == 2) {
				jsonObject.put("result", "1");
				jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
				return buildReqJsonInteger(1, jsonObject);
			}
			JSONObject headInfo = JSONObject.fromObject(headString);
			JSONObject bodyinfo = JSONObject.fromObject(bodyString);
			if(headInfo.get("user_uuid")==null) {
				jsonObject.put("result", "1");
				jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			}
			List<Orders> orders = new ArrayList<Orders>();
			orders = orderService.selectByOwnerUuid3(headInfo.getString("user_uuid"));
			List<Map<String, Object>>list=new ArrayList<Map<String, Object>>();
			if(orders.size()>0){
				for (Orders order : orders) {
					if (order != null) {
						Car car = CarSevice.selectByUuid(order.getCarUuid());
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("order_status", order.getOrderStatus());
						map1.put("car_name", car.getCarName());
						map1.put("take_car_address", order.getTakeCarAddress());
						map1.put("return_car_address", order.getReturnCarAddress());
						map1.put("daytime",DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime()));
						map1.put("deposit", order.getDeposit());
						map1.put("total_price", order.getTotalPrice());
						map1.put("is_damage", order.getIsDamage());
						list.add(map1);
					}
				}
				jsonObject.put("result", "0");
				jsonObject.put("description", "成功");
				jsonObject.put("ownercancellist", list);
				
			}else{
				jsonObject.put("result", "1");
				jsonObject.put("description", "失败");
			}

			return buildReqJsonObject(jsonObject);
		}
	//订单详情
	@RequestMapping(value="searchList/detail",method=RequestMethod.POST)
	@ResponseBody
	public String orderDetail(){
		JSONObject jsonObject=new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		
		JSONObject bodyinfo=JSONObject.fromObject(bodyString);
		JSONObject headInfo = JSONObject.fromObject(headString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		Orders orders=orderService.selectByOrdersUuid(bodyinfo.get("order_uuid").toString());
		Car car = CarSevice.selectByUuid(orders.getCarUuid());
		if(orders!=null&&car!=null){
			jsonObject.put("result", "0");
			jsonObject.put("description", "成功");
			jsonObject.put("order_number", orders.getOrderNumber());
			jsonObject.put("car_name", car.getCarName());
			jsonObject.put("take_car_address", orders.getTakeCarAddress());
			jsonObject.put("return_car_address", orders.getReturnCarAddress());
			jsonObject.put("daytime", DateUtil.countDays(orders.getReturnCarTime(), orders.getTakeCarTime()));
			jsonObject.put("deposit", orders.getDeposit());
			jsonObject.put("is_damage", orders.getIsDamage());
			jsonObject.put("lease_price", orders.getLeasePrice());
			jsonObject.put("insurance_price", orders.getInsurancePrice());
			jsonObject.put("other_price", orders.getOtherPrice());
			jsonObject.put("total_price", orders.getTotalPrice());
			jsonObject.put("order_status", orders.getOrderStatus());
		}else{
			jsonObject.put("result", "1");
			jsonObject.put("description", "失败");
		}
		return buildReqJsonObject(jsonObject);
	}
}
