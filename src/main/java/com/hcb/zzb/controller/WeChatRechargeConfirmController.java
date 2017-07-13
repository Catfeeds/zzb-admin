package com.hcb.zzb.controller;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.dto.RechargeRecord;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IFinanceRecordService;
import com.hcb.zzb.service.IRechargeRecordService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.Signature;
import com.tencent.common.XMLParser;

@RestController
public class WeChatRechargeConfirmController {
	@Autowired
	IRechargeRecordService rechargeRecordService;
	@Autowired
	IUsersService usersService;
	@Autowired
	IFinanceRecordService financeRecordService;
	
	@RequestMapping(value="comfirmWechatRecharge",method = RequestMethod.POST)
	@Transactional
	public String payNotifyUrl(HttpServletRequest request, HttpServletResponse response)throws Exception {
		BufferedReader reader = null;

        reader = request.getReader();
        String line = "";
        String xmlString = null;
        StringBuffer inputString = new StringBuffer();

        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        xmlString = inputString.toString();
        request.getReader().close();
        System.out.println("----接收到的数据如下：---" + xmlString);
        Map<String, Object> map = new HashMap<String, Object>();
        String result_code = "";
        String return_code = "";
        String out_trade_no = "";
        map = XMLParser.getMapFromXML(xmlString);
        result_code = (String)map.get("result_code");
        out_trade_no = (String)map.get("out_trade_no");
        return_code = (String)map.get("return_code");
        if(checkSign(xmlString)) {
        	RechargeRecord recharge = rechargeRecordService.selectByUuid(out_trade_no);
        	if(recharge.getRechargeStatus()==2) {
        		return returnXML(result_code);
        	}else {
        		Users user=usersService.selectByUserUuid(recharge.getUserUuid());
        		float totalMoney=recharge.getMoney();
        		float balance=user.getBalance();
        		BigDecimal totalMoney1=new BigDecimal(Float.toString(totalMoney));
        		BigDecimal balance1=new BigDecimal(Float.toString(balance));
        		BigDecimal total=balance1.add(totalMoney1);
        		user.setBalance(total.floatValue());
        		recharge.setRechargeStatus(2);
        		
        		//充值成功后添加信息到收支明细表中
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
        		if(i1>0&&i2>0&&i3>0) {
        			
        		}
        		return returnXML(result_code);
        	}
        }else {
        	return returnXML("FAIL");
        }
	}
	
	
    private boolean checkSign(String xmlString) {

        Map<String, Object> map = null;

        try {

            map = XMLParser.getMapFromXML(xmlString);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String signFromAPIResponse = map.get("sign").toString();

        if (signFromAPIResponse == "" || signFromAPIResponse == null) {

            System.out.println("API返回的数据签名数据不存在，有可能被第三方篡改!!!");

            return false;

        }
        System.out.println("服务器回包里面的签名是:" + signFromAPIResponse);

        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名

        map.put("sign", "");

        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较

        String signForAPIResponse = getSign(map);

        if (!signForAPIResponse.equals(signFromAPIResponse)) {

            //签名验不过，表示这个API返回的数据有可能已经被篡改了

            System.out.println("API返回的数据签名验证不通过，有可能被第三方篡改!!! signForAPIResponse生成的签名为" + signForAPIResponse);

            return false;

        }

        System.out.println("恭喜，API返回的数据签名验证通过!!!");

        return true;

    }
    private String returnXML(String return_code) {

        return "<xml><return_code><![CDATA["

                + return_code

                + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

    public String getSign(Map<String, Object> map) {
        SortedMap<String, Object> signParams = new TreeMap<String, Object>();
        for (Map.Entry<String, Object> stringStringEntry : map.entrySet()) {
            signParams.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        signParams.remove("sign");
        String sign = Signature.getSign(signParams);
        return sign;
    }
}
