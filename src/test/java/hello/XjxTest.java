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

public class XjxTest {
	private static SimpleDateFormat  form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {	

   	    HashMap<String, Object> map = new HashMap<String, Object>();
     	// map集合
        HashMap<String, Object> head = new HashMap<String, Object>();
       head.put("account", "admin");
       head.put("password", "123456");
        map.put("head", head);	
        
        HashMap<String, Object> body = new HashMap<String, Object>();
       /*  
        body.put("tittle", "测试编辑保存");
        body.put("articleType", 1);
        body.put("articleContent", "测试测试1111111");
        body.put("articlePicture", "ceshiEdit.jpg");
        body.put("carID1", "11");
        body.put("carID2", "21");
        body.put("carID3", "31");*/
        
      //  body.put("articleUuid", "7b5dbb2a6dfd488cbbbf01ed18768765");
       // body.put("order_uuid", "675cd2063021e7a100d8536196dcb7f0");
        //body.put("user_uuid", "1a318f190bb889eec5ba528d5691dfce");
       // body.put("user_status", 3);
      body.put("pageIndex", 1);
      body.put("pageSize", 5);
     // body.put("tittle", "测试");
     
       /* body.put("orderNumber", "03E30D9774504DD9B81AC8CA86191653D5788D75AF4A4AB9ADBEBCB06FF332D5");
        body.put("illegalTime", "2017-7-18 10:29:30");
        body.put("address", "天目山交叉路口");
        body.put("money", 200.00);
        body.put("points", 6);
        body.put("illegalCode", "00001");*/
        map.put("body",body);
  
   	    String jsonString = JSON.toJSONString(map); 
   	    System.out.println(jsonString);
   	    
   	 //jsonString =  jsonString.replaceAll("\"", "'");
   	    //评审类别 approval_type，案件uuid case_uuid，案件阶段 case_phase_uuid 创建人uuid creator_uuid
   	    //查找案件主办律师
   	    //新建审批条目 在审批信息表 approval_inner_base_infos中0558
   	    //返回审批uuid
   	  // String url = "http://120.26.120.57:8080/apparel/special/list";
   	    String url = "http://localhost:8080/zzb-admin-api/ticket/list";
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
			String afterXmlContent=xmlContent.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
			System.out.println(java.net.URLDecoder.decode(afterXmlContent, "utf-8"));
			System.out.println(response.toString());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
}
