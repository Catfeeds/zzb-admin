package hello;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class testApproval {
	
	private static SimpleDateFormat  form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {	

   	    HashMap<String, Object> map = new HashMap<String, Object>();
     	// map集合
        HashMap<String, Object> head = new HashMap<String, Object>();
        /*head.put("merchant_uuid", "fe34b39b2efb3f0202beb6dfe97d12fb");
        head.put("password", "123456");*/
        head.put("account", "admin");
        head.put("password", "123456");
        map.put("head", head);	
        
        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("pageIndex", 1);
        body.put("pageSize", 10);
        body.put("messageContent", "");
        map.put("body",body);
  
        
   	    String jsonString = JSON.toJSONString(map); 
   	    System.out.println(jsonString);
   	    //评审类别 approval_type，案件uuid case_uuid，案件阶段 case_phase_uuid 创建人uuid creator_uuid
   	    //查找案件主办律师
   	    //新建审批条目 在审批信息表 approval_inner_base_infos中0558
   	    //返回审批uuid
  	  String url = "http://localhost/zzb-admin-api/adminmessage/list";
   	    // String url = "192.168.1.101:8080/apparel/alipay/confirm";
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
