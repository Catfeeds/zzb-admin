package com.hcb.zzb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.CarModel;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.ICarModel;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.MD5Util;
import com.sun.org.apache.bcel.internal.generic.NEW;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("user")
public class UserController extends BaseControllers {
	@Autowired
	IUsersService IUsersService;
	@Autowired
	private ICarSevice carservice;
	@Autowired
	private ICarModel carModelService;
//我的首页
	@RequestMapping(value = "infoindex", method = RequestMethod.POST)
	@ResponseBody
	public String MyIndex() {
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
		
		Users users = IUsersService.selectByUserUuid(headInfo.get("user_uuid").toString());
		if (null != users) {
			jsonObject.put("description", "我的信息");
			jsonObject.put("result", "0");
			jsonObject.put("user_uuid", users.getUserUuid());
			jsonObject.put("headimgurl", users.getAvater());
			jsonObject.put("phone", users.getUserPhone());
			jsonObject.put("riders_status", users.getRidersStatus());
			jsonObject.put("balance", users.getBalance());
		}else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}
		return buildReqJsonObject(jsonObject);
	}
//我的资料
	@RequestMapping(value = "info/ziliao", method = RequestMethod.POST)
	@ResponseBody
	public String MyIndexMessage() {
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
		Users users = IUsersService.selectByUserUuid(headInfo.get("user_uuid").toString());
		// ModelMap modelMap = new ModelMap();
		if ( users!=null) {
			jsonObject.put("description", "查询成功");
			jsonObject.put("result", "0");
			jsonObject.put("user_uuid", users.getUserUuid());
			jsonObject.put("headimgurl", users.getAvater());
			jsonObject.put("user_name", users.getUserName());
			jsonObject.put("phone", users.getUserPhone());
			jsonObject.put("id_picture", users.getIdPicture());
			jsonObject.put("id_number", users.getIdNumber());
			jsonObject.put("driving", users.getDriving());
			jsonObject.put("constellation", users.getConstellation());
			jsonObject.put("authentication_status", users.getAuthenticationStatus());
			jsonObject.put("zm_open_id", users.getZmOpenId());
			jsonObject.put("wx_open_id", users.getWxOpenId());
			jsonObject.put("qq_open_id", users.getQqOpenId());

		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}
		return buildReqJsonObject(jsonObject);
	}
//保存我的资料
	@RequestMapping(value = "info/save", method = RequestMethod.POST)
	@ResponseBody
	public String SaveMyMessage() {
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
		
		Users users = IUsersService.selectByUserUuid(headInfo.getString("user_uuid"));
		users.setUserName(bodyinfo.getString("user_name"));
		users.setAvater(bodyinfo.getString("avater"));
		users.setUserPhone(bodyinfo.getString("user_phone"));
		users.setIdPicture(bodyinfo.getString("id_picture"));
		users.setIdNumber(bodyinfo.getString("id_number"));
		users.setDriving(bodyinfo.getInt("driving"));
		users.setConstellation(bodyinfo.getString("constellation"));
		users.setAuthenticationStatus(bodyinfo.getInt("authentication_status"));
		users.setZmOpenId(bodyinfo.getString("zm_open_id"));
		users.setWxOpenId(bodyinfo.getString("wx_open_id"));
		users.setQqOpenId(bodyinfo.getString("qq_open_id"));
		Integer rs = IUsersService.updateByPrimaryKey(users);
		if (rs == 1) {
			jsonObject.put("result", "0");
			jsonObject.put("description", "保存成功");
			jsonObject.put("users", users);
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "保存失败");
		}

		return buildReqJsonObject(jsonObject);
	}
//上传身份信息
	@RequestMapping(value = "info/id_picture", method = RequestMethod.POST)
	@ResponseBody
	public String uploadIdPictureList() {
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
		Users users = IUsersService.selectByUserUuid(headInfo.getString("user_uuid"));
		users.setIdPicture(bodyinfo.getString("id_picture"));
		Integer rs = IUsersService.updateByPrimaryKey(users);
		if (rs == 1) {
			jsonObject.put("result", "0");
			jsonObject.put("description", "查询成功");
			jsonObject.put("users", users);
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}
		return buildReqJsonObject(jsonObject);
	}
