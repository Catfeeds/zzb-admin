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
        head.put("account", "admin");
        head.put("password", "123456");
        map.put("head", head);	
        
        HashMap<String, Object> body = new HashMap<String, Object>();
       /* String [] color={"白色","黑色","银白色"};
        body.put("brand", "宝马");
        body.put("carSeries", "x1");
        body.put("color", color);
        body.put("modelYear", "2017");
        body.put("displacement", "20L");
        body.put("seatNumber", 5);
        body.put("clutch", "离合器");*/
        
       /* body.put("pageIndex", 1);
        body.put("pageSize", 5);*/
        //body.put("id", 90);
        
        /*body.put("tittle", "测试增加链接");
        body.put("articleType", 1);
        body.put("articleContent", "<p>测试增加链接</p>");
        body.put("articlePicture", "http://living.cto1024.com/e4e9d3b0ae5c0572ae777f8845272c31.png");
        body.put("carID1", 51);
        body.put("carID2", 61);
        body.put("activityCarID", 71);*/
        
        String s[]={"http://living.cto1024.com/6f58a2a8bd17346f98ad35169151366c.png","http://living.cto1024.com/6e7c1cbeadb14232b7b71502cfc59f4b.png"};
        
        body.put("id", 107);
        body.put("pictures", s);
        
        
        map.put("body",body);
  
        
   	    String jsonString = JSON.toJSONString(map); 
   	    System.out.println(jsonString);
   	    
   	 //jsonString =  jsonString.replaceAll("\"", "'");
   	    //评审类别 approval_type，案件uuid case_uuid，案件阶段 case_phase_uuid 创建人uuid creator_uuid
   	    //查找案件主办律师
   	    //新建审批条目 在审批信息表 approval_inner_base_infos中0558
   	    //返回审批uuid
   	  // String url = "http://120.26.120.57:8080/apparel/special/list";
   	    String url = "http://localhost:80/zzb-admin-api/ticket/updatePicture";
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
