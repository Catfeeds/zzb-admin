package com.hcb.zzb.controller;

import java.util.ArrayList;
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
import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.dto.PlatformConfig;
import com.hcb.zzb.service.IFinanceRecordService;
import com.hcb.zzb.service.IPlatformConfigService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("platform")
public class PlatformConfigController extends BaseControllers{
	@Autowired
	IFinanceRecordService financeRecordService;
	@Autowired
	IPlatformConfigService platformConfigService;
	
	/**
	 * 平台账户收支明细列表
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String platFormList() {
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
		if(pageIndex<=0) {
			json.put("result", "1");
			json.put("description", "请求页不能小于0");
			return buildReqJsonObject(json);
		}
		Integer pageSize=bodyInfo.getInt("pageSize");
		Integer start=(pageIndex-1)*pageSize;
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", pageSize);
		map.put("recordType", 4);
		int count = financeRecordService.countSelectByRecordType(map);
		if(count ==0) {
			json.put("result", "1");
			json.put("description", "未查询到平台账户的收支明细");
			return buildReqJsonObject(json);
		}
		int total=count % pageSize ==0 ? count/pageSize : count/pageSize + 1;
		if(pageIndex>total) {
			json.put("result", "1");
			json.put("description", "操作失败,请求页数大于总页数");
			return buildReqJsonObject(json);
		}
		
		ModelMap model=new ModelMap();
		
		//平台账户收支明细列表
		List<FinanceRecord> financeList=new ArrayList<>();
		financeList=financeRecordService.selectByRecordType(map);
		
		if(!financeList.isEmpty()) {
			
			model.put("result", "0");
			model.put("description", "查询成功");
			model.put("total",total );
			model.put("page", pageIndex);
			//平台账户今日收入
			Map<String, Object> tmap=new HashMap<>();
			tmap.put("recordType", 4);//记录类型；1：充值；2：提现；3：订单；4：平台收费
			tmap.put("financeType", 1);//1收入 2支出
			List<FinanceRecord> todayIncomeList= financeRecordService.selectIncomeAndExpenditureByToday(tmap);
			if(todayIncomeList!=null&&!todayIncomeList.isEmpty()) {
				float todayIncomeTotal=0;
				for (FinanceRecord financeRecord : todayIncomeList) {
					todayIncomeTotal= todayIncomeTotal + financeRecord.getMoney();
				}
				
				model.put("income", (float)(Math.round(todayIncomeTotal*100))/100);
			}else {
				model.put("income", 0);
			}	
			
			//平台账户今日支出
			Map<String, Object> tmap1=new HashMap<>();
			tmap1.put("recordType", 4);//记录类型；1：充值；2：提现；3：订单；4：平台收费
			tmap1.put("financeType", 2);//1收入 2支出
			List<FinanceRecord> todayExpenditureList= financeRecordService.selectIncomeAndExpenditureByToday(tmap1);
			if(todayExpenditureList!=null&&!todayExpenditureList.isEmpty()) {
				float todayExpenditureTotal = 0;
				for (FinanceRecord financeRecord : todayExpenditureList) {
					todayExpenditureTotal = todayExpenditureTotal + financeRecord.getMoney();
				}
				model.put("expenditure", (float)(Math.round(todayExpenditureTotal*100))/100);
			}else {
				model.put("expenditure", 0);
			}
			
			PlatformConfig platform = platformConfigService.selectByPrimaryKey(39);
			if(platform==null) {
				json.put("result", "1");
				json.put("description", "没有平台账号");
				return buildReqJsonObject(json);
			}
			model.put("balance", platform.getBalance()==null?0:platform.getBalance());
			model.put("platformList", financeList);
			
		}else {
			model.put("result", "1");
			model.put("description", "没有查询到平台账户的收支记录");
		}
		return buildReqJsonObject(model);
	}
	
	/**
	 * 查看平台账户收支详情
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String findDetail() {
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
}
