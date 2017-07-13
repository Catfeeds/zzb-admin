package com.hcb.zzb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Article;
import com.hcb.zzb.dto.BrowseLog;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.CarModel;
import com.hcb.zzb.dto.HomepageBanner;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IArticleService;
import com.hcb.zzb.service.IBrowseLogService;
import com.hcb.zzb.service.ICarModel;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.service.IhomePageBanner;
import com.hcb.zzb.util.DateUtil;
import com.hcb.zzb.util.MD5Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("index")
public class IndexController extends BaseControllers {
	@Autowired
	private ICarSevice CarSevice;
	@Autowired
	private IArticleService articleService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IUsersService userServie;
	@Autowired
	private ICarModel CarModelSevice;
	@Autowired
	private IhomePageBanner homePageBanner;
	@Autowired
	private IBrowseLogService browseLogService;

	@RequestMapping(value = "home_page", method = RequestMethod.POST)
	@ResponseBody
	public String index() {

		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (headInfo.get("user_uuid") == null ) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		String city=bodyInfo.getString("city");
		//List<Car> banners = new ArrayList<Car>();
		//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		
		//List<Article> articlelist = new ArrayList<Article>();
		//articlelist = articleService.selectAll();
		/*for (Article article : articlelist) {
			if (article != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tittle", article.getTittle());
				map.put("article_type", article.getArticleType());
				map.put("article_picture", article.getArticlePicture());
				map.put("car_id_list", article.getCarIdList());
				map.put("activity_cat", article.getActivityCat());
				map.put("activity_price", article.getActivityPrice());
				list.add(map);
			}
		}*/
		/*
		for (Article article : PremiumExperience) {
			if (article != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tittle", article.getTittle());
				map.put("article_type", article.getArticleType());
				map.put("article_picture", article.getArticlePicture());
				map.put("car_id_list", article.getCarIdList());
				map.put("activity_cat", article.getActivityCat());
				map.put("activity_price", article.getActivityPrice());
				PremiumExperiencelist.add(map);
			}
		}*/
		// 1超值体验
		List<Map<String, Object>> PremiumExperiencelist = new ArrayList<Map<String, Object>>();
		List<Article> PremiumExperience = new ArrayList<Article>();
		PremiumExperience=articleService.selectAll();
		if(PremiumExperience.size()>0){
			for (Article article : PremiumExperience) {
				if (article != null){
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("tittle", article.getTittle());
					map.put("article_type", article.getArticleType());
					map.put("article_picture", article.getArticlePicture());
					map.put("car_id_list", article.getCarIdList());
					map.put("activity_cat", article.getActivityCat());
					map.put("activity_price", article.getActivityPrice());
					PremiumExperiencelist.add(map);
				}
			}
			jsonObject.put("PremiumExperience", PremiumExperiencelist);
		}else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "超值体验查询失败");
		}
		// 2新鲜车型
		List<Map<String, Object>> newCarModellist = new ArrayList<Map<String, Object>>();
		List<Car> newCarModel = new ArrayList<Car>();
		newCarModel=CarSevice.selectCarStatusAll();
		if(newCarModel.size()>0){
			for (Car car : newCarModel) {
				if (car != null) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("banner", car.getBanner());
					map.put("brand", car.getBrand());
					map.put("working_day_price", car.getWorkingDayPrice());
					map.put("car_series", car.getCarSeries());
					newCarModellist.add(map);
				}
			}
			jsonObject.put("newCarModel", newCarModellist);
		}else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "新鲜车型查询失败");
		}
		
		/*for (Article article : newCarModel) {
			if (article != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tittle", article.getTittle());
				map.put("article_type", article.getArticleType());
				map.put("article_picture", article.getArticlePicture());
				map.put("car_id_list", article.getCarIdList());
				map.put("activity_cat", article.getActivityCat());
				map.put("activity_price", article.getActivityPrice());
				newCarModellist.add(map);
			}
		}*/
		// 3车型专区
		List<Map<String, Object>> carModellist = new ArrayList<Map<String, Object>>();
		List<Article> carModel = new ArrayList<Article>();
		carModel=articleService.selectTypeAll();
		if(carModel.size()>0){
			for (Article article : carModel) {
				if (article != null) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("article_uuid", article.getArticleUuid());
					map.put("tittle", article.getTittle());
					map.put("article_picture", article.getArticlePicture());
					carModellist.add(map);
				}
			}
			jsonObject.put("carModel", carModellist);
		}else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "车型专区查询失败");
		}
		//jsonObject.put("carModel", carModellist);
		/*List<Map<String, Object>> carModellist = new ArrayList<Map<String, Object>>();
		List<Article> carModel = new ArrayList<Article>();
		for (Article article : articlelist) {
			if (article != null && article.getArticleType() == 3) {
				carModel.add(article);
			}
		}
		for (Article article : newCarModel) {
			if (article != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tittle", article.getTittle());
				map.put("article_type", article.getArticleType());
				map.put("article_picture", article.getArticlePicture());
				map.put("car_id_list", article.getCarIdList());
				map.put("activity_cat", article.getActivityCat());
				map.put("activity_price", article.getActivityPrice());
				carModellist.add(map);
			}
		}*/
		// 4猜你喜欢
		List<Map<String, Object>> guessLikellist = new ArrayList<Map<String, Object>>();
		List<BrowseLog> guessLike = new ArrayList<BrowseLog>();
		//待判断uuid
		if(headInfo.get("user_uuid")!=null){
			guessLike=browseLogService.selectByGuessYouLike1(headInfo.getString("user_uuid"));
		}else{
			guessLike=browseLogService.selectByGuessYouLike1(null);
		}
		if(guessLike.size()>0){
			for (BrowseLog browseLog : guessLike) {
				if (browseLog != null) {
					Map<String, Object> map=new HashMap<String, Object>();
					Car car = CarSevice.selectByUuid(browseLog.getCarUuid());
					map.put("banner", car.getBanner());
					map.put("brand", car.getBrand());
					map.put("working_day_price", car.getWorkingDayPrice());
					map.put("car_series", car.getCarSeries());
					guessLikellist.add(map);
				}
			}
			jsonObject.put("guessLike", guessLikellist );
		}else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "猜你喜欢查询失败");
		}
		
		/*List<Map<String, Object>> guessLikelist = new ArrayList<Map<String, Object>>();
		List<Article> guessLike = new ArrayList<Article>();
		for (Article article : articlelist) {
			if (article != null && article.getArticleType() == 4) {
				guessLike.add(article);
			}
		}
		for (Article article : guessLike) {
			if (article != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tittle", article.getTittle());
				map.put("article_type", article.getArticleType());
				map.put("article_picture", article.getArticlePicture());
				map.put("car_id_list", article.getCarIdList());
				map.put("activity_cat", article.getActivityCat());
				map.put("activity_price", article.getActivityPrice());
				guessLikelist.add(map);
			}
		}*/
		// 5最近浏览
		List<Map<String, Object>> recentBrowselist = new ArrayList<Map<String, Object>>();
		List<BrowseLog> recentBrowse = new ArrayList<BrowseLog>();
		recentBrowse=browseLogService.selectByrecentBrowse(headInfo.getString("user_uuid"));
		if(recentBrowse.size()>0){
			for (BrowseLog browseLog : recentBrowse) {
				if (browseLog != null) {
					Map<String, Object> map=new HashMap<String, Object>();
					Car car = CarSevice.selectByUuid(browseLog.getCarUuid());
					map.put("banner", car.getBanner());
					map.put("brand", car.getBrand());
					map.put("working_day_price", car.getWorkingDayPrice());
					map.put("car_series", car.getCarSeries());
					guessLikellist.add(map);
				}
			}
			jsonObject.put("recentBrowse", recentBrowselist);
		}else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "最近浏览查询失败");
		}
		List<HomepageBanner> banners = new ArrayList<HomepageBanner>();
		banners=homePageBanner.selectAll();
		if (banners != null) {
			jsonObject.put("description", "我的信息");
			jsonObject.put("result", "0");
			jsonObject.put("banners", banners);
		}
		return buildReqJsonObject(jsonObject);
		
		/*List<Map<String, Object>> recentBrowselist = new ArrayList<Map<String, Object>>();
		List<Article> recentBrowse = new ArrayList<Article>();
		for (Article article : articlelist) {
			if (article != null && article.getArticleType() == 5) {
				recentBrowse.add(article);
			}
		}
		for (Article article : recentBrowse) {
			if (article != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tittle", article.getTittle());
				map.put("article_type", article.getArticleType());
				map.put("article_picture", article.getArticlePicture());
				map.put("car_id_list", article.getCarIdList());
				map.put("activity_cat", article.getActivityCat());
				map.put("activity_price", article.getActivityPrice());
				recentBrowselist.add(map);
			}
		}*/
		//banners = CarSevice.selectByCity(city);
		
	}

	// 车辆信息
	@RequestMapping(value = "home_page/list/one", method = RequestMethod.POST)
	@ResponseBody
	public String carMessage() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		// String useruuid;
		if (headInfo.get("user_uuid") == null ) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		
		String caruuid = bodyInfo.getString("car_uuid");
		BrowseLog browseLog=new BrowseLog();
		browseLog.setCarUuid(caruuid);
		browseLog.setCreateAt(new Date());
		String user_uuid = bodyInfo.getString("user_uuid");
		browseLog.setUserUuid(user_uuid);
		String browseLogUuid;
		try {
			browseLogUuid = MD5Util.md5Digest(System.currentTimeMillis() + RandomStringUtils.random(8));
			browseLog.setBrowseLogUuid(browseLogUuid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		browseLogService.insertSelectiveBrowseLog(browseLog);
		
		Car car = CarSevice.selectByUuid(caruuid);
		if (car != null) {
			// 以成交数量
			int count;
			try {
				count = orderService.selectCountByUserUuid(car.getUserUuid());
				jsonObject.put("count", count);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonObject.put("description", "车辆信息查询成功");
			jsonObject.put("result", "0");
			jsonObject.put("car_name", car.getCarName());
			jsonObject.put("license_plate_number", car.getLicensePlateNumber());
			jsonObject.put("displacement", car.getDisplacement());
			jsonObject.put("model_character", car.getModelCharacter());
			jsonObject.put("weekend_price", car.getWeekendPrice());
			jsonObject.put("working_day_price", car.getWorkingDayPrice());
			jsonObject.put("car_owner_name", car.getCarOwnerName());
			jsonObject.put("order_quantity", car.getOrderQuantity());
			jsonObject.put("collection", car.getCollection());
			jsonObject.put("address", car.getAddress());
			jsonObject.put("address_map", car.getAddressMap());
			jsonObject.put("car_dsp", car.getCarDsp());
			jsonObject.put("vehicle_delivery_mode", car.getVehicleDeliveryMode());
			jsonObject.put("vehicle_age", car.getVehicleAge());
			jsonObject.put("mileage", car.getMileage());
			jsonObject.put("seat_number", car.getSeatNumber());
			jsonObject.put("user_uuid", car.getUserUuid());
			jsonObject.put("lease_time", car.getLeaseTime());
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 车主信息
	@RequestMapping(value = "home_page/list/one/owner", method = RequestMethod.POST)
	@ResponseBody
	public String orderMessage() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		// String useruuid;
		if (headInfo.get("user_uuid") == null ) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		String car_uuid = bodyInfo.getString("car_uuid");
		Car car = CarSevice.selectByUuid(car_uuid);
		// 车主信息
		Users user = userServie.selectByUserUuid(car.getUserUuid());
		if (user != null) {
			int days = DateUtil.countDays(new Date(), user.getBirthday());
			int age = days / 365;
			jsonObject.put("description", "车主信息查询成功");
			jsonObject.put("result", "0");
			jsonObject.put("user_name", user.getUserName());
			jsonObject.put("age", age);
			jsonObject.put("driving", user.getDriving());
			jsonObject.put("constellation", user.getConstellation());
			jsonObject.put("zm_open_id", user.getZmOpenId());
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "车主信息查询失败");
		}

		// 查询共享车辆
		List<Car> carlist = CarSevice.selectByUserUuid(car.getUserUuid());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// Map<String, Object>maptime=new HashMap<String, Object>();

		if (carlist.size() > 0) {
			for (Car cars : carlist) {
				if (cars != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("brand", cars.getBrand());
					map.put("car_series", cars.getBrand()); 
					list.add(map);
				}
			}
			jsonObject.put("description", "车主共享车辆查询成功");
			jsonObject.put("result", "0");
			jsonObject.put("car_name", list);
		} else {
			jsonObject.put("description", "车主共享车辆查询失败");
			jsonObject.put("result", "0");
		}

		/*
		 * List<CarModel>
		 * carlist=CarModelSevice.selectByApplyId(car.getUserUuid());
		 * List<Map<String, Object>> list = new ArrayList<Map<String,
		 * Object>>(); if(list.size()>0){ for (CarModel carModel : carlist) { if
		 * (carModel != null) { Map<String, Object> map = new HashMap<String,
		 * Object>(); map.put("brand", carModel.getBrand());
		 * map.put("car_series", carModel.getBrand()); list.add(map); } }
		 * jsonObject.put("description", "车主共享车辆查询成功"); jsonObject.put("result",
		 * "0"); jsonObject.put("car_name", list); }else{
		 * jsonObject.put("description", "车主共享车辆查询失败"); jsonObject.put("result",
		 * "0"); }
		 */

		// 驾驶时常，次数 车型查询
		int sumtime = 0;
		int biketime = 0;
		int carmodeltime = 0;
		List<Orders> order = orderService.selectByUserUuid(car.getUserUuid());
		Set<String> uuidcount = new HashSet<String>();
		List<String> list2 = new ArrayList<String>();
		if (order.size() > 0) {
			for (Orders orders : order) {
				if (orders != null) {
					sumtime += (DateUtil.countDays(orders.getReturnCarTime(), orders.getTakeCarTime()) * 24);
					uuidcount.add(orders.getCarUuid());
				}
			}
			biketime = order.size();
			carmodeltime = uuidcount.size();
		}
		for (String string : uuidcount) {
			Car car2 = CarSevice.selectByUuid(string);
			list2.add(car2.getModelCharacter());
		}
		jsonObject.put("sumtime", sumtime);
		jsonObject.put("biketime", biketime);
		jsonObject.put("carmodeltime", carmodeltime);
		jsonObject.put("driverlistory", list2);
		return buildReqJsonObject(jsonObject);
	}

	// 出租时间查询
	@RequestMapping("carList/setTime")
	@ResponseBody
	public String getTime() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		// String useruuid;
		if (headInfo.get("user_uuid") == null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		String caruuid = bodyInfo.getString("car_uuid");
		Car car = CarSevice.selectByUuid(caruuid);

		if (car != null) {
			Date shelves_start_time = car.getShelvesStartTime();
			Date shelves_end_time = car.getShelvesEndTime();
			JSONArray jsonArray = JSONArray.fromObject(car.getLeaseTime());
			jsonObject.put("description", "出租价格查询成功");
			jsonObject.put("result", "0");
			jsonObject.put("shelves_start_time", DateUtil.getDate(shelves_start_time));
			jsonObject.put("shelves_end_time", DateUtil.getDate(shelves_end_time));
			jsonObject.put("lease_time", jsonArray);
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "出租价格查询失败");
		}

		return buildReqJsonObject(jsonObject);
	}

	// 车辆配置
	@RequestMapping(value = "home_page/list/one/config", method = RequestMethod.POST)
	@ResponseBody
	public String carConfig() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		// String useruuid;
		if (headInfo.get("user_uuid") == null ) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		
		String caruuid = bodyInfo.getString("car_uuid");
		Car car = CarSevice.selectByUuid(caruuid);
		if (car != null) {
			jsonObject.put("description", "车辆信息查询成功");
			jsonObject.put("result", "0");
			jsonObject.put("vehicle_delivery_mode", car.getVehicleDeliveryMode());
			jsonObject.put("mileage", car.getMileage());
			jsonObject.put("vehicle_age", car.getVehicleAge());
			jsonObject.put("seat_number", car.getSeatNumber());
			jsonObject.put("is_navigation", car.getIsNavigation());
			jsonObject.put("is_mp3", car.getIsMp3());
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}
		return buildReqJsonObject(jsonObject);

	}

	// 自驾
	@RequestMapping(value = "home_page/selfdrive", method = RequestMethod.POST)
	@ResponseBody
	public String selfDrive() {
		JSONObject jsonObject = new JSONObject();
		List<Car> cars = new ArrayList<Car>();
		cars = CarSevice.selectAll();
		List<Map<String, Object>> carlist = new ArrayList<Map<String, Object>>();
		if (carlist.size() > 0) {
			for (Car car : cars) {
				if (car != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("carName", car.getCarName());
					map.put("license_plate_number", car.getLicensePlateNumber());
					map.put("working_day_price", car.getWorkingDayPrice());
					map.put("address", car.getAddress());
					map.put("order_quantity", car.getOrderQuantity());
					map.put("collection", car.getCollection());
					map.put("car_uuid", car.getCarUuid());
					carlist.add(map);
				}
			}
			jsonObject.put("carlist", carlist);
			jsonObject.put("description", "车辆信息查询成功");
			jsonObject.put("result", "0");
		} else {
			jsonObject.put("description", "车辆信息查询失败");
			jsonObject.put("result", "1");
		}

		return buildReqJsonObject(jsonObject);

	}

	// 搜索
	@RequestMapping(value = "home_page/selfdrive/seach/car_name", method = RequestMethod.POST)
	@ResponseBody
	public String carSeach() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		
		List<Car> carlist = new ArrayList<Car>();
		carlist = CarSevice.selectByCarName(bodyinfo.getString("car_name"));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (carlist.size() > 0) {
			for (Car car : carlist) {
				if (car != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("carName", car.getCarName());
					map.put("license_plate_number", car.getLicensePlateNumber());
					map.put("working_day_price", car.getWorkingDayPrice());
					map.put("address", car.getAddress());
					map.put("order_quantity", car.getOrderQuantity());
					map.put("collection", car.getCollection());
					map.put("car_uuid", car.getCarUuid());
					list.add(map);
				}
			}
			jsonObject.put("carlist", carlist);
			jsonObject.put("description", "车辆信息查询成功");
			jsonObject.put("result", "0");
		} else {
			jsonObject.put("description", "车辆信息查询失败");
			jsonObject.put("result", "1");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 按照品牌查询
	@RequestMapping(value = "home_page/selfdrive/seach/brand", method = RequestMethod.POST)
	@ResponseBody
	public String seachByBrand() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		
		List<Car> carlist = new ArrayList<Car>();
		carlist = CarSevice.selectByBrand(bodyinfo.getString("brand"));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (carlist.size() > 0) {
			for (Car car : carlist) {
				if (car != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("carName", car.getCarName());
					map.put("license_plate_number", car.getLicensePlateNumber());
					map.put("working_day_price", car.getWorkingDayPrice());
					map.put("address", car.getAddress());
					map.put("order_quantity", car.getOrderQuantity());
					map.put("collection", car.getCollection());
					map.put("car_uuid", car.getCarUuid());
					list.add(map);
				}
			}
			jsonObject.put("carlist", carlist);
			jsonObject.put("description", "车辆信息查询成功");
			jsonObject.put("result", "0");
		} else {
			jsonObject.put("description", "车辆信息查询失败");
			jsonObject.put("result", "1");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 按照价格查询
	@RequestMapping(value = "home_page/selfdrive/seach/price", method = RequestMethod.POST)
	@ResponseBody
	public String seachByPrice() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		
		List<Car> carlist = new ArrayList<Car>();
		String price=bodyinfo.getString("price");
		carlist = CarSevice.selectByPrice(Float.parseFloat(price));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (carlist.size() > 0) {
			for (Car car : carlist) {
				if (car != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("carName", car.getCarName());
					map.put("license_plate_number", car.getLicensePlateNumber());
					map.put("working_day_price", car.getWorkingDayPrice());
					map.put("address", car.getAddress());
					map.put("order_quantity", car.getOrderQuantity());
					map.put("collection", car.getCollection());
					map.put("car_uuid", car.getCarUuid());
					list.add(map);
				}
			}
			jsonObject.put("carlist", carlist);
			jsonObject.put("description", "车辆信息查询成功");
			jsonObject.put("result", "0");
		} else {
			jsonObject.put("description", "车辆信息查询失败");
			jsonObject.put("result", "1");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 取车时间和地点
	@RequestMapping(value = "home_page/list/one/getcartime", method = RequestMethod.POST)
	@ResponseBody
	public String getCarTime() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		
		Car car = CarSevice.selectByUuid(bodyinfo.getString("car_uuid"));
		Orders orders = new Orders();
		orders.setCarOwnerUuid(car.getUserUuid());
		orders.setCarUuid(bodyinfo.getString("car_uuid"));
		orders.setUserUuid(headInfo.getString("user_uuid"));
		// 订单号属性待修改
		//orders.setOrderNumber(System.currentTimeMillis() + RandomStringUtils.random(8));
		orders.setCreateAt(new Date());
		orders.setDepositStatus(1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = simpleDateFormat.parse(bodyinfo.getString("take_car_time"));
			orders.setTakeCarTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orders.setTakeCarAddress(bodyinfo.getString("take_car_address"));
		orders.setTakeCarAddressMap(bodyinfo.getString("take_car_address_map"));
		String ordersUuid = "";
		try {
			ordersUuid = MD5Util.md5Digest(System.currentTimeMillis() + RandomStringUtils.random(8));
			orders.setOrderUuid(ordersUuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int rs = orderService.insert(orders);

		if (rs == 1) {
			Orders order = orderService.selectByOrdersUuid(ordersUuid);
			if (order != null) {
				jsonObject.put("description", "添加成功");
				jsonObject.put("result", "0");
				jsonObject.put("take_car_time", order.getTakeCarTime());
				jsonObject.put("take_car_address", orders.getTakeCarAddress());
				jsonObject.put("take_car_address_map", orders.getTakeCarAddressMap());
				jsonObject.put("car_uuid", orders.getCarUuid());
				jsonObject.put("user_uuid", orders.getUserUuid());
				jsonObject.put("order_uuid", orders.getOrderUuid());
			}

		} else {
			jsonObject.put("description", "添加失败");
			jsonObject.put("result", "1");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 还车时间和地点
	@RequestMapping(value = "home_page/list/one/returnscartime", method = RequestMethod.POST)
	@ResponseBody
	public String returnCarTime() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		Orders orders = orderService.selectByOrdersUuid(bodyinfo.getString("order_uuid"));
		orders.setUpdateAt(new Date());
		orders.setDepositStatus(1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = simpleDateFormat.parse(bodyinfo.getString("return_car_time"));
			orders.setTakeCarTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orders.setReturnCarAddress(bodyinfo.getString("return_car_address"));
		orders.setReturnCarAddressMap(bodyinfo.getString("return_car_address_map"));
		int rs = orderService.updateByPrimaryKey(orders);

		if (rs == 1) {
			Orders order = orderService.selectByOrdersUuid(bodyinfo.getString("order_uuid"));
			if (order != null) {
				jsonObject.put("description", "添加成功");
				jsonObject.put("result", "0");
				jsonObject.put("return_car_time", order.getReturnCarTime());
				jsonObject.put("return_car_address", orders.getReturnCarAddress());
				jsonObject.put("return_car_address_map", orders.getReturnCarAddressMap());
				jsonObject.put("car_uuid", orders.getCarUuid());
				jsonObject.put("user_uuid", orders.getUserUuid());
				jsonObject.put("order_uuid", orders.getOrderUuid());
			}

		} else {
			jsonObject.put("description", "添加失败");
			jsonObject.put("result", "1");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 生成订单号
	@RequestMapping(value = "home_page/list/one/generateOrder", method = RequestMethod.POST)
	@ResponseBody
	public String generateOrders() {
		JSONObject jsonObject = new JSONObject();
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		String orderuuid = bodyinfo.getString("order_uuid");
		Orders order = orderService.selectByOrdersUuid(orderuuid);
		Car car = CarSevice.selectByUuid(order.getCarUuid());
		int datetime = DateUtil.countDays(order.getReturnCarTime(), order.getTakeCarTime());
		if (order != null && car != null) {
			jsonObject.put("order_status", order.getOrderStatus());
			jsonObject.put("take_car_address", order.getTakeCarAddress());
			jsonObject.put("return_car_address", order.getReturnCarAddress());
			jsonObject.put("daytime", datetime);
			jsonObject.put("deposit", order.getDeposit());
			jsonObject.put("total_price", order.getTotalPrice());
			jsonObject.put("car_name", car.getCarName());
			jsonObject.put("description", "查询成功");
			jsonObject.put("result", "0");
		} else {
			jsonObject.put("description", "查询失败");
			jsonObject.put("result", "1");
		}
		return buildReqJsonObject(jsonObject);

	}

}
