package hello;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

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

public class TestCollection {
	private static SimpleDateFormat  form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {	

   	    HashMap<String, Object> map = new HashMap<String, Object>();
     	// map集合
        HashMap<String, Object> head = new HashMap<String, Object>();
        head.put("user_uuid", "8741951256ce34f7eb575c438b06ae65");
      
        map.put("head", head);	
        
        HashMap<String, Object> body = new HashMap<String, Object>();
        //body.put("articleUuid", "c03f26b8b162456fb3781706b3457007");
        //body.put("ticket_uuid", "761c7e81140c4112a4b694505df0d645");
        //body.put("pageIndex", 1);
        //body.put("pageSize", 5);
        // body.put("message_child_uuid", "6741951256ce34f7eb575c438b06ae66");
        //body.put("modelCharacter", "SUV");
        // body.put("ticketStatus", 1);
        //body.put("ticket_uuid", "761c7e81140c4112a4b694505df0d645");
        body.put("car_uuid", "5741951256ce34f7eb575c438b06ae70");
        /*body.put("accountNumber", "621238748439890098");
        body.put("money", 0);*/
        
        /*body.put("cardUserName", "李四");
        body.put("cardNumber", "621878727656767");
        body.put("phone", "13137279268");
        body.put("captcha", "368449");
        body.put("bank", "中国建设银行");
        body.put("bankIcon", "xusidksnhjhwl");
        body.put("cardType", "储蓄卡");*/
        
        map.put("body",body);
  
        
   	    String jsonString = JSON.toJSONString(map); 
   	    System.out.println(jsonString);
   	    
   	 //jsonString =  jsonString.replaceAll("\"", "'");
   	    //评审类别 approval_type，案件uuid case_uuid，案件阶段 case_phase_uuid 创建人uuid creator_uuid
   	    //查找案件主办律师
   	    //新建审批条目 在审批信息表 approval_inner_base_infos中0558
   	    //返回审批uuid
   	  // String url = "http://120.26.120.57:8080/apparel/special/list";
   	    String url = "http://localhost:8080/zzb-java/collection/insert";
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
