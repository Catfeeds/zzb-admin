package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Article;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.Manager;
import com.hcb.zzb.service.IArticleService;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IManagerService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="article")
public class ArticleController extends BaseControllers{
	@Autowired
	IArticleService articleService;
	@Autowired
	IManagerService managerService;
	@Autowired
	ICarSevice carService;
	/**
	 * 运营文章列表（分页）
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String findArticleList() {
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
		if(bodyInfo.get("tittle")!=null&&!"".equals(bodyInfo.get("tittle"))) {
			map.put("tittle", bodyInfo.getString("tittle"));
		}
		if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
			map.put("orderBy", bodyInfo.getInt("orderBy"));
		}else {
			map.put("orderBy", 2);
		}
		int count = articleService.countSelectByMapLimit(map);
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
		List<Article> list = articleService.selectByMapLimit(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			json.put("articleList", list);
		}else {
			json.put("result", "1");
			json.put("description", "没有查询到数据记录");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 新建文章时（增加推荐车辆时输入车辆名字，根据名字模糊查询出所有车辆供选择）
	 * @return
	 */
	@RequestMapping(value="toSerchCar",method=RequestMethod.POST)
	@ResponseBody
	public String getCars(){
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		//JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("carName")==null){
			json.put("result", "1");
			json.put("description", "请检查参数是否完整或者正确");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("carName").equals("")){
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		List<Car> list=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		map.put("carName", bodyInfo.getString("carName"));
		list = carService.selectCarsByName(map);
		if(!list.isEmpty()){
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("list", list);
		}else{
			json.put("result", "1");
			json.put("description", "没有查询到");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 新建文章
	 * @return
	 */
	@RequestMapping(value="newArticle",method=RequestMethod.POST)
	@ResponseBody
	public String addArticle() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("tittle")==null||bodyInfo.get("articleType")==null||
			bodyInfo.get("articleContent")==null||bodyInfo.get("articlePicture")==null
			||bodyInfo.get("carID1")==null||bodyInfo.get("carID2")==null||bodyInfo.get("activityCarID")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("tittle"))||"".equals(bodyInfo.get("articleType"))||
				"".equals(bodyInfo.get("articleContent"))||"".equals(bodyInfo.get("articlePicture"))
				||"".equals(bodyInfo.get("carID1"))||"".equals(bodyInfo.get("carID2"))||"".equals(bodyInfo.get("activityCarID"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Manager manager = managerService.selectByAccount(headInfo.getString("account"));
		if(manager==null) {
			json.put("result", "1");
			json.put("description", "登录账号错误,没有查询到");
			return buildReqJsonObject(json);
		}
		
		int carID1=bodyInfo.getInt("carID1");
		int carID2=bodyInfo.getInt("carID2");
		int activityCarID=bodyInfo.getInt("activityCarID");
		Car car1=carService.selectByPrimaryKey(carID1);
		Car car2=carService.selectByPrimaryKey(carID2);
		Car car3=carService.selectByPrimaryKey(activityCarID);
		if(car1==null||car2==null) {
			json.put("result", "1");
			json.put("description", "推荐车辆ID错误,车辆不存在");
			return buildReqJsonObject(json);
		}
		if(car3==null) {
			json.put("result", "1");
			json.put("description", "活动车辆ID错误,车辆不存在");
			return buildReqJsonObject(json);
		}
		Article article=new Article();
		article.setCreateAt(new Date());
		article.setTittle(bodyInfo.getString("tittle"));
		article.setArticleType(bodyInfo.getInt("articleType"));
		article.setArticleContent(bodyInfo.getString("articleContent"));
		article.setArticlePicture(bodyInfo.getString("articlePicture"));
		String carIdList=carID1+","+carID2;
		article.setCarIdList(carIdList);
		article.setArticleUuid(UUID.randomUUID().toString().replaceAll("-", ""));	
		article.setCreater(manager.getManagerUuid());
		article.setActivityCat(activityCarID);
		
		int id = articleService.insertSelective(article);
		Article article2 = articleService.selectByPrimaryKey(id);
		if(bodyInfo.getInt("articleType")==1){
			article2.setLink("http://app.zzbcar.com/zzb-java/toActivePage?articleUuid="+article2.getArticleUuid());
		}else if(bodyInfo.getInt("articleType")==2){
			article2.setLink("http://app.zzbcar.com/zzb-java/tofindPage?articleUuid="+article2.getArticleUuid());
		}else{
			json.put("result", "1");
			json.put("description", "参数articleType文章类型必须为1或者2");
			return buildReqJsonObject(json);
		}
		int rs =articleService.updateByPrimaryKeySelective(article2);
		if(id>0 && rs ==1) {
			json.put("result", "0");
			json.put("description", "新建文章成功");
		}else {
			json.put("result", "1");
			json.put("description", "新建文章失败");
		}
		return buildReqJsonObject(json);
	}
	
	
	
	/**
	 * 运营文章详情
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String findArticleDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("articleUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("articleUuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Article article = articleService.selectByArticleUuid(bodyInfo.getString("articleUuid"));
		if(article!=null) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("tittle", article.getTittle()==null?"":article.getTittle());
			json.put("articleType", article.getArticleType()==null?"":article.getArticleType());
			json.put("articleContent", article.getArticleContent()==null?"":article.getArticleContent());
			json.put("articlePicture", article.getArticlePicture()==null?"":article.getArticlePicture());
			json.put("activityCarID", article.getActivityCat()==null?"":article.getActivityCat()+"");
			if(article.getCarIdList()==null) {
				json.put("carID1", "");
				json.put("carID2", "");
			}else {
				String[] strs=article.getCarIdList().split(",");
				if(strs.length==0) {
					json.put("carID1", "");
					json.put("carID2", "");
				}else if(strs.length==1) {
					json.put("carID1", strs[0]);
					json.put("carID2", "");
				}else if(strs.length==2) {
					json.put("carID1", strs[0]);
					json.put("carID2", strs[1]);		
				}else{
					json.put("carID1", strs[0]);
					json.put("carID2", strs[1]);	
				}		
			}
		}else {
			json.put("result", "1");
			json.put("description", "articleUuid不正确,没有查询到,文章不存在");
		}
		return buildReqJsonObject(json);
	} 
	
	/**
	 * 保存编辑后运营文章
	 * @return
	 */
	@RequestMapping(value="editAndSave",method=RequestMethod.POST)
	@ResponseBody
	public String editAticleSave() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("articleUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("articleUuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("tittle")==null||bodyInfo.get("articleType")==null||
				bodyInfo.get("articleContent")==null||bodyInfo.get("articlePicture")==null
				||bodyInfo.get("carID1")==null||bodyInfo.get("carID2")==null||bodyInfo.get("activityCarID")==null) {
				json.put("result", "1");
				json.put("description", "请检查参数是否完整");
				return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("tittle"))||"".equals(bodyInfo.get("articleType"))||
			"".equals(bodyInfo.get("articleContent"))||"".equals(bodyInfo.get("articlePicture"))
			||"".equals(bodyInfo.get("carID1"))||"".equals(bodyInfo.get("carID2"))||"".equals(bodyInfo.get("activityCarID"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		int carID1=bodyInfo.getInt("carID1");
		int carID2=bodyInfo.getInt("carID2");
		int activityCarID=bodyInfo.getInt("activityCarID");
		Car car1=carService.selectByPrimaryKey(carID1);
		Car car2=carService.selectByPrimaryKey(carID2);
		Car car3=carService.selectByPrimaryKey(activityCarID);
		if(car1==null||car2==null) {
			json.put("result", "1");
			json.put("description", "推荐车辆ID错误,车辆不存在");
			return buildReqJsonObject(json);
		}
		if(car3==null) {
			json.put("result", "1");
			json.put("description", "活动车辆ID错误,车辆不存在");
			return buildReqJsonObject(json);
		}
		Article article=articleService.selectByArticleUuid(bodyInfo.getString("articleUuid"));
		if(article==null) {
			json.put("result", "1");
			json.put("description", "articleUuid不正确,没有查询到,文章不存在");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("tittle")==null&&bodyInfo.get("articleType")==null&&
				bodyInfo.get("articleContent")==null&&bodyInfo.get("articlePicture")==null
				&&bodyInfo.get("carID1")==null&&bodyInfo.get("carID2")==null&&bodyInfo.get("activityCarID")==null) {
				json.put("result", "1");
				json.put("description", "参数为空，不保存");
				return buildReqJsonObject(json);
		}
		
		article.setTittle(bodyInfo.getString("tittle"));
		article.setArticleType(bodyInfo.getInt("articleType"));
		article.setArticleContent(bodyInfo.getString("articleContent"));
		article.setArticlePicture(bodyInfo.getString("articlePicture"));
		article.setCarIdList(carID1+","+carID2);
		article.setActivityCat(bodyInfo.getInt("activityCarID"));
		if(bodyInfo.getInt("articleType")==1){
			article.setLink("http://app.zzbcar.com/zzb-java/toActivePage?articleUuid="+article.getArticleUuid());
		}else if(bodyInfo.getInt("articleType")==2){
			article.setLink("http://app.zzbcar.com/zzb-java/tofindPage?articleUuid="+article.getArticleUuid());
		}else{
			json.put("result", "1");
			json.put("description", "参数articleType文章类型必须为1或者2");
			return buildReqJsonObject(json);
		}
		int rs = articleService.updateByPrimaryKeySelective(article);
		if(rs==1) {
			json.put("result", "0");
			json.put("description", "保存成功");
		}else {
			json.put("result", "1");
			json.put("description", "保存失败");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 删除运营文章
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String deleteArticle() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("articleUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("articleUuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Article article = articleService.selectByArticleUuid(bodyInfo.getString("articleUuid"));
		if(article==null) {
			json.put("result", "1");
			json.put("description", "articleUuid不正确,没有查询到,文章不存在");
			return buildReqJsonObject(json);
		}
		article.setDeleteAt(new Date());
		int rs = articleService.updateByPrimaryKeySelective(article);
		if(rs == 1) {
			json.put("result", "0");
			json.put("description", "删除成功");
		}else {
			json.put("result", "0");
			json.put("description", "删除失败");
		}
		return buildReqJsonObject(json);
	}
}
