package com.hcb.zzb.controller;

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
import com.hcb.zzb.dto.Manager;
import com.hcb.zzb.service.IArticleService;
import com.hcb.zzb.service.IManagerService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="article")
public class ArticleController extends BaseControllers{
	@Autowired
	IArticleService articleService;
	@Autowired
	IManagerService managerService;
	
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
			||bodyInfo.get("carIdList")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("tittle"))||"".equals(bodyInfo.get("articleType"))||
				"".equals(bodyInfo.get("articleContent"))||"".equals(bodyInfo.get("articlePicture"))
				||"".equals(bodyInfo.get("carIdList"))) {
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
		Article article=new Article();
		article.setCreateAt(new Date());
		article.setTittle(bodyInfo.getString("tittle"));
		article.setArticleType(bodyInfo.getInt("articleType"));
		article.setArticleContent(bodyInfo.getString("articleContent"));
		article.setArticlePicture(bodyInfo.getString("articlePicture"));
		article.setCarIdList(bodyInfo.getString("carIdList"));
		article.setArticleUuid(UUID.randomUUID().toString().replaceAll("-", ""));	
		article.setCreater(manager.getManagerUuid());
		
		int rs = articleService.insertSelective(article);
		if(rs==1) {
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
			json.put("tittle", article.getTittle());
			json.put("articleType", article.getArticleType());
			json.put("articleContent", article.getArticleContent());
			json.put("articlePicture", article.getArticlePicture());
			json.put("carIdList", article.getCarIdList());
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
		Article article=articleService.selectByArticleUuid(bodyInfo.getString("articleUuid"));
		if(article==null) {
			json.put("result", "1");
			json.put("description", "articleUuid不正确,没有查询到,文章不存在");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("tittle")==null&&bodyInfo.get("articleType")==null&&
				bodyInfo.get("articleContent")==null&&bodyInfo.get("articlePicture")==null
				&&bodyInfo.get("carIdList")==null) {
				json.put("result", "1");
				json.put("description", "未编辑,不保存");
				return buildReqJsonObject(json);
		}
		if(bodyInfo.get("tittle")!=null) {
			article.setTittle(bodyInfo.getString("tittle"));
		}
		if(bodyInfo.get("articleType")!=null) {
			article.setArticleType(bodyInfo.getInt("articleType"));
		}
		if(bodyInfo.get("articleContent")!=null) {
			article.setArticleContent(bodyInfo.getString("articleContent"));
		}
		if(bodyInfo.get("articlePicture")!=null) {
			article.setArticlePicture(bodyInfo.getString("articlePicture"));
		}
		if(bodyInfo.get("carIdList")!=null) {
			article.setCarIdList(bodyInfo.getString("carIdList"));
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
