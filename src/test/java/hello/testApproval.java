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
        head.put("merchant_uuid", "fe34b39b2efb3f0202beb6dfe97d12fb");
        head.put("password", "123456");
        map.put("head", head);	
        
        HashMap<String, Object> body = new HashMap<String, Object>();
      /*  JSONArray jsonDp1 = new JSONArray(); 
        jsonDp1.add(0, "dhhdh");*/
 /*       body.put("merchant_name", "胡巴");
        body.put("gender", "男");
        body.put("age", 12);
        body.put("id_card", "330681152363984152");
        body.put("upload_photo_id_card", "kkdkk");
        body.put("store_name", "胡巴");
        body.put("store_address", "男");
        body.put("address_details", 12);
        body.put("upload_store_photo", "330681152363984152");
        body.put("upload_bussiness_license", "kkdkk");*/
       /* JSONArray jsonDp1 = new JSONArray(); 
        jsonDp1.add(0, "b1873d210f4161034714eef975693174");
        jsonDp1.add(1, "5f0e49c8d57e294c7994f1dd4fb3891f");
        body.put("purchase_car_uuid", jsonDp1);
        body.put("totalMoney", "41.1");
        body.put("takeScore", 0);
        body.put("takeBalance", "0");*/
        // body.put("pageIndex",1);
        /* body.put("sku_uuid", "455576046f627ec67516e70b7fdfcc92");
        body.put("mode", "consignment");
        body.put("number",10 );*/
   /*       body.put("merchants_pay_treasure", "15958520686");
         body.put("takeBalance", "120.51");
         body.put("takeScore", 500);
         body.put("present_password", "kobe");*/
     /*   body.put("phone", "15958520686");
        body.put("captcha", "082168");
        body.put("password", "123456");*/
      // body.put("myOrderUuid", "327597acb07002532c561e97ab894058");
        /*body.put("order_uuid", "e9593a58d0397ac0451a0641236fc9d4");
        body.put("purchase_car_uuid", "b1873d210f4161034714eef975693174");
        body.put("reason", "质量问题");
        body.put("number", 10);*/
        
        
        
        Map<String, Object> mapp = new HashMap<String,Object>();
        JSONObject jo = new JSONObject();
        JSONObject jo1 = new JSONObject();
        jo1.put("code", "10000");
        jo1.put("msg", "Success");
        jo1.put("app_id", "2015102200511864");
        jo1.put("auth_app_id", "2015102200511864");
        jo1.put("charset", "utf-8");
        jo1.put("timestamp", "2016-08-29 15:34:40");
        jo1.put("total_amount", "0.01");
        jo1.put("trade_no", "2016082921001004970211882643");
        jo1.put("seller_id", "2088911200563043");
        jo1.put("out_trade_no", "d0d2b39171f44253516bc3cf2ff1e819");

        String b = jo1.toString();
        b = b.replaceAll("\\\\", "");
        jo.put("alipay_trade_app_pay_response",b);
        jo.put("sign", "uhKb/Z60B6xPP5eHtmvi9GNtSf5nE9IQ17SaYFGi81m46tXWnrQ6fPKcWn78I2aVQsFSLCfC6MMSlULV9KPUy7CxsnV2t1sgcegFUja9DRUhXEKUAvNwfaU+NvNSoy7xgMBH3TaH0UBcFJplb0E3WJyymGwGrerXBADy0ImAix4=");
        jo.put("sign_type", "RSA");
        String a = jo.toString();
		a = a.replaceAll("\\\\", "");
        mapp.put("memo", "");
        mapp.put("result", a);
        mapp.put("resultStatus", "9000");

        body.put("alipayResult", mapp);
        body.put("myOrderUuid", "d0d2b39171f44253516bc3cf2ff1e819");
        map.put("body",body);
  
        
   	    String jsonString = JSON.toJSONString(map); 
   	    System.out.println(jsonString);
   	    //评审类别 approval_type，案件uuid case_uuid，案件阶段 case_phase_uuid 创建人uuid creator_uuid
   	    //查找案件主办律师
   	    //新建审批条目 在审批信息表 approval_inner_base_infos中0558
   	    //返回审批uuid
  	  String url = "http://192.168.1.187:8080/apparel/alipay/confirm";
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
