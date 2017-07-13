package com.hcb.zzb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Ticket;
import com.hcb.zzb.service.ITicketService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("ticket")
public class TicketController extends BaseControllers{
	@Autowired
	ITicketService ticketService;
	
	@RequestMapping(value="ticket_list",method=RequestMethod.POST)
	@ResponseBody
	public String find() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null||bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null||bodyInfo.get("ticketStatus")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Map<String, Object> map1=new HashMap<>();
		map1.put("user_uuid", headInfo.getString("user_uuid"));
		map1.put("ticketStatus", bodyInfo.getInt("ticketStatus"));
		int count=ticketService.selectAllTickeByStstusCount(map1);
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
		Map<String, Object> map=new HashMap<>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", bodyInfo.get("pageSize"));
		map.put("ticketStatus", bodyInfo.get("ticketStatus"));
		map.put("user_uuid", headInfo.getString("user_uuid"));
		List<Ticket> list=ticketService.selectAllTickeByStstus(map);
		
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.get("pageIndex"));
			json.put("total", total);
			json.put("ticketList", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String findDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("ticket_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Ticket ticket=ticketService.selectByTicketUuid(bodyInfo.getString("ticket_uuid"));
		if(ticket!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("orderNumber", ticket.getOrderNumber());
			json.put("address", ticket.getAddress());
			json.put("money", ticket.getMoney());
			json.put("points", ticket.getPoints());
			json.put("illegalTime", ticket.getIllegalTime());
			json.put("illegalCode", ticket.getIllegalCode());
			json.put("ticketStatus", ticket.getTicketStatus());
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
}
