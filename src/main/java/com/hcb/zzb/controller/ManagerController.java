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
import com.hcb.zzb.dto.Manager;
import com.hcb.zzb.service.IManagerService;
import com.hcb.zzb.util.MD5Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("manager")
public class ManagerController extends BaseControllers {
	
	@Autowired
	IManagerService managerService;

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

		List<Manager> list = new ArrayList<Manager>();
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
			if(bodyInfo.get("contacts") != null){
				map.put("contacts", bodyInfo.getString("contacts"));
			}
			if(bodyInfo.get("account") != null){
				map.put("account", bodyInfo.getString("account"));
			}
			if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
				map.put("orderBy", bodyInfo.getInt("orderBy"));
			}else {
				map.put("orderBy", 2);
			}
			list = managerService.searchByMap(map);
			Integer count = 0;
			count = managerService.countByMap(map);
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
		model.put("result","0");
		model.put("list", list);
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
		if (bodyInfo.get("manager_id") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Manager managerF = managerService.selectByPrimaryKey(bodyInfo.getInt("manager_id"));
		if(managerF!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("manager", managerF);
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
		if (bodyInfo.get("manager_id") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Manager managerF = managerService.selectByPrimaryKey(bodyInfo.getInt("manager_id"));
		if(managerF!=null){
			managerF.setDeleteAt(new Date());
			int rs =0;
			rs = managerService.updateByPrimaryKeySelective(managerF);
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
	
	@RequestMapping(value="changestatus",method = RequestMethod.POST)
	@ResponseBody
	public String changestatus() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("manager_id") == null||bodyInfo.get("manager_status") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Manager managerF = managerService.selectByPrimaryKey(bodyInfo.getInt("manager_id"));
		if(managerF!=null){
			managerF.setManagerStatus(bodyInfo.getInt("manager_status"));
			managerF.setUpdateAt(new Date());
			int rs =0;
			rs = managerService.updateByPrimaryKeySelective(managerF);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "更改状态成功");
			}else{
				json.put("result", 1);
				json.put("description", "更改状态失败，请重新尝试");
			}
		}else{
			json.put("result", 1);
			json.put("description", "更改状态失败，未查询到相关消息");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public String newManager() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("account") == null||bodyInfo.get("password") == null||bodyInfo.get("contacts") == null||bodyInfo.get("manager_status") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Manager managerF = managerService.selectByAccount(bodyInfo.getString("account"));
		if(managerF!=null){
			json.put("result", 1);
			json.put("description", "创建失败，该账号已存在");
		}else{
			Manager newManager = new Manager();
			newManager.setAccount(bodyInfo.getString("account"));
			newManager.setContacts(bodyInfo.getString("contacts"));
			newManager.setCreateAt(new Date());
			newManager.setManagerStatus(bodyInfo.getInt("manager_status"));
			newManager.setPassword(bodyInfo.getString("password"));
			int rs = 0;
			String managerUuid = "";
			try {
				managerUuid = MD5Util.md5Digest(bodyInfo.getString("account") + System.currentTimeMillis() + RandomStringUtils.random(8));
				newManager.setManagerUuid(managerUuid);
				rs = managerService.insertSelective(newManager);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "创建成功");
			}else{
				json.put("result", 1);
				json.put("description", "创建失败，请重新尝试");
			}
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value="update",method = RequestMethod.POST)
	@ResponseBody
	public String updateManager() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("manager_id") == null||bodyInfo.get("account") == null||bodyInfo.get("password") == null||bodyInfo.get("contacts") == null||bodyInfo.get("manager_status") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Manager managerF = managerService.selectByPrimaryKey(bodyInfo.getInt("manager_id"));
		// 判断修改后的账号是否已存在
		Manager aManager = managerService.selectByAccount(bodyInfo.getString("account"));
		if(aManager!=null){
			if(!aManager.getId().equals(bodyInfo.getInt("manager_id"))){
				json.put("result", 1);
				json.put("description", "更新失败，该账号名已存在");
				return buildReqJsonObject(json);
			}
		}
		if(managerF!=null){
			managerF.setAccount(bodyInfo.getString("account"));
			managerF.setContacts(bodyInfo.getString("contacts"));
			managerF.setUpdateAt(new Date());
			managerF.setManagerStatus(bodyInfo.getInt("manager_status"));
			managerF.setPassword(bodyInfo.getString("password"));
			int rs = 0;
			try {
				rs = managerService.updateByPrimaryKeySelective(managerF);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "更新成功");
			}else{
				json.put("result", 1);
				json.put("description", "更新失败，请重新尝试");
			}
		}else{
			json.put("result", 1);
			json.put("description", "更新失败，未查询该账号信息");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value="updatepassword",method = RequestMethod.POST)
	@ResponseBody
	public String updatepassword() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("manager_id") == null||bodyInfo.get("password") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Manager managerF = managerService.selectByPrimaryKey(bodyInfo.getInt("manager_id"));
		if(managerF!=null){
			managerF.setUpdateAt(new Date());
			managerF.setPassword(bodyInfo.getString("password"));
			int rs = 0;
			try {
				rs = managerService.updateByPrimaryKeySelective(managerF);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "修改成功");
			}else{
				json.put("result", 1);
				json.put("description", "修改失败，请重新尝试");
			}
		}else{
			json.put("result", 1);
			json.put("description", "修改失败，未查询该账号信息");
		}
		return buildReqJsonObject(json);
	}
}	
