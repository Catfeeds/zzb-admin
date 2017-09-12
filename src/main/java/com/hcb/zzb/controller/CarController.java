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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.BrowseLog;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.PushInfo;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.service.IBrowseLogService;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.IPushInfoService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.HttpGet;
import com.hcb.zzb.util.MD5Util;

import net.sf.json.JSONObject;
/**
 * 车辆car
 * @author kk
 *
 */
@Controller
@RequestMapping(value="car")
public class CarController extends BaseControllers{
	@Autowired
	private ICarSevice carService;
	@Autowired
	private IUsersService userService;
	@Autowired
	private IPushInfoService pushInfoService;
	@Autowired
	private IBrowseLogService browseLogService;
	@Autowired
	IOrderService orderService;
	/**
	 * 车辆列表
	 * @return0..........................
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public String findCarList() {
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
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		Integer start=(pageIndex-1)*pageSize;
		Integer end=pageSize;
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		}
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		if(bodyInfo.get("modelCharacter")!=null&&!"".equals(bodyInfo.get("modelCharacter"))) {
			map.put("modelCharacter", bodyInfo.getString("modelCharacter"));
		}
		if(bodyInfo.get("orderBy")!=null&&!"".equals(bodyInfo.get("orderBy"))) {
			map.put("orderBy", bodyInfo.getInt("orderBy"));
		}else {
			map.put("orderBy", 2);
		}
		int count=carService.countSelectByMapLimit(map);
		if(count==0) {
			json.put("result", "1");
			json.put("description", "没有查询到车辆信息");
			return buildReqJsonObject(json);
		}
		int total = count % pageSize == 0 ? count/pageSize:count/pageSize+1;
		if(pageIndex > total) {
			json.put("result", "1");
			json.put("description", "操作失败,pageIndex大于总页数");
			return buildReqJsonObject(json);
		}
		List<Car> list = carService.selectByMapLimit(map);
		List<Map<String, Object>> listlist=new ArrayList<Map<String, Object>>();
		Map<String, Object> mapppp=new HashMap<String, Object>();
		///mapppp.put("list", list);
		///listlist.add(mapppp);
		int ordercount;//
		if(!list.isEmpty()) {
			for (Car car : list) {
				Map<String, Object> mappp=new HashMap<String, Object>();
				if(car!=null){
					String uuid = car.getUserUuid();
					String carUuid = car.getCarUuid();
					mappp.put("car", car);
					mappp.put("user_uuid", uuid);
					mappp.put("car_uuid",carUuid );
					ordercount=orderService.selectCount(carUuid);//订单数
					Map<String, Object> mapp=new HashMap<>();
					mapp.put("user_uuid", uuid);
					mapp.put("car_uuid",carUuid );
					BrowseLog browseLogg=browseLogService.selectByUserIdAndCarId(mapp);
					if(browseLogg==null){
						//json.put("updatetime", new Date());//最后登录时间
						//json.put("updatetime", new Date());//最后登录时间
						mappp.put("updatetime", new Date());
					}else{
						//json.put("updatetime", browseLogg.getUpdateAt());
						mappp.put("updatetime", browseLogg.getUpdateAt());
					}
					Users user = userService.selectByUserOwnerUuid(uuid);
					//json.put("user", user);//用户ID（绑定+超链）
					//json.put("status", 1);//状态（服务中、等待接单、离线中、失联中——可定义最好）
					//json.put("rate", 0);////差价利润
					//json.put("hot", 0);//需求热度
					//json.put("price", 0f);//上架价格（需审核，并且需定义到服役时间）
					//json.put("ordercount", ordercount);//订单数
					mappp.put("user", user);
					mappp.put("status", 1);
					mappp.put("rate", 0);
					mappp.put("hot", 0);
					mappp.put("price", 0f);
					int collec=car.getCollection()==null?0:car.getCollection();
					mappp.put("ordercount", ordercount*10+collec*1);
				}else{
					//json.put("updatetime", new Date());
					//json.put("user", user);//用户ID（绑定+超链）
					//json.put("status", 1);//状态（服务中、等待接单、离线中、失联中——可定义最好）
					//json.put("rate", 0);////差价利润
					//json.put("hot", 0);//需求热度
					//json.put("price", 0f);//上架价格（需审核，并且需定义到服役时间）
					//json.put("ordercount", 0);//订单数
					mappp.put("user", "");
					mappp.put("status", 1);
					mappp.put("rate", 0);
					mappp.put("hot", 0);
					mappp.put("price", 0f);
					mappp.put("ordercount", 0);
				}
				listlist.add(mappp);
			}
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("total", total);
			json.put("page", pageIndex);
			//json.put("carList", list);
			json.put("carList", listlist);
		}else {
			json.put("result", "1");
			json.put("description", "没有查询到车辆信息");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 车辆详情
	 * @return
	 */
	@RequestMapping(value="detail",method=RequestMethod.POST)
	@ResponseBody
	public String carDetail() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("carUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("carUuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Car car = carService.selectByUuid(bodyInfo.getString("carUuid"));
		if(car!=null) {
			String userUuid = car.getUserUuid();
			Users user = userService.selectByUserUuid(userUuid);
			String name="";
			if(user!=null){
				name=user.getUserName();
			}
			else{
				name="";
			}
			car.setUserName(name);
			json.put("userName", car.getUserName());
			json.put("result", "0");
			json.put("description", "查询成功");
			json.put("car", car);
		}else {
			json.put("result", "1");
			json.put("description", "carUuid不正确,没有查询到该车辆");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 通过、冻结、驳回车辆
	 * @return
	 */
	@RequestMapping(value="updateCarStatus",method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String operationCarStatus() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("carUuid")==null||bodyInfo.get("carStatus")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("carUuid"))||"".equals(bodyInfo.get("carStatus"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Car car=carService.selectByUuid(bodyInfo.getString("carUuid"));
		if(car!=null) {
			Users user = userService.selectByUserUuid(car.getUserUuid());
			if(user == null){
				json.put("result", "1");
				json.put("description", "操作失败，没有查询到该车辆的用户");
				return buildReqJsonObject(json);
			}
			car.setUpdateAt(new Date());
			car.setCarStatus(bodyInfo.getInt("carStatus"));
			int rs=0;
			rs = carService.updateByPrimaryKeySelective(car);
			if(rs==1) {
				if(bodyInfo.getInt("carStatus")==2){//如果通过，设置上传车辆的人为车主身份
					
					user.setUserType(1);//1车主 2车友
					userService.updateByPrimaryKeySelective(user);
					
					//推送消息
					PushInfo push = new PushInfo();
					push.setCreateDatetime(new Date());
					push.setGroups("article");
					push.setUserUuid(user.getUserUuid());
					push.setPushTitle("至尊宝");
					push.setPushDsp("您的车辆已通过审核");
					push.setPushDatetime(new Date());
					push.setPushType(8);
					try {
						push.setPushUuid(MD5Util.md5Digest(user.getUserUuid() + System.currentTimeMillis() + RandomStringUtils.random(8)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				   Integer rs1 = pushInfoService.insertSelective(push);
					if(rs1==1){
						String url = "http://app.zzbcar.com/zzb-java/phppushinfo";
						String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
						System.out.println("================="+str);
						//推送消息
						new Thread(new Runnable() {
							public void run() {
								PushInfo pushInfo = pushInfoService.selectByPushUuid(push.getPushUuid());
								if(pushInfo!=null){
									String url = "http://app.zzbcar.com/zzb-java/phppushinfo";
									String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
									System.out.println("================="+str);
								}
							}
						}).start();
					}
				
					////////////推送结束
				}else if(bodyInfo.getInt("carStatus")==3){
					//推送消息
					PushInfo push = new PushInfo();
					push.setCreateDatetime(new Date());
					push.setGroups("article");
					push.setUserUuid(user.getUserUuid());
					push.setPushTitle("至尊宝");
					push.setPushDsp("很遗憾！您的车辆未通过审核");
					push.setPushDatetime(new Date());
					push.setPushType(8);
					try {
						push.setPushUuid(MD5Util.md5Digest(user.getUserUuid() + System.currentTimeMillis() + RandomStringUtils.random(8)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				   Integer rs1 = pushInfoService.insertSelective(push);
					if(rs1==1){
						String url = "http://app.zzbcar.com/zzb-java/phppushinfo";
						String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
						System.out.println("================="+str);
						//推送消息
						new Thread(new Runnable() {
							public void run() {
								PushInfo pushInfo = pushInfoService.selectByPushUuid(push.getPushUuid());
								if(pushInfo!=null){
									String url = "http://app.zzbcar.com/zzb-java/phppushinfo";
									String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
									System.out.println("================="+str);
								}
							}
						}).start();
					}
				
					////////////推送结束
				}else if(bodyInfo.getInt("carStatus")==4){
					//推送消息
					PushInfo push = new PushInfo();
					push.setCreateDatetime(new Date());
					push.setGroups("article");
					push.setUserUuid(user.getUserUuid());
					push.setPushTitle("至尊宝");
					push.setPushDsp("您的车辆被冻结");
					push.setPushDatetime(new Date());
					push.setPushType(8);
					try {
						push.setPushUuid(MD5Util.md5Digest(user.getUserUuid() + System.currentTimeMillis() + RandomStringUtils.random(8)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				   Integer rs1 = pushInfoService.insertSelective(push);
					if(rs1==1){
						String url = "http://app.zzbcar.com/zzb-java/phppushinfo";
						String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
						System.out.println("================="+str);
						//推送消息
						new Thread(new Runnable() {
							public void run() {
								PushInfo pushInfo = pushInfoService.selectByPushUuid(push.getPushUuid());
								if(pushInfo!=null){
									String url = "http://app.zzbcar.com/zzb-java/phppushinfo";
									String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
									System.out.println("================="+str);
								}
							}
						}).start();
					}
				
					////////////推送结束
				}else if(bodyInfo.getInt("carStatus")==5){
					//推送消息
					PushInfo push = new PushInfo();
					push.setCreateDatetime(new Date());
					push.setGroups("article");
					push.setUserUuid(user.getUserUuid());
					push.setPushTitle("至尊宝");
					push.setPushDsp("您的车辆被驳回");
					push.setPushDatetime(new Date());
					push.setPushType(8);
					try {
						push.setPushUuid(MD5Util.md5Digest(user.getUserUuid() + System.currentTimeMillis() + RandomStringUtils.random(8)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				   Integer rs1 = pushInfoService.insertSelective(push);
					if(rs1==1){
						String url = "http://app.zzbcar.com/zzb-java/phppushinfo";
						String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
						System.out.println("================="+str);
						//推送消息
						new Thread(new Runnable() {
							public void run() {
								PushInfo pushInfo = pushInfoService.selectByPushUuid(push.getPushUuid());
								if(pushInfo!=null){
									String url = "http://app.zzbcar.com/zzb-java/phppushinfo";
									String str = HttpGet.sendGet(url, "push_uuid="+push.getPushUuid());
									System.out.println("================="+str);
								}
							}
						}).start();
					}
					////////////推送结束
				}
				
				json.put("result", "0");
				json.put("description", "操作成功");
			}else {
				json.put("result", "1");
				json.put("description", "操作失败");
			}
			
		}else {
			json.put("result", "1");
			json.put("description", "carUuid不正确,没有查询到该车辆");
		}
		return buildReqJsonObject(json);
	}
	
	/**
	 * 删除车辆
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String deleteCar() {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("carUuid")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("carUuid"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Car car = carService.selectByUuid(bodyInfo.getString("carUuid"));
		if(car!=null) {
			car.setDeleteAt(new Date());
			int rs = carService.updateByPrimaryKeySelective(car);
			if(rs==1) {
				json.put("result", "0");
				json.put("description", "删除成功");
			}else {
				json.put("result", "1");
				json.put("description", "删除失败");
			}			
		}else {
			json.put("result", "1");
			json.put("description", "carUuid不正确,没有查询到该车辆,或者该车辆已被删除");
		}
		return buildReqJsonObject(json);
	}
	
}
