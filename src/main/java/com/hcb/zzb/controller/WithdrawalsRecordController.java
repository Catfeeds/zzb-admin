package com.hcb.zzb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.dto.WithdrawalsRecord;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.service.IWithdrawalsRecordService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="withdrawalsRecord")
public class WithdrawalsRecordController extends BaseControllers{
	@Autowired
	private IWithdrawalsRecordService withdrawalsRecordService;
	@Autowired
	private IUsersService userService;
	
	
	/**
	 * 提现记录列表
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String findWithdrawalsRecord() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确或者完整");
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
		if(bodyInfo.get("userId")!=null&&!"".equals(bodyInfo.get("userId"))) {
			Users user =userService.selectByPrimaryKey(bodyInfo.getInt("userId"));
			if(user!=null) {
				map.put("applyUuuid",user.getUserUuid()==null?"":user.getUserUuid());	
			}else {
				json.put("result", "1");
				json.put("description", "userId不正确,没有查询到该用户");
				return buildReqJsonObject(json);
			}
			
		}
		int count = withdrawalsRecordService.countSelectByMapLimit(map);
		if(count==0) {
			json.put("result", "1");
			json.put("description", "没有查询到数据记录");
			return buildReqJsonObject(json);
		}
		int total=count%pageSize==0?count/pageSize:count/pageSize+1;
		List<WithdrawalsRecord> list = withdrawalsRecordService.selectByMapLimit(map);
		
		List<Map<String, Object>> lists=new ArrayList<>();
		
		if(list!=null&&!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);

			for (WithdrawalsRecord withdrawalsRecord : list) {
				if(withdrawalsRecord.getApplyUuid()==null) {
					json.put("result", "1");
					json.put("description", "错误,该提现记录的申请人不存在");
				}
				Users user =userService.selectByUserUuid(withdrawalsRecord.getApplyUuid());
				
				Map<String, Object> nmap=new HashMap<>();
				nmap.put("withdrawalsRecordUuid", withdrawalsRecord.getWithdrawalsRecordUuid());
				nmap.put("id", withdrawalsRecord.getId());
				nmap.put("userId", user.getId());
				nmap.put("userUuid", user.getUserUuid()==null?"":user.getUserUuid());
				nmap.put("userName", user.getUserName()==null?"":user.getUserName());
				nmap.put("type", withdrawalsRecord.getAccountType()==null?1:withdrawalsRecord.getAccountType());
				nmap.put("accountNumber", withdrawalsRecord.getAccountNumber()==null?"":withdrawalsRecord.getAccountNumber());
				nmap.put("money", withdrawalsRecord.getMoney()==null?0:withdrawalsRecord.getMoney());
				nmap.put("handleUuid", withdrawalsRecord.getHandleUuid()==null?"":withdrawalsRecord.getHandleUuid());
				nmap.put("handleTime", withdrawalsRecord.getHandleTime()==null?"":new SimpleDateFormat().format(withdrawalsRecord.getHandleTime()));
				nmap.put("handleDsp", withdrawalsRecord.getHandleDsp()==null?"":withdrawalsRecord.getHandleDsp());
				nmap.put("status", withdrawalsRecord.getApplyStatus()==null?1:withdrawalsRecord.getApplyStatus());
				lists.add(nmap);
			}
			
			json.put("list", lists);
		}else {
			json.put("result", "1");
			json.put("description", "没有数据");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 提现记录详情
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
		
		if(bodyInfo.get("id")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确或者完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("id"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		WithdrawalsRecord  withdrawalsRecord = withdrawalsRecordService.selectByPrimaryKey(bodyInfo.getInt("id"));
		if(withdrawalsRecord!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("withdrawalsRecord", withdrawalsRecord);
		}else {
			json.put("result", "1");
			json.put("description", "id不正确,未查询到结果");
		}
		return buildReqJsonObject(json);
	}
	
	
	/**
	 * 删除提现记录
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		
		if(bodyInfo.get("id")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确或者完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("id"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		WithdrawalsRecord  withdrawalsRecord = withdrawalsRecordService.selectByPrimaryKey(bodyInfo.getInt("id"));
		if(withdrawalsRecord!=null) {
			withdrawalsRecord.setDeleteAt(new Date());
			int rs = withdrawalsRecordService.updateByPrimaryKeySelective(withdrawalsRecord);
			if(rs==1) {
				json.put("result", "0");
				json.put("description", "删除成功");
			}else {
				json.put("result", "1");
				json.put("description", "删除失败");
			}
		}else {
			json.put("result", "1");
			json.put("description", "id不正确,未查询到结果");
		}
		return buildReqJsonObject(json);
	}
}
