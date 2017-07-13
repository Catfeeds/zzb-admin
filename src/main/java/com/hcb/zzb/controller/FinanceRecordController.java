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
import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.service.IFinanceRecordService;

import net.sf.json.JSONObject;
@Controller
public class FinanceRecordController extends BaseControllers{
	@Autowired
	IFinanceRecordService financeRecordService;
	
	/**
	 * 收支明细
	 * @return
	 */
	@RequestMapping(value="earningAndExpenditure",method=RequestMethod.POST)
	@ResponseBody
	public String earningAndExpenditure() {
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
		//查询总记录数
		int count = financeRecordService.selectCountByUserUuid(headInfo.getString("user_uuid"));
		//通过总记录数与pageSize得到总页数
		int total=count%bodyInfo.getInt("pageSize")==0?count/bodyInfo.getInt("pageSize"):count/bodyInfo.getInt("pageSize")+1;
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
		//把参数封装到map中
		Map<String, Object> map=new HashMap<>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", bodyInfo.getInt("pageSize"));
		map.put("user_uuid", headInfo.getString("user_uuid"));
		//查询收支明细列表
		List<FinanceRecord> list=financeRecordService.selectAllByUserUuid(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", bodyInfo.getInt("pageIndex"));
			json.put("financeList", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据记录");
		}
		return buildReqJsonObject(json);
	}
}
