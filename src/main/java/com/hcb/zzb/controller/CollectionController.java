package com.hcb.zzb.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.Collection;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.ICollectionService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("collection")
public class CollectionController extends BaseControllers{
	@Autowired
	ICollectionService collectionService;
	@Autowired
	ICarSevice carService;
	
	@RequestMapping(value="collect_list",method=RequestMethod.POST)
	@ResponseBody
	public String myCollection() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageSize") == null|| bodyInfo.get("pageIndex")==null) { 
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		int count =collectionService.selectByUserUuidCount(headInfo.getString("user_uuid"));
		int total = count%bodyInfo.getInt("pageSize")==0?count/bodyInfo.getInt("pageSize"):count/bodyInfo.getInt("pageSize")+1;
		if(total==0) {
			json.put("result", "1");
			json.put("description", "未查询到数据记录");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.getInt("pageIndex")<=0||bodyInfo.getInt("pageIndex")>total) {
			json.put("result", "1");
			json.put("description", "输入的pageIndex参数错误,或者输入的数字大于总页数");
			return buildReqJsonObject(json);
		}
		int pageIndex=(bodyInfo.getInt("pageIndex")-1)*bodyInfo.getInt("pageSize");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", bodyInfo.get("pageSize"));
		map.put("user_uuid", headInfo.getString("user_uuid"));
		
		List<Collection> collectList=collectionService.selectCollectionByUserUuid(map);
		//创建一个空的泛型是car的list,存放用户收藏的车辆
		List<Car> carList=new ArrayList<Car>();
		if(!collectList.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.get("pageIndex"));
			json.put("total", total);
			//循环收藏列表
			for (Collection collection : collectList) {
				System.out.println("id:"+collection.getId()+"--"+"createAt:"+collection.getCreateAt()+"--"+"uuid:"+collection.getCollectionUuid()+"--"+"carUuid:"+collection.getCarUuid()+"--"+"userUuid:"+collection.getUserUuid());
				//通过收藏列表的carUuid查询车辆
				Car car=carService.selectByUuid(collection.getCarUuid());
				//放入到car列表中
				carList.add(car);
			}
			json.put("collectList", carList);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value="insert",method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String addCollection() {
		JSONObject json = new JSONObject();
		if(sign==1){
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		// 登录认证失败
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if(bodyInfo.get("car_uuid")==null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("car_uuid").equals("")) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		Users user = eplogin;
		if(user == null){
			json.put("result", "1");
			json.put("description", "操作失败，未查询到此用户");
			return buildReqJsonObject(json);
		}
		Car car=carService.selectByUuid(bodyInfo.getString("car_uuid"));
		if(car==null) {
			json.put("result", "1");
			json.put("description", "操作失败，car_uuid没有找到这辆车");
		}
		Collection collection=new Collection();
		collection.setCarUuid(bodyInfo.getString("car_uuid"));
		collection.setCollectionUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		collection.setCreateAt(new Date());
		collection.setUserUuid(headInfo.getString("user_uuid"));
		int i=collectionService.insertSelective(collection);
		System.out.println("i="+i);
		if(i > 0) {	
			int collectionCount=car.getCollection();
			car.setCollection(collectionCount+1);
			int status=carService.updateByPrimaryKeySelective(car);
			if(status>0) {
				json.put("result", "0");
				json.put("description", "收藏车辆成功");
			}else {
				json.put("result", "1");
				json.put("description", "收藏车辆失败,车辆收藏量没有增加");
			}
			car.setCollection(1);
		}else {
			json.put("result", "1");
			json.put("description", "收藏车辆失败");
		}
		return buildReqJsonObject(json);
	}
}
