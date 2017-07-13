package com.hcb.zzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.bean.base.BaseResp;
import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.controller.base.PublicController;
import com.hcb.zzb.dao.interfaceClass.UsersMapper;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.ICaptchaService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.HttpGet;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("user")
public class LoginController extends BaseControllers {
	
	@Value("${wechat.appid}")
	 private String appid;
	@Value("${wechat.appsecret}")
	 private String appsecret;
	 
	@Autowired
	ICaptchaService captchaService;
	@Autowired
	IUsersService usersService;
	@Autowired
	UsersMapper usersMapper;

	 @Autowired
     PublicController publicController;
	@RequestMapping(value = "phone/login", method = RequestMethod.POST)
	@ResponseBody
	public String phoneLogin() {
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("phone") == null || bodyInfo.get("captcha") == null) {
			json.put("result", 1);
			json.put("description", "请输入手机号和验证码");
			return buildReqJsonObject(json);
		}
		BaseResp resp = null;
		boolean captchaGood = false;
		try {
			captchaGood = captchaService.check(bodyInfo.getString("phone"), bodyInfo.getString("captcha"));
		//System.out.println(captchaGood);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (captchaGood) {
			// json.put("result", 0);
			// json.put("description", "登录成功");
			// Users users = new Users();
			 /*
			  * Users u = new Users();
             u.setPhone(bodyInfo.getString("phone"));
             Users user = usersMapper.selectOne(u);
             if(user == null){
                   resp = userService.register(bodyInfo.getString("phone"), bodyInfo.getString("password"));
             }else {
                   resp = userService.login(user,bodyInfo.getString("phone"), bodyInfo.getString("password"));
             }
             */
			
			String phone = bodyInfo.getString("phone");
			Users users = usersMapper.selectByPhone(phone);
			if (users == null) {
				resp = usersService.register(bodyInfo.getString("phone"));

			} else {
				resp = usersService.login(users, bodyInfo.getString("phone"));
			}

		} else {
			new BaseResp().setResult("1").setDescription("验证码错误");
		}
		return buildReqJsonObject(resp);
	}
	
	//微信登录
	@RequestMapping(value = "wechat/login" , method =  RequestMethod.POST)
	public String wechatLogin(){
        JSONObject json = new JSONObject();
        if(!publicController.commonMethod1().equals("1"))return publicController.commonMethod1();
        JSONObject bodyInfo = JSONObject.fromObject(bodyString);
        if (bodyInfo.get("wechatResult") == null)  {
              json.put("result", "1");
              json.put("description", "请检查参数是否完整");
              return buildReqJsonObject(json);
        }
        String code = bodyInfo.getString("wechatResult");
        String str = HttpGet.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token","appid=wx09724d9c895080b3&secret=8d1b2916b9bd4e071d4f232d244c93e1&code="+code+"&grant_type=authorization_code");
        JSONObject map1 = JSONObject.fromObject(str);
        String openid = null;
        String access_token = null;
        openid = map1.getString("openid");
        access_token = map1.getString("access_token");
        String str1 = HttpGet.sendGet("https://api.weixin.qq.com/sns/userinfo", "access_token="+access_token+"&openid="+openid+"");
        JSONObject map2 = JSONObject.fromObject(str1);
        //微信登录返回参数
        String wxOpenId = map2.getString("openid");
        String userName=map2.getString("nickname");
        //String userPhone = map2.getString("userPhone");
        String avater = map2.getString("headimgurl");
        //String idNumber = map2.getString("idNumber");
        //String driving = map2.getString("country");
        String sex=map2.getString("sex");
        int gender =Integer.parseInt(sex);
        ModelMap model = publicController.update(wxOpenId,avater,userName,gender);
        return buildReqJsonObject(model);
  }
	//qq登录 
	@RequestMapping(value = "qq/login" , method = RequestMethod.POST)
    public String qqLogin(){
          JSONObject json = new JSONObject();
          if(!publicController.commonMethod1().equals("1"))return publicController.commonMethod1();
          JSONObject bodyInfo = JSONObject.fromObject(bodyString);
          if (bodyInfo.get("openid") == null || bodyInfo.get("nickname") == null ||
                  bodyInfo.get("headimgurl") == null) {
                json.put("result", "1");
                json.put("description", "请检查参数是否完整");
                return buildReqJsonObject(json);
          }
          ModelMap model = publicController.updateqq(bodyInfo.getString("openid"),bodyInfo.getString("nickname"),
                  bodyInfo.getString("headimgurl"));
          return buildReqJsonObject(model);
    }
}	
