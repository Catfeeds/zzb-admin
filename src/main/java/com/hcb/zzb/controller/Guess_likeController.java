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
import com.hcb.zzb.dto.BrowseLog;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.service.IBrowseLogService;
import com.hcb.zzb.service.ICarSevice;

import net.sf.json.JSONObject;

@Controller
public class Guess_likeController extends BaseControllers{
	@Autowired
	IBrowseLogService browseLogService;
	@Autowired
	ICarSevice carService;
	/**
	 * 猜你喜欢
	 * @return
	 */
	@RequestMapping(value="guess_like",method=RequestMethod.POST)
	@ResponseBody
	public String guess_like() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null||bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		//查询猜你喜欢的总记录数
		int count = browseLogService.selectGuessYouLikeCount(headInfo.getString("user_uuid"));
		//通过总记录数得到总页数
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
		//通过前端传过来的当前页数,得到分页需要的第一个参数
		int pageIndex=(bodyInfo.getInt("pageIndex")-1)*bodyInfo.getInt("pageSize");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("user_uuid", headInfo.getString("user_uuid"));
		map.put("pageIndex", pageIndex);
		map.put("pageSize", bodyInfo.getInt("pageSize"));
		//查询猜你喜欢列表
		List<BrowseLog> list=browseLogService.selectByGuessYouLike(map);
		//创建一个空的list,泛型是car
		List<Car> carList=new ArrayList<>();
		if(!list.isEmpty()) {
			//循环猜你喜欢列表
			for (BrowseLog browseLog : list) {
				carList.add(carService.selectByUuid(browseLog.getCarUuid()));
			}
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.get("pageIndex"));
			json.put("total", total);
			json.put("guessLike", carList);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据记录");
		}
		
		return buildReqJsonObject(json);
	}
}
