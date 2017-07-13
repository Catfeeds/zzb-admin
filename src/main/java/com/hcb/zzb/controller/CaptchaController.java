package com.hcb.zzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseController;
import com.hcb.zzb.service.ICaptchaService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("user")
public class CaptchaController extends BaseController {

	@Autowired
	ICaptchaService captchaServer;
	
	
	@RequestMapping(value = "captcha/{phone}", method = RequestMethod.GET)
	@ResponseBody
	public String sendCaptcha(@PathVariable String phone) {
		JSONObject json = new JSONObject();
		boolean succeed = false;
		try {
			succeed = captchaServer.sendTo(phone);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (succeed) {
			json.put("result", "0");
			json.put("descriptin", "发送验证码成功");
		}else{
			json.put("result", "0");
			json.put("descriptin", "发送验证码失败");
		}
		return buildReqJsonObject(json);
	}
}
