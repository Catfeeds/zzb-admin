package com.hcb.zzb.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.MessageChild;
import com.hcb.zzb.service.IMessageService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("message")
public class AdminMessageController extends BaseControllers{
	@Autowired
	IMessageService messageService;
	
	/**
	 * 消息列表
	 * @param pageSize
	 * @param pageIndex
	 * @param user_uuid
	 * @return
	 */
	@RequestMapping(value="info_list",method = RequestMethod.POST)
	@ResponseBody
	public String messages() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		int count=messageService.selectAllMessageByUserCount(headInfo.getString("user_uuid"));
		int total = count%bodyInfo.getInt("pageSize")==0?count/bodyInfo.getInt("pageSize"):count/bodyInfo.getInt("pageSize")+1;
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
		map.put("pageSize", bodyInfo.getInt("pageSize"));
		map.put("user_uuid", headInfo.getString("user_uuid"));
		List<MessageChild> childList=messageService.selectAllMessageByUser(map);
		
		if(!childList.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.get("pageIndex"));
			json.put("total", total);
			json.put("infoList", childList);
			
		}else {
			json.put("result", "1");
			json.put("description", "未查询到结果");
		}
		return buildReqJsonObject(json);
	}
	/**
	 * 消息详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="information",method = RequestMethod.POST)
	@ResponseBody
	public String findMessage() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("message_child_uuid") == null||headInfo.get("user_uuid")==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Map<String, Object> map=new HashMap<>();
		map.put("user_uuid", headInfo.getString("user_uuid"));
		map.put("messageChildUuid", bodyInfo.getString("message_child_uuid"));
		MessageChild mess= messageService.selectByUserUuidMessageChildUuid(map);
		if(mess!=null){
			//修改消息为已读状态
			mess.setIsRead(2);
			messageService.updateByPrimaryKeySelective(mess);
			System.out.println("title:"+mess.getTittle()+"--"+"content:"+mess.getContent()+"--"+"messChildUuid:+"+mess.getMessageChildUuid()+"--"+"createAt:"+mess.getCreateAt());
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("title", mess.getTittle());
			json.put("content", mess.getContent());
			json.put("messageChildUuid", mess.getMessageChildUuid());
			json.put("createAt", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mess.getCreateAt()));
		}else{
			json.put("result", "1");
			json.put("description", "未查询到该消息");
		}
		return buildReqJsonObject(json);
	}
}
