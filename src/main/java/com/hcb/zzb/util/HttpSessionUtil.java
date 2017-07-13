package com.hcb.zzb.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hcb.zzb.dto.Users;


/** 
 * @author  作者 E-mail <a href="mailto:kelianlang@126.com">陈聚波</a> 
 * @version 创建时间：2010-8-2 下午02:11:02 
 * 类说明 
 */

public class HttpSessionUtil
{
//	public static void setSessionUser(UserDTO user)
//	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpSession session = request.getSession(true);
//		session.setAttribute(ConstantUtil.KEY_SESSION_USER, user);
//	}
	
	public static Users getSessionUser(HttpServletRequest request )
	{
		HttpSession session = request.getSession();
        if(session == null) {
        	return null;	
        }
        Users user = (Users) session.getAttribute("morgen_user");
		return user;
	}
}
