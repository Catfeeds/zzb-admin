package com.hcb.zzb.controller;

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
import com.hcb.zzb.service.ICarSevice;

import net.sf.json.JSONObject;
@Controller
public class ModelZoneController extends BaseControllers{
	@Autowired
	ICarSevice carService;
	
	/**
	 * 车型专区
	 * @return
	 */
	@RequestMapping(value="modelZone",method=RequestMethod.POST)
	@ResponseBody
	public String modelZone() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		//JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null||bodyInfo.get("modelCharacter")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		//查询总的记录数
		int count = carService.selectCountByCarModel(bodyInfo.getString("modelCharacter"));
		//通过记录数与pageSize得到总页数
		int total =count%bodyInfo.getInt("pageSize")==0?count/bodyInfo.getInt("pageSize"):count/bodyInfo.getInt("pageSize")+1;
		if(total==0) {
			json.put("result", "1");
			json.put("description", "未查询到,没有该类车型");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.getInt("pageIndex")<=0||bodyInfo.getInt("pageIndex")>total) {
			json.put("result", "1");
			json.put("description", "输入的pageIndex参数错误,或者输入的数字大于总页数");
			return buildReqJsonObject(json);
		}
		//limit分页需要的第一个参数
		int pageIndex=(bodyInfo.getInt("pageIndex")-1)*bodyInfo.getInt("pageSize");
		//把参数封装到map中
		Map<String, Object> map=new HashMap<>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", bodyInfo.getInt("pageSize"));
		map.put("modelCharacter", bodyInfo.getString("modelCharacter"));
		//查询车型专区列表
		List<Car> list=carService.selectByCarModel(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", bodyInfo.getInt("pageIndex"));
			json.put("modelZone", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到,没有该类车型");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value="modelZone/detail",method=RequestMethod.POST)
	@ResponseBody
	public String modelZoneDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		//JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("car_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		Car car=carService.selectByUuid(bodyInfo.getString("car_uuid"));
		if(car!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("car", car);
			
		}else {
			json.put("result", "1");
			json.put("description", "查询失败");
		}
		return buildReqJsonObject(json);
	}
}
