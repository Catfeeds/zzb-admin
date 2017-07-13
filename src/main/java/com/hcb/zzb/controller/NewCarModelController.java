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
public class NewCarModelController extends BaseControllers{
	@Autowired
	ICarSevice carService;
	/**
	 * 新鲜车型
	 * @return
	 */
	@RequestMapping(value="new_car_model",method=RequestMethod.POST)
	@ResponseBody
	public String findNewCarModel() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		int count=carService.selectByCreateAtCount();
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
		Map<String, Object> map=new HashMap<>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", bodyInfo.get("pageSize"));
		List<Car> list= carService.selectByCreateAt(map);
		
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.get("pageIndex"));
			json.put("total", total);
			json.put("newCarModel", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 新鲜车型详情页
	 * @return
	 */
	@RequestMapping(value="new_car_model/detail",method=RequestMethod.POST)
	@ResponseBody
	public String findNewCarModelDetail() {
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
			json.put("carName", car.getCarName());
			json.put("workingDayPrice", car.getWorkingDayPrice());
			json.put("weekend_price", car.getWeekendPrice());
			json.put("licensePlateNumber", car.getLicensePlateNumber());
			json.put("banner", car.getBanner());
			json.put("address", car.getAddress());
			json.put("orderQuantity", car.getOrderQuantity());
			json.put("collection", car.getCollection());
			json.put("carDsp", car.getCarDsp());
			json.put("manualOrAutomatic", car.getManualOrAutomatic());
			json.put("model_character", car.getModelCharacter());
			json.put("model", car.getModel());
			json.put("brand", car.getBrand());
			json.put("color", car.getColor());
			json.put("shelvesStartTime", car.getShelvesStartTime());
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
}
