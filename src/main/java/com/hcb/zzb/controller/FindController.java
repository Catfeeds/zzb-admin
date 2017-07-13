package com.hcb.zzb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Article;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.service.IArticleService;
import com.hcb.zzb.service.ICarSevice;

import net.sf.json.JSONObject;

@Controller
public class FindController extends BaseControllers{
	
	@Autowired
	IArticleService articleService;
	@Autowired
	ICarSevice carService;
	/**
	 * 发现
	 * @return
	 */
	@RequestMapping(value="find",method=RequestMethod.POST)
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
		if(bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		int count = articleService.selectAllByArticleTypeCount(2);
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
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", bodyInfo.get("pageSize"));
		map.put("article_type", 2);//发现类型是2
		List<Article> list= articleService.selectAllByTypeIsPremium_experience(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.get("pageIndex"));
			json.put("tatal", total);
			json.put("findList", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
	/**
	 * 发现详情
	 * @param articleUuid
	 * @return
	 */
	@RequestMapping(value="find/detail",method=RequestMethod.POST)
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
		if(bodyInfo.get("articleUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Article article=articleService.selectByArticleUuid(bodyInfo.getString("articleUuid"));
		List<Car> carList=new ArrayList<>();
		if(article!=null) {
			String[] carIds=article.getCarIdList().split(",");
			for (String str : carIds) {
				Car car=carService.selectByPrimaryKey(Integer.parseInt(str));
				carList.add(car);
			}
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("articlePicture", article.getArticlePicture());
			json.put("createAt", article.getCreateAt());
			json.put("tittle", article.getTittle());
			json.put("articleContent", article.getArticleContent());
			json.put("carList", carList);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
}
