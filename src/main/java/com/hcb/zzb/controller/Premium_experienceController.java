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
public class Premium_experienceController extends BaseControllers{
	@Autowired
	IArticleService articleService;
	@Autowired
	ICarSevice carService;
	
	/**
	 * 超值体验
	 * @return
	 */
	@RequestMapping(value="premium_experience",method=RequestMethod.POST)
	@ResponseBody
	public String premium_experience() {
		JSONObject json = new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		//JSONObject headInfo=JSONObject.fromObject(headString);
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		int count = articleService.selectAllByArticleTypeCount(1);
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
		map.put("pageSize", bodyInfo.get("pageSize"));
		map.put("article_type", 1);
		List<Article> list=articleService.selectAllByTypeIsPremium_experience(map);
		if(!list.isEmpty()) {
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("page", bodyInfo.getInt("pageIndex"));
			json.put("total", total);
			json.put("premiumExperience", list);
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value="premium_experience/detail",method=RequestMethod.POST)
	@ResponseBody
	public String premium_experience_detail() {
		JSONObject json = new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("articleUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Article article=articleService.selectByArticleUuid(bodyInfo.getString("articleUuid"));
		List<Car> carList=new ArrayList<Car>();
		if(article!=null) {
			String carIds[]=article.getCarIdList().split(",");
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
			json.put("activityPrice", article.getActivityPrice());
			json.put("activityStartTime", article.getActivityStartTime());
			json.put("activityEndTime", article.getActivityEndTime());
			json.put("carList", carList);
			
		}else {
			json.put("result", "1");
			json.put("description", "未查询到数据");
		}
		return buildReqJsonObject(json);
	}
}
