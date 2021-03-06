package com.hcb.zzb.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Config {
    private static Log log = LogFactory.getLog(Config.class); 
	private static ResourceBundle resources=null;
	private static ResourceBundle namedResources = null;
	
	private static void getBundle(){
        try {
            resources = ResourceBundle.getBundle("system", Locale.getDefault());
        } catch (MissingResourceException mre) {
            log.error(mre);
        }
    }
	
	private static void getNamedBundle(String filename){
		try {
			namedResources = ResourceBundle.getBundle(filename, Locale.getDefault());
        } catch (MissingResourceException mre) {
            log.error(mre);
        }
	}
	
	public static String getValue(String key, String filename){
		getNamedBundle(filename);
    	try{
    		return namedResources.getString(key);	
		}catch(Exception e){
			return null;
		}
    }
    
    private static boolean checkResources(){
    	if(resources==null)
    		getBundle();
    	return (resources!=null);
    }
    
    
    private static boolean changeToBoolean(String str)throws Exception{
    	String tmp = str.toLowerCase();
    	if(tmp.equals("true"))
    		return true;
    	else if(tmp.equals("false"))
    		return false;
    	else
    		throw new Exception("不能找到资源文件");
    }
    
    public static boolean getBoolean(String key){
    	String str = getString(key);
    	try{
    		return changeToBoolean(str);
    	}catch(Exception e){
    		return false;
    	}
    }
    
    public static boolean getBoolean(String key,boolean defaultValue){
    	String str = getString(key);
    	try{
    		return changeToBoolean(str);
    	}catch(Exception e){
    		return defaultValue;
    	}
    }
    
    
    private static int changeToInt(String str)throws Exception {
    	return Integer.parseInt(str);
    }
    
    public static int getInt(String key){
    	String str = getString(key);    	
    	try{
    		return changeToInt(str);
    	}catch(Exception e){
    		return 0;
    	}   		
    }
    
    public static int getInt(String key,int defaultValue){
    	String str = getString(key);    	
    	try{
    		return changeToInt(str);
    	}catch(Exception e){
    		return defaultValue;
    	}   		
    }
    
    
    
    public static String getString(String key,String defaultValue){
    	String tmp = null;
    	if(checkResources()){
    		try{
	    		tmp = resources.getString(key);	
    		}catch(Exception e){
    			tmp = defaultValue;
    		}
    	}    
	    return tmp;
    }
    
    public static String getString(String key){
    	if(checkResources()){    	
    		try{
	    		return resources.getString(key);	
    		}catch(Exception e){
    			;
    		}
    	}
	    return null;
    }
    
    public static String[] getStringArray(String key){
    	if(checkResources())
	    	return resources.getStringArray(key);	    
	    return null;
    }
    
    public static Enumeration getKeys(){
    	return resources.getKeys()	;
    }
    
    public static void main(String[] args){
//    	if(checkResources()){
//	    	Enumeration keys = Config.getKeys();
//	    	while(keys.hasMoreElements()){
//	    		String key = (String) keys.nextElement();
//	    		System.out.println(key + "=" + Config.getString(key));
//	    	}
//    	}
    	System.out.println(Config.getString("CHANNEL_NAME"));
    }
}
