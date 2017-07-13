package com.hcb.zzb.controller.base;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import net.sf.json.JSONObject;
import com.hcb.zzb.bean.base.OutHead;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.Config;
import com.hcb.zzb.util.EmojiConvert;

@Scope("prototype")
public class BaseControllers {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	IUsersService userservices;


	protected String webPath;
	protected StringBuffer requestUrl;
	protected String headString;
	protected String bodyString;
	protected int sign = 0; // sign等于1时表示参数错误，sign等于2时表示登录认证失败
	protected Users eplogin;

	/**
	 * 所有的子类方法执行之前都要先执行此方法，子类方法不需要在model此方法中的参数
	 * 
	 * @param req
	 * @param res
	 * @param model
	 * @param platformUserName
	 * @param platformType
	 * @param code
	 * @throws UnsupportedEncodingException 
	 */
	@ModelAttribute
	public void init(HttpServletRequest req, HttpServletResponse res, ModelMap model,
			@RequestBody String json_package) throws UnsupportedEncodingException {
		sign = 0;
		requestUrl = null;
		headString = null;
		bodyString = null;
		eplogin = null;
		System.out.println(json_package);
		if (json_package == null) {
			sign = 1; // 参数错误
		} else {
			try {
				json_package = java.net.URLDecoder.decode(json_package, "utf-8");
			} catch (UnsupportedEncodingException e) {
				sign = 1;
				e.printStackTrace();
			}
			String reqJson = json_package.replaceFirst("json_package=", "");
			reqJson = EmojiConvert.emojiConvert1(reqJson);
			if (reqJson == null || "".equals(reqJson)) {
				sign = 1;
			} else {
				requestUrl = req.getRequestURL();
				String url = requestUrl.toString();
				try {
					JSONObject json = JSONObject.fromObject(reqJson);
					if (url.contains("captcha")) {
						// 不做处理

					}else {
						if (url.contains("login")) {
							JSONObject bodyInfo = (JSONObject) json.get("body");
							bodyString = bodyInfo.toString();
							if (bodyString == null) {
								sign = 1;
							}
						} else {
							JSONObject headInfo = (JSONObject) json.get("head");
							headString = headInfo.toString();
							String uuid = null;
							try {
								uuid = headInfo.getString("user_uuid");
							} catch (Exception e) {
								e.printStackTrace();
							}
							if(uuid!=null&&!"".equals(uuid)){
								eplogin = userservices.selectByUserUuid(uuid);
								if(eplogin == null){
									//什么都不操作
									JSONObject bodyInfo = (JSONObject) json.get("body");
									bodyString = bodyInfo.toString();
									if (bodyString == null) {
										sign = 1;
									}
								}else{
									JSONObject bodyInfo = (JSONObject) json.get("body");
									bodyString = bodyInfo.toString();
									if (bodyString == null) {
										sign = 1;
									}
									//更新用户的clientId和deviceToken
									/*if(headInfo.get("cid")!=null){
									   if(headInfo.get("devicetoken")!=null){
									        eplogin.setClientId(headInfo.getString("cid"));
									        eplogin.setDeviceToken(headInfo.getString("devicetoken"));
									        userservices.updateByPrimaryKeySelective(eplogin);
										}else{
											eplogin.setClientId(headInfo.getString("cid"));
											userservices.updateByPrimaryKeySelective(eplogin);
										}
								     }	*/
								}
							}else{
								sign = 2;
							}
						}
					}
				} catch (Exception e) {
					sign = 1;
				}
			}
		}
	}

	public String getBasePath() {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
	}

	public String getWebPath() {
		return Config.getString("webPath");
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	protected String toJsonString(Object obj) {
		return net.sf.json.JSONObject.fromObject(obj).toString();
	}

	protected OutHead getDefaultOutHead() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		return new OutHead().setReturnCode("000").setReturnDescription("验证通过").setSysTime(time);
		
	}

	protected String buildReqJson(List<Object> msgs) {
		JSONObject jo = new JSONObject();
		jo.put("head", getDefaultOutHead());
		jo.put("body", msgs);
		return jo.toString();
	}

	protected String buildReqJsonObject(Object msgs) {
		JSONObject jo = new JSONObject();
		jo.put("head", getDefaultOutHead());
		jo.put("body", msgs);
		String json =  jo.toString();
		try {
			json = EmojiConvert.emojiRecovery2(json);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}

	protected OutHead getNoJurisdictionOutHead() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		return new OutHead().setReturnCode("000").setReturnDescription("验证通过").setSysTime(time);
	}

	protected OutHead getLoginFailureOutHead() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		return new OutHead().setReturnCode("002").setReturnDescription("验证失败，user_uuid或密码不正确").setSysTime(time);
	}

	protected OutHead getParamWrongOutHead() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		return new OutHead().setReturnCode("001").setReturnDescription("请检查参数格式是否正确或者参数是否完整").setSysTime(time);
	}

	protected String buildReqJsonInteger(Integer result, Object msgs) {
		JSONObject jo = new JSONObject();
		if (result == 1) {
			jo.put("head", getParamWrongOutHead());
		} else if (result == 2) {
			jo.put("head", getLoginFailureOutHead());
		}else if(result == 3){
			jo.put("head", getNoJurisdictionOutHead());
		} else {
			jo.put("head", getDefaultOutHead());
		}
		jo.put("body", msgs);
		return jo.toString();
	}

}
