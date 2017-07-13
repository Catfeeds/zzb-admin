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
import com.hcb.zzb.dto.BankCard;
import com.hcb.zzb.service.IBankCardService;
import com.hcb.zzb.service.ICaptchaService;

import net.sf.json.JSONObject;
@Controller
public class BankCardController extends BaseControllers{
	@Autowired
	IBankCardService bankCardService;
	@Autowired
	ICaptchaService captchaService;
	/**
	 * 我的银行卡
	 * @return
	 */
	@RequestMapping(value="bankCard_List",method=RequestMethod.POST)
	@ResponseBody
	public String myBankCard() {
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
		int count=bankCardService.selectByUserUuidCount(headInfo.getString("user_uuid"));
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
		map.put("userUuid", headInfo.getString("user_uuid"));
		//查询用户银行卡列表
		List<BankCard> list=bankCardService.selectByUserUuid(map);
		//判断银行卡列表是否为空
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.getInt("pageIndex"));
			json.put("total", total);
			json.put("bankCardList", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据记录");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 添加银行卡
	 * @return
	 */
	@RequestMapping(value="addBankCard",method=RequestMethod.POST)
	@ResponseBody
	public String addBankCard() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("cardUserName")==null||
				headInfo.get("user_uuid")==null||bodyInfo.get("phone")==null||
				bodyInfo.get("cardNumber")==null||bodyInfo.get("captcha")==null||
				bodyInfo.get("bank")==null||bodyInfo.get("bankIcon")==null||
				bodyInfo.get("cardType")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Boolean captchaIsRight=false;
		captchaIsRight=captchaService.check(bodyInfo.getString("phone"), bodyInfo.getString("captcha"));
		if(captchaIsRight) {
			BankCard record=new BankCard();
			record.setCreateAt(new Date());
			record.setBankCardUuid((UUID.randomUUID().toString()).replaceAll("-", ""));
			record.setUserUuid(headInfo.getString("user_uuid"));
			record.setCardNumber(bodyInfo.getString("cardNumber"));
			record.setPhone(bodyInfo.getString("phone"));
			record.setCardUserName(bodyInfo.getString("cardUserName"));
			record.setBindingTime(new Date());
			record.setBank(bodyInfo.getString("bank"));
			record.setBankIcon(bodyInfo.getString("bankIcon"));
			record.setCardType(bodyInfo.getString("cardType"));
			int i=bankCardService.insertSelective(record);
			if(i>0) {
				json.put("result", "0");
				json.put("description", "添加银行卡成功");
			}else {
				json.put("result", "1");
				json.put("description", "添加银行卡失败");
			}
		}else {
			json.put("result", "1");
			json.put("description", "手机号或者验证码不正确");
		}
		return buildReqJsonObject(json);
	}
}
