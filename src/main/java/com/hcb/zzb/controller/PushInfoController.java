package com.hcb.zzb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import net.sf.json.JSONObject;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.hcb.zzb.util.Config;
import com.hcb.zzb.controller.base.BaseController;
import com.hcb.zzb.controller.base.BaseControllers;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;

@Controller
public class PushInfoController  extends BaseController{

	public static final String  APPID        = "NSklDOmNyx8UDvzcaNFbE7";
    public static final String  APPKEY       = "kbEV6bJzob6HPOllLrBhw8";
    public static final String  AppSecret    = "FwxvEHadoj6uqq6a0tduX";
    public static final String  MasterSecret = "CsX9DZPylk8U1KXey4S011";
    public static final String  HOST         = "http://sdk.open.api.igexin.com/apiex.htm";
    private static      IGtPush push         = new IGtPush(HOST, APPKEY, MasterSecret);

    @RequestMapping(value ="phppushinfo" , method = RequestMethod.GET)
    public static void pushInfo(HttpServletRequest req, HttpServletResponse res,@RequestParam(required = false) String push_uuid,ModelMap model) throws Exception {
    	try {
            Connection con = getConnection();
            //Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            Statement stmt = con.createStatement();

            String query = "SELECT p.fake_id,client_id,device_token,push_title,push_dsp,push_type,push_datetime,is_send,push_uuid " +
                            "FROM push_info AS p INNER JOIN users AS h " +
                            "ON p.user_uuid = h.user_uuid WHERE is_send = 0";
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                int fake_id = result.getInt("fake_id");
                //String user_uuid = result.getString("user_uuid");
                String pushUuid = result.getString("push_uuid");
                Object device_token = result.getObject("device_token");
                Timestamp push_date = result.getTimestamp("push_datetime");
                if (push_date != null) {
                    HashMap<String, String> content = new HashMap<>();
                    content.put("client_id", result.getString("client_id"));
                    content.put("push_title", result.getString("push_title"));
                    content.put("push_dsp", result.getString("push_dsp"));
                    content.put("push_type", result.getString("push_type"));

                    //System.out.println(device_token);
                    Calendar after = Calendar.getInstance();
                    after.add(Calendar.MINUTE, 10);
                    Calendar before = Calendar.getInstance();
                    before.add(Calendar.MINUTE, -10);

                    if (push_date.before(after.getTime()) && push_date.after(before.getTime()) && pushUuid.equals(push_uuid)) {
                        Map<String, Object> getui;
                        //device_token不能是sql的null，并且长度要=64
                        if (null == device_token || 64 != device_token.toString().length()) {
                            getui = push(content);
                        } else {
                            //apnpush本身就在透传模板的基础上，如果device_token可用
                            getui = apnpush(content, result.getString("device_token"));
                            if (!getui.get("result").toString().equalsIgnoreCase("ok")) {
                                getui = push(content);
                            }
                        }

                        if (getui.get("result").toString().equalsIgnoreCase("ok")) {
                            //因为是多表查询，还是只能手写update
                            //result.updateInt("is_send", 1);
                            //result.updateRow();
                            String            update = "UPDATE push_info SET is_send = 1 WHERE fake_id = ?";
                            PreparedStatement pstm   = con.prepareStatement(update);
                            pstm.setInt(1, fake_id);
                            pstm.executeUpdate();
                            pstm.close();
                            //去掉输出
                            /*int rows = pstm.executeUpdate();
                            if (rows > 0) {
                                System.out.println("fake_id：" + fake_id + "\t状态更新成功\n" + getui);
                            } else {
                                System.out.println("fake_id：" + fake_id + "\t状态更新失败，请查看数据库连接");
                            }

                            pstm.close();
                        } else {
                            System.out.println("fake_id：" + fake_id + "\t推送失败\n" + getui);*/
                        }
                    }
                }
            }

            stmt.close();
            con.close();

        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String url      = Config.getString("jdbc.url");
        String username = Config.getString("jdbc.username");
        String password = Config.getString("jdbc.password");

        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

    public static Map<String, Object> push(HashMap<String, String> content) throws Exception {
        String               json_content = JSONObject.fromObject(content).toString();
        TransmissionTemplate template     = transmissionTemplate(json_content);

        APNPayload payload = new APNPayload();
        payload.setBadge(0);
        payload.setContentAvailable(1);
        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content.get("push_dsp")));
        //字典模式使用下者
        //payload.setAlertMsg(getDictionaryAlertMsg());
        template.setAPNInfo(payload);

        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);

        Target target = new Target();
        target.setAppId(APPID);
        target.setClientId(content.get("client_id"));
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            return ret.getResponse();
        } else {
            return (Map<String, Object>) new HashMap<>().put("result", "error");
        }
    }

    public static Map<String, Object> apnpush(HashMap<String, String> content, String device_token) throws Exception {
        String               json_content = JSONObject.fromObject(content).toString();
        TransmissionTemplate template     = transmissionTemplate(json_content);

        APNPayload payload = new APNPayload();
        payload.setBadge(0);
        payload.setContentAvailable(1);
        //payload.setSound("");
        //payload.setCategory("");
        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content.get("push_dsp")));
        //字典模式使用下者
        //payload.setAlertMsg(getDictionaryAlertMsg());
        template.setAPNInfo(payload);

        SingleMessage message = new SingleMessage();
        message.setData(template);
        IPushResult ret0 = push.pushAPNMessageToSingle(APPID, device_token, message);
        return ret0.getResponse();
    }

    public static TransmissionTemplate transmissionTemplate(String content) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(APPID);
        template.setAppkey(APPKEY);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(content);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
	
}
