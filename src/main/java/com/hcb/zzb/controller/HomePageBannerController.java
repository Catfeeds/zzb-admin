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
import com.hcb.zzb.dto.Article;
import com.hcb.zzb.dto.HomepageBanner;
import com.hcb.zzb.dto.Manager;
import com.hcb.zzb.service.IManagerService;
import com.hcb.zzb.service.IhomePageBanner;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("homePage")
public class HomePageBannerController extends BaseControllers {

	@Autowired
	private IhomePageBanner homePageBanner;
	@Autowired
	IManagerService managerService;
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public String homePageList() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整!!");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整,,");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确..");
			return buildReqJsonObject(json);
		}
		Integer pageIndex=bodyInfo.getInt("pageIndex");
		Integer pageSize=bodyInfo.getInt("pageSize");
		Integer start=(pageIndex-1)*pageSize;
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", pageSize);
		if(bodyInfo.get("operation_info")!=null&&!"".equals(bodyInfo.get("operation_info"))) {
			map.put("operation_info", bodyInfo.getString("operation_info"));
		}
		if(bodyInfo.get("is_display")!=null&&!"".equals(bodyInfo.get("is_display"))) {
			map.put("is_display", bodyInfo.getInt("is_display"));
		}
		if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
			map.put("orderBy", bodyInfo.getInt("orderBy"));
		}else {
			map.put("orderBy", 2);
		}
		int count = homePageBanner.countByMap(map);
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
		List<HomepageBanner> list = new ArrayList<HomepageBanner>();
		list =homePageBanner.searchByMap(map);
		//List<Article> list = articleService.selectByMapLimit(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			json.put("homePageBannerlist", list);
		}else {
			json.put("result", "1");
			json.put("description", "没有查询到数据记录");
		}
		return buildReqJsonObject(json);
		//ModelMap model = new ModelMap();

		/*List<HomepageBanner> list = new ArrayList<HomepageBanner>();
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
			if(bodyInfo.get("operation_info") != null){
				map.put("operation_info", bodyInfo.getString("operation_info"));
			}
			if(bodyInfo.get("is_display") != null){
				map.put("is_display", bodyInfo.getInt("is_display"));
			}
			list = homePageBanner.searchByMap(map);
			Integer count = 0;
			count = homePageBanner.countByMap(map);
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
		a = a.replace("]\"", "]");*/
		
	}
	/**
	 * 新建首页轮播图
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String addBanner(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("operation_info") == null||bodyInfo.get("operation_picture") == null||bodyInfo.get("is_display") == null
				||bodyInfo.get("link")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		
		if("".equals(bodyInfo.get("operation_info"))||"".equals(bodyInfo.get("operation_picture"))
				||"".equals(bodyInfo.get("is_display"))||"".equals(bodyInfo.get("link"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		
		int countDisplay = homePageBanner.countDisplay();
		if(bodyInfo.getInt("is_display")==1 && countDisplay>=4) {
			json.put("result", "1");
			json.put("description", "轮播图上架数量不能大于4张");
			return buildReqJsonObject(json);
		}
		
		HomepageBanner homepageBanner=new HomepageBanner();
		homepageBanner.setCreateAt(new Date());
		homepageBanner.setIsDisplay(bodyInfo.getInt("is_display"));
		homepageBanner.setOperationInfo(bodyInfo.getString("operation_info"));
		homepageBanner.setOperationPicture(bodyInfo.getString("operation_picture"));
		homepageBanner.setLink(bodyInfo.getString("link"));
		//Manager manager=  managerService.selectByAccountUuid(headInfo.getString("manager_uuid"));
		Manager manager=  managerService.selectByAccount(headInfo.getString("account"));
		if(manager!=null){
			homepageBanner.setCreater(manager.getManagerUuid());
		}
		int rs=homePageBanner.insertSelective(homepageBanner);
		if(rs == 1){
			json.put("result", "0");
			json.put("description", "创建成功");
		}else{
			json.put("result", "1");
			json.put("description", "创建失败，请重新尝试");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 编辑保存 
	 * @return
	 */
	@RequestMapping(value ="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(){
		
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("operation_info") == null||bodyInfo.get("operation_picture") == null||bodyInfo.get("is_display") == null
				||bodyInfo.get("link")==null||bodyInfo.get("id")==null) {
			json.put("result", 1);
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		
		if("".equals(bodyInfo.get("operation_info"))||"".equals(bodyInfo.get("operation_picture"))
				||"".equals(bodyInfo.get("is_display"))||"".equals(bodyInfo.get("link"))
				||"".equals(bodyInfo.get("id"))) {
			json.put("result", 1);
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		
		
		HomepageBanner banner = homePageBanner.selectByPrimaryKey(bodyInfo.getInt("id"));
		if(banner.getIsDisplay()==1){
			//编辑时已为上架状态时，保存时不阻止
		}else if(banner.getIsDisplay()==2){
			//编辑时为下架状态时，保存为上架时，如果大于4就阻止
			int countDisplay = homePageBanner.countDisplay();
			if(bodyInfo.getInt("is_display")==1 && countDisplay>=4) {
				json.put("result", "1");
				json.put("description", "轮播图上架数量不能大于4张");
				return buildReqJsonObject(json);
			}
		}
		
		//Manager manager=  managerService.selectByAccountUuid(headInfo.getString("manager_uuid"));
		
		
		Manager manager=  managerService.selectByAccount(headInfo.getString("account"));
		if(manager!=null){
			//json.put("banner", banner);
			banner.setCreater(manager.getManagerUuid());
		}
		banner.setIsDisplay(bodyInfo.getInt("is_display"));
		banner.setOperationInfo(bodyInfo.getString("operation_info"));
		banner.setUpdateAt(new Date());
		banner.setLink(bodyInfo.getString("link"));
		banner.setOperationPicture(bodyInfo.getString("operation_picture"));
		int rs = homePageBanner.updateByPrimaryKeySelective(banner);
		if(rs == 1){
			json.put("result", "0");
			json.put("description", "修改成功");
		}else{
			json.put("result", "1");
			json.put("description", "修改失败，请重新尝试");
		}
		
		return buildReqJsonObject(json);
	}
	
	/**
	 * 上架/下架轮播图
	 * @return
	 */
	@RequestMapping(value ="editceil", method = RequestMethod.POST)
	@ResponseBody
	public String editceil(){
		
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("is_display") == null||bodyInfo.get("id") == null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		
		if ("".equals(bodyInfo.get("is_display"))||"".equals(bodyInfo.get("id"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		
		int countDisplay = homePageBanner.countDisplay();
		if(bodyInfo.getInt("is_display")==1 && countDisplay>=4) {
			json.put("result", "1");
			json.put("description", "轮播图上架数量不能大于4张");
			return buildReqJsonObject(json);
		}
		
		HomepageBanner banner = homePageBanner.selectByPrimaryKey(bodyInfo.getInt("id"));
		banner.setUpdateAt(new Date());
		//Manager manager=  managerService.selectByAccountUuid(headInfo.getString("manager_uuid"));
		Manager manager=  managerService.selectByAccount(headInfo.getString("account"));
		if(manager!=null){
			json.put("banner", banner);
			banner.setCreater(manager.getManagerUuid());
		}
		banner.setIsDisplay(bodyInfo.getInt("is_display"));
		int rs = homePageBanner.updateByPrimaryKeySelective(banner);
		if(rs == 1){
			json.put("result", "0");
			json.put("description", "上架成功");
		}else{
			json.put("result", "1");
			json.put("description", "上架失败，请重新尝试");
		}
		
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String detail(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		/*if (headInfo.get("manager_uuid")==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}*/
		HomepageBanner banner = homePageBanner.selectByPrimaryKey(bodyInfo.getInt("id"));
		if(banner != null){
			json.put("result", "0");
			json.put("description", "查看详情成功");
			json.put("banner", banner);
		}else{
			json.put("result", "1");
			json.put("description", "查看详情失败，请重新尝试");
		}
		
		return buildReqJsonObject(json);
	}
	
	/**
	 * 删除首页轮播
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete() {
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if(bodyInfo.get("id")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("id"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Integer id=bodyInfo.getInt("id");
		HomepageBanner hb= homePageBanner.selectByPrimaryKey(id);
		if(hb!=null) {
			hb.setDeleteAt(new Date());
			int rs = homePageBanner.updateByPrimaryKeySelective(hb);
			if(rs==1) {
				json.put("result", "0");
				json.put("description", "删除成功");
			}else {
				json.put("result", "1");
				json.put("description", "删除失败");
			}
		}else {
			json.put("result", "1");
			json.put("description", "操作失败,id不正确,没有查询到该记录");
		}
		return buildReqJsonObject(json);
	}
}
