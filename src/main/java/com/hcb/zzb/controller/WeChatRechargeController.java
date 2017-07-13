package com.hcb.zzb.controller;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.dto.RechargeRecord;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.dto.WxOrder;
import com.hcb.zzb.service.IFinanceRecordService;
import com.hcb.zzb.service.IRechargeRecordService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.HttpsRequest;
import com.hcb.zzb.util.MD5Util;
import com.hcb.zzb.util.RandomStringGenerator;
import com.hcb.zzb.util.Signature;
import com.hcb.zzb.util.WeChat;
import com.hcb.zzb.util.XMLParser;

import net.sf.json.JSONObject;
@RestController
@RequestMapping("wxrecharge")
public class WeChatRechargeController extends BaseControllers{
	@Autowired
	IRechargeRecordService rechargeRecordService;
	@Autowired
	IFinanceRecordService financeRecordService;
	@Autowired
	IUsersService usersService;
	/**
	 * 微信充值
	 * @return
	 */
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String weChatRecharge() {
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
		Users user = eplogin;
		if(user == null){
			json.put("result", "1");
			json.put("description", "操作失败，未查询到此用户");
			return buildReqJsonObject(json);
		}
		
		float total = (float)bodyInfo.getDouble("totalMoney");
		ModelMap model = new ModelMap();
		RechargeRecord recharge=new RechargeRecord();
		WxOrder wx=new WxOrder();
		recharge.setCreateAt(new Date());
		recharge.setMoney(total);
		recharge.setPayType(2);
		recharge.setUserUuid(headInfo.getString("user_uuid"));
		recharge.setRechargeStatus(1);
		
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
			
			Map<String, Object> map = new HashMap<String, Object>();
			//生成随机数
			String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
			// 充值uuid
			String rechargeUuid = recharge.getRechargeRecordUuid();
			//充值金额
			wx.setNoncestr(nonce_str);
			map.put("appid", WeChat.APP_ID);
			map.put("body", "至尊宝租车-余额充值");
			map.put("mch_id", WeChat.MCH_ID);
			map.put("nonce_str", nonce_str);
			map.put("notify_url", "http://120.27.151.185/zzb_java/comfirmWechatRecharge");
			map.put("out_trade_no", rechargeUuid);
			map.put("spbill_create_ip", "14.23.150.211");
			map.put("total_fee", total);
			map.put("trade_type", "APP");
			
			//签名
			String sign = Signature.getSign(map);
			
			StringBuilder sb = new StringBuilder();
			sb.append("<xml>");
			sb.append("<appid>"+WeChat.APP_ID+"</appid>");
			sb.append("<body>至尊宝租车-余额充值</body>");
			sb.append("<mch_id>"+WeChat.MCH_ID+"</mch_id>");
			sb.append("<nonce_str>" + nonce_str + "</nonce_str>");
			sb.append("<notify_url>http://localhost:8080/zzb_java/comfirmWechatRecharge</notify_url>");
			sb.append("<out_trade_no>" + rechargeUuid + "</out_trade_no>");
			sb.append("<spbill_create_ip>14.23.150.211</spbill_create_ip>");
			sb.append("<total_fee>" + total + "</total_fee>");
			sb.append("<trade_type>APP</trade_type>");
			sb.append("<sign>" + sign + "</sign>");
			sb.append("</xml>");
			String objXml = sb.toString();
			String mapFromXml = new HttpsRequest().testPost(WeChat.ORDER_URL, objXml);
			System.out.println(mapFromXml);
			try {
				Map<String, Object> flag = XMLParser.getMapFromXML(mapFromXml);
				System.out.println(flag);
				Iterator it = flag.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					Object key = entry.getKey();
					Object value = entry.getValue();
					if (key.equals("prepay_id")) {
						Map<String, Object> mop = new HashMap<String, Object>();
						Date timestamp1 = new Date();
						SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String tamp = time.format(timestamp1);
						Long timestamp2;
						try {
							timestamp2 = time.parse(tamp).getTime() / 1000;
							mop.put("timestamp", timestamp2);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						String timestamp = "" + System.currentTimeMillis() / 1000;
						mop.put("appid", WeChat.APP_ID);
						mop.put("noncestr", nonce_str);
						mop.put("partnerid", WeChat.MCH_ID);
						mop.put("package", "Sign=WXPay");
						mop.put("prepayid", (String) value);
						mop.put("timestamp", timestamp);
						System.out.println(mop.toString());
						String sign1 = Signature.getSign(mop);
						wx.setSign(sign1);
						wx.setTimestamp(timestamp);
						wx.setPrepayId((String) value);
						wx.setAppId(WeChat.APP_ID);
						wx.setPartnerId(WeChat.MCH_ID);
						wx.setPkg("Sign=WXPay");
					}

					// System.out.println("key=" + key + " value=" + value);
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			json.put("result", "1");
			json.put("description", "充值订单生成失败");
			return buildReqJsonObject(json);
		}
		
		model.put("rechargeUuid", recharge.getRechargeRecordUuid());
		model.put("wxOrder", wx);
		model.put("description", "查询成功");
		model.put("result", "0");
		return buildReqJsonObject(model);
	}
	/**
	 * 微信充值结果确认
	 * @return
	 */
	@RequestMapping(value="result",method = RequestMethod.POST)
	@Transactional
	public String resultWxrecharge() {
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
		if(bodyInfo.get("rechargeUuid")==null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("rechargeUuid"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		Users user = eplogin;
		if(user == null){
			json.put("result", "1");
			json.put("description", "操作失败，未查询到此用户");
			return buildReqJsonObject(json);
		}
		RechargeRecord recharge = rechargeRecordService.selectByUuid(bodyInfo.getString("rechargeUuid"));
		if(recharge == null){
			json.put("result", "1");
			json.put("description", "操作失败，未查询到充值信息");
			return buildReqJsonObject(json);	
		}
		if(!recharge.getUserUuid().equals(headInfo.getString("user_uuid"))) {
			json.put("result", "1");
			json.put("description", "操作失败，此订单不属于该用户");
			return buildReqJsonObject(json);
		}
		if(recharge.getRechargeStatus()==2) {
			model.put("trade_state", "SUCCESS");
			model.put("description", "查询成功");
			model.put("result", "0");
			return buildReqJsonObject(model);
		}else {
			synchronized (WeChatRechargeController.class){
				String rechargeUuid = recharge.getRechargeRecordUuid();
				Map<String, Object> map = new HashMap<String, Object>();
				String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
				map.put("appid", WeChat.APP_ID);
				map.put("mch_id", WeChat.MCH_ID);
				map.put("nonce_str", nonce_str);
				map.put("out_trade_no", rechargeUuid);
				String sign = Signature.getSign(map);
				
				StringBuilder sb = new StringBuilder();
				sb.append("<xml>");
				sb.append("<appid>"+WeChat.APP_ID+"</appid>");
				sb.append("<mch_id>"+WeChat.MCH_ID+"</mch_id>");
				sb.append("<out_trade_no>" + rechargeUuid + "</out_trade_no>");
				sb.append("<nonce_str>" + nonce_str + "</nonce_str>");
				sb.append("<sign>" + sign + "</sign>");
				sb.append("</xml>");
				String objXml = sb.toString();
				String mapFromXml = new HttpsRequest().testPost(WeChat.COMFIRM_URL, objXml);
				System.out.println(mapFromXml);
				try {
					Map<String, Object> flag = XMLParser.getMapFromXML(mapFromXml);
					Iterator it = flag.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry entry = (Map.Entry) it.next();
						Object key = entry.getKey();
						Object value = entry.getValue();
						if (key.equals("trade_state")) {
							model.put("trade_state", value);
							if (value.equals("SUCCESS")) {
								float totalMoney=recharge.getMoney();
								float balance=user.getBalance();
								BigDecimal totalMoney1=new BigDecimal(Float.toString(totalMoney));
								BigDecimal balance1=new BigDecimal(Float.toString(balance));
								BigDecimal total=balance1.add(totalMoney1);
								user.setBalance(total.floatValue());
								recharge.setRechargeStatus(2);
								
								//余额明细（收支明细表）
								FinanceRecord finance=new FinanceRecord();
				        		finance.setCreateAt(new Date());
				        		finance.setRechargeRecordUuid(recharge.getRechargeRecordUuid());
				        		finance.setFinanceRecordUuid(UUID.randomUUID().toString().replaceAll("-", ""));
				        		finance.setRecordType(1);//记录类型；1：充值；2：提现；3：订单   
				        		finance.setMoney(recharge.getMoney());
				        		finance.setFinanceType(1);//交易类型 1:收入  2:支出
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
							}
						}else if (key.equals("err_code_des")) {
							model.put("err_code_des", value);
						}
						
					}
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.put("description", "查询成功");
				model.put("result", "0");
				return buildReqJsonObject(model);
			}
		}
		
		
	}
}
