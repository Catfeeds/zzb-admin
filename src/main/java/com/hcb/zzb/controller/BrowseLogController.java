package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.BrowseLog;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.service.IBrowseLogService;
import com.hcb.zzb.service.ICarSevice;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("browse")
public class BrowseLogController extends BaseControllers{
	@Autowired
	IBrowseLogService browseLogService;
	@Autowired
	ICarSevice carService;
	/**
	 * 添加我的浏览
	 * @return
	 */
	@RequestMapping(value="insert",method=RequestMethod.POST)
	@ResponseBody
	public String insertBrowse() {
		JSONObject json = new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		BrowseLog record=new BrowseLog();
		record.setBrowseLogUuid((UUID.randomUUID().toString()).replaceAll("-", ""));
		record.setCarUuid(bodyInfo.getString("car_uuid"));
		record.setUserUuid(headInfo.getString("user_uuid"));
		record.setCreateAt(new Date());
		int i=browseLogService.insertSelectiveBrowseLog(record);
		if(i>0) {
			json.put("result", "0");
			json.put("description", "增加成功");
		}else {
			json.put("result", "1");
			json.put("description", "增加失败");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 我的足迹（最近浏览）
	 * @return
	 */
	@RequestMapping(value="browselist")
	@ResponseBody
	public String selectAllByUserUuid() {
		  JSONObject json = new JSONObject();
		  if(sign==1||sign==2) {
				json.put("result", "1");
				json.put("description", "请检查参数格式是否正确或者参数是否完整");
				return buildReqJsonInteger(1, json);
			}
		  JSONObject headInfo=JSONObject.fromObject(headString);
		  JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		  if(headInfo.getString("user_uuid")==null||bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			  json.put("result", "1");
			  json.put("description", "请检查参数格式是否正确或者参数是否完整");
			  return buildReqJsonObject(json);
		  }
		  int count =browseLogService.selectByUserUuidCount(headInfo.getString("user_uuid"));
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
		  map.put("user_uuid", headInfo.getString("user_uuid"));
		  map.put("pageIndex", pageIndex);
		  map.put("pageSize", bodyInfo.get("pageSize"));
		  
		  List<BrowseLog>  list=browseLogService.selectAllByUserUuid(map);
		  List<Car> carList=new ArrayList<Car>();
		  if(!list.isEmpty()) {
			  json.put("result", "0");
			  json.put("description", "查询成功");
			  json.put("page", bodyInfo.get("pageIndex"));
			  json.put("total", total);
			  for (BrowseLog browseLog : list) {
				Car car=carService.selectByUuid(browseLog.getCarUuid());
				carList.add(car);
			}
			  json.put("browseList", carList);
		  }else {
			  json.put("result", "1");
			  json.put("description", "未查询到数据");
		  }
		  return buildReqJsonObject(json);
	}

}
