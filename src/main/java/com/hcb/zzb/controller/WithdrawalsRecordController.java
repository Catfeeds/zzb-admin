package com.hcb.zzb.controller;

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
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.dto.WithdrawalsRecord;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.service.IWithdrawalsRecordService;

import net.sf.json.JSONObject;
@Controller
public class WithdrawalsRecordController extends BaseControllers{
	@Autowired
	IWithdrawalsRecordService withdrawalsRecordService;
	@Autowired
	IUsersService usersService;
	
	/**
	 * 提现（申请）
	 * @return
	 */
	@RequestMapping(value="withdrawals",method=RequestMethod.POST)
	@ResponseBody
	public String withdrawals() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null||bodyInfo.get("accountNumber")==null||bodyInfo.get("money")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Users user=usersService.selectByUserUuid(headInfo.getString("user_uuid"));
		if(user!=null) {
			if(bodyInfo.getDouble("money")>user.getBalance()||bodyInfo.getDouble("money")<=0) {
				json.put("result", "1");
				json.put("description", "您输入的金额大于账户余额，或者您输入的金额有误");
				return buildReqJsonObject(json);
			}
		}
		WithdrawalsRecord record=new WithdrawalsRecord();
		record.setApplyUuid(headInfo.getString("user_uuid"));
		record.setAccountNumber(bodyInfo.getString("accountNumber"));
		record.setCreateAt(new Date());
		record.setApplyStatus(1);
		record.setMoney((float)bodyInfo.getDouble("money"));
		record.setWithdrawalsRecordUuid((UUID.randomUUID().toString()).replaceAll("-", ""));
		int i=withdrawalsRecordService.insertSelective(record);
		if(i>0) {
			//申请提现成功后,把用户的余额减去提现的金额
			user.setBalance(user.getBalance()-(float)bodyInfo.getDouble("money"));
			//把提现的金额暂时放到冻结金额
			user.setFrozenBalance(user.getFrozenBalance()+(float)bodyInfo.getDouble("money"));
			usersService.updateByPrimaryKeySelective(user);
			json.put("result", "0");
			json.put("description", "提现申请成功");
		}else {
			json.put("result", "1");
			json.put("description", "提现申请失败");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 提现记录
	 * @return
	 */
	@RequestMapping(value="withdrawals/record",method=RequestMethod.POST)
	@ResponseBody
	public String withdrawalsRecord() {
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
		//查询用户提现记录的总记录数
		int count = withdrawalsRecordService.selectByUserUuidCount(headInfo.getString("user_uuid"));
		//通过总记录数与pageSize得到总页数
		int total =count%bodyInfo.getInt("pageSize")==0?count/bodyInfo.getInt("pageSize"):count/bodyInfo.getInt("pageSize")+1;
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
		map.put("userUuid", headInfo.get("user_uuid"));
		//分页查询用户的提现记录列表
		List<WithdrawalsRecord> list= withdrawalsRecordService.selectByUserUuid(map);
		
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.get("pageIndex"));
			json.put("total", total);
			json.put("withdrawalsRecord_list", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
}
