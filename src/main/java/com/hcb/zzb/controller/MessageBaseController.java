package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.AdminMessage;
import com.hcb.zzb.dto.HomepageBanner;
import com.hcb.zzb.dto.Manager;
import com.hcb.zzb.dto.MessageBase;
import com.hcb.zzb.service.IManagerService;
import com.hcb.zzb.service.ImessageBaseService;
import com.hcb.zzb.util.MD5Util;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("MessageBase")
public class MessageBaseController extends BaseControllers {

	@Autowired
	private ImessageBaseService messageBaseService;
	@Autowired
	IManagerService managerService;
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String messageList(){
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();

		List<MessageBase> list = new ArrayList<MessageBase>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			if(bodyInfo.get("tittle") != null){
				map.put("tittle", bodyInfo.getString("tittle"));
			}
			/*if(bodyInfo.get("tittle") != null){
				map.put("operation_info", bodyInfo.getString("operation_info"));
			}
			if(bodyInfo.get("is_display") != null){
				map.put("is_display", bodyInfo.getInt("is_display"));
			}*/
			list = messageBaseService.searchByMap(map);
			Integer count = 0;
			count = messageBaseService.countByMap(map);
			if (count % pageSize == 0) {
				Integer total = count / pageSize;
				Integer sign = 0;
				if (!total.equals(sign)) {
					if (pageIndex > total) {
						json.put("result", "1");
						json.put("description", "操作失败，请求页数大于总页数");
						return buildReqJsonObject(json);
					}
				}
				model.put("total", total);
				model.put("page", pageIndex);
			} else {
				Integer total = count / pageSize + 1;
				if (pageIndex > total) {
					json.put("result", "1");
					json.put("description", "操作失败，请求页数大于总页数");
					return buildReqJsonObject(json);
				}
				model.put("total", total);// 页码总数
				model.put("page", pageIndex);
			}
		}
		
		model.put("description", "查询成功");
		model.put("result",0);
		model.put("list", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	} 
	
	@RequestMapping(value ="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(){
		
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (headInfo.get("manager_uuid")==null||bodyInfo.get("tittle") == null||bodyInfo.get("content") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		MessageBase messageBase=new MessageBase();
		messageBase.setCreateAt(new Date());
		String messageBaseUuid;
		try {
			messageBaseUuid = MD5Util.md5Digest( System.currentTimeMillis() + RandomStringUtils.random(8));
			messageBase.setMessageBaseUuid(messageBaseUuid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageBase.setTittle(bodyInfo.getString("tittle"));
		messageBase.setContent(bodyInfo.getString("content"));
		Manager manager=  managerService.selectByAccountUuid(headInfo.getString("manager_uuid"));
		if(manager!=null){
			messageBase.setCreater(manager.getCreater());
		}
		int rs=messageBaseService.insertSelective(messageBase);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "创建成功");
		}else{
			json.put("result", 1);
			json.put("description", "创建失败，请重新尝试");
		}
		return buildReqJsonObject(json);
	}
	@RequestMapping(value ="delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		JSONObject headInfo=JSONObject.fromObject(headString);
		if (headInfo.get("manager_uuid")==null||bodyInfo.get("id")==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		MessageBase messageBase = messageBaseService.selectByPrimaryKey(bodyInfo.getInt("id"));
		if(messageBase!=null){
			messageBase.setDeleteAt(new Date());
			int rs =0;
			rs = messageBaseService.updateByPrimaryKeySelective(messageBase);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "删除成功");
			}else{
				json.put("result", 1);
				json.put("description", "删除失败，请重新尝试");
			}
		}else{
			json.put("result", 1);
			json.put("description", "删除失败，未查询到相关消息");
		}
		return buildReqJsonObject(json);
		
	}
	
	
}
