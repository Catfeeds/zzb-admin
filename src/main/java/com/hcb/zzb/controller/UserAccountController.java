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
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IFinanceRecordService;
import com.hcb.zzb.service.IUsersService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="userAccount")
public class UserAccountController extends BaseControllers{
	@Autowired
	IUsersService userService;
	@Autowired
	IFinanceRecordService financeRecordService;
	
	/**
	 * 用户账户列表（分页）
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String findUserAccount() {
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
		if(bodyInfo.get("userName")!=null&&!"".equals(bodyInfo.get("userName"))) {
			map.put("userName", bodyInfo.getString("userName"));
		}
		int count=userService.countUsersByMap(map);
		if(count==0) {
			json.put("result", "1");
			json.put("description", "未查询到数据");
			return buildReqJsonObject(json);
		}
		int total=count % pageSize ==0 ? count/pageSize : count/pageSize + 1;
		if(pageIndex>total) {
			json.put("result", "1");
			json.put("description", "操作失败,请求页数大于总页数");
			return buildReqJsonObject(json);
		}
		List<Users> list=userService.selectUsersByMap(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			json.put("accountList", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 用户交易记录（余额，今日收入，今日支出，交易记录列表）	 ---单个用户的记录列表
	 * @return
	 */
	@RequestMapping(value="userTradingRecord",method=RequestMethod.POST)
	@ResponseBody
	public String findUserAllAccountDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("user_uuid")==null||bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("user_uuid"))||"".equals(bodyInfo.get("pageIndex"))||"".equals(bodyInfo.get("pageSize"))) {
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
		Integer end=pageSize;
	
		int count=financeRecordService.selectCountByUserUuid(bodyInfo.getString("user_uuid"));
		if(count==0) {
			json.put("result", "1");
			json.put("description", "未查询到该用户的交易记录");
			return buildReqJsonObject(json);
		}
		int total=count % pageSize ==0 ? count/pageSize : count/pageSize + 1;
		if(pageIndex>total) {
			json.put("result", "1");
			json.put("description", "操作失败,请求页数大于总页数");
			return buildReqJsonObject(json);
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("pageIndex", start);
		map.put("pageSize", end);
		map.put("user_uuid", bodyInfo.getString("user_uuid"));
		Users user=userService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		if(user==null) {
			json.put("result", "1");
			json.put("description", "操作失败,该用户不存在或者已被删除");
			return buildReqJsonObject(json);
		}
		List<FinanceRecord> list = financeRecordService.selectAllByUserUuid(map);
		if(!list.isEmpty()) {
			//今日收入
			Map<String, Object> map1=new HashMap<>();
			map1.put("financeType", 1);
			map1.put("user_uuid", bodyInfo.getString("user_uuid"));
			List<FinanceRecord>  income=financeRecordService.selectIncomeAndExpenditureByToday(map1);
			//今日支出
			Map<String, Object> map2=new HashMap<>();
			map2.put("financeType", 2);
			map2.put("user_uuid", bodyInfo.getString("user_uuid"));
			List<FinanceRecord>  expenditure=financeRecordService.selectIncomeAndExpenditureByToday(map2);
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			json.put("balance", user.getBalance());
			if(!income.isEmpty()) {
				float totalIncome=0;
				for (FinanceRecord financeRecord : income) {
					totalIncome = totalIncome + financeRecord.getMoney();
				}
				json.put("income", totalIncome);
			}else {
				json.put("income", 0);
			}
			if(!expenditure.isEmpty()) {
				float totalexp=0;
				for (FinanceRecord financeRecord : expenditure) {
					totalexp = totalexp + financeRecord.getMoney();
				}
				json.put("expenditure", totalexp);
			}else {
				json.put("expenditure", 0);
			}
			json.put("userId", user.getId());
			json.put("userUuid", user.getUserUuid());
			json.put("userName", user.getUserName()==null?"":user.getUserName());
			json.put("tradingList", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到该用户的交易记录");
		}
		return buildReqJsonObject(json);
	}
	
	
	/**
	 * 交易记录详情
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String tradingDetail() {
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
	
	
	/**
	 * 用户账户收支明细（用户交易记录列表）  ---所有用户的记录
	 * @return
	 */
	@RequestMapping(value="financeRecord",method=RequestMethod.POST)
	@ResponseBody
	public String findUserFinanceRecord() {
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
		int count=financeRecordService.countSelectByMapLimit(map);
		if(count==0) {
			json.put("result", "1");
			json.put("description", "未查询到数据");
			return buildReqJsonObject(json);
		}
		int total=count % pageSize ==0 ? count/pageSize : count/pageSize + 1;
		if(pageIndex>total) {
			json.put("result", "1");
			json.put("description", "操作失败,请求页数大于总页数");
			return buildReqJsonObject(json);
		}
		List<FinanceRecord> list=financeRecordService.selectByMapLimit(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			json.put("financeList", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		
		return buildReqJsonObject(json);
	}
}
