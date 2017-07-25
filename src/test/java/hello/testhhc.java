package hello;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

/**
 * [说明/描述]
 * @author ChenJianQiang
 * @date 2016-5-11 上午08:55:36
 * @company Gainet
 * @version 1.0
 * @copyright copyright (c) 2016
 */
public class testhhc {
	
	private static SimpleDateFormat  form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {	

   	    HashMap<String, Object> map = new HashMap<String, Object>();
     	// map集合
        HashMap<String, Object> head = new HashMap<String, Object>();
        head.put("account", "admin");
        //head.put("manager_uuid", "76ef6325b002133aa32d3ea7f0be832c");
        head.put("password", "123456");
        map.put("head", head);	
        
        HashMap<String, Object> body = new HashMap<String, Object>();
        //body.put("price", "222");
        //body.put("operation_info", "w");
        //body.put("id", 11);
       // body.put("operation_picture", "http://living.cto1024.com/10c7855f31113a24745cdff9213549dc.jpg");
       // body.put("is_display", 2);
       body.put("pageIndex", 1);
       body.put("pageSize", 5);
        body.put("tittle", "zzzzz");
        //body.put("content", "zxxxxxxxx");
        //body.put("return_car_time", "2017-07-30 20:40:15");
        //body.put("return_car_address", "西湖区康乐新村xxx");
        //body.put("return_car_address_map", "1233,90.11");
        //body.put("order_uuid", "675cd2063021e7a100d8536196dcb7f0");
       // body.put("shelves_end_time", "2017-02-03");
        //body.put("lease_time", "[2017-01-05,2017-01-07]");
        //body.put("car_uuid", "5741951256ce34f7eb575c438b06ae69");
        //body.put("brand_model", "嘻嘻123");
        //body.put("brand", "凯迪拉克");
        //body.put("car_series", "s90");
        //body.put("model_year", "1993");
        //body.put("transmission_case", "cxs");
        //body.put("displacement", "100");
        //body.put("car_model", "国产100");
        /*body.put("car_owner_name", "11.11");
        body.put("license_plate_number", "10.21");
        body.put("vehicle_identification_number", "1");
        body.put("engine_number", "22");
        body.put("color", "12");
        body.put("brand", "5");
        body.put("register_time", "1");
        body.put("car_name", "1");
        body.put("city", "111");
        body.put("model_character", "33");
        body.put("manual_or_automatic", "1");
        body.put("seat_number", "5");
        body.put("displacement", "4");
        body.put("model", "3");
        body.put("driving_license_photo", "2");
        body.put("order_uuid", "675cd2063021e7a100d8536196dcb7f0");*/
        
        map.put("body",body);
  
   	    String jsonString = JSON.toJSONString(map); 
   	    System.out.println(jsonString);
   	    
   	 //jsonString =  jsonString.replaceAll("\"", "'");
   	    //评审类别 approval_type，案件uuid case_uuid，案件阶段 case_phase_uuid 创建人uuid creator_uuid
   	    //查找案件主办律师
   	    //新建审批条目 在审批信息表 approval_inner_base_infos中0558
   	    //返回审批uuid
   	  // String url = "http://120.26.120.57:8080/apparel/special/list";
   	    String url = "http://localhost:8080/zzb-admin-api/MessageBase/list";
   	 //String url = "http://192.168.73.1:8080/user/phone/login";
      // String url = "http://test.appring.cn:8080/fastask/question/show"; /* * * * * * root wget http://test.appring.cn:8080/fastask/energy/timing -q -O /usr/local/time/timelog
 	    HttpPost httpPost = new HttpPost(url);
   	    StringEntity entity;
   	    entity = new StringEntity(jsonString, HTTP.UTF_8);
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		@SuppressWarnings("deprecation")
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(httpPost);
			//获取服务器返回页面的值
			HttpEntity entity2=response.getEntity();
			String xmlContent=EntityUtils.toString(entity2);
			System.out.println(java.net.URLDecoder.decode(xmlContent, "utf-8"));
			System.out.println(response.toString());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
	
}
