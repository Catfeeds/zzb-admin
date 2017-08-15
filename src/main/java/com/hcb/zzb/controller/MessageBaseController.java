package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.AdminMessage;
import com.hcb.zzb.dto.HomepageBanner;
import com.hcb.zzb.dto.Manager;
import com.hcb.zzb.dto.MessageBase;
import com.hcb.zzb.dto.MessageChild;
import com.hcb.zzb.dto.PushInfo;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IManagerService;
import com.hcb.zzb.service.IMessageChildService;
import com.hcb.zzb.service.IPushInfoService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.service.ImessageBaseService;
import com.hcb.zzb.util.HttpGet;
import com.hcb.zzb.util.MD5Util;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("MessageBase")
public class MessageBaseController extends BaseControllers {

	@Autowired
	private ImessageBaseService messageBaseService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IUsersService userService;
	@Autowired
	private IMessageChildService messageChildService;
	private IPushInfoService pushInfoService;
	
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
		Integer pageIndex=bodyInfo.getInt("pageIndex");
		Integer pageSize=bodyInfo.getInt("pageSize");
		Integer start=(pageIndex-1)*pageSize;
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", pageSize);
		if(bodyInfo.get("tittle")!=null&&!"".equals(bodyInfo.get("tittle"))) {
			map.put("tittle", bodyInfo.getString("tittle"));
		}
		if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
			map.put("orderBy", bodyInfo.getInt("orderBy"));
		}else {
			map.put("orderBy", 2);
		}
		int count = messageBaseService.countByMap(map);
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
		List<MessageBase> list = new ArrayList<MessageBase>();
		list =messageBaseService.searchByMap(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			json.put("list", list);
		}else {
			json.put("result", "1");
			json.put("description", "没有查询到数据记录");
		}
		return buildReqJsonObject(json);
		//List<Article> list = articleService.selectByMapLimit(map);
		/*ModelMap model = new ModelMap();

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
			if(bodyInfo.get("tittle") != null){
				map.put("operation_info", bodyInfo.getString("operation_info"));
			}
			if(bodyInfo.get("is_display") != null){
				map.put("is_display", bodyInfo.getInt("is_display"));
			}
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
				model.put("list", list);
			}
		}
		
		model.put("description", "查询成功");
		model.put("result",0);
		model.put("list", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");*/
	} 
	
	@RequestMapping(value ="add", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String add(){
		
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("tittle") == null||bodyInfo.get("content") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		MessageBase messageBase=new MessageBase();
		Date nowDate=new Date();
		messageBase.setCreateAt(nowDate);
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
		//Manager manager=  managerService.selectByAccountUuid(headInfo.getString("manager_uuid"));
		Manager manager=  managerService.selectByAccount(headInfo.getString("account"));
		if(manager!=null){
			messageBase.setCreater(manager.getManagerUuid());
		}
		int rs=messageBaseService.insertSelective(messageBase);
		
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "创建成功");
		}else{
			json.put("result", 1);
			json.put("description", "创建失败，请重新尝试");
		}
		//增加消息子表
		
		Map<String, Object> map=new HashMap<>();
		int count = userService.countUsersByMap(map);
		map.put("start", 0);
		map.put("end", count);
		
		List<Users> list=userService.selectUsersByMap(map);
		if(list!=null&&!list.isEmpty()) {
			for (Users users : list) {
				MessageChild messageChild=new MessageChild();
				messageChild.setContent(bodyInfo.getString("content"));
				messageChild.setCreateAt(nowDate);
				if(manager!=null) {
					messageChild.setCreater(manager.getManagerUuid());
				}
				messageChild.setIsRead(1);//未读
				messageChild.setMessageBaseUuid(messageBase.getMessageBaseUuid());
				String messageChildUuid;
				try {
					messageChildUuid=MD5Util.md5Digest( System.currentTimeMillis() + RandomStringUtils.random(8));
					messageChild.setMessageChildUuid(messageChildUuid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				messageChild.setTittle(bodyInfo.getString("tittle"));
				messageChild.setUserUuid(users.getUserUuid());
				
				messageChildService.insertSelective(messageChild);
				
				/*//推送消息
				PushInfo push = new PushInfo();
				push.setCreateDatetime(new Date());
				push.setGroups("article");
				push.setUserUuid(users.getUserUuid());
				push.setPushTitle("至尊宝");
				push.setPushDsp("您有新的消息");
				push.setPushDatetime(new Date());
				push.setPushType(4);
				try {
					push.setPushUuid(MD5Util.md5Digest(users.getUserUuid() + System.currentTimeMillis() + RandomStringUtils.random(8)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			   Integer rs1 = pushInfoService.insertSelective(push);
				if(rs1==1){
					String url = "http://120.27.151.185/zzb-admin-java/phppushinfo";
					String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
					System.out.println("================="+str);
					//推送消息
					new Thread(new Runnable() {
						public void run() {
							PushInfo pushInfo = PushInfoService.selectByPushUuid(push.getPushUuid());
							if(pushInfo!=null){
								String url = "http://120.27.151.185/zzb-java/phppushinfo";
								String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
								System.out.println("================="+str);
							}
						}
					}).start();
				}
			
				////////////推送结束*/
			}
		}
		
		return buildReqJsonObject(json);
	}
	@RequestMapping(value ="delete", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String delete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		JSONObject headInfo=JSONObject.fromObject(headString);
		if (bodyInfo.get("id")==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		MessageBase messageBase = messageBaseService.selectByPrimaryKey(bodyInfo.getInt("id"));
		if(messageBase!=null){
			messageBase.setDeleteAt(new Date());
			int rs =0;
			rs = messageBaseService.updateByPrimaryKeySelective(messageBase);
			//删除子表信息
			List<MessageChild> list =messageChildService.selectMessageChildByMessageBaseUuid(messageBase.getMessageBaseUuid());
			for (MessageChild messageChild : list) {
				messageChild.setDeleteAt(new Date());
				messageChildService.updateByPrimaryKeySelective(messageChild);
			}	
			
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
