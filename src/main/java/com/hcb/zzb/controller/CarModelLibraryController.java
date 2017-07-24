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
import com.hcb.zzb.dto.CarModel;
import com.hcb.zzb.service.ICarModel;


import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="carMoldeLibrary")
public class CarModelLibraryController extends BaseControllers{
	@Autowired
	ICarModel carModelService; 
	
	
	/**
	 * 车型库管理（车型列表）
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String findCarModelList() {
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
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		List<CarModel> list=new ArrayList<>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			int start=(pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			if(bodyInfo.get("brand")!=null && !"".equals(bodyInfo.get("brand"))) {
				map.put("brand", bodyInfo.getString("brand"));
			}
			list=carModelService.selectByMapLimit(map);
			Integer count=carModelService.countByMap(map);
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
		return buildReqJsonObject(model);
	}
	
	/**
	 * 车型库管理（导出Excel）
	 * @return
	 */
	public String exportExcel() {
		
		return "";
	}
	
	/**
	 * 车型库管理（车型详情）
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String carModelDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("car_model_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("car_model_uuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		CarModel carModel= carModelService.selectByUuid(bodyInfo.getString("car_model_uuid"));
		ModelMap model=new ModelMap();
		if(carModel==null) {
			json.put("result", "1");
			json.put("description", "未查询到");
			return buildReqJsonObject(json);
		}else {
			
			model.put("result", "0");
			model.put("description", "查询成功");
			model.put("brand", carModel.getBrand());
			model.put("carSeries", carModel.getCarSeries());
			model.put("modelYear", carModel.getModelYear());
			model.put("carModel", carModel.getCarModel());
			model.put("color", carModel.getColor());
		}
		return buildReqJsonObject(model);
	}
	
	/**
	 * 编辑车型后保存信息（修改）
	 * @return
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public String saveEdit() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("car_model_uuid")==null||bodyInfo.get("brand")==null||bodyInfo.get("carSeries")==null||bodyInfo.get("modelYear")==null
				||bodyInfo.get("carModel")==null||bodyInfo.get("color")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("car_model_uuid"))||bodyInfo.get("brand").equals("")||bodyInfo.get("carSeries").equals("")||bodyInfo.get("modelYear").equals("")
				||bodyInfo.get("carModel").equals("")||bodyInfo.get("color").equals("")) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		CarModel carModel= carModelService.selectByUuid(bodyInfo.getString("car_model_uuid"));
		ModelMap model=new ModelMap();
		if(carModel==null) {
			json.put("result", "1");
			json.put("description", "未查询到,uuid不正确");
			return buildReqJsonObject(json);
		}else {	
			carModel.setBrand(bodyInfo.getString("brand"));	
			carModel.setCarSeries(bodyInfo.getString("carSeries"));
			carModel.setModelYear(bodyInfo.getString("modelYear"));
			carModel.setCarModel(bodyInfo.getString("carModel"));
			carModel.setColor(bodyInfo.getString("color"));
		
			int i=carModelService.updateByPrimaryKeySelective(carModel);
			if(i>0) {
				model.put("result", "0");
				model.put("description", "保存成功");
			}else {
				model.put("result", "1");
				model.put("description", "保存失败");
			}
		}			
		return buildReqJsonObject(model);
	}
	
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String deleteCarModel() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("car_model_uuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("car_model_uuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		CarModel carModel=carModelService.selectByUuid(bodyInfo.getString("car_model_uuid"));
		if(carModel!=null) {
			carModel.setDeleteAt(new Date());
			int rs = 0;
			rs = carModelService.updateByPrimaryKeySelective(carModel);
			if(rs == 1) {
				json.put("result", "0");
				json.put("description", "删除成功");
			}else {
				json.put("result", "0");
				json.put("description", "删除失败，请重新尝试");
			}
		}else {
			json.put("result", "1");
			json.put("description", "删除失败，未查询到相关信息");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
}
