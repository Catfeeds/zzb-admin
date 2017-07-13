package com.hcb.zzb.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.dto.RechargeRecord;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IFinanceRecordService;
import com.hcb.zzb.service.IRechargeRecordService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.Alipay;
import com.hcb.zzb.util.AlipayApiException;
import com.hcb.zzb.util.AlipayConstants;
import com.hcb.zzb.util.AlipayCore;
import com.hcb.zzb.util.AlipaySignature;
import com.hcb.zzb.util.MD5Util;
import com.hcb.zzb.util.SignUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("alipayRecharge")
public class AlipayRechargeController extends BaseControllers{
	@Autowired
	IRechargeRecordService rechargeRecordService;
	@Autowired
	IUsersService usersService;
	@Autowired
	IFinanceRecordService financeRecordService;
	
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String alipayRechargeOrder() {
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		// 登录认证失败
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("totalMoney") == null) {
			json.put("result", "1");
			json.put("description", "请输入充值金额");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("totalMoney"))) {
			json.put("result", "1");
			json.put("description", "请检查您输入的金额是否正确");
			return buildReqJsonObject(json);
		}
		Users user=eplogin;
		if (user == null) {
			json.put("result", "1");
			json.put("description", "操作失败，未查询到此用户");
			return buildReqJsonObject(json);
		}
		//充值的金额
		float total=(float) bodyInfo.getDouble("totalMoney");
		ModelMap model = new ModelMap();
		RechargeRecord recharge=new RechargeRecord();
		recharge.setCreateAt(new Date());
		recharge.setMoney(total);
		recharge.setPayType(1);
		recharge.setRechargeStatus(1);
		recharge.setUserUuid(headInfo.getString("user_uuid"));
		try {
			recharge.setRechargeRecordUuid(MD5Util.md5Digest(bodyInfo.getString("totalMoney")+System.currentTimeMillis()+RandomStringUtils.random(8)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=rechargeRecordService.insertSelective(recharge);
		if(i>0) {
			json.put("result", "0");
			json.put("description", "充值订单生成成功");
		}else {
			json.put("result", "1");
			json.put("description", "充值订单生成失败");
			return buildReqJsonObject(json);
		}
		// 充值uuid
		String rechargeUuid = recharge.getRechargeRecordUuid();
		Map<String, String> bc = new HashMap<String, String>();
		bc.put("\"body\"", "\"至尊宝租车APP余额-充值\"");
		bc.put("\"subject\"", "\"至尊宝租车APP余额-充值\"");
		bc.put("\"out_trade_no\"", "\"" + rechargeUuid + "\"");
		bc.put("\"total_amount\"", "\"" + total + "\"");
		bc.put("\"product_code\"", "\"QUICK_MSECURITY_PAY\"");
		String str = AlipayCore.check(bc);
		String biz_content = "{" + str + "}";
		System.out.println(biz_content);
		
		Date timestamp1 = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tamp = time.format(timestamp1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("app_id", Alipay.APP_ID);
		map.put("method", "alipay.trade.app.pay");
		map.put("charset", "utf-8");
		map.put("sign_type", "RSA");
		map.put("timestamp", tamp);
		map.put("version", "1.0");
		map.put("notify_url", "http://120.27.151.185/zzb-java/confirmNotifyAlipayRecharge");
		map.put("biz_content", biz_content);
		String sing = AlipaySignature.getSignContent(map);
		
		String sign1 = SignUtil.sign(sing, Alipay.PRIVATE_KEY);
		try {
			String sign = URLEncoder.encode(sign1, "UTF-8");
			Map<String, String> biz = new HashMap<String, String>();
			biz.put("\"body\"", "\"至尊宝租车APP余额-充值\"");
			biz.put("\"subject\"", "\"至尊宝租车APP余额-充值\"");
			biz.put("\"out_trade_no\"", "\"" + rechargeUuid + "\"");
			biz.put("\"total_amount\"", "\"" + total + "\"");
			biz.put("\"product_code\"", "\"QUICK_MSECURITY_PAY\"");
			String str1 = AlipayCore.check(biz);
			String biz_content1 = "{" + str1 + "}";
			String bizContent = URLEncoder.encode(biz_content1, "UTF-8");
			System.out.println(bizContent);
			
			Map<String, String> mappp = new HashMap<String, String>();
			String urls = "http://120.27.151.185/zzb-java/confirmNotifyAlipayRecharge";
			String url = URLEncoder.encode(urls, "UTF-8");
			String tamp1 = URLEncoder.encode(tamp, "UTF-8");
			mappp.put("app_id", Alipay.APP_ID);
			mappp.put("method", "alipay.trade.app.pay");
			mappp.put("charset", "utf-8");
			mappp.put("sign_type", "RSA");
			mappp.put("timestamp", tamp1);
			mappp.put("version", "1.0");
			mappp.put("notify_url", url);
			mappp.put("biz_content", bizContent);
			
			String preOrder = AlipaySignature.getSignContent(mappp);
			String aliSignedOrder = preOrder + "&sign=" + sign;
			model.put("aliSignedOrder", aliSignedOrder);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.put("rechargeUuid", recharge.getRechargeRecordUuid());
		model.put("description", "充值下单成功");
		model.put("result", "0");
		return buildReqJsonObject(model);
	}
	
	@RequestMapping(value="result",method=RequestMethod.POST)
	@Transactional
	public String resultAlipayRecharge() {
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		// 登录认证失败
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确");
			return buildReqJsonInteger(2, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("rechargeUuid") == null || bodyInfo.get("alipayResult") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("rechargeUuid")) || "".equals(bodyInfo.get("alipayResult"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		Users user=eplogin;
		if(user == null){
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		RechargeRecord recharge = rechargeRecordService.selectByUuid(bodyInfo.getString("rechargeUuid"));
		if(recharge==null) {
			json.put("result", "1");
			json.put("description", "操作失败，未查询到充值信息");
			return buildReqJsonObject(json);	
		}
		if(!recharge.getUserUuid().equals(user.getUserUuid())) {
			json.put("result", "1");
			json.put("description", "操作失败，此订单不属于该用户");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		if(recharge.getRechargeStatus()==2) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("trade_state", "SUCCESS");
			return buildReqJsonObject(json);
		}else {
			String result = bodyInfo.getString("alipayResult");
			JSONObject map = JSONObject.fromObject(result);
			JSONObject content = null;
			String sign = null;
			String type = null;
			String charset = AlipayConstants.CHARSET_UTF8;
			boolean flag = false;
			String key = map.getString("resultStatus");
			if (key.equals("9000")) {
				JSONObject resultJson = JSONObject.fromObject(map.getJSONObject("result"));
				type = resultJson.getString("sign_type");
				sign = resultJson.getString("sign");
				content = resultJson.getJSONObject("alipay_trade_app_pay_response");
			} else if (key.equals("8000")) {
				json.put("result", "1");
				json.put("description", "正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态");
				return buildReqJsonObject(json);

			} else if (key.equals("4000")) {
				json.put("result", "1");
				json.put("description", "订单支付失败");
				return buildReqJsonObject(json);

			} else if (key.equals("5000")) {
				json.put("result", "1");
				json.put("description", "重复请求");
				return buildReqJsonObject(json);

			} else if (key.equals("6001")) {
				json.put("result", "1");
				json.put("description", "用户中途取消");
				return buildReqJsonObject(json);

			} else if (key.equals("6002")) {
				json.put("result", "1");
				json.put("description", "网络连接出错");
				return buildReqJsonObject(json);

			} else if (key.equals("6004")) {
				json.put("result", "1");
				json.put("description", " 	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态");
				return buildReqJsonObject(json);

			} else if (key.equals("其它")) {
				json.put("result", "1");
				json.put("description", "其它支付错误");
				return buildReqJsonObject(json);

			}
			String contents = content.toString();
			sign = sign.replaceAll(" ", "+");
			try {
				flag = AlipaySignature.rsaCheck(contents, sign, Alipay.PUBLIC_KEY, charset, type);
				
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag) {
				float money=recharge.getMoney();
				String totalMoney=String.valueOf(money);
				String recharge_uuid=recharge.getRechargeRecordUuid();
				String seller_id =Alipay.PARTNER;
				String app_id = Alipay.APP_ID;
				JSONObject jsonObject = JSONObject.fromObject(content);
				String out_trade_no = jsonObject.getString("out_trade_no");
				String total_amount = jsonObject.getString("total_amount");
				String app_idd = jsonObject.getString("app_id");
				String seller_idd = jsonObject.getString("seller_id");
				if (!out_trade_no.equals(recharge_uuid)) {
					json.put("result", "1");
					json.put("description", "操作失败,与商户系统中创建的充值订单号不一致");
					return buildReqJsonObject(json);
				} else if (!total_amount.equals(totalMoney)) {
					json.put("result", "1");
					json.put("description", "操作失败，与商户充值订单创建时的金额不一致");
					return buildReqJsonObject(json);
				} else if (!seller_idd.equals(seller_id)) {
					json.put("result", "1");
					json.put("description", "操作失败，seller_id不一致");
					return buildReqJsonObject(json);
				} else if (!app_idd.equals(app_id)) {
					json.put("result", "1");
					json.put("description", "操作失败，app_id不一致");
					return buildReqJsonObject(json);
				} else {
					synchronized (AlipayRechargeController.class) {
						float totalMoney1 =recharge.getMoney();
						float balance=user.getBalance();
						BigDecimal totalMoney2=new BigDecimal(Float.toString(totalMoney1));
						BigDecimal balance2=new BigDecimal(Float.toString(balance));
						BigDecimal tatal=balance2.add(totalMoney2);
						user.setBalance(tatal.floatValue());
						recharge.setRechargeStatus(2);
						//充值成功后,将数据保存到收支明细表中
						FinanceRecord finance=new FinanceRecord();
						finance.setCreateAt(new Date());
						finance.setFinanceRecordUuid(UUID.randomUUID().toString().replaceAll("-", ""));
						finance.setRechargeRecordUuid(recharge.getRechargeRecordUuid());
						finance.setFinanceType(1);//交易类型 1:收入  2:支出
						finance.setMoney(recharge.getMoney());
						finance.setRecordType(1);//记录类型；1：充值；2：提现；3：订单
						finance.setUserUuid(recharge.getUserUuid());
						int i1=financeRecordService.insertSelective(finance);
						int i2=usersService.updateByPrimaryKeySelective(user);
						int i3=rechargeRecordService.updateByPrimaryKeySelective(recharge);
						if(i1 > 0 && i2 > 0 && i3 > 0) {
							json.put("result", "0");
							json.put("description", "充值成功");
						}else {
							json.put("result", "1");
							json.put("description", "充值失败");
							return buildReqJsonObject(json);
						}
						model.put("trade_state", "SUCCESS");
						model.put("description", "查询成功");
						model.put("result", "0");
						return buildReqJsonObject(model);
					}
				}
			}else {
				json.put("result", "1");
				json.put("description", "操作失败,签名不合法");
				return buildReqJsonObject(json);
			}
		}
	}
}
