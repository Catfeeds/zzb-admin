package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.AdminMessage;
import com.hcb.zzb.service.IAdminMessageService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("adminmessage")
public class AdminMessageController extends BaseControllers{
	@Autowired
	IAdminMessageService adminMessageService;
	
	/**
	 * 消息列表
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public String messages() {
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

		List<AdminMessage> list = new ArrayList<AdminMessage>();
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
			if(bodyInfo.get("messageContent") == null){
				map.put("messageContent", bodyInfo.getString("messageContent"));
			}
			
			list = adminMessageService.searchByMap(map);
			Integer count = 0;
			count = adminMessageService.countByMap(map);
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
		model.put("bannerList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	/**
	 * 消息详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="detail",method = RequestMethod.POST)
	@ResponseBody
	public String findMessage() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("message_id") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		AdminMessage adminMessage = adminMessageService.selectByPrimaryKey(bodyInfo.getInt("message_id"));
		if(adminMessage!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("adminMessage", adminMessage);
		}else{
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public String delete() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("message_id") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		AdminMessage adminMessage = adminMessageService.selectByPrimaryKey(bodyInfo.getInt("message_id"));
		if(adminMessage!=null){
			adminMessage.setUpdateAt(new Date());
			int rs =0;
			rs = adminMessageService.updateByPrimaryKeySelective(adminMessage);
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
			json.put("adminMessage", adminMessage);
		}
		return buildReqJsonObject(json);
	}
}