//我的车辆
	@RequestMapping(value = "carList", method = RequestMethod.POST)
	@ResponseBody
	public String uploadCar() {
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
		carlist = carservice.selectByUserUuid(headInfo.getString("user_uuid"));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (carlist.size() >0) {
			for (Car car : carlist) {
				if (car != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("car_name", car.getCarName());
					map.put("license_plate_number", car.getLicensePlateNumber());
					map.put("man_car_photo", car.getManCarPhoto());
					map.put("car_status", car.getCarStatus());
					map.put("car_uuid", car.getCarUuid());
					list.add(map);
				} else {
					jsonObject.put("result", "1");
					jsonObject.put("description", "查询失败");
				}
			}
			jsonObject.put("result", "0");
			jsonObject.put("description", "查询成功");
			jsonObject.put("list", list);
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}

		return buildReqJsonObject(jsonObject);
	}
//车辆详情基础
	@RequestMapping(value = "carList/detail", method = RequestMethod.POST)
	@ResponseBody
	public String CarDetail() {
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
		Car car = carservice.selectByUuid(bodyinfo.getString("car_uuid"));
		if (car != null) {
			jsonObject.put("result", "0");
			jsonObject.put("description", "查询成功");
			jsonObject.put("car_status", car.getCarStatus());
			jsonObject.put("license_plate_number", car.getLicensePlateNumber());
			jsonObject.put("car_name", car.getCarName());
			jsonObject.put("city", car.getCity());
			jsonObject.put("car_owner_name", car.getCarOwnerName());
			jsonObject.put("register_time", car.getRegisterTime());
			jsonObject.put("driving_license_photo", car.getDrivingLicensePhoto());
			jsonObject.put("man_car_photo", car.getManCarPhoto());
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 车辆出租
	@RequestMapping(value = "carList/rent", method = RequestMethod.POST)
	@ResponseBody
	public String CartRent() {
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
		
		Car car = carservice.selectByUuid(bodyinfo.getString("car_uuid"));
		/*List<Car> listcar = new ArrayList<Car>();
		listcar.add(car);*/
		if (car != null && car.getCarType() == 1) {
			jsonObject.put("result", "0");
			jsonObject.put("description", "查询成功");
			//jsonObject.put("list", listcar);
			jsonObject.put("seachcar", car);
			
			/*
			 * jsonObject.put("working_day_price", car.getWorkingDayPrice());
			 * jsonObject.put("weekend_price", car.getWeekendPrice());
			 * jsonObject.put("vehicle_delivery_mode",
			 * car.getVehicleDeliveryMode()); jsonObject.put("mileage",
			 * car.getMileage()); jsonObject.put("vehicle_age",
			 * car.getVehicleAge()); jsonObject.put("seat_number",
			 * car.getSeatNumber()); jsonObject.put("is_navigation",
			 * car.getIsNavigation()); jsonObject.put("is_mp3", car.getIsMp3());
			 * jsonObject.put("car_dsp", car.getCarDsp());
			 * jsonObject.put("man_car_photo", car.getManCarPhoto());
			 */
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}
		car.setWeekendPrice(Float.parseFloat(bodyinfo.getString("weekend_price")));
		car.setWorkingDayPrice(Float.parseFloat(bodyinfo.getString("working_day_price")));
		car.setVehicleDeliveryMode(bodyinfo.getInt("vehicle_delivery_mode"));
		car.setMileage(bodyinfo.getInt("mileage"));
		car.setVehicleAge(bodyinfo.getInt("vehicle_age"));
		car.setSeatNumber(bodyinfo.getInt("seat_number"));
		car.setIsNavigation(bodyinfo.getInt("is_navigation"));
		car.setIsMp3(bodyinfo.getInt("is_mp3"));
		car.setCarDsp(bodyinfo.getString("car_dsp"));
		car.setManCarPhoto(bodyinfo.getString("man_car_photo"));
		int rs = carservice.updateByPrimaryKey(car);
		Car carsave = carservice.selectByUuid(bodyinfo.getString("car_uuid"));
		if (rs == 1) {
			jsonObject.put("result", "0");
			jsonObject.put("description", "保存成功");
			jsonObject.put("working_day_price", carsave.getWorkingDayPrice());
			jsonObject.put("weekend_price", carsave.getWeekendPrice());
			jsonObject.put("vehicle_delivery_mode", carsave.getVehicleDeliveryMode());
			jsonObject.put("mileage", carsave.getMileage());
			jsonObject.put("vehicle_age", carsave.getVehicleAge());
			jsonObject.put("seat_number", carsave.getSeatNumber());
			jsonObject.put("is_navigation", carsave.getIsNavigation());
			jsonObject.put("is_mp3", carsave.getIsMp3());
			jsonObject.put("car_dsp", carsave.getCarDsp());
			jsonObject.put("man_car_photo", carsave.getManCarPhoto());
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "保存失败");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 修改车辆基本信息
	@RequestMapping(value = "carList/detail/edit", method = RequestMethod.POST)
	@ResponseBody
	public String editCarMessage() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if(headInfo.get("user_uuid")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		
		Car car = carservice.selectByUuid(bodyInfo.getString("car_uuid"));
		//List<Car> listcar = new ArrayList<Car>();
		//listcar.add(car);
		if (car != null && car.getCarType() == 1) {
			jsonObject.put("result", "0");
			jsonObject.put("description", "查询成功");
			jsonObject.put("car_owner_name", car.getCarOwnerName());
			jsonObject.put("license_plate_number", car.getLicensePlateNumber());
			jsonObject.put("vehicle_identification_number", car.getVehicleIdentificationNumber());
			jsonObject.put("engine_number", car.getEngineNumber());
			jsonObject.put("color", car.getColor());
			jsonObject.put("brand", car.getBrand());
			jsonObject.put("register_time", car.getRegisterTime());
			jsonObject.put("car_name", car.getCarName());
			jsonObject.put("city", car.getCity());
			jsonObject.put("model_character", car.getModelCharacter());
			jsonObject.put("manual_or_automatic", car.getManualOrAutomatic());
			jsonObject.put("seat_number", car.getSeatNumber());
			jsonObject.put("displacement", car.getDisplacement());
			jsonObject.put("model", car.getModel());
			jsonObject.put("driving_license_photo", car.getDrivingLicensePhoto());
			jsonObject.put("man_car_photo", car.getManCarPhoto());
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}
		
		car.setCarOwnerName(bodyInfo.getString("car_owner_name"));
		car.setLicensePlateNumber(bodyInfo.getString("license_plate_number"));
		car.setVehicleIdentificationNumber(bodyInfo.getString("vehicle_identification_number"));
		car.setEngineNumber(bodyInfo.getString("engine_number"));
		car.setColor(bodyInfo.getString("color"));
		car.setBrand(bodyInfo.getString("brand"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		Date date;
		try {
			date = simpleDateFormat.parse(bodyInfo.getString("register_time"));
			car.setRegisterTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		car.setCarName(bodyInfo.getString("car_name"));
		car.setCity(bodyInfo.getString("city"));
		car.setModelCharacter(bodyInfo.getString("model_character"));
		Integer integer = Integer.valueOf(bodyInfo.getString("manual_or_automatic"));
		car.setManualOrAutomatic(integer);
		Integer integer1 = Integer.valueOf(bodyInfo.getString("seat_number"));
		car.setSeatNumber(integer1);
		car.setDisplacement(bodyInfo.getString("displacement"));
		// car.setRegisterTime(bodyInfo.getString("register_time"));
		car.setModel(bodyInfo.getString("model"));
		car.setDrivingLicensePhoto(bodyInfo.getString("driving_license_photo"));
		car.setManCarPhoto(bodyInfo.getString("man_car_photo"));
		int rs = carservice.updateByPrimaryKey(car);
		Car carsave = carservice.selectByUuid(bodyInfo.getString("car_uuid"));
		if (rs == 1) {
			jsonObject.put("result", "0");
			jsonObject.put("description", "保存成功");
			jsonObject.put("car", carsave);
			

		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "保存失败");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 车型列表
	@RequestMapping(value = "carList/detail/edit/modelList", method = RequestMethod.POST)
	@ResponseBody
	public String modelList() {
		JSONObject jsonObject = new JSONObject();

		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		// JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		// String useruuid;
		if (headInfo.get("user_uuid") == null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		List<CarModel> list = new ArrayList<CarModel>();
		// List<CarModel> hotlist=new ArrayList<CarModel>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		//如果需要显示圆形上面热门在用List<Map<String, Object>> hotlist = new ArrayList<Map<String, Object>>();
		list = carModelService.selectAll();
		if (list.size() > 0) {
			for (CarModel carModel : list) {
				if (carModel != null) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("brand", carModel.getBrand());
					list2.add(map1);
					/*if (carModel.getBrand() == "大众" || carModel.getBrand() == "丰田" || carModel.getBrand() == "现代"
							|| carModel.getBrand() == "日产" || carModel.getBrand() == "本田" || carModel.getBrand() == "别克"
							|| carModel.getBrand() == "奔驰" || carModel.getBrand() == "奥迪" || carModel.getBrand() == "宝马"
							|| carModel.getBrand() == "标志") {
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("brand", carModel.getBrand());
						list2.add(map1);
					} else {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("brand", carModel.getBrand());
						hotlist.add(map);
					}*/
				} else {
					jsonObject.put("result", "1");
					jsonObject.put("description", "查询失败");
				}
			}
			jsonObject.put("result", "0");
			jsonObject.put("description", "查询成功");
			jsonObject.put("brands", list2);
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}

		return buildReqJsonObject(jsonObject);
	}

	// 添加车型
	@RequestMapping(value = "carList/detail/edit/modelList/addmodel", method = RequestMethod.POST)
	@ResponseBody
	public String addCarModel() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		// String useruuid;
		if (headInfo.get("user_uuid") == null || bodyinfo.get("brand_model") == null || bodyinfo.get("brand") == null
				|| bodyinfo.get("car_series") == null || bodyinfo.get("model_year") == null
				|| bodyinfo.get("transmission_case") == null || bodyinfo.get("displacement") == null
				|| bodyinfo.get("car_model") == null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");

		}
		CarModel carmodel = new CarModel();
		carmodel.setCreateAt(new Date());
		String carModelUuid = "";
		try {
			carModelUuid = MD5Util.md5Digest(System.currentTimeMillis() + RandomStringUtils.random(8));
			carmodel.setCarModelUuid(carModelUuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		carmodel.setBrandModel(bodyinfo.get("brand_model").toString());
		carmodel.setBrand(bodyinfo.get("brand").toString());
		carmodel.setCarSeries(bodyinfo.get("car_series").toString());
		carmodel.setModelYear(bodyinfo.get("model_year").toString());
		carmodel.setTransmissionCase(bodyinfo.get("transmission_case").toString());
		carmodel.setDisplacement(bodyinfo.get("displacement").toString());
		carmodel.setCarModel(bodyinfo.get("car_model").toString());
		carmodel.setApplyUserUuid(headInfo.get("user_uuid").toString());
		carmodel.setApplyStatus(1);
		int rs = carModelService.insert(carmodel);
		if (rs == 1) {
			jsonObject.put("result", "0");
			jsonObject.put("description", "添加成功");
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "添加失败");
		}
		return buildReqJsonObject(jsonObject);
	}

	// 选择车系
	@RequestMapping(value = "carList/detail/edit/modelList/car_seriesList", method = RequestMethod.POST)
	@ResponseBody
	public String seachCarSeries() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyinfo = JSONObject.fromObject(bodyString);
		// String useruuid;
		if (headInfo.get("user_uuid") == null || bodyinfo.get("brand") == null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		List<CarModel> list = new ArrayList<CarModel>();
		// List<CarModel> hotlist=new ArrayList<CarModel>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		//国产待定List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		list = carModelService.selectByCarBrand(bodyinfo.get("brand").toString());
		if (list.size() > 0) {
			for (CarModel carModel : list) {
				if (carModel != null && carModel.getApplyStatus() == 2) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("car_series", carModel.getCarSeries());
					list2.add(map);
				}
			}
			jsonObject.put("result", "0");
			jsonObject.put("description", "查询成功");
			jsonObject.put("jinkou", list2);
		} else {
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询失败");
		}

		return buildReqJsonObject(jsonObject);
	}

	// 设置车辆出租时间
	@RequestMapping(value ="carList/setTime", method = RequestMethod.POST)
	@ResponseBody
	public String setTime() {
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		// String useruuid;
		if (headInfo.get("user_uuid") == null || bodyInfo.get("shelves_start_time") == null||bodyInfo.get("shelves_end_time") == null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		
		String car_uuid = bodyInfo.getString("car_uuid");
		Car car = carservice.selectByUuid(car_uuid);
		//String date = Arrays.toString(bodyInfo.getString("lease_time"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date enddate;
		Date startdate;
		if (car != null) {
			try {
				enddate = simpleDateFormat.parse(bodyInfo.getString("shelves_end_time"));
				startdate = simpleDateFormat.parse(bodyInfo.getString("shelves_end_time"));
				car.setShelvesEndTime(enddate);
				car.setShelvesStartTime(startdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			car.setLeaseTime(bodyInfo.getString("lease_time"));
			int rs = carservice.updateByPrimaryKey(car);
			if (rs == 1) {
				jsonObject.put("result", "0");
				jsonObject.put("description", "设置成功");
			} else {
				jsonObject.put("result", "1");
				jsonObject.put("description", "设置失败");
			}
		}
		return buildReqJsonObject(jsonObject);
	}
	//查询绑定状态
	@RequestMapping(value="bandstatus",method=RequestMethod.POST)
	@ResponseBody
	public String bandStatus(){
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (headInfo.get("user_uuid") == null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		Users users = IUsersService.selectByUserUuid(headInfo.getString("user_uuid"));
		if(users!=null){
			if(users.getWxOpenId()!=null&&users.getWxOpenId()!=""){
				jsonObject.put("wxresult", "已绑定微信");
			}else{
				jsonObject.put("wxresult", "未绑定微信");
			}
			if(users.getQqOpenId()!=null&&users.getQqOpenId()!=""){
				jsonObject.put("qqresult", "已绑定qq");
			}else {
				jsonObject.put("qqresult", "未绑定qq");
			}
			jsonObject.put("result", "0");
			jsonObject.put("description", "查询状态成功");
		}else{
			jsonObject.put("result", "1");
			jsonObject.put("description", "查询状态失败");
		}
		return buildReqJsonObject(jsonObject);
		
	}
	
	//绑定微信
	@RequestMapping(value="bandwx",method=RequestMethod.POST)
	@ResponseBody
	public String bandWX(){
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (headInfo.get("user_uuid") == null ||bodyInfo.getString("wx_open_id")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		Users users = IUsersService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		users.setWxOpenId(bodyInfo.getString("wx_open_id"));
		int rs = IUsersService.updateByPrimaryKeySelective(users);
		if(rs==1){
			jsonObject.put("result", "0");
			jsonObject.put("description", "微信绑定成功");
		}else{
			jsonObject.put("result", "1");
			jsonObject.put("description", "微信绑定失败");
		}
		return buildReqJsonObject(jsonObject);
	}
	
	//绑定QQ
	@RequestMapping(value="bandqq",method=RequestMethod.POST)
	@ResponseBody
	public String bandQQ(){
		JSONObject jsonObject = new JSONObject();
		if (sign == 1 || sign == 2) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, jsonObject);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (headInfo.get("user_uuid") == null||bodyInfo.getString("qq_open_id")==null) {
			jsonObject.put("result", "1");
			jsonObject.put("description", "请检查参数格式是否正确或者参数是否完整");
		}
		Users users = IUsersService.selectByUserUuid(bodyInfo.getString("user_uuid"));
		users.setQqOpenId(bodyInfo.getString("qq_open_id"));
		int rs = IUsersService.updateByPrimaryKeySelective(users);
		if(rs==1){
			jsonObject.put("result", "0");
			jsonObject.put("description", "微信绑定成功");
		}else{
			jsonObject.put("result", "1");
			jsonObject.put("description", "微信绑定失败");
		}
		return buildReqJsonObject(jsonObject);
	}
	
	
}
