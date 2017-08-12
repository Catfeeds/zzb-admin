package com.hcb.zzb.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.Ticket;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.ITicketService;
import com.hcb.zzb.service.IUsersService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="ticket")
public class TicketController extends BaseControllers{
	@Autowired
	ITicketService ticketService;
	@Autowired
	IUsersService usersService;
	@Autowired
	IOrderService orderService;
	/**
	 * 罚单信息列表
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String findTicketList() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("pageIndex"))||"".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Integer pageIndex=bodyInfo.getInt("pageIndex");
		Integer pageSize=bodyInfo.getInt("pageSize");
		Integer start=(pageIndex-1)*pageSize;
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", pageSize);
		if(bodyInfo.get("address")!=null&&!"".equals(bodyInfo.get("address"))) {
			map.put("address", bodyInfo.getString("address"));
		}
		if(bodyInfo.get("orderNumber")!=null&&!"".equals(bodyInfo.get("orderNumber"))) {
			map.put("orderNumber", bodyInfo.getString("orderNumber"));
		}
		if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
			map.put("orderBy", bodyInfo.getInt("orderBy"));
		}else {
			map.put("orderBy", 2);
		}
		int count = ticketService.countSelectTickets(map);
		if(count==0) {
			json.put("result", "1");
			json.put("description", "没有查询到数据信息");
			return buildReqJsonObject(json);
		}
		if(pageIndex<=0) {
			json.put("result", "1");
			json.put("description", "pageIndex不能小于0");
			return buildReqJsonObject(json);
		}
		int total = count%pageSize==0?count/pageSize:count/pageSize+1;
		if(pageIndex>total) {
			json.put("result", "1");
			json.put("description", "pageIndex不能大于总页数");
			return buildReqJsonObject(json);
		}
		
		List<Ticket> list = ticketService.selectTicketsLimit(map);
		List<Ticket> lists=new ArrayList<Ticket>();
		if(list!=null&&!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			for (Ticket ticket : list) {
				if(ticket.getUserUuid()==null) {
					json.put("result", "1");
					json.put("description", "错误,该罚单中没有用户关联");
					return buildReqJsonObject(json);
				}
				Users user = usersService.selectByUserUuid(ticket.getUserUuid());
				if(user==null) {
					json.put("result", "1");
					json.put("description", "错误,没有查询到该罚单的用户");
					return buildReqJsonObject(json);
				}
				ticket.setUserName(user.getUserName()==null?"":user.getUserName());
				lists.add(ticket);
			}
			json.put("ticketList", lists);
		}else {
			json.put("result", "1");
			json.put("description", "没有查询到数据记录");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 新建罚单
	 * @return
	 */
	@RequestMapping(value="insert",method=RequestMethod.POST)
	@ResponseBody
	public String addTicket() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("orderNumber")==null||bodyInfo.get("illegalTime")==null||bodyInfo.get("address")==null||
		   bodyInfo.get("money")==null||bodyInfo.get("points")==null||
		   bodyInfo.get("illegalCode")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("orderNumber"))||"".equals(bodyInfo.get("illegalTime"))||
			"".equals(bodyInfo.get("address"))||
			"".equals(bodyInfo.get("money"))||
			"".equals(bodyInfo.get("points"))||
			"".equals(bodyInfo.get("illegalCode"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Orders order=orderService.selectByOrderNumber(bodyInfo.getString("orderNumber"));
		if(order==null) {
			json.put("result", "1");
			json.put("description", "操作失败,订单号不存在");
			return buildReqJsonObject(json);
		}
		if(order.getUserUuid()==null) {
			json.put("result", "1");
			json.put("description", "操作失败,没有查询到订单的用户");
			return buildReqJsonObject(json);
		}
		Users user=usersService.selectByUserUuid(order.getUserUuid());
		if(user==null) {
			json.put("result", "1");
			json.put("description", "操作失败,没有查询到订单的用户");
			return buildReqJsonObject(json);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Ticket ticket=new Ticket();
		ticket.setAddress(bodyInfo.getString("address"));
		ticket.setCreateAt(new Date());
		ticket.setIllegalCode(bodyInfo.getString("illegalCode"));
		try {
			ticket.setIllegalTime(sdf.parse(bodyInfo.getString("illegalTime")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			json.put("result", "1");
			json.put("description", "illegalTime参数请输入日期格式如：2017-7-17 14:03:28");
			return buildReqJsonObject(json); 
		}
		ticket.setMoney(new BigDecimal(bodyInfo.getDouble("money")).floatValue());
		ticket.setOrderNumber(bodyInfo.getString("orderNumber"));
		ticket.setPoints(bodyInfo.getInt("points"));
		ticket.setTicketStatus(1);
		ticket.setTicketUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		ticket.setUserUuid(order.getUserUuid());
		int rs=ticketService.insertSelective(ticket);
		if(rs==1) {
			json.put("result", "0");
			json.put("description", "罚单新建成功");
		}else {
			json.put("result", "1");
			json.put("description", "罚单新建失败");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 罚单详情
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String ticketDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("ticketUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("ticketUuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model=new ModelMap();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Ticket ticket=ticketService.selectByTicketUuid(bodyInfo.getString("ticketUuid"));	
		if(ticket!=null) {
			Users user=usersService.selectByUserUuid(ticket.getUserUuid());	
			model.put("result", "0");
			model.put("description", "查询成功");
			model.put("userId", user.getId());
			model.put("userUuid", ticket.getUserUuid());
			model.put("userType", user.getUserType());
			model.put("userName", user.getUserName());
			model.put("userPhone", user.getUserPhone());
			model.put("orderNumber", ticket.getOrderNumber());
			model.put("address", ticket.getAddress());
			model.put("illegalTime", sdf.format(ticket.getIllegalTime()));
			model.put("money", ticket.getMoney());
			model.put("points", ticket.getPoints());
			model.put("illegalCode", ticket.getIllegalCode());
			model.put("ticketStatus", ticket.getTicketStatus());
		}else {
			model.put("result", "1");
			model.put("description", "查询失败");
		}
		return buildReqJsonObject(model);
	}
	
	/**
	 * 删除罚单
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String deleteTicket() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("ticketUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("ticketUuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model=new ModelMap();
		Ticket ticket=ticketService.selectByTicketUuid(bodyInfo.getString("ticketUuid"));	
		if(ticket!=null) {
			ticket.setDeleteAt(new Date());
			int rs=ticketService.updateByPrimaryKeySelective(ticket);
			if(rs==1) {
				model.put("result", "0");
				model.put("description", "删除成功");
			}else {
				model.put("result", "1");
				model.put("description", "删除失败");
			}		
		}else {
			model.put("result", "1");
			model.put("description", "操作失败，没有查询到该罚单");
		}
		return buildReqJsonObject(model);
	}
	
	/**
	 * 修改罚单状态
	 * @return
	 */
	@RequestMapping(value="updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public String updateTicketStatus() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("ticketUuid")==null||bodyInfo.get("ticketStatus")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("ticketUuid"))||"".equals(bodyInfo.get("ticketStatus"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model=new ModelMap();
		Ticket ticket=ticketService.selectByTicketUuid(bodyInfo.getString("ticketUuid"));	
		if(ticket!=null) {
			ticket.setTicketStatus(bodyInfo.getInt("ticketStatus"));
			int rs=ticketService.updateByPrimaryKeySelective(ticket);
			if(rs==1) {
				model.put("result", "0");
				model.put("description", "修改状态成功");
			}else {
				model.put("result", "1");
				model.put("description", "修改状态失败");
			}
		}else {
			model.put("result", "1");
			model.put("description", "操作失败，没有查询到该罚单");
		}
		return buildReqJsonObject(model);
	}
	
	/**
	 * 导出Excel
	 * @return
	 */
	@RequestMapping(value="exportExcel",method=RequestMethod.POST)
	@ResponseBody
	public String exportExcel() {
		
		return "";
	}
}
